package com.sibelyildiz.roomexample.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sibelyildiz.roomexample.data.local.dao.WordDao
import com.sibelyildiz.roomexample.data.local.entitiy.Word
import com.sibelyildiz.roomexample.data.local.typeconverter.DateTypeConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*


/**     Code with ❤
╔════════════════════════════╗
║  Created by Sibel YILDIZ   ║
╠════════════════════════════╣
║ sibelyldz2012@gmail.com    ║
╠════════════════════════════╣
║     06/09/2020 - 15:25     ║
╚════════════════════════════╝
 */

@Database(entities = [Word::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao   //Dao interfacelerimiz abstract metod olarak verilir


    //Singleton yapısı
    companion object {

        @Volatile  //multi thread bir işlemde birden fazla thread'in buraya girmesini ve birden fazla db oluştuma işlemini engellemek için.
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    //Default veri, ilk ayağa kalkarken
    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }

        //ayrı bir thread üzerinde çalışsın, sonuç geldiğinde de bizi haberdar etsin.
        suspend fun populateDatabase(wordDao: WordDao) {
            // Delete all content here.
            wordDao.deleteAll()

            // Add sample words.
            var word = Word("Hello", Calendar.getInstance().time)
            wordDao.insert(word)
            word = Word("Sibel!", Calendar.getInstance().time)
            wordDao.insert(word)

            // TODO: Add your own words!
        }
    }
}

