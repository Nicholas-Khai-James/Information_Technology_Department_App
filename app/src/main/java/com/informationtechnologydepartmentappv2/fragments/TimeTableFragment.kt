package com.informationtechnologydepartmentappv2.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.informationtechnologydepartmentappv2.adapters.TimeTableAdapter
import com.example.informationtechnologydepartmentappv2.databinding.FragmentTimetableBinding
import com.example.informationtechnologydepartmentappv2.DBAdapter

class TimeTableFragment : BaseFragment<FragmentTimetableBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTimetableBinding {
        return FragmentTimetableBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        val dbAdapter = DBAdapter(mContext)
        dbAdapter.open()
        val list = dbAdapter.getTimeTableList()
        dbAdapter.close()
        mBinding.recyclerView.adapter = TimeTableAdapter(list)
    }
}