package com.androiddev.internetharrypotter.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androiddev.internetharrypotter.network.CharacterModel

class DetailViewModel(character: CharacterModel, app: Application): AndroidViewModel(app) {
    /*
    factory class ı: constructer içerisine bir context veya applicatin tanımlamamız gerektiği durumlarda bir hatayla karşılaşabiliyoruz
    *direk viewmodel e context verirsek hata oluşuyor bunun önüne geçmek için factory oluşturuyoruz
     */

    private val _selectedCharacter = MutableLiveData<CharacterModel>()
    val selectedChracter: LiveData<CharacterModel>
        get() = _selectedCharacter


    init {
        _selectedCharacter.value = character
    }
}