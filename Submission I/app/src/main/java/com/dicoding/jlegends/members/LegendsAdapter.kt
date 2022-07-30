package com.dicoding.jlegends.members

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.jlegends.R
import java.util.*

class LegendsAdapter(
        private var listLegends: ArrayList<Legends>

) : RecyclerView.Adapter<LegendsAdapter.ListViewHolder>(),Filterable{

    var legendsListFilter : ArrayList<Legends> = arrayListOf()
    var legendsListItem : ArrayList<Legends> = arrayListOf()

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
        val (name, area, photo) = listLegends[position]

        holder.tvName.text = name
        holder.tvPosition.text = area

        Glide.with(holder.itemView.context)
                .load(photo)
                .apply(RequestOptions())
                .into(holder.imgPhoto)

        val context = holder.itemView.context
        val parcelable = listLegends[position]

        holder.itemView.setOnClickListener {
            val moveDetail = Intent(context, LegendsDetail::class.java)
            moveDetail.putExtra(LegendsDetail.EXTRA_LEGEND, parcelable)
            context.startActivity(moveDetail)
        }
    }

    override fun getItemCount(): Int {
        return listLegends.size
    }

    override fun getFilter(): Filter {
       return object : Filter(){
           override fun performFiltering(constraint: CharSequence?): FilterResults
                   {
                       val filterResults = FilterResults()
                       legendsListFilter.clear()
                       legendsListItem.clear()
                       listLegends.clear()
                           if (constraint.isNullOrEmpty() || constraint.isBlank() ){
                               listLegends.addAll(LegendsData.listData)
                               filterResults.count=listLegends.size
                               filterResults.values=listLegends
                           }else{
                               val searchText= constraint.toString().toLowerCase(Locale.ROOT).trim()
                               legendsListFilter.addAll(LegendsData.listData)
                               for(item:Legends in legendsListFilter) {
                                   if (item.name.toString().toLowerCase(Locale.ROOT).trim().contains(searchText)){
                                       legendsListItem.add(item)
                                   }
                               }

                                   filterResults.count = legendsListItem.size
                                   filterResults.values = legendsListItem

                           }

                     return filterResults
                   }
           @Suppress("UNCHECKED_CAST")
           override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?)
                   {
                       if (filterResults != null) {
                           listLegends = filterResults.values as ArrayList<Legends>

                       } else {
                           listLegends.addAll(LegendsData.listData)
                       }

                       notifyDataSetChanged()
                   }


           }

       }


}



