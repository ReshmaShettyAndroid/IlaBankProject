package com.example.ilabankproject.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ilabankproject.databinding.RowRecyclerImagelabelBinding
import java.util.ArrayList

class LableRecyclerAdapter(
    private var dataList: MutableList<String>?
) : RecyclerView.Adapter<LableRecyclerAdapter.LabelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        val binding =
            RowRecyclerImagelabelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return LabelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        holder.txtLabel.setText(dataList?.get(position));
    }

    override fun getItemCount(): Int {
        return dataList?.size!!
    }

    fun filter(labelList: MutableList<String>?, query: String): MutableList<String> {
        var query = query.toLowerCase()
        var filteredDataList: MutableList<String> = ArrayList()
        if (!labelList.isNullOrEmpty()) {
            filteredDataList = labelList!!.filter { it.contains(query) } as MutableList<String>
        }
        this.dataList = filteredDataList
        notifyDataSetChanged()
        return filteredDataList
    }

    inner class LabelViewHolder(
        private var binding: RowRecyclerImagelabelBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        val txtLabel get() = binding.txtLabel
    }
}