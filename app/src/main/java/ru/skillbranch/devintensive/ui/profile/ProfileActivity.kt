package ru.skillbranch.devintensive.ui.profile

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.extensions.isKeyboardOpen
import ru.skillbranch.devintensive.models.Bender
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.ui.custom.CircleImageView
import ru.skillbranch.devintensive.viewmodels.ProfileViewModel


class ProfileActivity : AppCompatActivity() {

    companion object{
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }

    private lateinit var viewModel: ProfileViewModel
    var isEditMode = false
    var valid = true
    lateinit var viewFields : Map<String,TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews(savedInstanceState)
        initViewModel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(IS_EDIT_MODE, isEditMode)
    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.getProfileData().observe(this, Observer { updateUI(it) })
        viewModel.getTheme().observe(this, Observer { updateTheme(it) })
    }

    private fun updateTheme(mode: Int) {
//        Log.d("M_ProfileActivity","updateTheme")
        delegate.setLocalNightMode(mode)
    }

    private fun updateUI(profile: Profile) {
        profile.toMap().also {
            for((k,v) in viewFields){
                v.text = it[k].toString()
            }
        }
    }

    private fun initViews(savedInstanceState: Bundle?) {

        viewFields = mapOf(
            "nickName" to tv_nick_name,
            "rank" to tv_rank,
            "respect" to tv_respect,
            "rating" to tv_rating,
            "firstName" to et_first_name,
            "lastName" to et_last_name,
            "about" to et_about,
            "repository" to et_repository
        )

        isEditMode = savedInstanceState?.getBoolean(IS_EDIT_MODE, false) ?: false
        showCurrentMode(isEditMode)

        btn_edit.setOnClickListener {
            if(isEditMode) saveProfileInfo()
            isEditMode = !isEditMode
            showCurrentMode(isEditMode)
        }
        btn_switch_theme.setOnClickListener {
            viewModel.switchTheme()
        }
        //!!!!!
//        iv_avatar.alpha = 1F
//        iv_avatar.setBorderColor(R.color.design_default_color_primary)

        et_repository.addTextChangedListener(object : TextWatcher{//!!!!!
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if(s.toString() == "")
                    valid = true
                else{
                    var slash = 0
                    for(i in 1..s.toString().length){
                        if(s.toString()[i - 1] == '/')
                            slash++
                    }
                    if(s.toString().contains("https://")){
                        valid = when {
                            s.toString().contains("https://github.com/") && slash == 3 -> true
                            s.toString().contains("https://www.github.com/") && slash == 3 -> true
                            else -> false
                        }

                    }
                    else if(s.toString()[0] == 'w' && s.toString().contains("www") && slash == 1){
                        if(s.toString().contains("www.github.com/"))
                            valid = true
                    }
                    else valid = s.toString()[0] == 'g' && s.toString().contains("github.com/") && slash == 1

                    if(valid){
                        valid = s.toString()[s.toString().length - 1] != '/'
                        valid = !s.toString().endsWith("enterprise")
                        valid = !s.toString().endsWith("features")
                        valid = !s.toString().endsWith("topics")
                        valid = !s.toString().endsWith("collections")
                        valid = !s.toString().endsWith("trending")
                        valid = !s.toString().endsWith("events")
                        valid = !s.toString().endsWith("marketplace")
                        valid = !s.toString().endsWith("pricing")
                        valid = !s.toString().endsWith("nonprofit")
                        valid = !s.toString().endsWith("customer-stories")
                        valid = !s.toString().endsWith("security")
                        valid = !s.toString().endsWith("login")
                        valid = !s.toString().endsWith("join")
                    }
                }
                if(!valid)
                    wr_repository.error = "Невалидный адрес репозитория"
                else
                    wr_repository.error = ""
            }
        })
    }

    private fun showCurrentMode(isEdit: Boolean) {
        val info = viewFields.filter { setOf("firstName", "lastName", "about", "repository").contains(it.key) }
        for((_,v) in info){
            v as EditText
            v.isFocusable = isEdit
            v.isFocusableInTouchMode = isEdit
            v.isEnabled = isEdit
            v.background.alpha = if(isEdit) 255 else 0
        }

        ic_eye.visibility = if(isEdit) View.GONE else View.VISIBLE
        wr_about.isCounterEnabled = isEdit
            //!!!!
        with(btn_edit){
            val filter : ColorFilter? = if(isEdit){
                if(viewModel.getTheme().value == AppCompatDelegate.MODE_NIGHT_YES){
                    PorterDuffColorFilter(
                        resources.getColor(R.color.color_accent_night, theme),
                        PorterDuff.Mode.SRC_IN
                    )
                }
                else{
                    PorterDuffColorFilter(
                        resources.getColor(R.color.color_accent, theme),
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
            else
                null

            val icon = if(isEdit){
                resources.getDrawable(R.drawable.ic_save_black_24dp, theme)
            }
            else{
                resources.getDrawable(R.drawable.ic_edit_black_24dp, theme)
            }
            background.colorFilter = filter
            setImageDrawable(icon)
        }

    }

    private fun saveProfileInfo(){
        if(!valid){
            et_repository.text = null // try
        Profile(
            firstName = et_first_name.text.toString(),
            lastName = et_last_name.text.toString(),
            about = et_about.text.toString(),
            repository = et_repository.text.toString()
        ).apply {
            viewModel.saveProfileData(this)
        }
    }

}
}

