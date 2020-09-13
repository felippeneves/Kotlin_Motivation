package com.felippe_neves.motivation.screens

import android.content.Context
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import com.felippe_neves.motivation.R
import com.felippe_neves.motivation.infra.Constants
import com.felippe_neves.motivation.repository.Mock
import com.felippe_neves.motivation.shared_preferences.SharedPreferences
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*

class MainActivity : AppCompatActivity(), View.OnClickListener
{
    private val context: Context = this
    private var mPhraseFilter = Constants.PHRASE_FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideToolbar()
        initializeEvents()
        configureSceen()
    }

    private fun hideToolbar()
    {
        if(supportActionBar != null)
            supportActionBar!!.hide()
    }

    private fun initializeEvents()
    {
        ivAll.setOnClickListener(this)
        ivHappy.setOnClickListener(this)
        ivMorning.setOnClickListener(this)
        btNewPhrase.setOnClickListener(this)
    }

    private fun configureSceen()
    {
        val name = SharedPreferences(context).getString(Constants.KEY.PERSON_NAME)
        tvName.text = "Olá, $name!"
        setColorIcon(ivAll, R.color.white)
        handleNewPhrase()
    }

    override fun onClick(view: View)
    {
        when(view.id)
        {
            R.id.btNewPhrase ->
            {
                handleNewPhrase()
            }
            R.id.ivAll, R.id.ivHappy, R.id.ivMorning ->
            {
                handleFilter(view.tag.toString())
            }
        }
    }

    private fun handleNewPhrase()
    {
        tvPhrase.text = Mock().getPhrase(mPhraseFilter)
    }

    private fun handleFilter(filter: String)
    {
        mPhraseFilter = filter
        //
        setColorIcon(ivAll, R.color.white)
        setColorIcon(ivHappy, R.color.white)
        setColorIcon(ivMorning, R.color.white)

        when(filter)
        {
            Constants.PHRASE_FILTER.ALL ->
            {
                setColorIcon(ivAll, R.color.colorAccent)
            }
            Constants.PHRASE_FILTER.HAPPY ->
            {
                setColorIcon(ivHappy, R.color.colorAccent)
            }
            Constants.PHRASE_FILTER.MORNING ->
            {
                setColorIcon(ivMorning, R.color.colorAccent)
            }
        }
    }

    private fun setColorIcon(iv: ImageView, resColor: Int)
    {
        iv.setColorFilter(resources.getColor(resColor))
    }
}