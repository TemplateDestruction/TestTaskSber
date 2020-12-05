package com.test.sber.presentation.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.test.sber.R
import com.test.sber.di.main.DaggerMainComponent
import com.test.sber.di.main.MainModule
import com.test.sber.di.main.MainScope
import com.test.sber.domain.entity.Entity
import com.test.sber.presentation.CustomApp
import com.test.sber.presentation.view.base.fragment.BaseVmFragment
import com.test.sber.presentation.vm.main.DrugFragmentVm
import kotlinx.android.synthetic.main.drug_frag.*
import javax.inject.Inject

@MainScope
class DrugFragment @Inject constructor() : BaseVmFragment<DrugFragmentVm>() {

    override fun getVmClass() = DrugFragmentVm::class.java

    @Inject
    override lateinit var vmFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.drug_frag, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainComponent
            .builder()
            .appComponent(CustomApp.appComponent)
            .mainModule(MainModule(requireContext()))
            .build()
            .injectDrugFragment(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drug_title.text = arguments?.getParcelable<Entity.Drug>("drug")?.title
    }

    override fun onResume() {
        super.onResume()
    }

}


