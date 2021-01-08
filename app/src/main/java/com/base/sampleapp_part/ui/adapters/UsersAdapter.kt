package com.base.sampleapp_part.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.sampleapp_part.data.entities.user
import com.base.sampleapp_part.databinding.ItemUserBinding

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop


class UsersAdapter(private val listener: UserItemListener) : RecyclerView.Adapter<UserViewHolder>() {

    interface UserItemListener {
        fun onClickedCharacter(userId: Int)
    }

 private  val items = ArrayList<user>()

    fun setItems(items: ArrayList<user>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: ItemUserBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(items[position])
}

class UserViewHolder(private val itemBinding: ItemUserBinding, private val listener: UsersAdapter.UserItemListener) : RecyclerView.ViewHolder(itemBinding.root),


    View.OnClickListener {

    private lateinit var user: user

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: user) {
        this.user = item
        itemBinding.firstName.text = item.first_name
        itemBinding.lastName.text =item.last_name
        Glide.with(itemBinding.root)
            .load(item.avatar)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        listener.onClickedCharacter(user.id)
    }
}

