package app.shahshail.com.flickr_mailchimp.View.ViewModels

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import app.shahshail.com.flickr_mailchimp.Network.FlickrApi
import app.shahshail.com.flickr_mailchimp.R
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
    val loadingVisibility : MutableLiveData<Int> = MutableLiveData()
    val errorHandleMessage : MutableLiveData<Int> = MutableLiveData()

    val errorHandlerOnClick = View.OnClickListener {loadFlickrPhotos()} // Now ,Observe the value of error message in our activity

    init {
        loadFlickrPhotos()
    }

    private fun loadFlickrPhotos(){
        disposable = flickrApi.search()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePhotosStart() }
                .doOnTerminate { onRetrievePhotosFinish() }
                .subscribe(
                        {data ->
                            run {
                                Log.w(TAG, "Success ${data.toString()}")
                               onRetrievePhotosSuccess()
                            }
                        },
                        {err -> run {
                            Log.w(TAG, "Failure ${err.message}")
                            onRetrievePhotosError()
                        } })
    }

    override fun onCleared() {
        super.onCleared()
        TODO("Please clear disposable object when this method calls")
    }


    private fun onRetrievePhotosStart(){
        loadingVisibility.value = View.VISIBLE
        errorHandleMessage.value = null
    }

    private fun onRetrievePhotosFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePhotosSuccess(){

    }

    private fun onRetrievePhotosError(){
        errorHandleMessage.value = R.string.error_handler
    }

}