package emeraldbismaputra.practice.myapplication.database

import androidx.room.TypeConverter
import java.util.*

class CardTypeConverter {
    @TypeConverter
    fun fromDate(date: Date?):Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long): Date {
        return Date(millisSinceEpoch)
    }
}