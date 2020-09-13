package com.sibelyildiz.roomexample.data.local.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**     Code with ❤
╔════════════════════════════╗
║  Created by Sibel YILDIZ   ║
╠════════════════════════════╣
║ sibelyldz2012@gmail.com    ║
╠════════════════════════════╣
║     06/09/2020 - 15:05     ║
╚════════════════════════════╝
 */

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey @ColumnInfo val word: String,
    val modifiedDate: Date
)