package app.shahshail.com.flickr_mailchimp.Views.ViewModels

import app.shahshail.com.flickr_mailchimp.Network.FlickrApi
import app.shahshail.com.flickr_mailchimp.base.BaseViewModel
import javax.inject.Inject

class photoListViewModel : BaseViewModel() {
    @Inject
    lateinit var flickrApi: FlickrApi
}