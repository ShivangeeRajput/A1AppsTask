package com.example.a1appstask.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1appstask.repository.MangaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mangaRepository: MangaRepository
) : ViewModel() {

    private val _mangaList = MutableStateFlow<List<Manga>>(emptyList())
    val mangaList: StateFlow<List<Manga>> = _mangaList

    fun fetchMangaList() {
        viewModelScope.launch {
            val mangaData = mangaRepository.getMangaList()
            if (mangaData != null) {
                _mangaList.value = mangaData
            }
        }
    }
}
