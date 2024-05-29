package com.example.english_word_app

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.english_word_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        setStatusBarColor(this,window)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        b.apply {
            //FAB Size is changed when change Scroll Value of NestedScrollView
            scroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY > oldScrollY){
                    fab.shrink()
                }
                  else {
                    fab.extend()
                }
            }
            fab.setOnClickListener {
                startActivity(Intent(this@MainActivity,CreateQuizActivity::class.java))
            }

        }
    }

}
fun setStatusBarColor(context: Context,window: Window){
    val config = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    if (config == Configuration.UI_MODE_NIGHT_YES){
        window.statusBarColor = context.resources.getColor(R.color.md_theme_onPrimary,context.theme)
    }
    else {
        window.statusBarColor = context.resources.getColor(R.color.md_theme_onSecondaryContainer,context.theme)

    }
}