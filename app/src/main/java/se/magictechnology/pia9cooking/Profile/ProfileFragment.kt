package se.magictechnology.pia9cooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import se.magictechnology.pia9cooking.Addrecipe.AddrecipeFragment


class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnalyticsHelper().trackScreen("Profile")

        view.findViewById<Button>(R.id.profileLogoutBtn).setOnClickListener {
            Firebase.auth.signOut()

            (activity as MainActivity).updateProfileTab()

        }

        view.findViewById<Button>(R.id.profileAddrecipeBtn).setOnClickListener {

            activity!!.supportFragmentManager.beginTransaction().add(R.id.mainFragmentLayout, AddrecipeFragment()).addToBackStack(null).commit()
        }
    }

}