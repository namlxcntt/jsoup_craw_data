package com.lxn.appuploaddata.feature

import android.util.Log
import androidx.lifecycle.*
import com.lxn.appuploaddata.data.model.Category
import com.lxn.appuploaddata.feature.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by @Author: Nam_Lx
 * Project : AppUploadData
 * Create Time : 11:51 - 14/05/2022
 * For all issue contact me : namlxcntt@gmail.com
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _listCategory: MutableLiveData<List<Category>> by lazy { MutableLiveData() }

    val listCategory: LiveData<List<Category>> get() = _listCategory

    init {
        getAllVocabulary()
    }

    private fun getAllCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = async { mainRepository.getAllCategory() }
            val dataListCategory = data.await()

            dataListCategory.collect {
                withContext(Dispatchers.Main) {
                    _listCategory.value = it
                    uploadImageCategoryToFirebase(it)
                }
            }
        }
    }

    private fun uploadImageCategoryToFirebase(category: List<Category>) {
        viewModelScope.launch(Dispatchers.IO) {
            category.forEach {
                delay(500)
                val uploadTask = async { mainRepository.saveImageCategory(category = it) }
                uploadTask.await().collect { category ->
                    val uploadDataTask = async { mainRepository.saveCategoryToFirebase(category = category) }
                    uploadDataTask.await().collect {
                        Log.e("Namlxcntt", "Upload database -> $it")
                    }

                }
            }
        }
    }

    private fun getAllVocabulary() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = async { mainRepository.getAllVocabularyById() }
            data.await().collect {
                for (vocabulary in it) {
                    val vocabularyImage = async(Dispatchers.IO) { mainRepository.saveImageVocabulary(vocabulary) }
                    vocabularyImage.await().collect { vocabImage ->
                        val vocabularyAudio = async { mainRepository.saveAudioVocabulary(vocabImage) }
                        vocabularyAudio.await().collect {
                            val vocabularyUpdate = async { mainRepository.saveVocabularyToFirebase(it) }
                            vocabularyUpdate.await().collect {
                                Log.e("Namlxcntt", "Upload Category $it")
                            }
                        }
                    }

                }
            }
        }
    }
}