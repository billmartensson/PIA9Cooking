package se.magictechnology.pia9cooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HighlightsAdapter() : RecyclerView.Adapter<HighlightViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        val vh = HighlightViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.highlight_item, parent, false))
        return vh
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {

    }

}

class HighlightViewHolder (view: View) : RecyclerView.ViewHolder(view) {



}