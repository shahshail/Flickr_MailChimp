package app.shahshail.com.flickr_mailchimp.utils

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter("mutableProgressBarVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }


    @BindingAdapter("image")
    fun setImage(imageView: ImageView, imageURL: String?){
        if(imageURL != null && imageURL!!.isNotEmpty())
            Picasso.get().load(imageURL).fit().centerCrop().into(imageView)
    }

}