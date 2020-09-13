package com.sibelyildiz.roomexample.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sibelyildiz.roomexample.data.local.db.WordRoomDatabase
import com.sibelyildiz.roomexample.data.local.entitiy.Word
import com.sibelyildiz.roomexample.data.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**     Code with ❤
╔════════════════════════════╗
║  Created by Sibel YILDIZ   ║
╠════════════════════════════╣
║ sibelyldz2012@gmail.com    ║
╠════════════════════════════╣
║     06/09/2020 - 16:09     ║
╚════════════════════════════╝
 */
class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Word>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}
