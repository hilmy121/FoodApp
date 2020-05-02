package com.onappfeedback.foodappdemo

import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.onappfeedback.foodapplibrary.FoodAppLogin
import androidx.core.os.HandlerCompat.postDelayed



class FoodLogin : FoodAppLogin() {

    override var socialMediaOptions: HashMap<String, Int> = hashMapOf(
        "email" to R.drawable.mail,
        "facebook" to R.drawable.ic_facebook_icon
    )

    override fun socialMediaOptionSelected(socialMedia: String) {
        Toast.makeText(this,"Option " + socialMedia, Toast.LENGTH_LONG).show()
    }

    override fun validateInformation(
        currentPage: LOGIN_PAGE,
        username: String,
        password: String,
        phone: String
    ): Boolean {
        if(username.contains("Alphys")){
            displayUsernameError("D-don't do that")
            return false
        }

        return true
    }

    override fun sign(currentPage: LOGIN_PAGE, username: String, password: String, phone: String) {
        //Toast.makeText(this,"Sign In",Toast.LENGTH_LONG).show()
        fakeSign()
    }

    fun fakeSign(){
        /*showLoading(true)

        val handler = Handler()
        handler.postDelayed({
            showLoading(false)

            startActivity(Intent(this@FoodLogin,MainActivity::class.java))
            finish()
        }, 3000)*/

        startActivity(Intent(this@FoodLogin,MainActivity::class.java))
        finish()

    }
}