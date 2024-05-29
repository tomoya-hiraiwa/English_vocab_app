package com.example.english_word_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.english_word_app.databinding.ActivityCreateQuizBinding

class CreateQuizActivity : AppCompatActivity() {
    private lateinit var b: ActivityCreateQuizBinding
    private lateinit var v: CreateQuizViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityCreateQuizBinding.inflate(layoutInflater)
        setContentView(b.root)
        v = ViewModelProvider(this)[CreateQuizViewModel::class.java]
        setStatusBarColor(this, window)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        b.apply {
            for (i in 0 until stepTab.tabCount){
                val view = stepTab.getTabAt(i)?.view
                view?.isClickable = false
            }
            v.stepCount.observe(this@CreateQuizActivity){position ->
                when(position){
                    0 -> {
                        changeFragment(CreateFirstFragment())
                        stepTab.getTabAt(0)?.select()
                    }
                    1 -> {
                        changeFragment(CreateSecondFragment())
                        stepTab.getTabAt(1)?.select()
                    }
                }
            }
        }

    }
    private fun changeFragment(fm: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.addQuizFragmentContainer,fm)
            .commit()
    }
}