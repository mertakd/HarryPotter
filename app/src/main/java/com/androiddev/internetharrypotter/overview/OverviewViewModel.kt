package com.androiddev.internetharrypotter.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddev.internetharrypotter.network.CharacterModel
import com.androiddev.internetharrypotter.network.HarryPotterApi
import kotlinx.coroutines.launch
import java.lang.Exception


enum class HarryPotterApiStatus{LOADING, ERROR, DONE }

enum class HarryPotterApiFilter(val filterWord: String) {
    SHOW_GRYFFINDOR("Gryffindor"),
    SHOW_HUFFLEPUF("Hufflepuf"),
    SHOW_RAVENCLAW("Ravenclaw"),
    SHOW_SLYTHERIN("Slytherin")

    //bu seçeneklerden biri seçilirse başka bir yerde filterWord argumanına yerleştiriyoruz ve ilgili yerde kullanıyoruz
}

class OverviewViewModel: ViewModel() {

    //3 state done error loading
    private val  _status = MutableLiveData<HarryPotterApiStatus>()
    val status: LiveData<HarryPotterApiStatus> //fragment tarafından fragment observe eder
        get() = _status


    //türü karakter model olan 12 adet karakteri içeri çekiyor
    private val _characters = MutableLiveData<List<CharacterModel>>()
    val characters: LiveData<List<CharacterModel>>
        get() = _characters

    private val _navigateToSelectedCharater = MutableLiveData<CharacterModel?>()
    val navigateToSelectedCharater: LiveData<CharacterModel?>
        get() = _navigateToSelectedCharater



    init {
        //init viewmodel oluştuğunda çalışmasını istediğimiz yapıları buraya yerleştiriyoruz
        getCharacters()
    }

    private fun getCharacters() {

        viewModelScope.launch {
            _status.value = HarryPotterApiStatus.LOADING
            //loading simgesi gösterebileceğiz
            try {
                _characters.value = HarryPotterApi.retrofitService.getCharacters().characters
                //CharacterModel e verileri yerleştiriyor dönen response, characters response sınıfından geliyor yani cevap geliyor
                /*
                *HarryPotterApi ye gider daha sonra retrofitService sonrasında interface olan HarryPotterApiService e gider bu
                *bu interface retrofit servis ile birlikte get sorgusunu atıyor php dosyasına, php dosyası da veri tabanında bulunan 12 adet karakteri tamamını çekme işlemini deniyordu.
                * bu get işleminin sonucunda CharacterResponse türünde dönüyor.
                * CharacterResponse data class ı : harrypotter key inde bir liste istiyoruz, php dosyasında öyle belirtilmiş. bunu da bir değişken içerisine yerleştirdik
                * var characters: List<CharacterModel> olarak
                * sonrasında dönen veriyi moshi ile convert edebildik
                 */
                _status.value = HarryPotterApiStatus.DONE
            }catch(e: Exception) {
                _status.value = HarryPotterApiStatus.ERROR
                _characters.value = ArrayList()
                //arraylist verilmesinin bir sebebi bir hata olduğunda verimizi boşaltmak istiyoruz
                //yani recyclerview ı temizlemek manasına geliyor
            }


        }
    }

    fun filterCharacters(filter: HarryPotterApiFilter) {

        viewModelScope.launch {

            _status.value = HarryPotterApiStatus.LOADING

            try {
                _characters.value = HarryPotterApi.retrofitService.filterCharacters(filter.filterWord).characters
                _status.value = HarryPotterApiStatus.DONE
            }catch (e: Exception){
                _status.value = HarryPotterApiStatus.ERROR
                _characters.value = ArrayList()
            }
        }
    }

    fun displayCharacterDetail(characterModel: CharacterModel) {
        _navigateToSelectedCharater.value = characterModel
    }

    fun displayCharacterDetailComplete() {
        _navigateToSelectedCharater.value = null
    }

}