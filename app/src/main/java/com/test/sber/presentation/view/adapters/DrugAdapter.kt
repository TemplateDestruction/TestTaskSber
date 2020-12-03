package com.test.sber.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.sber.R
import com.test.sber.domain.entity.Entity
import com.test.sber.domain.model.Drug
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.drug_layout.*

class DrugAdapter :
    BaseAdapter<DrugAdapter.DrugViewHolder, Entity.Drug>(mutableListOf()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrugViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.drug_layout, parent, false)
        return DrugViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DrugViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }

    inner class DrugViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(drug: Entity.Drug) {
            Picasso
                .get()
                .load(drug.icon)
                .into(image_drug_layout)
            text_drug_layout.text = drug.title
        }
    }
}