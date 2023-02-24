package emeraldbismaputra.practice.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedDeckListViewModel: ViewModel(){
    private val cardRepository = CardRepository.get()

    private val _cards: MutableStateFlow<List<Card>> =
        MutableStateFlow(emptyList())

    val cards: StateFlow<List<Card>>
        get() = _cards.asStateFlow()

    init {
        viewModelScope.launch {
            cardRepository.getFlowCards().collect {
                _cards.value = it
            }
        }
    }

    suspend fun addCard(card: Card) {
        cardRepository.addCard(card)
    }
}