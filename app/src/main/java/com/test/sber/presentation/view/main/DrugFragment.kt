package com.test.sber.presentation.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import com.test.sber.R
import com.test.sber.domain.entity.Entity
import com.test.sber.domain.model.Drug
import com.test.sber.presentation.view.base.fragment.BaseVmFragment
import com.test.sber.presentation.vm.main.DrugFragmentVm
import kotlinx.android.synthetic.main.drug_frag.*

class DrugFragment(val drug: Entity.Drug) : BaseVmFragment<DrugFragmentVm>() {

    override fun getVmClass() = DrugFragmentVm::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.drug_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drug_title.text = drug.title
    }

    override fun onResume() {
        super.onResume()
        vm.init()
    }


    private fun addBinds() {
    }

}


