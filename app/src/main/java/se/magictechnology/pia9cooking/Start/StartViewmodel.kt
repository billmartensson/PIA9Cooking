package se.magictechnology.pia9cooking.Start

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import se.magictechnology.pia9cooking.Models.CookCategory
import se.magictechnology.pia9cooking.Models.CookRecipe

class StartViewmodel : ViewModel() {

    private var database: DatabaseReference = Firebase.database.reference

    private var categoriesList : List<CookCategory>? = null
    private var highlightRecipes : List<CookRecipe>? = null

    private var categories = MutableLiveData<List<CookCategory>>()
    private var highlights = MutableLiveData<List<CookRecipe>>()

    fun getCategories() : LiveData<List<CookCategory>>
    {
        return categories
    }
    fun getHighlights() : LiveData<List<CookRecipe>>
    {
        return highlights
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
                categories.value = categoriesList
                //categoriesadapter.notifyDataSetChanged()
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
                highlights.value = highlightRecipes
                //hightlightsadapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("pia9debug", "loadPost:onCancelled", databaseError.toException())
            }
        }
        recipesRef.addListenerForSingleValueEvent(recipiesListener)
    }

}