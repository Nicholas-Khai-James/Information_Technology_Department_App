package com.informationtechnologydepartmentappv2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.informationtechnologydepartmentappv2.databinding.ItemStaffBinding
import com.informationtechnologydepartmentappv2.global.showImage
import com.informationtechnologydepartmentappv2.structures.UserInfo

class StaffAdapter(private val mList: MutableList<UserInfo>) : RecyclerView.Adapter<StaffAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemStaffBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() : Int {
        return mList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder(ItemStaffBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = mList[position]
        val context = holder.itemView.context
        context.showImage(holder.binding.imgPhoto, info.photo)
        holder.binding.txtName.text = info.name
        holder.binding.txtEmail.text = info.email
        holder.binding.txtPhone.text = info.phone
    }
}