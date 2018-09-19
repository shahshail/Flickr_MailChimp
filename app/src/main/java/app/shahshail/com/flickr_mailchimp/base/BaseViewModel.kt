package app.shahshail.com.flickr_mailchimp.base

import android.arch.lifecycle.ViewModel
import app.shahshail.com.flickr_mailchimp.DI.Module.NetworkModule
import app.shahshail.com.flickr_mailchimp.DI.component.DaggerViewModelInjector
import app.shahshail.com.flickr_mailchimp.DI.component.ViewModelInjector
import app.shahshail.com.flickr_mailchimp.View.ViewModels.PhotoListViewModel

abstract class BaseViewModel: ViewModel() {
//    private val injector: ViewModelInjector = DaggerViewModelInjector.

    private val injector: ViewModelInjector = DaggerViewModelInjector.builder()
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
            is PhotoListViewModel -> injector.inject(this)
        }
    }
}