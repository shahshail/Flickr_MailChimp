package app.shahshail.com.flickr_mailchimp.Network

import app.shahshail.com.flickr_mailchimp.Model.Photos
import io.reactivex.Observable
import retrofit2.http.GET

interface FlickrApi {

    @GET("services/rest/?method=flickr.photos.search&api_key=698e1302856e60e61b87e5dd9d14f458&text=mailchimp&safe_search=1&format=json&nojsoncallback=1")
    fun search(
//            @Query("api_key") apiKey: String,
//            @Query("text") text: String? = null
    ): Observable<Photos>

}