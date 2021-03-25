package se.magictechnology.pia9cooking

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
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

        AnalyticsHelper().trackRecipe(currentrecipe.title!!)

        val headertextview = view.findViewById<TextView>(R.id.recipeHeaderTextview)

        headertextview.text = currentrecipe.title

        val descriptiontextview = view.findViewById<TextView>(R.id.recipeDescriptionTextview)

        descriptiontextview.text = currentrecipe.recipedescription

        val recipeImageview = view.findViewById<ImageView>(R.id.recipeImageView)

        if(currentrecipe.recipeimage == null)
        {
            recipeImageview.visibility = View.GONE
        } else {
            val imageRef = Firebase.storage.reference.child("pia9cooking").child(currentrecipe.recipeimage!!)

            /*
            imageRef.getBytes(1024*1024).addOnSuccessListener {
                // DET GICK BRA
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)

                recipeImageview.setImageBitmap(bitmap)

            }.addOnFailureListener {
                // DET BLEV FEL
            }
             */

            imageRef.downloadUrl.addOnSuccessListener {
                Glide.with(this).load(it).into(recipeImageview)
            }

        }


    }
}