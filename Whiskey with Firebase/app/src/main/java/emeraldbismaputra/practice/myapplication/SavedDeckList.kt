package emeraldbismaputra.practice.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import emeraldbismaputra.practice.myapplication.databinding.SavedDeckRecyclerviewBinding
import kotlinx.coroutines.launch
import java.util.*

class SavedDeckList: Fragment() {

    private var _binding: SavedDeckRecyclerviewBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    // take the deck name from SavedDeckOptionsFragment.kt
    // for later when there are multiple decks/database
    private val args: SavedDeckListArgs by navArgs()
    private val savedDeckListViewModel: SavedDeckListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    //Inflate the menu in app bar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.saved_card_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_card -> {
                showNewCard()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SavedDeckRecyclerviewBinding.inflate(inflater, container, false)
        binding.cardsRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                savedDeckListViewModel.cards.collect { cards ->
                    binding.cardsRecyclerView.adapter =
                        CardHolder.CardListAdapter(cards) { cardDate ->
                            findNavController().navigate(SavedDeckListDirections.showCardDetail(cardDate))
                        }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showNewCard() {
        viewLifecycleOwner.lifecycleScope.launch {
            val newCard = Card(
                date = Date(),
                title = "Title",
                description = "Card Description"
            )
            savedDeckListViewModel.addCard(newCard)
            findNavController().navigate(
                SavedDeckListDirections.showCardDetail(newCard.date)
            )
        }
    }
}