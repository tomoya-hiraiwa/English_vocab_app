package com.example.english_word_app

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.english_word_app.databinding.FragmentCreateFirstBinding


class CreateFirstFragment : Fragment() {
    private lateinit var b: FragmentCreateFirstBinding
    private lateinit var v: CreateQuizViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        b = FragmentCreateFirstBinding.inflate(inflater)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        v = ViewModelProvider(requireActivity())[CreateQuizViewModel::class.java]
        b.apply {
            nextBt.setOnClickListener {
                checkInputBox()
            }
            back.setOnClickListener {
                backDialog()
            }
        }
    }
    private fun checkInputBox(){
        b.apply {
            if (!quizNameEditText.text.isNullOrEmpty()){
                val titleText = quizNameEditText.text.toString()
                val regex = Regex("[a-zA-Z0-9]+")
                if (regex.matches(titleText)){
                    v.title = titleText
                    v.stepCount.value = 1
                }
                else {
                    quizNameFrame.error = "The input string does not meet the conditions."
                }
            }
            else quizNameFrame.error = "Please enter the title."
        }
    }
    private fun backDialog(){
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Notify")
            setMessage("Finish creating Quiz and return to home screen")
            setNegativeButton("Cancel"){_,_->}
            setPositiveButton("OK"){_,_->
                startActivity(Intent(requireContext(),MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
        }.show()
    }


}