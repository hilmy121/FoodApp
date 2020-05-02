package com.onappfeedback.foodappdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        loadDefaultPage()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.menu_home -> goToHomePage()
                R.id.menu_search -> goToSearchPage()
                R.id.menu_favorite -> goToFavoritePage()
                R.id.menu_shopping -> goToControlsPage()
                R.id.menu_user -> goToImagesPage()

            }

            true
        }

    }

    private fun loadDefaultPage(){
        val fragmentTransitionSupport = supportFragmentManager.beginTransaction()
        fragmentTransitionSupport.add(R.id.fragment_content,HomeFragment())
        fragmentTransitionSupport.commit()
    }

    private fun goToHomePage(){
        val fragmentTransitionSupport = supportFragmentManager.beginTransaction()
        fragmentTransitionSupport.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        fragmentTransitionSupport.replace(R.id.fragment_content,HomeFragment())
        fragmentTransitionSupport.commit()
    }

    private fun goToSearchPage(){
        val fragmentTransitionSupport = supportFragmentManager.beginTransaction()
        fragmentTransitionSupport.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        fragmentTransitionSupport.replace(R.id.fragment_content,SearchFragment())
        fragmentTransitionSupport.commit()
    }

    private fun goToFavoritePage(){
        val fragmentTransitionSupport = supportFragmentManager.beginTransaction()
        fragmentTransitionSupport.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        fragmentTransitionSupport.replace(R.id.fragment_content,PaymentFragment())
        fragmentTransitionSupport.commit()
    }

    private fun goToControlsPage(){
        val fragmentTransitionSupport = supportFragmentManager.beginTransaction()
        fragmentTransitionSupport.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        fragmentTransitionSupport.replace(R.id.fragment_content,ControlsFragment())
        fragmentTransitionSupport.commit()
    }

    private fun goToImagesPage(){
        val fragmentTransitionSupport = supportFragmentManager.beginTransaction()
        fragmentTransitionSupport.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        fragmentTransitionSupport.replace(R.id.fragment_content,ImagesFragment())
        fragmentTransitionSupport.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_home_menu,menu)
        return true
    }


}
