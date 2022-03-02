package com.androiddev.internetharrypotter.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddev.internetharrypotter.network.CharacterModel
import java.lang.IllegalArgumentException

class DetailViewModelFactory(private val characterModel: CharacterModel, private val application: Application) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(characterModel, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }


}