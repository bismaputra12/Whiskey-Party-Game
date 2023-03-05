package emeraldbismaputra.practice.myapplication

import android.animation.ObjectAnimator
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import emeraldbismaputra.practice.myapplication.databinding.GameBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.util.*

class Game: Fragment() {
    private val cardRepository = CardRepository.get()
    private var _binding: GameBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val gameViewModel: GameViewModel by viewModels()
    private val args: GameArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // take playerArray from EnterPlayers.kt
        val playerList = args.playerArray.toList()

        // take deck name
        val deck = args.deck

        // take all the cards of the picked deck
        var cards = gameViewModel.provideCards(deck, playerList)
        var card: String
        var replacedCard: String

        // list variable for background colors
        val colorsArray = arrayOf(R.color.background_game1, R.color.background_game2, R.color.background_game3,
            R.color.background_game4, R.color.background_game5, R.color.background_game6,
            R.color.background_game7, R.color.background_game8, R.color.background_game9,
            R.color.background_game10)
        var colorIndex: Int = 0

        // keep track of player turns
        var currentIndex = 0
        val maxIndex = playerList.size

        // keep track of current saved card index
        var savedCardIndex = 0

        binding.apply {
            screenView.setOnClickListener {
                // change the background color
                val color = colorsArray[colorIndex] // retrieve the color at the current index
                parent.setBackgroundResource(color) // set the background color of the fragment's view
                colorIndex = (colorIndex + 1) % colorsArray.size // increment the index and wrap around if necessary

                // if the user want to play saved deck
                if (args.deck == "SAVED DECK #1"){
                    if (currentIndex == maxIndex){
                        currentIndex = 0
                    }
                    viewLifecycleOwner.lifecycleScope.launch {
                        var savedCards: List<Card> = cardRepository.getCards()
                        savedCards = savedCards.toMutableList()
                        if (savedCards.size != savedCardIndex) {
                            // set player's name for the current turn
                            var playerName = playerList[currentIndex]
                            playerTurn.text = getString(R.string.player_turn, playerName)
                            card = savedCards[savedCardIndex].description
                            challenge.text = card
                            currentIndex += 1
                            savedCardIndex += 1
                        }
                        else {
                            challenge.text = "GAME OVER"
                            playerTurn.visibility = View.INVISIBLE
                        }
                    }
                }
                else{
                    if (currentIndex == maxIndex){
                        currentIndex = 0
                    }
                    if (cards.size > 1) {
                        // player's name for the current turn
                        var playerName = playerList[currentIndex]
                        playerTurn.text = getString(R.string.player_turn, playerName)
                        card = cards[gameViewModel.random(0, cards.size)]

                        // make sure the challenge string doesn't contain current player's name
                        if (card.contains(playerList[currentIndex])) {
                            val rnd = Random()
                            val exclude = playerList[currentIndex]
                            val filteredList = playerList.filter { it != exclude }
                            val randomIndex = rnd.nextInt(filteredList.size)
                            val replacedPlayer = filteredList[randomIndex]
                            replacedCard = card.replace(playerList[currentIndex], replacedPlayer, ignoreCase = true)
                            challenge.text = replacedCard
                            cards.remove(replacedCard)
                            currentIndex += 1
                        }
                        else {
                            challenge.text = card
                            cards.remove(card)
                            currentIndex += 1
                        }
                    }
                    else {
                        challenge.text = "GAME OVER"
                        playerTurn.visibility = View.INVISIBLE
                    }
                }
                // create animation to the textview everytime the button is clicked
                val animation = AnimationUtils.loadAnimation(context, R.anim.challenge_animation)
                challenge.startAnimation(animation)
            }
        }
    }

    // the code below can be used to let the user be able to choose either they want
    // to play in portrait or landscape mode. However, to use the code below,
    // some of the code in here have to be moved to the view model file.
    // So, the fragment doesn't restart everytime the user change the orientation

    //override fun onResume() {
    //    super.onResume()
    //    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER
    //}

    //override fun onPause() {
    //    super.onPause()
    //    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    //}

    suspend fun <T> Flow<List<T>>.flattenToList() =
        flatMapConcat { it.asFlow() }.toList()
}