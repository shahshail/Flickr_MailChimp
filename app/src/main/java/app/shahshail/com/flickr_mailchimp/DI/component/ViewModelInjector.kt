package app.shahshail.com.flickr_mailchimp.DI.component

import app.shahshail.com.flickr_mailchimp.DI.NetworkModule
import app.shahshail.com.flickr_mailchimp.Views.ViewModels.photoListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(photoListViewModel: photoListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}