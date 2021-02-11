package se.magictechnology.pia9cooking.Category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import se.magictechnology.pia9cooking.R
import se.magictechnology.pia9cooking.StartFragment

class CategoryAdapter() : RecyclerView.Adapter<RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val vh = RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false))
        return vh
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

    }

}

class RecipeViewHolder (view: View) : RecyclerView.ViewHolder(view) {

}