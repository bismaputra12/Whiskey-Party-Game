package emeraldbismaputra.practice.myapplication

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import emeraldbismaputra.practice.myapplication.databinding.SavedCardDetailBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*

class SavedCardDetailViewModel(cardDate: Date): ViewModel() {
    private val cardRepository = CardRepository.get()

    private val _card: MutableStateFlow<Card?> = MutableStateFlow(null)
    val card: StateFlow<Card?> = _card.asStateFlow()

    init {
        viewModelScope.launch {
            _card.value = cardRepository.getCard(cardDate)
        }
    }

    fun updateCard(onUpdate: (Card) -> Card) {
        _card.update { oldCard ->
            oldCard?.let { onUpdate(it) }
        }
    }

    suspend fun deleteCard(cardDate: Date) {
        cardRepository.deleteCard(cardDate)
    }

    override fun onCleared() {
        super.onCleared()

        card.value?.let { cardRepository.updateCard(it) }
    }
}

class SavedCardDetailViewModelFactory(private val cardDate: Date) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SavedCardDetailViewModel(cardDate) as T
    }
}