package app.shahshail.com.flickr_mailchimp.base

import android.arch.lifecycle.ViewModel
import app.shahshail.com.flickr_mailchimp.DI.NetworkModule
import app.shahshail.com.flickr_mailchimp.DI.component.ViewModelInjector
import app.shahshail.com.flickr_mailchimp.Views.ViewModels.photoListViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is photoListViewModel -> injector.inject(this)
        }
    }
}