package emeraldbismaputra.practice.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import emeraldbismaputra.practice.myapplication.databinding.DeckFragmentBinding
import emeraldbismaputra.practice.myapplication.databinding.StartingFragmentBinding

class StartingFragment: Fragment() {

    private var _binding: StartingFragmentBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StartingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply{
            startButton.setOnClickListener{
                findNavController().navigate(StartingFragmentDirections.startPickDeck())
            }
            howToPlayButton.setOnClickListener{
                findNavController().navigate(StartingFragmentDirections.openHowToPlay())
            }
        }
    }
}