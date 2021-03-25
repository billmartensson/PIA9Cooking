package se.magictechnology.pia9cooking

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

class AnalyticsHelper {

    fun trackScreen(screenname : String)
    {
        var firebaseAnalytics = Firebase.analytics

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenname)
        }
    }

    fun trackEvent(eventname : String)
    {
        var firebaseAnalytics = Firebase.analytics

        firebaseAnalytics.logEvent(eventname) {

        }
    }

    fun trackRecipe(recipename : String)
    {
        trackScreen("RecipeDetail")

        var firebaseAnalytics = Firebase.analytics

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_NAME, recipename)
        }
    }

    fun trackCategory(categoryname : String)
    {
        trackScreen("Category")

        var firebaseAnalytics = Firebase.analytics

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST) {
            param(FirebaseAnalytics.Param.ITEM_LIST_NAME, categoryname)
        }
    }

}