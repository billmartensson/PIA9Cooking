package se.magictechnology.pia9cooking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// Skriva kod. Ändrat i android studio

// Mera från github

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    // Här skriver jag lite viktig kod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAnalytics = Firebase.analytics

        /*
        firebaseAnalytics.logEvent("") {
            param("","")
        }
        */

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

    fun doFancyStuff()
    {
        var number = 7
        number = 99
    }





}
