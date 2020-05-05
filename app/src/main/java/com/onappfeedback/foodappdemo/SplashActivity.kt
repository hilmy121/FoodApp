package com.onappfeedback.foodappdemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private val AUTO_HIDE:Boolean = true
    private val AUTO_HIDE_DELAY_MILLIS : Int = 3000
    private val UI_ANIMATION_DELAY : Int = 300;
    private val mHideHandler : Handler = Handler()
    private lateinit var mContentView : View

    private val mHidePart2Runnable :Runnable = Runnable {
        mContentView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LOW_PROFILE
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                )

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(Runnable{
            startActivity(Intent(SplashActivity@this,FoodLogin::class.java))
            finish()
        },2000)
        hidestatusbarok()
    }

    private fun hidestatusbarok(){
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        var av : ActionBar? = supportActionBar
        av?.hide()
    }
}
