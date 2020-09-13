package com.sibelyildiz.roomexample.data.local.typeconverter

import androidx.room.TypeConverter
import java.util.*


/**     Code with ❤
╔════════════════════════════╗
║  Created by Sibel YILDIZ   ║
╠════════════════════════════╣
║ sibelyldz2012@gmail.com    ║
╠════════════════════════════╣
║     09/09/2020 - 10:28     ║
╚════════════════════════════╝
 */

class DateTypeConverter {

    @TypeConverter
    fun fromTimeStampToDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun fromDateToTimeStamp(value: Date): Long {
        return value.time
    }
}