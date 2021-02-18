package se.magictechnology.pia9cooking.Addrecipe

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import se.magictechnology.pia9cooking.Models.CookRecipe
import se.magictechnology.pia9cooking.R
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.*

class AddrecipeFragment : Fragment() {

    val REQUEST_IMAGE_CAPTURE = 1

    val REQUEST_GALLERY = 2

    var selectedBitmap : Bitmap? = null
    var imageFilename : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addrecipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleET = view.findViewById<EditText>(R.id.addrecipeTitleET)
        val descriptionET = view.findViewById<EditText>(R.id.addrecipeDescriptionET)
        val recipeimageview = view.findViewById<ImageView>(R.id.addrecipeImageview)

        view.findViewById<Button>(R.id.addrecipeCameraBtn).setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                // display error state to the user
            }
        }
        view.findViewById<Button>(R.id.addrecipeGalleryBtn).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_GALLERY)
        }
        view.findViewById<Button>(R.id.addrecipeSaveBtn).setOnClickListener {

            if(selectedBitmap != null)
            {
                val baos = ByteArrayOutputStream()
                selectedBitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()

                val storage = Firebase.storage
                var storageRef = storage.reference


                imageFilename = UUID.randomUUID().toString()+".jpg"


                val testimageRef = storageRef.child("pia9cooking").child(imageFilename!!)

                Log.d("PIA9DEBUG", "LETS UPLOAD")
                var uploadTask = testimageRef.putBytes(data)
                uploadTask.addOnFailureListener {
                    // Handle unsuccessful uploads
                    Log.d("PIA9DEBUG", "Upload FAIL")
                }.addOnSuccessListener { taskSnapshot ->
                    // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                    // ...
                    Log.d("PIA9DEBUG", "Upload OK")
                    // SPARA TILL DATABAS
                    saveToDatabase()
                }
                uploadTask.resume()
            } else {
                // SPARA TILL DATABAS
                saveToDatabase()
            }



        }

        /*
        val storage = Firebase.storage
        var storageRef = storage.reference

        val testimageRef = storageRef.child("pia9cooking/testbild.jpg")

        val ONE_MEGABYTE: Long = 1024 * 1024
        testimageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            // Download ok

            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)

            recipeimageview.setImageBitmap(bitmap)
            recipeimageview.visibility = View.VISIBLE

        }.addOnFailureListener {
            // Handle any errors
        }

        testimageRef.downloadUrl.addOnSuccessListener {
            // Got the download URL
            Log.d("PIA9DEBUG", it.toString())
        }.addOnFailureListener {
            // Handle any errors
        }
        */

    }

    fun saveToDatabase()
    {

        val recTitle = view!!.findViewById<EditText>(R.id.addrecipeTitleET).text.toString()
        val recDescription = view!!.findViewById<EditText>(R.id.addrecipeDescriptionET).text.toString()

        val database = Firebase.database
        val myRef = database.getReference("pia9cooking").child("userrecepies").child(Firebase.auth.currentUser!!.uid).push()

        var newRecepie = CookRecipe(recTitle, recDescription, imageFilename)

        myRef.setValue(newRecepie).addOnCompleteListener {
            activity!!.supportFragmentManager.popBackStack()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val theImage = view!!.findViewById<ImageView>(R.id.addrecipeImageview)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            selectedBitmap = data!!.extras!!.get("data") as Bitmap
            theImage.setImageBitmap(selectedBitmap)
            theImage.visibility = View.VISIBLE


        }

        if (requestCode == REQUEST_GALLERY && resultCode == AppCompatActivity.RESULT_OK){
            theImage.setImageURI(data?.data)
            theImage.visibility = View.VISIBLE


        }
    }
}