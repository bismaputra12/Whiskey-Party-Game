package emeraldbismaputra.practice.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import emeraldbismaputra.practice.myapplication.databinding.DeckFragmentBinding

class DeckFragment: Fragment() {

    private var _binding: DeckFragmentBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DeckFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply{
            sampleDeckButton.setOnClickListener{
                //change sampleDeckButton color to blue_purple
                sampleDeckButton.setBackgroundColor(Color.parseColor("#332FD0"))
                sampleDeckButton.setTextColor(Color.WHITE)
                //change savedDeckButton color to white
                savedDeckButton.setBackgroundColor(Color.WHITE)
                savedDeckButton.setTextColor(Color.parseColor("#5a5a5a"))
                // show choices of sample decks
                firstSampleDeck.visibility = View.VISIBLE
                secondSampleDeck.visibility = View.VISIBLE
                thirdSampleDeck.visibility = View.VISIBLE
                firstSavedDeck.visibility = View.INVISIBLE
                secondSavedDeck.visibility = View.INVISIBLE
                thirdSavedDeck.visibility = View.INVISIBLE
            }
            savedDeckButton.setOnClickListener{
                // change savedDeckButton color to blue_purple
                savedDeckButton.setBackgroundColor(Color.parseColor("#332FD0"))
                savedDeckButton.setTextColor(Color.WHITE)
                // change sampleDeckButton color to blue_purple
                sampleDeckButton.setBackgroundColor(Color.WHITE)
                sampleDeckButton.setTextColor(Color.parseColor("#5a5a5a"))
                // show choices of saved decks
                firstSavedDeck.visibility = View.VISIBLE
                secondSavedDeck.visibility = View.VISIBLE
                thirdSavedDeck.visibility = View.VISIBLE
                firstSampleDeck.visibility = View.INVISIBLE
                secondSampleDeck.visibility = View.INVISIBLE
                thirdSampleDeck.visibility = View.INVISIBLE
            }
            firstSampleDeck.setOnClickListener{
                val funWithFriendsDeck: String = "FUN WITH FRIENDS"
                findNavController().navigate(DeckFragmentDirections.enterPlayers(funWithFriendsDeck))
            }
            secondSampleDeck.setOnClickListener{
                val eighteenPlusDeck: String = "18+"
                findNavController().navigate(DeckFragmentDirections.enterPlayers(eighteenPlusDeck))
            }
            firstSavedDeck.setOnClickListener {
                val firstSavedDeck: String = "SAVED DECK #1"
                findNavController().navigate(DeckFragmentDirections.toSavedDeckOptionsFragment(firstSavedDeck))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}