package se.magictechnology.pia9cooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import se.magictechnology.pia9cooking.Models.CookRecipe


class RecipeDetailFragment : Fragment() {

    lateinit var currentrecipe : CookRecipe

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val headertextview = view.findViewById<TextView>(R.id.recipeHeaderTextview)

        headertextview.text = currentrecipe.title

        val descriptiontextview = view.findViewById<TextView>(R.id.recipeDescriptionTextview)

        descriptiontextview.text = currentrecipe.recipedescription

    }
}