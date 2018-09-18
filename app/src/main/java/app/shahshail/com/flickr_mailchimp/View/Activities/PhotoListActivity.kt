package app.shahshail.com.flickr_mailchimp.View.Activities

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import app.shahshail.com.flickr_mailchimp.R
import app.shahshail.com.flickr_mailchimp.View.ViewModels.PhotoListViewModel
import app.shahshail.com.flickr_mailchimp.databinding.ActivityPhotoListBinding

class PhotoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoListBinding
    private lateinit var flickrViewModel: PhotoListViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_list)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        flickrViewModel = ViewModelProviders.of(this).get(PhotoListViewModel::class.java)
        binding.flickrModel = flickrViewModel
    }

}