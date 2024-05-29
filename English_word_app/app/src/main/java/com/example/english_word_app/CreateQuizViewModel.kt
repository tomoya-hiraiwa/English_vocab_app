package com.example.english_word_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateQuizViewModel: ViewModel() {
    var stepCount = MutableLiveData<Int>().apply { value = 0 }
    var title = ""
}