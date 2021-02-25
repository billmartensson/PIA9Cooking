package se.magictechnology.pia9cooking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// Skriva kod

// TODO: Fixa saken

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        supportFragmentManager.beginTransaction().add(R.id.mainFragmentLayout, StartFragment()).commit()

        findViewById<TextView>(R.id.tabRecipeTV).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.mainFragmentLayout, StartFragment()).commit()
        }

        findViewById<TextView>(R.id.tabProfileTV).setOnClickListener {

            updateProfileTab()

        }

    }

    fun updateProfileTab()
    {
        if(auth.currentUser != null)
        {
            supportFragmentManager.beginTransaction().replace(R.id.mainFragmentLayout, ProfileFragment()).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.mainFragmentLayout, LoginFragment()).commit()
        }
    }
}