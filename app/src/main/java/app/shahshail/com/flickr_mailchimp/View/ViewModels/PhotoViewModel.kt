package app.shahshail.com.flickr_mailchimp.View.ViewModels

import android.arch.lifecycle.MutableLiveData
import app.shahshail.com.flickr_mailchimp.Model.Photo
import app.shahshail.com.flickr_mailchimp.base.BaseViewModel

class PhotoViewModel : BaseViewModel() {

    //Photo Info
    private var photoTitle = MutableLiveData<String>()
    private var imageURL = MutableLiveData<String>()
    private var noOfPhotos = MutableLiveData<String>()

    fun bindData(photo: Photo){
        photoTitle.value = photo.title
        imageURL.value = photo.url_m
    }

    fun getPhotoTitle() : MutableLiveData<String>{
        return photoTitle
    }

    fun getPhotoImageURL(): MutableLiveData<String> {
        return imageURL
    }
}