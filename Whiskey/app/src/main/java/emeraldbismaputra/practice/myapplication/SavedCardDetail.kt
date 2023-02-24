package emeraldbismaputra.practice.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import emeraldbismaputra.practice.myapplication.databinding.SavedCardDetailBinding
import emeraldbismaputra.practice.myapplication.databinding.SavedDeckRecyclerviewBinding
import kotlinx.coroutines.launch
import java.text.DateFormat

class SavedCardDetail: Fragment() {

    private var _binding: SavedCardDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val args: SavedCardDetailArgs by navArgs()

    private val savedCardDetailViewModel: SavedCardDetailViewModel by viewModels {
        SavedCardDetailViewModelFactory(args.cardDate)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.saved_card_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_card -> {
                viewLifecycleOwner.lifecycleScope.launch {
                    savedCardDetailViewModel.deleteCard(args.cardDate)
                    findNavController().popBackStack()
                }
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
        _binding = SavedCardDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            clearButton.setOnClickListener {
                title.text.clear()
                description.text.clear()
            }
            saveButton.setOnClickListener {
                findNavController().popBackStack()
            }

            title.doOnTextChanged { text, _, _, _ ->
                if (text.toString() != "") {
                    savedCardDetailViewModel.updateCard { oldCard ->
                        oldCard.copy(title = text.toString())
                    }
                }
            }

            description.doOnTextChanged { text, _, _, _ ->
                if (text.toString() != "") {
                    savedCardDetailViewModel.updateCard { oldCard ->
                        oldCard.copy(description = text.toString())
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                savedCardDetailViewModel.card.collect { card ->
                    card?.let { updateUi(it) }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUi(card: Card) {
        binding.apply {
            if (title.text.toString() != card.title) {
                title.setText(card.title)
            }
            if (description.text.toString() != card.description) {
                description.setText(card.description)
            }
        }
    }
}
