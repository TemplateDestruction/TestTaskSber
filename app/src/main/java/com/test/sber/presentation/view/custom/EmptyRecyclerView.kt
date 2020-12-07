package com.test.sber.presentation.view.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView


class EmptyRecyclerView : RecyclerView {

    private var mEmptyView: View? = null

    //region constructors
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context, attrs, defStyle
    )
    //endregion

    fun checkIfEmpty() {
        if (adapter!!.itemCount > 0) {
            showRecycler()
        } else {
            showEmptyView()
        }
    }

    fun setEmptyView(view: View) {
        mEmptyView = view
    }

    @VisibleForTesting
    internal fun showRecycler() {
        if (mEmptyView != null) {
            mEmptyView!!.visibility = View.GONE
        }
        visibility = View.VISIBLE
    }

    @VisibleForTesting
    internal fun showEmptyView() {
        if (mEmptyView != null) {
            mEmptyView!!.visibility = View.VISIBLE
        }
        visibility = View.GONE
    }
    
}