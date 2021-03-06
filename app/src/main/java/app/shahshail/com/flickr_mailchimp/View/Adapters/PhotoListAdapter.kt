package app.shahshail.com.flickr_mailchimp.View.Adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.shahshail.com.flickr_mailchimp.Model.Photo
import app.shahshail.com.flickr_mailchimp.R
import app.shahshail.com.flickr_mailchimp.View.ViewModels.PhotoViewModel
import app.shahshail.com.flickr_mailchimp.databinding.ItemPhotoBinding

class PhotoListAdapter : RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {

    private lateinit var photoList: List<Photo>
    private var adapterPosition = 0


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PhotoListAdapter.ViewHolder{
      val binding: ItemPhotoBinding = DataBindingUtil.inflate(LayoutInflater.from(p0.context), R.layout.item_photo, p0, false)
       return ViewHolder(binding)
    }



    override fun getItemCount(): Int {
        //return if (photoList == null) 0 else photoList!!.size
        return if(::photoList.isInitialized) photoList.size else 0
        TODO("Please Find Better solution to prevent Null pointer exception")
    }



    override fun onBindViewHolder(holder: PhotoListAdapter.ViewHolder, position: Int) {
       holder.bindData(photoList!![position])
        adapterPosition = position
    }

    fun updatePhoto(photoList : List<Photo>){
        this.photoList = photoList
        notifyDataSetChanged()
    }

    fun getAdapterPosition(): Int {
        return adapterPosition
    }


    class ViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root){

        private val photoViewModel = PhotoViewModel()

        fun bindData(photo : Photo){
            photoViewModel.bindData(photo)
            binding.viewModel = photoViewModel
        }
    }
}