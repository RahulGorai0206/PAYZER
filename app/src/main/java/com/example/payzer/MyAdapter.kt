package com.example.payzer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (private val transactions : ArrayList<Transaction>) :
    RecyclerView.Adapter<MyAdapter.TransactionHolder>(){

    class TransactionHolder(view : View) : RecyclerView.ViewHolder(view) {
        var label = view.findViewById<TextView>(R.id.transaction_name)
        var amount = view.findViewById<TextView>(R.id.transaction_amount)
    }


    override fun  onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent , false)
        return TransactionHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val transaction  = transactions[position]
        val context = holder.amount.context

    }

    override fun getItemCount(): Int {
        return  transactions.size
    }
}

