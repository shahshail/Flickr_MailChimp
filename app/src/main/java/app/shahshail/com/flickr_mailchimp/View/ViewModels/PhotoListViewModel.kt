package app.shahshail.com.flickr_mailchimp.View.ViewModels

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import app.shahshail.com.flickr_mailchimp.Helper.API_KEY
import app.shahshail.com.flickr_mailchimp.Model.result
import app.shahshail.com.flickr_mailchimp.Network.FlickrApi
import app.shahshail.com.flickr_mailchimp.R
import app.shahshail.com.flickr_mailchimp.View.Adapters.PhotoListAdapter
import app.shahshail.com.flickr_mailchimp.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhotoListViewModel : BaseViewModel() {

    private val TAG = "PhotoListViewModel"

    @Inject
    lateinit var flickrApi: FlickrApi
    private lateinit var disposable: Disposable
    val photoListAdapter : PhotoListAdapter = PhotoListAdapter()
    val loadingVisibility : MutableLiveData<Int> = MutableLiveData()
    val errorHandleMessage : MutableLiveData<Int> = MutableLiveData()
    val noResultFoundMessage : MutableLiveData<Int> = MutableLiveData()
    val title : MutableLiveData<String> = MutableLiveData()
    //TODO : Research - Should we use ViewModel Factory method or this should be fine
    val errorHandlerOnClick = View.OnClickListener {loadFlickrPhotos(title.value.toString())} // Now ,Observe the value of error message in our activity

    init {
       // loadFlickrPhotos("MailChimp")
        title.value = "Flickr_MailChimp"
    }

    fun loadFlickrPhotos(sstring:String){
        title.value = sstring
        disposable = flickrApi.search(API_KEY,sstring)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePhotosStart() }
                .doOnTerminate { onRetrievePhotosFinish() }
                .subscribe(
                        //TODO : -----Clean This code----
                        {data ->
                            run {
                                Log.w(TAG, "Success $data")
                               onRetrievePhotosSuccess(data)
                            }
                        },
                        {err -> run {
                            Log.w(TAG, "Failure ${err.message}")
                            onRetrievePhotosError()
                        } })
    }


    //TODO :Research OnClear Callback on ViewModel and find is it a good idea to clear disposable object when this method calls
    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }


    private fun onRetrievePhotosStart(){
        loadingVisibility.value = View.VISIBLE
        errorHandleMessage.value = null
    }

    private fun onRetrievePhotosFinish(){
        loadingVisibility.value = View.GONE
    }

    //TODO : Research on is it good idea to call Adapter here or it requires cleanup
    private fun onRetrievePhotosSuccess( result : result){
        val photoList = result.photos.photo
        photoListAdapter.updatePhoto(photoList)
        if(photoList.isEmpty()) onResultEmpty() else noResultFoundMessage.value = null
    }

    private fun onRetrievePhotosError(){
        errorHandleMessage.value = R.string.error_handler
    }

    private fun onResultEmpty(){
        noResultFoundMessage.value = R.string.no_result_found
    }
}