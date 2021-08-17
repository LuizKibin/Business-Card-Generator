package me.lc_vi.businesscard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.lc_vi.businesscard.data.BusinessCard
import me.lc_vi.businesscard.data.BusinessCardRepository
import java.lang.IllegalArgumentException

class MainViewModel(private val businessCardRepository: BusinessCardRepository) : ViewModel() {

    fun insert(businessCard: BusinessCard) {

        businessCardRepository.insert(businessCard)

    }

    fun getAll(): LiveData<List<BusinessCard>> {

        return businessCardRepository.getAll()

    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            businessCardRepository.deleteAll()
        }
    }

}

class MainViewModelFactory(private val repository: BusinessCardRepository) :
        ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T

        }

        throw IllegalArgumentException("Unknown Viewmodel class")

    }
}