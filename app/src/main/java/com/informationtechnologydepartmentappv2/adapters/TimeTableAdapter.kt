package com.informationtechnologydepartmentappv2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.informationtechnologydepartmentappv2.databinding.ItemTimetableBinding
import com.informationtechnologydepartmentappv2.structures.TimeTableInfo

class TimeTableAdapter(private val mList: MutableList<TimeTableInfo>) : RecyclerView.Adapter<TimeTableAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemTimetableBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() : Int {
        return mList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder(ItemTimetableBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = mList[position]
        holder.binding.txtCode.text = info.code
        holder.binding.txtTitle.text = info.title
        holder.binding.txtDay.text = info.day
        holder.binding.txtBegin.text = info.begin
        holder.binding.txtEnd.text = info.end
    }
}