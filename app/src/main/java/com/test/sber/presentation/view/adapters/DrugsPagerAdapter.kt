package com.test.sber.presentation.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.test.sber.R
import com.test.sber.domain.entity.Entity

import com.test.sber.presentation.view.main.DrugFragment
import com.test.sber.presentation.view.main.MainActivity
import com.test.sber.presentation.view.main.MainFragment
import kotlinx.android.synthetic.main.rv_single.view.*
import javax.inject.Inject

@SuppressLint("CheckResult")

class DrugsPagerAdapter @Inject constructor (
    private val mContext : Context,
    private val adultDrugAdapter : DrugAdapter,
    private val childDrugAdapter: DrugAdapter
) : PagerAdapter() {

    private val PAGES_COUNT = 2

    init {

        adultDrugAdapter.drugItemClickEvent.subscribe { onDrugClick(it) }
        childDrugAdapter.drugItemClickEvent.subscribe { onDrugClick(it) }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount() = PAGES_COUNT

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = mContext
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View = inflater.inflate(
            R.layout.rv_single, container,
            false
        )

        if (position == 0) {
            adultDrugAdapter.attachToRecyclerView(itemView.rv_single)
        } else {
            childDrugAdapter.attachToRecyclerView(itemView.rv_single)
        }
        container.addView(itemView)
        return itemView
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) mContext.getString(R.string.adults)
        else mContext.getString(R.string.children)
    }

    fun updateDrugs(it: Pair<List<Entity.Drug>, List<Entity.Drug>>) {
        adultDrugAdapter.changeDataSet(it.first)
        childDrugAdapter.changeDataSet(it.second)
    }

    private fun onDrugClick(item: Entity.Drug) {
        val bundle = Bundle()
        bundle.putParcelable("drug", item)
        (mContext as MainActivity).navController.navigate(R.id.drugFragment, bundle)
    }



}