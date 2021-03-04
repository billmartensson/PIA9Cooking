package se.magictechnology.pia9cooking

import org.junit.Test

import org.junit.Assert.*
import se.magictechnology.pia9cooking.Models.CookRecipe

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun checkRecipeDesc()
    {
        var testRecipe = CookRecipe(recipedescription = "Hej tjena mera text. Detta är en test. Skriva massa saker.")

        assertEquals("Hej tjena mera text....", testRecipe.getShortDescription())

        var testRecipe2 = CookRecipe()

        assertEquals("", testRecipe2.getShortDescription())

        var testRecipe3 = CookRecipe(recipedescription = "Abc")

        assertEquals("Abc", testRecipe3.getShortDescription())

        var testRecipe4 = CookRecipe(recipedescription = "")

        assertEquals("", testRecipe4.getShortDescription())

    }

}