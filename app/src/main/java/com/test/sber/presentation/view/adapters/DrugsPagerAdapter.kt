package com.test.sber.presentation.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import com.test.sber.R
import com.test.sber.di.main.MainScope
import com.test.sber.domain.entity.Entity

import com.test.sber.presentation.view.main.DrugFragment
import kotlinx.android.synthetic.main.rv_single.view.*
import javax.inject.Inject

@SuppressLint("CheckResult")

class DrugsPagerAdapter (
    val mContext : Context,
    val adultDrugAdapter : DrugAdapter,
    val childDrugAdapter: DrugAdapter
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
        val drugFragment = DrugFragment()
        val bundle = Bundle()
        bundle.putParcelable("drug", item)
        drugFragment.arguments = bundle
        (mContext as FragmentActivity)
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, drugFragment)
            .addToBackStack(null)
            .commit()
    }



}