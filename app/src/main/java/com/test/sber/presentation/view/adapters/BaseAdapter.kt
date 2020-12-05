package com.test.sber.presentation.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.sber.domain.entity.Entity
import com.test.sber.presentation.view.custom.EmptyRecyclerView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

abstract class BaseAdapter<VH : RecyclerView.ViewHolder, T>(items: List<T>) :
    RecyclerView.Adapter<VH>() {

    private val onDrugItemClickSubject = PublishSubject.create<T>()
    val drugItemClickEvent: Observable<T> = onDrugItemClickSubject


    private val mItems = ArrayList<T>()

    private var mOnItemClickListener: OnItemClickListener<T>? = null

    private val mInternalListener = { view : View ->
            val position = view.tag as Int
            val item = mItems[position]
            onDrugItemClickSubject.onNext(item)
    }


    private var mRecyclerView: EmptyRecyclerView? = null

    init {
        mItems.addAll(items)
    }

    fun attachToRecyclerView(recyclerView: EmptyRecyclerView) {
        mRecyclerView = recyclerView
        mRecyclerView!!.adapter = this
        refreshRecycler()
    }

    fun add(value: T) {
        mItems.add(value)
        refreshRecycler()
    }

    fun changeDataSet(values: List<T>) {
        mItems.clear()
        mItems.addAll(values)
        refreshRecycler()
    }

    fun clear() {
        mItems.clear()
        refreshRecycler()
    }


    private fun refreshRecycler() {
        notifyDataSetChanged()
        if (mRecyclerView != null) {
            mRecyclerView!!.checkIfEmpty()
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.tag = position
        holder.itemView.setOnClickListener(mInternalListener)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>) {
        mOnItemClickListener = onItemClickListener
    }

    protected fun getItem(position: Int): T {
        return mItems[position]
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    interface OnItemClickListener<T> {
        fun onItemClick(item: T, view: View)
    }
}