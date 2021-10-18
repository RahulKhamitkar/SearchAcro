package com.rahul.acro.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahul.acro.R
import com.rahul.acro.model.AcroResponse
import com.rahul.acro.model.Lfs

class AcroListAdapter(
    val acroList: ArrayList<Lfs>, val context: Context
) : RecyclerView.Adapter<AcroListAdapter.AcroVieHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateMyCartList(newAcroList: List<Lfs>) {
        acroList.clear()
        acroList.addAll(newAcroList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AcroVieHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.acro_list, parent, false)
        return AcroVieHolder(view)
    }

    override fun onBindViewHolder(holder: AcroVieHolder, position: Int) {
        holder.itemName.text = acroList[position].lf
    }

    override fun getItemCount() = acroList.size

    class AcroVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemName: TextView = itemView.findViewById(R.id.name)


    }
}

