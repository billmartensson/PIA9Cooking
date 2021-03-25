package se.magictechnology.pia9cooking

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import se.magictechnology.pia9cooking.Category.CategoryAdapter
import se.magictechnology.pia9cooking.Category.CategoryViewmodel
import se.magictechnology.pia9cooking.Models.CookCategory
import se.magictechnology.pia9cooking.Models.CookRecipe

class CategoryFragment : Fragment() {

    var categoryadapter = CategoryAdapter()

    lateinit var categoryvm : CategoryViewmodel

    lateinit var currentCategory : CookCategory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnalyticsHelper().trackCategory(currentCategory.title!!)

        categoryvm = ViewModelProvider(this).get(CategoryViewmodel::class.java)
        categoryvm.currentCategory = currentCategory

        categoryadapter.categoryFrag = this

        val categoryrv = view.findViewById<RecyclerView>(R.id.categoryRV)

        categoryrv.layoutManager = LinearLayoutManager(context)
        categoryrv.adapter = categoryadapter

        view.findViewById<TextView>(R.id.categoryHeaderTextview).text = currentCategory.title

        categoryvm.loadRecepies()

        categoryvm.getRecepies().observe(this, {
            categoryadapter.notifyDataSetChanged()
        })

    }

    fun goRecipe(recipe : CookRecipe)
    {
        Log.d("PIA9DEBUG", "GO RECIPE " + recipe.title)

        var recipedetailfrag = RecipeDetailFragment()
        recipedetailfrag.currentrecipe = recipe

        activity!!.supportFragmentManager.beginTransaction().add(R.id.mainFragmentLayout, recipedetailfrag).addToBackStack(null).commit()
    }
}