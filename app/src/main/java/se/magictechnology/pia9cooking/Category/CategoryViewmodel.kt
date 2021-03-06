package se.magictechnology.pia9cooking.Category

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

class CategoryViewmodel : ViewModel() {

    lateinit var currentCategory : CookCategory

    private var database: DatabaseReference = Firebase.database.reference

    private var categoryRecipes : List<CookRecipe>? = null

    private var recepies = MutableLiveData<List<CookRecipe>>()

    fun getRecepies() : LiveData<List<CookRecipe>>
    {
        return recepies
    }

    fun loadRecepies()
    {

        val recipesRef = database.child("pia9cooking").child("recepies").orderByChild("category").equalTo(currentCategory.fbid)

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

                categoryRecipes = tempRecepieList
                recepies.value = categoryRecipes
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