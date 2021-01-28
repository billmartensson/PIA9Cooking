package se.magictechnology.pia9cooking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction().add(R.id.mainFragmentLayout, StartFragment()).commit()

        findViewById<TextView>(R.id.tabRecipeTV).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.mainFragmentLayout, StartFragment()).commit()
        }

        findViewById<TextView>(R.id.tabProfileTV).setOnClickListener {

            var isLoggedIn = true

            if(isLoggedIn)
            {
                supportFragmentManager.beginTransaction().replace(R.id.mainFragmentLayout, ProfileFragment()).commit()
            } else {
                supportFragmentManager.beginTransaction().replace(R.id.mainFragmentLayout, LoginFragment()).commit()
            }

        }


    }
}