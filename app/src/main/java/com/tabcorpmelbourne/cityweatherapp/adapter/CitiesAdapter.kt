package com.tabcorpmelbourne.cityweatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tabcorpmelbourne.cityweatherapp.R
import com.tabcorpmelbourne.cityweatherapp.`interface`.CellClickListener
import com.tabcorpmelbourne.cityweatherapp.model.CitiesList
import kotlinx.android.synthetic.main.layout_items.view.*

class CitiesAdapter(
    val citiesList: List<CitiesList>,
    private val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>() {

    class CitiesViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        return CitiesViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.layout_items,
                    parent,
                    false
                )
        )
    }

    //return size of the list
    override fun getItemCount() = citiesList.size

    //bind the actual data that we want to bind to viewholder
    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val text = citiesList[position]
        holder.view.textViewCity.text = text.name

        val data = citiesList[position].id.toString()

        holder.view.textViewCity.setOnClickListener {
            cellClickListener.onCellClickListener(data)
        }

    }
}