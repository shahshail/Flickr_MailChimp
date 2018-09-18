package app.shahshail.com.flickr_mailchimp.utils

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.layoutManager = GridLayoutManager(view.context,3)
    view.adapter = adapter
}

@BindingAdapter("mutableProgressBarVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view:TextView, text : MutableLiveData<String>){
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null){
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("image")
fun setImage(imageView: ImageView, imageURL: String?){
    if(imageURL != null && imageURL!!.isNotEmpty())
        Picasso.get().load(imageURL).fit().centerCrop().into(imageView)
}