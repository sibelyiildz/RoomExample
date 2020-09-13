package com.sibelyildiz.roomexample.data.repository

import androidx.lifecycle.LiveData
import com.sibelyildiz.roomexample.data.local.dao.WordDao
import com.sibelyildiz.roomexample.data.local.entitiy.Word


/**     Code with ❤
╔════════════════════════════╗
║  Created by Sibel YILDIZ   ║
╠════════════════════════════╣
║ sibelyldz2012@gmail.com    ║
╠════════════════════════════╣
║     06/09/2020 - 15:56     ║
╚════════════════════════════╝
 */
class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}
