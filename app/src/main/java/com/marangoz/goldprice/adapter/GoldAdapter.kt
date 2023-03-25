package com.marangoz.goldprice.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marangoz.goldprice.R
import com.marangoz.goldprice.model.RoomResult
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class GoldAdapter(val mContext: Context,private val insertData : (RoomResult) -> Unit) : RecyclerView.Adapter<GoldAdapter.ViewHolderClass>() {

    private var goldList: List<com.marangoz.goldprice.model.Result> = mutableListOf()
    private var goldResultList: List<RoomResult> = mutableListOf()

    inner class ViewHolderClass(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView
        val buyText: TextView
        val sellText: TextView
        val dateText: TextView
        val button: Button

        init {
            nameText = view.findViewById(R.id.nameText)
            buyText = view.findViewById(R.id.buyText)
            sellText = view.findViewById(R.id.sellText)
            button = view.findViewById(R.id.saveButton)
            dateText = view.findViewById(R.id.dateText)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val design = LayoutInflater.from(mContext).inflate(R.layout.adapter_design, parent, false)
        return ViewHolderClass(design)
    }

    override fun getItemCount(): Int {
        if (goldList.isEmpty()) return goldResultList.size
        else if (goldResultList.isEmpty()) return goldList.size
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        var gold: com.marangoz.goldprice.model.Result? = null
        var roomGold: RoomResult? = null
        if (goldList.isEmpty()) {
            roomGold = goldResultList[position]

            holder.button.visibility = View.GONE

            holder.nameText.text = roomGold.name
            holder.dateText.text = roomGold.time
            holder.buyText.text = roomGold.pricestr
            holder.sellText.text = roomGold.rate.toString()


        } else {
            gold = goldList[position]

            holder.nameText.text = gold.name
            holder.dateText.text = gold.time
            holder.buyText.text = gold.pricestr
            holder.sellText.text = gold.rate.toString()

            holder.button.setOnClickListener() {
                val roomResult = RoomResult(gold.name,gold.pricestr,gold.rate,getDateTime())
                insertData(roomResult)
            }

        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<com.marangoz.goldprice.model.Result>) {
        goldList = newList
        goldResultList = emptyList()
        notifyDataSetChanged()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setResultList(newResultList: List<RoomResult>) {
        goldResultList = newResultList
        goldList = emptyList()
        notifyDataSetChanged()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val date = Date()
        return dateFormat.format(date)
    }

}