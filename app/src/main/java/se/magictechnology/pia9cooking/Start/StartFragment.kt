package se.magictechnology.pia9cooking

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import se.magictechnology.pia9cooking.Models.CookRecipe
import se.magictechnology.pia9cooking.Start.StartViewmodel

class StartFragment : Fragment() {

    lateinit var startvm : StartViewmodel

    var hightlightsadapter = HighlightsAdapter()
    var categoriesadapter = CategoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startvm = ViewModelProvider(this).get(StartViewmodel::class.java)

        hightlightsadapter.startfrag = this
        categoriesadapter.startfrag = this

        var highlightsrv = view.findViewById<RecyclerView>(R.id.startHighlightsRV)

        highlightsrv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        highlightsrv.adapter = hightlightsadapter

        var categoriesrv = view.findViewById<RecyclerView>(R.id.startCategoriesRV)

        categoriesrv.layoutManager = LinearLayoutManager(context)
        categoriesrv.adapter = categoriesadapter

        startvm.loadCategories()
        startvm.loadHighlights()

        startvm.getCategories().observe(this, {
            categoriesadapter.notifyDataSetChanged()
        })

        startvm.getHighlights().observe(this, {
            hightlightsadapter.notifyDataSetChanged()
        })

    }

    fun goRecipe(recipe : CookRecipe)
    {
        Log.d("PIA9DEBUG", "GO RECIPE " + recipe.title)

        var recipedetailfrag = RecipeDetailFragment()
        recipedetailfrag.currentrecipe = recipe

        activity!!.supportFragmentManager.beginTransaction().add(R.id.mainFragmentLayout, recipedetailfrag).addToBackStack(null).commit()
    }

    fun goCategory(categorynumber : Int)
    {
        activity!!.supportFragmentManager.beginTransaction().add(R.id.mainFragmentLayout, CategoryFragment()).addToBackStack(null).commit()
    }

}



