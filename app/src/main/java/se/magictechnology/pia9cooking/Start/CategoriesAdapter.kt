package se.magictechnology.pia9cooking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter() : RecyclerView.Adapter<CategoryViewHolder>() {

    lateinit var startfrag : StartFragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val vh = CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false))
        return vh
    }

    override fun getItemCount(): Int {
        startfrag.startvm.getCategories().value?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val currentCategory = startfrag.startvm.getCategories().value!![position]

        holder.categorytextview.text = currentCategory.title

        holder.itemView.setOnClickListener {
            startfrag.goCategory(currentCategory)
        }
    }

}

class CategoryViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    var categorytextview = view.findViewById<TextView>(R.id.categoryTextview)

}