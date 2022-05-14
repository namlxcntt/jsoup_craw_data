package com.lxn.appuploaddata.feature.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import com.lxn.appuploaddata.data.EnglishDatabase
import com.lxn.appuploaddata.data.mapper.CategoryMapper
import com.lxn.appuploaddata.data.mapper.VocabularyCacheMapper
import com.lxn.appuploaddata.data.model.Category
import com.lxn.appuploaddata.data.model.Vocabulary
import com.lxn.appuploaddata.utils.Constant
import com.lxn.appuploaddata.utils.getFileAudioByName
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.io.InputStream
import java.util.*
import javax.inject.Inject

/**
 * Created by @Author: Nam_Lx
 * Project : AppUploadData
 * Create Time : 11:54 - 14/05/2022
 * For all issue contact me : namlxcntt@gmail.com
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MainRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val englishDatabase: EnglishDatabase,
    private val databaseReference: DatabaseReference,
    private val firebaseStorage: FirebaseStorage,
    private val categoryMapper: CategoryMapper,
    private val vocabularyCacheMapper: VocabularyCacheMapper
) : MainRepository {

    override suspend fun getAllCategory(): Flow<List<Category>> = flow {
        try {
            emit(categoryMapper.mapFromEntityList(englishDatabase.categoryDao().getAllCategory()))
        } catch (exception: Exception) {
            exception.message?.let { Log.e("Namlxcntt", it) }
        }
    }

    override suspend fun getAllVocabularyById(): Flow<List<Vocabulary>> = flow {
        try {
            emit(vocabularyCacheMapper.mapFromEntityList(englishDatabase.vocabularyDao().getAllNewWord()))
        } catch (exception: Exception) {
            exception.message?.let { Log.e("Namlxcntt", it) }
        }
    }


    override suspend fun saveImageCategory(category: Category): Flow<Category> = callbackFlow {
        var name = ""
        when (category.categoryId) {
            0 -> {
                name = "contract"
            }
            1 -> {
                name = "marketing"
            }
            2 -> {
                name = "warranty"
            }
            3 -> {
                name = "bussiness_planing"
            }
            4 -> {
                name = "conference"
            }
            5 -> {
                name = "computer"
            }
            6 -> {
                name = "office_technology"
            }
            7 -> {
                name = "office_procedure"
            }
            8 -> {
                name = "electronic"
            }
            9 -> {
                name = "_correspondence"
            }
            10 -> {
                name = "jobads_recruitment"
            }
            11 -> {
                name = "apply_interview"
            }
            12 -> {
                name = "hiring_training"
            }
            13 -> {
                name = "sale_benefit"
            }
            14 -> {
                name = "promotions_awards"
            }
            15 -> {
                name = "shopping"
            }
            16 -> {
                name = "order_supplies"
            }
            17 -> {
                name = "shipping"
            }
            18 -> {
                name = "invoices"
            }
            19 -> {
                name = "inventory"
            }
            20 -> {
                name = "banking"
            }
            21 -> {
                name = "_accounting"
            }
            22 -> {
                name = "investment"
            }
            23 -> {
                name = "taxes"
            }
            24 -> {
                name = "finacial_state"
            }
            25 -> {
                name = "property_department"
            }
            26 -> {
                name = "board_meeting"
            }
            27 -> {
                name = "quality_control"
            }
            28 -> {
                name = "product_development"
            }
            29 -> {
                name = "renting_leasing"
            }
            30 -> {
                name = "selecting_restaurant"
            }
            31 -> {
                name = "eating_out"
            }
            32 -> {
                name = "order_lunch"
            }
            33 -> {
                name = "cooking_carrier"
            }
            34 -> {
                name = "event"
            }
            35 -> {
                name = "general_travel"
            }
            36 -> {
                name = "airline"
            }
            37 -> {
                name = "train"
            }
            38 -> {
                name = "hotel"
            }
            39 -> {
                name = "car_rental"
            }
            40 -> {
                name = "movie"
            }
            41 -> {
                name = "theater"
            }
            42 -> {
                name = "music"
            }
            43 -> {
                name = "museum"
            }
            44 -> {
                name = "media"
            }
            45 -> {
                name = "doctor_office"
            }
            46 -> {
                name = "dentist"
            }
            47 -> {
                name = "heal_insurance"
            }
            48 -> {
                name = "hospital"
            }
            49 -> {
                name = "pharmacy"
            }
        }
        val uri: Uri = Uri.parse("android.resource://com.lxn.appuploaddata/drawable/$name")
        val stream: InputStream? = context.contentResolver.openInputStream(uri)
        if (stream != null) {
            firebaseStorage.reference.child(Constant.CATEGORY_CHILD).child(category.title).putStream(stream).addOnSuccessListener {
                it.storage.downloadUrl.addOnSuccessListener { uri ->
                    category.image = uri.toString()
                    trySend(category)
                    channel.close()
                }
            }

        }
        awaitClose {
            Log.e("Namlxcntt", "Callback flow is closed")
        }
    }

    override suspend fun saveImageVocabulary(vocabulary: Vocabulary): Flow<Vocabulary> = callbackFlow {

        try {
            val inputStream: InputStream = context.assets.open("image/" + vocabulary.vocabulary.lowercase(Locale.getDefault()).trim { it <= ' ' }.replace(" ", "_") + ".jpeg")
            firebaseStorage.reference.child(Constant.VOCABULARY_CHILD_IMAGE).child(vocabulary.vocabulary).putStream(inputStream)
                .addOnSuccessListener {
                    it.storage.downloadUrl.addOnSuccessListener { uri ->
                        vocabulary.image = uri.toString()
                        trySend(vocabulary)
                        channel.close()
                    }

                    inputStream.close()
                }
        } catch (e: IOException) {
            e.printStackTrace()
            vocabulary.image = ""
            trySend(vocabulary)
            channel.close()
        }
        awaitClose {
            Log.e("Namlxcntt", "Callback saveImageVocabulary is closed")
        }

    }

    override suspend fun saveAudioVocabulary(vocabulary: Vocabulary): Flow<Vocabulary> = callbackFlow {
        try {
            val fileStream = getFileAudioByName(context, vocabulary.vocabulary)
            if (fileStream != null) {
                firebaseStorage.reference.child(Constant.VOCABULARY_CHILD_AUDIO).child(vocabulary.vocabulary).putStream(fileStream)
                    .addOnSuccessListener {
                        it.storage.downloadUrl.addOnSuccessListener { uri ->
                            vocabulary.linkMp3 = uri.toString()
                            trySend(vocabulary)
                            channel.close()
                        }
                        fileStream.close()
                    }
            }
            else{
                channel.close()
            }
        }catch (exception : Exception){
            exception.printStackTrace()
            vocabulary.linkMp3 = ""
            trySend(vocabulary)
            channel.close()
        }

        awaitClose {
            Log.e("Namlxcntt", "Callback saveAudioVocabulary is closed")
        }

    }

    override suspend fun saveVocabularyToFirebase(vocabulary: Vocabulary): Flow<Boolean>  = callbackFlow{
        databaseReference.child(Constant.VOCABULARY_CHILD).child(vocabulary.vocabulary).setValue(vocabulary).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                trySend(true)
            } else {
                trySend(false)
            }
            channel.close()
        }
        awaitClose {
            Log.e("Namlxcntt", "Callback saveVocabularyToFirebase is closed")
        }
    }

    override suspend fun saveCategoryToFirebase(category: Category): Flow<Boolean> = callbackFlow {
        databaseReference.child(Constant.CATEGORY_CHILD).child(category.title).setValue(category).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                trySend(true)
            } else {
                trySend(false)
            }
            channel.close()
        }
        awaitClose {
            Log.e("Namlxcntt", "Callback flow is closed")
        }
    }

}