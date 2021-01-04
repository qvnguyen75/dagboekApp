package com.example.dagboek.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.dagboek.R
import com.example.dagboek.domain.Item
import com.example.dagboek.ui.MainActivity

// Adapter class is used to transform data into something that the RecyclerView can display
class ItemRecyclerViewAdapter(
    private val activity: MainActivity,
    private val items: LiveData<List<Item>>
) : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {

    init {
        val dataObserver = Observer<List<Item>> {
            this.notifyDataSetChanged()
        }

        items.observe(activity, dataObserver)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.titleView)
        val bodyText: TextView = view.findViewById(R.id.bodyText)
        val dateText: TextView = view.findViewById(R.id.date_view)
        var id: Int? = null

        init {
            view.setOnClickListener {
                val id = id ?: throw IllegalStateException()
                activity.showDetail(id)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.view_item, parent, false)
        return ViewHolder(view)
    }

    // viewHolder is like binding object
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.value?.get(position) ?: throw IllegalStateException()

        holder.id = item.id
        holder.titleText.text = item.title
        holder.bodyText.text = item.body
        holder.dateText.text = item.date
    }

    override fun getItemCount(): Int {
        return items.value?.size ?: 0
    }
}