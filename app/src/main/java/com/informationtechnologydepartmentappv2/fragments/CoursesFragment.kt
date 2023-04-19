package com.informationtechnologydepartmentappv2.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.informationtechnologydepartmentappv2.DBAdapter
import com.informationtechnologydepartmentappv2.adapters.CourseAdapter
import com.example.informationtechnologydepartmentappv2.databinding.FragmentCoursesBinding


class CoursesFragment : BaseFragment<FragmentCoursesBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCoursesBinding {
        return FragmentCoursesBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        val dbAdapter = DBAdapter(mContext)
        dbAdapter.open()
        val list = dbAdapter.getCourseList()
        dbAdapter.close()
        mBinding.recyclerView.adapter = CourseAdapter(list)
    }
}