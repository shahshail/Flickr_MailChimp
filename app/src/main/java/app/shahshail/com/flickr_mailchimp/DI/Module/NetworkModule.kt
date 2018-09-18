package app.shahshail.com.flickr_mailchimp.DI.Module

import app.shahshail.com.flickr_mailchimp.Helper.BASE_API
import app.shahshail.com.flickr_mailchimp.Network.FlickrApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module

// We should be safe here, Dealing with Dagger 2 .. :D
@Suppress("unused")

object NetworkModule {

    /**
     * Provides Photo Service injection implementation.
     */

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideFlickrApi(retrofit: Retrofit) : FlickrApi{
        return retrofit.create(FlickrApi::class.java)
    }

    /**
     * Provides the Retrofit Object for out Flickr API
     */

    @Provides
    @Reusable
    @JvmStatic
    internal fun  provideRetrofit():Retrofit{
        return Retrofit.Builder()
                .baseUrl(BASE_API)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

}