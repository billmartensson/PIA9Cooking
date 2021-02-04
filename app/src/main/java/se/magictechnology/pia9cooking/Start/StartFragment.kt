package se.magictechnology.pia9cooking

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class StartFragment : Fragment() {

    private lateinit var database: DatabaseReference

    var hightlightsadapter = HighlightsAdapter()
    var categoriesadapter = CategoriesAdapter()

    var categoriesList : List<CookCategory>? = null
    var highlightRecipes : List<CookRecipe>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Firebase.database.reference

        hightlightsadapter.startfrag = this
        categoriesadapter.startfrag = this

        var highlightsrv = view.findViewById<RecyclerView>(R.id.startHighlightsRV)

        highlightsrv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        highlightsrv.adapter = hightlightsadapter

        var categoriesrv = view.findViewById<RecyclerView>(R.id.startCategoriesRV)

        categoriesrv.layoutManager = LinearLayoutManager(context)
        categoriesrv.adapter = categoriesadapter

        loadCategories()
        loadHighlights()
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


    fun loadCategories()
    {
        val categoriesRef = database.child("pia9cooking").child("categories")

        val categoriesListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val post = dataSnapshot.getValue<Post>()
                // ...

                var tempCategoryList = mutableListOf<CookCategory>()
                for(categorySnapshot in dataSnapshot.children)
                {
                    Log.d("pia9debug", categorySnapshot.key!!)
                    var tempCategory = categorySnapshot.getValue<CookCategory>()!!
                    tempCategoryList.add(tempCategory)
                }

                categoriesList = tempCategoryList
                categoriesadapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("pia9debug", "loadPost:onCancelled", databaseError.toException())
            }
        }
        categoriesRef.addListenerForSingleValueEvent(categoriesListener)
    }

    fun loadHighlights()
    {
        val recipesRef = database.child("pia9cooking").child("recepies")

        val recipiesListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val post = dataSnapshot.getValue<Post>()
                // ...

                var tempRecepieList = mutableListOf<CookRecipe>()
                for(recipieSnapshot in dataSnapshot.children)
                {
                    Log.d("pia9debug", recipieSnapshot.key!!)
                    var tempRecepie = recipieSnapshot.getValue<CookRecipe>()!!
                    tempRecepieList.add(tempRecepie)
                }

                highlightRecipes = tempRecepieList
                hightlightsadapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("pia9debug", "loadPost:onCancelled", databaseError.toException())
            }
        }
        recipesRef.addListenerForSingleValueEvent(recipiesListener)
    }

}

data class CookCategory(val title : String? = null)

data class CookRecipe(val title : String? = null)