package emeraldbismaputra.practice.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import emeraldbismaputra.practice.myapplication.databinding.SavedDeckOptionsFragmentBinding

class SavedDeckOptionsFragment: Fragment() {

    private var _binding: SavedDeckOptionsFragmentBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val args: SavedDeckOptionsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SavedDeckOptionsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val deck: String = args.deck

        binding.apply{
            playButton.setOnClickListener {
                findNavController().navigate(SavedDeckOptionsFragmentDirections.enterPlayers(deck))
            }
            editDeckButton.setOnClickListener {
                findNavController().navigate(SavedDeckOptionsFragmentDirections.savedDeckList(deck))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}