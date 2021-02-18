package se.magictechnology.pia9cooking.Category

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import se.magictechnology.pia9cooking.CategoryFragment
import se.magictechnology.pia9cooking.R
import se.magictechnology.pia9cooking.StartFragment

class CategoryAdapter() : RecyclerView.Adapter<RecipeViewHolder>() {

    lateinit var categoryFrag : CategoryFragment
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val vh = RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false))
        return vh
    }

    override fun getItemCount(): Int {
        categoryFrag.categoryvm.getRecepies().value?.let {
            return it.size
        }

        return 0
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipie = categoryFrag.categoryvm.getRecepies().value!![position]

        holder.categoryRecipeTextview.text = currentRecipie.title

        holder.itemView.setOnClickListener {
            Log.d("PIA9DEBUG", "KLICKAT PÅ ITEM")

            //startfrag.goRecipe(currentRecipie)

        }
    }

}

class RecipeViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val categoryRecipeTextview = view.findViewById<TextView>(R.id.categoryRecipeTextview)
}