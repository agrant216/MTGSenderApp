package com.gfoundry.adg.mtgsender.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gfoundry.adg.mtgsender.R
import com.gfoundry.adg.mtgsender.models.Card
import com.gfoundry.adg.mtgsender.models.CardList

class ImportListAdapter (val CardList: ArrayList<Card>): RecyclerView.Adapter<ImportListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        //Displaying each of the members of "Card"
        holder?.txtName?.text = CardList[position].name
        holder?.txtTitle?.text = CardList[position].set
        holder?.txtPrice?.text = CardList[position].price
        holder?.txtQuantity?.text = CardList[position].quantity.toString() + 'x'
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return CardList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtName: TextView = itemView.findViewById(R.id.name)
        val txtTitle: TextView = itemView.findViewById(R.id.set)
        val txtPrice: TextView = itemView.findViewById(R.id.price)
        val txtQuantity: TextView = itemView.findViewById(R.id.quantity)
    }

}