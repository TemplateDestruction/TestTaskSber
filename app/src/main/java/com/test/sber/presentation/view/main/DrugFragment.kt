package com.test.sber.presentation.view.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.test.sber.R
import com.test.sber.domain.entity.Entity
import com.test.sber.presentation.view.base.fragment.BaseVmFragment
import com.test.sber.presentation.vm.main.DrugFragmentVm
import kotlinx.android.synthetic.main.drug_frag.*
import javax.inject.Inject

class DrugFragment : BaseVmFragment<DrugFragmentVm>(R.layout.drug_frag) {

    override fun getVmClass() = DrugFragmentVm::class.java

    @Inject
    override lateinit var vmFactory: ViewModelProvider.Factory


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drug_title.text = arguments?.getParcelable<Entity.Drug>("drug")?.title
    }

}


