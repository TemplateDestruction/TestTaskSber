package com.test.sber.presentation.view.main

import android.os.Bundle
import android.util.Log
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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.main_frag.*


class MainFragment : BaseVmFragment<MainFragmentVm>() {

    override fun getVmClass() = MainFragmentVm::class.java
    private lateinit var a: MutableList<Entity.Drug>
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

    }

    override fun onResume() {
        super.onResume()
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
                shimmer_view_container.stopShimmer()
                shimmer_view_container.visibility = View.GONE
                error_layout_main_frag.visibility = View.VISIBLE
            },
            btn_repeat_error_layout.clicks().subscribe {
                error_layout_main_frag.visibility = View.GONE
                vm.loadContent()
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


