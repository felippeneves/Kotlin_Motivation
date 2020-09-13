package com.felippe_neves.motivation.screens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.felippe_neves.motivation.R
import com.felippe_neves.motivation.infra.Constants
import com.felippe_neves.motivation.shared_preferences.SharedPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener
{
    private val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        hideToolbar()
        initializeEvents()
        configureScreen()
    }

    private fun hideToolbar()
    {
        if(supportActionBar != null)
            supportActionBar!!.hide()
    }

    private fun initializeEvents()
    {
        btSave.setOnClickListener(this)
    }

    override fun onClick(view: View)
    {
        when(view.id)
        {
            R.id.btSave -> handleSave()
        }
    }

    private fun handleSave()
    {
        if(!validName())
            return

        val name = etName.text.toString().trim()

        SharedPreferences(context).storeString(Constants.KEY.PERSON_NAME, name)

        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun validName() : Boolean
    {
        if(etName.text.toString().isNotBlank())
            return true
        else
        {
            Toast.makeText(context, "Informe seu nome", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    private fun configureScreen()
    {
        verifyName()
    }

    private fun verifyName()
    {
        val name =  SharedPreferences(context).getString(Constants.KEY.PERSON_NAME)
        if(name.isNotBlank())
        {
            startActivity(Intent(context, MainActivity::class.java))
            finish()
        }
    }
}