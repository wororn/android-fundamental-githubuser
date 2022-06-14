package com.dicoding.jlegends.legends

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jlegends.R

class LegendsAdapter(val listLegends: ArrayList<Legends>) : RecyclerView.Adapter<LegendsAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_set_name)
        var tvPosition: TextView = itemView.findViewById(R.id.tv_item_position)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_jlegends, viewGroup, false)

        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, area,photo) = listLegends[position]

        holder.tvName.text = name
        holder.tvPosition.text = area

        Glide.with(holder.itemView.context)
                .load(photo)
                .apply(RequestOptions())
                .into(holder.imgPhoto)

        val context = holder.itemView.context
        val parcelable =listLegends[position]

        holder.itemView.setOnClickListener {
            val moveDetail = Intent(context, LegendsDetail::class.java)
            moveDetail.putExtra(LegendsDetail.EXTRA_LEGEND, parcelable)
            context.startActivity(moveDetail)
        }
    }

    override fun getItemCount(): Int {
        return listLegends.size
    }

}