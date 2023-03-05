package emeraldbismaputra.practice.myapplication

import android.content.Context
import androidx.room.Room
import emeraldbismaputra.practice.myapplication.database.CardDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

private const val DATABASE_NAME = "card-database.db"

class CardRepository private constructor(
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope
){

    private val database: CardDatabase = Room.databaseBuilder(
        context.applicationContext,
        CardDatabase::class.java,
        DATABASE_NAME
    )
        .build()

    fun getFlowCards(): Flow<List<Card>> = database.cardDao().getFlowCards()

    suspend fun getCards(): List<Card> = database.cardDao().getCards()

    suspend fun getCard(cardDate: Date): Card = database.cardDao().getCard(cardDate)

    fun updateCard(card: Card) {
        coroutineScope.launch {
            database.cardDao().updateCard(card)
        }
    }

    suspend fun addCard(card: Card) {
        database.cardDao().addCard(card)
    }

    suspend fun deleteCard(cardDate: Date) {
        database.cardDao().deleteCard(cardDate)
    }

    companion object {
        private var INSTANCE: CardRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CardRepository(context)
            }
        }

        fun get(): CardRepository {
            return INSTANCE ?:
            throw IllegalStateException("CardRepository must be initialized")
        }
    }
}