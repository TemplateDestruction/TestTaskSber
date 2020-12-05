package com.test.sber.presentation.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding2.view.clicks
import com.test.sber.R
import com.test.sber.di.main.DaggerMainComponent
import com.test.sber.di.main.MainModule
import com.test.sber.presentation.CustomApp
import com.test.sber.presentation.view.adapters.DrugsPagerAdapter
import com.test.sber.presentation.view.base.fragment.BaseVmFragment
import com.test.sber.presentation.vm.main.MainFragmentVm
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.main_frag.*
import javax.inject.Inject


class MainFragment : BaseVmFragment<MainFragmentVm>() {

    override fun getVmClass() = MainFragmentVm::class.java

    @Inject
    lateinit var drugsPagerAdapter: DrugsPagerAdapter

    @Inject
    override lateinit var vmFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_frag, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainComponent
            .builder()
            .appComponent(CustomApp.appComponent)
            .mainModule(MainModule(requireContext()))
            .build()
            .injectMainFragment(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabs()

    }

    override fun init() {
        vm.init()
    }

    private fun setupTabs() {
        drugs_viewpager.adapter = drugsPagerAdapter
        tabs_drug_frag.setupWithViewPager(drugs_viewpager)
    }

    override fun createBinds() {
        rxBinds.addAll(
            vm.drugListState.subscribe {
                shimmer_view_container.stopShimmer()
                shimmer_view_container.visibility = View.GONE
                drugsPagerAdapter.updateDrugs(it)
            },
            vm.loadingState.subscribe {
                if (it) {
                    shimmer_view_container.startShimmer()
                    shimmer_view_container.visibility = View.VISIBLE
                    drugsPagerAdapter.updateDrugs(Pair(mutableListOf(), mutableListOf()))
                } else {

                }
            },
            vm.internetErrorState.subscribe {
                error_layout_main_frag.visibility = View.VISIBLE
            },
            btn_repeat_error_layout.clicks().subscribe {
                error_layout_main_frag.visibility = View.GONE
                vm.loadContent()
            }
        )
    }
}


