package com.test.sber.presentation.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.clicks
import com.test.sber.R
import com.test.sber.domain.entity.Entity
import com.test.sber.domain.model.Drug
import com.test.sber.presentation.view.adapters.BaseAdapter
import com.test.sber.presentation.view.adapters.DrugsPagerAdapter
import com.test.sber.presentation.view.base.fragment.BaseVmFragment
import com.test.sber.presentation.vm.main.MainFragmentVm
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.main_frag.*


class MainFragment : BaseVmFragment<MainFragmentVm>() {

    override fun getVmClass() = MainFragmentVm::class.java
    private lateinit var drugsPagerAdapter: DrugsPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabs()
        createBinds()
        vm.init()
    }

    override fun onPause() {
        super.onPause()
        rxBinds.clear()
    }

    private fun setupTabs() {
        drugsPagerAdapter = DrugsPagerAdapter(requireContext(), drugClickListener)
        drugs_viewpager.adapter = drugsPagerAdapter
        tabs_drug_frag.setupWithViewPager(drugs_viewpager)
    }

    override fun createBinds() {
        rxBinds.addAll(
            vm.drugListState.subscribe {
                drugsPagerAdapter.updateDrugs(it)
            },
            vm.loadingState.subscribe {
                if (it) {
                    error_layout_main_frag.visibility = View.GONE
                    comp_loading_view.visibility = View.VISIBLE
                    drugsPagerAdapter.updateDrugs(Pair(mutableListOf(), mutableListOf()))
                } else {
                    comp_loading_view.visibility = View.GONE
                }
            },
            vm.internetErrorState.subscribe {
                error_layout_main_frag.visibility = View.VISIBLE
            },
            btn_repeat_error_layout.clicks().subscribe {
                vm.init()
            }
        )
    }

    private val drugClickListener = object : BaseAdapter.OnItemClickListener<Entity.Drug> {
        override fun onItemClick(item: Entity.Drug, view: View) {
            activity!!
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, DrugFragment(item))
                .addToBackStack(null)
                .commit()
        }
    }
}


