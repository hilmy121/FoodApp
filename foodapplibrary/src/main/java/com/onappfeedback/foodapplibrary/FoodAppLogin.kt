package com.onappfeedback.foodapplibrary

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.transition.TransitionManager
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import androidx.constraintlayout.widget.ConstraintSet
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import android.view.ViewGroup
import android.widget.*

abstract class FoodAppLogin : AppCompatActivity(), OnEditorActionListener{
    //Layout
    lateinit var transitionContainer: ConstraintLayout

    //Controls
    lateinit var button_page_sign_up: Button
    lateinit var button_page_sign_in: Button
    lateinit var til_username: TextInputLayout
    lateinit var tie_username: TextInputEditText
    lateinit var til_password: TextInputLayout
    lateinit var tie_password: TextInputEditText
    lateinit var til_phone: TextInputLayout
    lateinit var tie_phone: TextInputEditText
    lateinit var button_sign: Button
    lateinit var text_forget_password: TextView
    lateinit var text_or_signin_with: TextView
    lateinit var text_need_help: TextView
    lateinit var progress: ProgressBar

    lateinit var clSocialMediaOptions: ConstraintLayout

    abstract var socialMediaOptions: HashMap<String,Int>

    var currentPage : LOGIN_PAGE = LOGIN_PAGE.SING_IN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_app_login)

        //Initialize Container
        transitionContainer = findViewById<ConstraintLayout>(R.id.transition_container)

        //Initialize Controls
        button_page_sign_in = findViewById<Button>(R.id.button_page_sign_in)
        button_page_sign_up = findViewById<Button>(R.id.button_page_sign_up)
        til_username = findViewById<TextInputLayout>(R.id.til_username)
        tie_username = findViewById<TextInputEditText>(R.id.tie_username)
        til_password = findViewById<TextInputLayout>(R.id.til_password)
        tie_password = findViewById<TextInputEditText>(R.id.tie_password)
        til_phone = findViewById<TextInputLayout>(R.id.til_phone)
        tie_phone = findViewById<TextInputEditText>(R.id.tie_phone)
        button_sign = findViewById<Button>(R.id.button_sign)
        text_forget_password = findViewById<TextView>(R.id.text_forget_password)
        text_or_signin_with = findViewById<TextView>(R.id.text_or_signin_with)
        text_need_help = findViewById<TextView>(R.id.text_need_help)
        clSocialMediaOptions = findViewById<ConstraintLayout>(R.id.cl_social_media_options)
        progress = findViewById<ProgressBar>(R.id.progressbar)

        button_page_sign_up.setOnClickListener {
            goToPage(LOGIN_PAGE.SIGN_UP)
        }

        button_page_sign_in.setOnClickListener {
            goToPage(LOGIN_PAGE.SING_IN)
        }

        tie_phone.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        tie_password.setOnEditorActionListener(this)

        tie_phone.setOnEditorActionListener(this)

        tie_phone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                til_phone.error = ""
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        tie_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                til_password.error = ""
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        tie_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                til_username.error = ""
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        button_sign.setOnClickListener {
            if(validateInformation(currentPage,tie_username.text.toString(),tie_password.text.toString(),tie_phone.text.toString()))
                sign(currentPage,tie_username.text.toString(),tie_password.text.toString(),tie_phone.text.toString())
        }

        goToPage(LOGIN_PAGE.SING_IN)

        addSocialMediaIcons()

    }

    fun goToPage(page : LOGIN_PAGE = LOGIN_PAGE.SING_IN){


        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        when(page){
            FoodAppLogin.LOGIN_PAGE.SING_IN -> {
                currentPage = page;
                TransitionManager.beginDelayedTransition(transitionContainer)

                button_page_sign_up.setTextColor(ContextCompat.getColor(this,R.color.falColorOnSecondary_normal))
                button_page_sign_in.setTextColor(ContextCompat.getColor(this,R.color.falColorOnSecondary))
                til_phone.visibility = View.INVISIBLE
                text_forget_password.visibility = View.VISIBLE
                text_or_signin_with.visibility = View.VISIBLE
                clSocialMediaOptions.visibility = View.VISIBLE
                text_need_help.visibility = View.VISIBLE

                val constraintSet = ConstraintSet()
                val layout: ConstraintLayout = findViewById(R.id.transition_container)
                constraintSet.clone(layout)
                constraintSet.connect(R.id.button_sign, ConstraintSet.TOP, R.id.text_forget_password, ConstraintSet.BOTTOM, getResources().getDimension(R.dimen.fal_button_sign_in_margin_top).toInt())
                constraintSet.applyTo(layout)

                val usernameMargins = til_username.layoutParams as ViewGroup.MarginLayoutParams
                usernameMargins.topMargin =  resources.getDimension(R.dimen.fal_edittext_username_margin_top_sign_in).toInt()
                til_username.layoutParams = usernameMargins

                val passwordMargins = til_password.layoutParams as ViewGroup.MarginLayoutParams
                passwordMargins.topMargin =  resources.getDimension(R.dimen.fal_edittext_password_margin_top_sign_in).toInt()
                til_password.layoutParams = passwordMargins

                button_sign.setText(getString(R.string.fal_action_sign_in))

            }
            FoodAppLogin.LOGIN_PAGE.SIGN_UP -> {
                currentPage = page;
                TransitionManager.beginDelayedTransition(transitionContainer)

                button_page_sign_up.setTextColor(ContextCompat.getColor(this,R.color.falColorOnSecondary))
                button_page_sign_in.setTextColor(ContextCompat.getColor(this,R.color.falColorOnSecondary_normal))
                til_phone.visibility = View.VISIBLE
                text_forget_password.visibility = View.INVISIBLE
                text_or_signin_with.visibility = View.INVISIBLE
                clSocialMediaOptions.visibility = View.INVISIBLE
                text_need_help.visibility = View.INVISIBLE

                val constraintSet = ConstraintSet()
                val layout: ConstraintLayout = findViewById(R.id.transition_container)
                constraintSet.clone(layout)
                constraintSet.connect(R.id.button_sign, ConstraintSet.TOP, R.id.til_phone, ConstraintSet.BOTTOM, getResources().getDimension(R.dimen.fal_button_sign_up_margin_top).toInt())
                constraintSet.applyTo(layout)

                val usernameMargins = til_username.layoutParams as ViewGroup.MarginLayoutParams
                usernameMargins.topMargin =  resources.getDimension(R.dimen.fal_edittext_username_margin_top_sign_up).toInt()
                til_username.layoutParams = usernameMargins

                val passwordMargins = til_password.layoutParams as ViewGroup.MarginLayoutParams
                passwordMargins.topMargin =  resources.getDimension(R.dimen.fal_edittext_password_margin_top_sign_up).toInt()
                til_password.layoutParams = passwordMargins

                button_sign.setText(getString(R.string.fal_action_sign_up))
            }
        }

    }

    fun addSocialMediaIcons(){
        val socialOptions = Array(socialMediaOptions.size) { i ->
            ImageView(this).also {
                it.id = View.generateViewId()
                it.layoutParams = ViewGroup.LayoutParams(36.fromDps(this),36.fromDps(this))
                val padding = 6.fromDps(this)
                it.setPadding(padding,padding,padding,padding)
                clSocialMediaOptions.addView(it)
            }
        }

        for((i,icon) in socialMediaOptions.iterator().withIndex()){

            socialOptions[i].setImageResource(icon.value)
            socialOptions[i].setBackgroundResource(R.drawable.fal_white_circle_border)
            socialOptions[i].setColorFilter(ContextCompat.getColor(this, R.color.falColorOnSecondary), android.graphics.PorterDuff.Mode.SRC_IN)

            val constraintSet = ConstraintSet()
            constraintSet.clone(clSocialMediaOptions)

            constraintSet.connect(socialOptions[i].id,ConstraintSet.TOP,clSocialMediaOptions.id,ConstraintSet.TOP,0)
            constraintSet.connect(socialOptions[i].id,ConstraintSet.BOTTOM,clSocialMediaOptions.id,ConstraintSet.BOTTOM,0)

            if(i < socialMediaOptions.size - 1)
                constraintSet.connect(socialOptions[i].id,ConstraintSet.END,socialOptions[i+1].id,ConstraintSet.START,0)
            else
                constraintSet.connect(socialOptions[i].id,ConstraintSet.END,clSocialMediaOptions.id, ConstraintSet.END,0)

            if(i > 0)
                constraintSet.connect(socialOptions[i].id,ConstraintSet.START,socialOptions[i-1].id,ConstraintSet.END,0)
            else
                constraintSet.connect(socialOptions[i].id,ConstraintSet.START,clSocialMediaOptions.id, ConstraintSet.START,0)

            constraintSet.applyTo(clSocialMediaOptions)

            socialOptions[i].setOnClickListener {
                socialMediaOptionSelected(icon.key)
            }

        }
    }

    abstract fun socialMediaOptionSelected(socialMedia:String)


    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE){
            if(validateInformation(currentPage,tie_username.text.toString(),tie_password.text.toString(),tie_phone.text.toString()))
                sign(currentPage,tie_username.text.toString(),tie_password.text.toString(),tie_phone.text.toString())
        }
        return false
    }

    /**
     * validates the information and returns the result if the information is correct returns true.
     */
    abstract fun validateInformation(currentPage: LOGIN_PAGE,username: String,password: String, phone: String) : Boolean

    fun displayUsernameError(error: String){
        til_username.error = error
    }

    fun displayPasswordError(error: String){
        til_password.error = error
    }

    fun displayPhoneError(error: String){
        til_phone.error = error
    }

    abstract fun sign(currentPage: LOGIN_PAGE,username: String,password: String, phone: String)

    fun showLoading(isLoading: Boolean){

        button_page_sign_in.isEnabled = !isLoading
        button_page_sign_up.isEnabled = !isLoading

        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        til_username.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
        til_username.animate()
            .setDuration(shortAnimTime)
            .alpha((if (isLoading) 0 else 1).toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    til_username.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
                }
            })

        til_password.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
        til_password.animate()
            .setDuration(shortAnimTime)
            .alpha((if (isLoading) 0 else 1).toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    til_password.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
                }
            })

        when(currentPage){
            FoodAppLogin.LOGIN_PAGE.SING_IN -> {

                text_forget_password.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
                text_forget_password.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (isLoading) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            text_forget_password.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
                        }
                    })

                text_or_signin_with.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
                text_or_signin_with.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (isLoading) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            text_or_signin_with.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
                        }
                    })

                text_need_help.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
                text_need_help.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (isLoading) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            text_need_help.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
                        }
                    })

                clSocialMediaOptions.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
                clSocialMediaOptions.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (isLoading) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            clSocialMediaOptions.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
                        }
                    })
            }
            FoodAppLogin.LOGIN_PAGE.SIGN_UP -> {
                til_phone.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
                til_phone.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (isLoading) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            til_phone.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
                        }
                    })
            }
        }

        button_sign.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
        button_sign.animate()
            .setDuration(shortAnimTime)
            .alpha((if (isLoading) 0 else 1).toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    button_sign.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
                }
            })

        progress.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
        progress.animate()
            .setDuration(shortAnimTime)
            .alpha((if (isLoading) 1 else 0).toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    progress.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
                }
            })
    }

    enum class LOGIN_PAGE{
        SING_IN,
        SIGN_UP
    }
}