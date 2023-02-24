package emeraldbismaputra.practice.myapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import emeraldbismaputra.practice.myapplication.Card
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface CardDao {
    @Query("SELECT * FROM Card")
    fun getFlowCards(): Flow<List<Card>>

    @Query("SELECT * FROM Card")
    suspend fun getCards(): List<Card>

    @Query("SELECT * FROM Card WHERE date=(:cardDate)")
    suspend fun getCard(cardDate: Date): Card

    @Update
    suspend fun updateCard(card: Card)

    @Insert
    suspend fun addCard(card: Card)

    @Query("DELETE FROM Card WHERE date=(:cardDate)")
    suspend fun deleteCard(cardDate: Date)
}