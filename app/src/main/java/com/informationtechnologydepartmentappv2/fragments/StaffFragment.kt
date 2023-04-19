package com.informationtechnologydepartmentappv2.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.informationtechnologydepartmentappv2.DBAdapter
import com.informationtechnologydepartmentappv2.adapters.StaffAdapter
import com.example.informationtechnologydepartmentappv2.databinding.FragmentStaffBinding


class StaffFragment : BaseFragment<FragmentStaffBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentStaffBinding {
        return FragmentStaffBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        val dbAdapter = DBAdapter(mContext)
        dbAdapter.open()
        val list = dbAdapter.getUserList()
        dbAdapter.close()
        mBinding.recyclerView.adapter = StaffAdapter(list)
    }
}