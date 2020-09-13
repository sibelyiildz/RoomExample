package com.sibelyildiz.roomexample.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sibelyildiz.roomexample.data.local.entitiy.Word


/**     Code with ❤
╔════════════════════════════╗
║  Created by Sibel YILDIZ   ║
╠════════════════════════════╣
║ sibelyldz2012@gmail.com    ║
╠════════════════════════════╣
║     06/09/2020 - 15:11     ║
╚════════════════════════════╝
 */

@Dao
interface WordDao {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    /*@Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: Word)
*/

}