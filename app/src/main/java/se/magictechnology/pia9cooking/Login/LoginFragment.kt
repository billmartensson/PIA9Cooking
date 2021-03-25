package se.magictechnology.pia9cooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        AnalyticsHelper().trackScreen("Login")

        view.findViewById<Button>(R.id.loginRegisterBtn).setOnClickListener {

            val email = view.findViewById<EditText>(R.id.loginEmailET).text.toString()
            val password = view.findViewById<EditText>(R.id.loginPasswordET).text.toString()

            if(email == "" || password == "")
            {
                Snackbar.make(view, getText(R.string.login_missing_info), Snackbar.LENGTH_LONG).show()

            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            AnalyticsHelper().trackEvent(FirebaseAnalytics.Event.SIGN_UP)

                            (activity as MainActivity).updateProfileTab()
                        } else {

                            Snackbar.make(view, task.exception!!.message!!, Snackbar.LENGTH_LONG).show()
                        }

                        // ...
                    }
            }


        }

        view.findViewById<Button>(R.id.loginLoginBtn).setOnClickListener {

            val email = view.findViewById<EditText>(R.id.loginEmailET).text.toString()
            val password = view.findViewById<EditText>(R.id.loginPasswordET).text.toString()

            if(email == "" || password == "")
            {
                Snackbar.make(view, getText(R.string.login_missing_info), Snackbar.LENGTH_LONG).show()

            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            AnalyticsHelper().trackEvent(FirebaseAnalytics.Event.LOGIN)

                            (activity as MainActivity).updateProfileTab()
                        } else {
                            Snackbar.make(view, task.exception!!.message!!, Snackbar.LENGTH_LONG).show()
                        }

                        // ...
                    }
            }



        }

    }

}