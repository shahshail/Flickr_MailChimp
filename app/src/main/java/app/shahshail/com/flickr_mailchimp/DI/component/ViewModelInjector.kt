package app.shahshail.com.flickr_mailchimp.DI.component

import app.shahshail.com.flickr_mailchimp.DI.Module.NetworkModule
import app.shahshail.com.flickr_mailchimp.View.ViewModels.PhotoListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    fun inject(photoListViewModel: PhotoListViewModel)
}