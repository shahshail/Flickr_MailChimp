package app.shahshail.com.flickr_mailchimp.View.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import app.shahshail.com.flickr_mailchimp.R
import app.shahshail.com.flickr_mailchimp.View.ViewModels.PhotoListViewModel
import app.shahshail.com.flickr_mailchimp.databinding.ActivityPhotoListBinding
import com.miguelcatalan.materialsearchview.MaterialSearchView


class PhotoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoListBinding
    private lateinit var flickrViewModel: PhotoListViewModel
    private var errorSnackBar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_list)
        binding.photoList.layoutManager = GridLayoutManager(this, calculateNoOfColumns(binding.root.context))

        binding.toolbar.inflateMenu(R.menu.search_photos)
        binding.searchView.setMenuItem(binding.toolbar.menu.findItem(R.id.search))
        binding.searchView.setVoiceSearch(false)
        binding.searchView.setEllipsize(true)

        binding.searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean{
                if(checkForConnection()){
                    if (validateString(query))flickrViewModel.loadFlickrPhotos(query!!)
                }else Toast.makeText(binding.root.context,"No Connection Available!! Please try again later.",Toast.LENGTH_LONG).show()
                binding.searchView.closeSearch()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean = false
        })

        flickrViewModel = ViewModelProviders.of(this).get(PhotoListViewModel::class.java)

        //Observe noResultFound Message
        flickrViewModel.noResultFoundMessage.observe(this, Observer { message ->
            if(message != null) Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
        })

        //Observe for error message
        flickrViewModel.errorHandleMessage.observe(this, Observer { message ->
            if(message != null){
                errorSnackBar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
                errorSnackBar?.setAction(R.string.retry,flickrViewModel.errorHandlerOnClick)
                errorSnackBar?.show()
            }else errorSnackBar?.dismiss()
        })

        flickrViewModel.title.observe(this, Observer { title -> if(title != null) binding.toolbar.title = title })
        binding.viewModel = flickrViewModel
    }

    fun validateString(string: String?): Boolean{
        if (string == ""){
            Toast.makeText(binding.root.context,"Please Enter a Valid Text!!", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

   private fun checkForConnection() : Boolean{
        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    private fun calculateNoOfColumns(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val scalingFactor = 180
        return (dpWidth / scalingFactor).toInt()
    }


}