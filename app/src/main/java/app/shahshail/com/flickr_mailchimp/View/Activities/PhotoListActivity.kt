package app.shahshail.com.flickr_mailchimp.View.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import app.shahshail.com.flickr_mailchimp.R
import app.shahshail.com.flickr_mailchimp.View.ViewModels.PhotoListViewModel
import app.shahshail.com.flickr_mailchimp.databinding.ActivityPhotoListBinding
import com.miguelcatalan.materialsearchview.MaterialSearchView

class PhotoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoListBinding
    private lateinit var flickrViewModel: PhotoListViewModel
    private var errorSnackBar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_list)
        binding.photoList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.toolbar.inflateMenu(R.menu.search_photos)
        binding.searchView.setMenuItem(binding.toolbar.menu.findItem(R.id.search))
        binding.searchView.setVoiceSearch(false)
        binding.searchView.setEllipsize(true)
        binding.searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean{
                if (validateString(query)) {
                    flickrViewModel.loadFlickrPhotos(query!!)
                    flickrViewModel.setSearchString(query)

                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })


        flickrViewModel = ViewModelProviders.of(this).get(PhotoListViewModel::class.java)

        //Observe for error message
        flickrViewModel.errorHandleMessage.observe(this, Observer { message ->
            if(message != null){
                errorSnackBar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
                errorSnackBar?.setAction(R.string.retry,flickrViewModel.errorHandlerOnClick)
                errorSnackBar?.show()
            }else{
                errorSnackBar?.dismiss()
            }
        })

        binding.viewModel = flickrViewModel

    }

    fun validateString(string: String?): Boolean{
        if (string == null){
            Toast.makeText(binding.root.context,"Please Enter a Valid Text!!", Toast.LENGTH_SHORT).show()
            return false;
        }
        return true
    }

}