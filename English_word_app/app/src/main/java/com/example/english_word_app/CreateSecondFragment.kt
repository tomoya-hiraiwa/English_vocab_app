package com.example.english_word_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.english_word_app.databinding.FragmentCreateSecondBinding


class CreateSecondFragment : Fragment() {
private lateinit var b: FragmentCreateSecondBinding
private lateinit var v: CreateQuizViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        b = FragmentCreateSecondBinding.inflate(inflater)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = ViewModelProvider(requireActivity())[CreateQuizViewModel::class.java]
        b.apply {
            nextButton.setOnClickListener {
                v.stepCount.value = 2
                parentFragmentManager.beginTransaction()
                    .replace(R.id.addQuizFragmentContainer,CreateThirdFragment())
                    .commit()
            }
        }
    }

   
}