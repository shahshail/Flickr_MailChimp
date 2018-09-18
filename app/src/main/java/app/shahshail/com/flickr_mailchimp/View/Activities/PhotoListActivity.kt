package app.shahshail.com.flickr_mailchimp.View.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import app.shahshail.com.flickr_mailchimp.R
import app.shahshail.com.flickr_mailchimp.View.ViewModels.PhotoListViewModel
import app.shahshail.com.flickr_mailchimp.databinding.ActivityPhotoListBinding

class PhotoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoListBinding
    private lateinit var flickrViewModel: PhotoListViewModel
    private var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_list)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        flickrViewModel = ViewModelProviders.of(this).get(PhotoListViewModel::class.java)

        //Observe for error message
        flickrViewModel.errorHandleMessage.observe(this, Observer { message ->
            if(message != null){
                snackBar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
                snackBar?.setAction(R.string.retry,flickrViewModel.errorHandlerOnClick)
                snackBar?.show()
            }else{
                snackBar?.dismiss()
            }
        })

        binding.viewModel = flickrViewModel

    }

}