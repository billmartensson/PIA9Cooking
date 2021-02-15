package se.magictechnology.pia9cooking.Addrecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import se.magictechnology.pia9cooking.R

class AddrecipeFragment : Fragment() {

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

        }
        view.findViewById<Button>(R.id.addrecipeGalleryBtn).setOnClickListener {

        }
        view.findViewById<Button>(R.id.addrecipeSaveBtn).setOnClickListener {

        }

    }
}