package com.informationtechnologydepartmentappv2.fragments

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.informationtechnologydepartmentappv2.databinding.FragmentAdmissionsBinding

class AdmissionsFragment : BaseFragment<FragmentAdmissionsBinding>() {

    companion object {
        const val ADMISSION_URL= "https://ucc.edu.jm/apply/undergraduate/preform"
    }
    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAdmissionsBinding {
        return FragmentAdmissionsBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        mBinding.txtAdmission1.text = "1. Individuals must have completed five (5) subjects at the GCE or CSEC level (including the required English Language and Mathematics) with grades of A, B, C or 1, 2, or 3 in order to be unconditionally admitted to finish UCC undergraduate programs. A level 3 CSEC pass must have been acquired after 1998."
        mBinding.txtAdmission2.text = "2. Candidates who have at least four CXCs may also apply, subject to the completion of any remaining CXC topics, or they may choose to take the UCC replacement courses for Core Mathematics, English for Academic Purpose, and Fundamentals of Accounting."
        mBinding.txtAdmission3.text = "3. The Mature Entry option is available to applicants. Applicants must have worked for at least five years and be at least 25 years old in order to be approved. It will be necessary to provide academic credentials, a thorough résumé, a cover letter, and three professional references."
        mBinding.btnApply.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ADMISSION_URL))
            startActivity(intent)
        }
    }
}