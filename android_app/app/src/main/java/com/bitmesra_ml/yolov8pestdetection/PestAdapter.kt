package com.bitmesra_ml.yolov8pestdetection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class PestAdapter(private val pestList: List<Pest>) :
    RecyclerView.Adapter<PestAdapter.PestViewHolder>() {

    inner class PestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pestImage: ImageView = itemView.findViewById(R.id.pestImage)
        val pestName: TextView = itemView.findViewById(R.id.pestName)
        val pestDescription: TextView = itemView.findViewById(R.id.pestDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pest_card, parent, false)
        return PestViewHolder(view)
    }

    override fun onBindViewHolder(holder: PestViewHolder, position: Int) {
        val pest = pestList[position]
        holder.pestImage.setImageResource(pest.imageResId)
        holder.pestName.text = pest.name
        holder.pestDescription.text = pest.description
        holder.pestDescription.visibility = if (pest.isExpanded) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            val fragment = PestDetailFragment.newInstance(pest)
            (holder.itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }

    }

    override fun getItemCount(): Int = pestList.size
}
