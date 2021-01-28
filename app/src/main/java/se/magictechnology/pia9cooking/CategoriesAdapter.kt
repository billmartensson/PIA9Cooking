package se.magictechnology.pia9cooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter() : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val vh = CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false))
        return vh
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

    }

}

class CategoryViewHolder (view: View) : RecyclerView.ViewHolder(view) {



}