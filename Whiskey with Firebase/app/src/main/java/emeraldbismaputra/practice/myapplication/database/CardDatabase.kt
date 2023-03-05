package emeraldbismaputra.practice.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import emeraldbismaputra.practice.myapplication.Card

@Database(entities = [Card::class], version = 1)
@TypeConverters(CardTypeConverter::class)
abstract class CardDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}
