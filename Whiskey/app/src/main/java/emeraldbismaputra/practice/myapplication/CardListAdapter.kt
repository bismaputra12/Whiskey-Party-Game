package emeraldbismaputra.practice.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import emeraldbismaputra.practice.myapplication.databinding.ListSavedDeckBinding
import kotlinx.coroutines.NonDisposableHandle.parent
import java.text.DateFormat
import java.util.*

class CardHolder(
    private val binding: ListSavedDeckBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(card: Card, onCardClicked: (cardDate: Date) -> Unit) {
        binding.cardTitle.text = card.title
        binding.cardDesc.text =  descUpdate(card.description)

        binding.root.setOnClickListener {
            onCardClicked(card.date)
        }
    }

    private fun descUpdate(old_description:String): String {
        var newDescription:String = ""
        newDescription = old_description.take(35)
        newDescription += "..."
        return newDescription
    }

    class CardListAdapter(
        private val cards: List<Card>,
        private val onCardClicked: (cardDate: Date) -> Unit
    ) : RecyclerView.Adapter<CardHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListSavedDeckBinding.inflate(inflater, parent, false)
            return CardHolder(binding)
        }

        override fun onBindViewHolder(holder: CardHolder, position: Int) {
            val card = cards[position]
            holder.bind(card, onCardClicked)
        }

        override fun getItemCount() = cards.size
    }
}