package emeraldbismaputra.practice.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Card(@PrimaryKey var date:Date = GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).time,
                var title:String = "Title",
                var description:String = "Card Description")