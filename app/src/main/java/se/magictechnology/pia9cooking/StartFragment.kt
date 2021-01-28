package se.magictechnology.pia9cooking

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StartFragment : Fragment() {

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

        hightlightsadapter.startfrag = this
        categoriesadapter.startfrag = this

        var highlightsrv = view.findViewById<RecyclerView>(R.id.startHighlightsRV)

        highlightsrv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        highlightsrv.adapter = hightlightsadapter

        var categoriesrv = view.findViewById<RecyclerView>(R.id.startCategoriesRV)

        categoriesrv.layoutManager = LinearLayoutManager(context)
        categoriesrv.adapter = categoriesadapter

    }

    fun goRecipe(recipenumber : Int)
    {
        Log.d("PIA9DEBUG", "GO RECIPE " + recipenumber.toString())

        activity!!.supportFragmentManager.beginTransaction().add(R.id.mainFragmentLayout, RecipeDetailFragment()).addToBackStack(null).commit()
    }

    fun goCategory(categorynumber : Int)
    {
        activity!!.supportFragmentManager.beginTransaction().add(R.id.mainFragmentLayout, CategoryFragment()).addToBackStack(null).commit()
    }
}