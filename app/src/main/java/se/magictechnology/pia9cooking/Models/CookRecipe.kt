package se.magictechnology.pia9cooking.Models

data class CookRecipe(val title : String? = null,
                      val recipedescription : String? = null,
                      val recipeimage : String? = null,
                      val highlight : Boolean? = null) {

    fun getShortDescription() : String
    {
        recipedescription?.let {

            if(it.length < 25)
            {
                return it
            }

            return it.take(25).substringBeforeLast(" ") + "..."
        }

        return ""
    }

}