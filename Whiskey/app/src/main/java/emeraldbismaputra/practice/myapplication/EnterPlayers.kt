package emeraldbismaputra.practice.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import emeraldbismaputra.practice.myapplication.databinding.EnterPlayersBinding
import android.widget.Toast
import androidx.navigation.fragment.navArgs

class EnterPlayers: Fragment() {

    private var _binding: EnterPlayersBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val args: EnterPlayersArgs by navArgs()

    // create a list to keep track all the player names. then send it to the next fragment
    private var playerList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EnterPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val deck: String = args.deck

        binding.apply {
            playerList.clear()
            addButton.setOnClickListener {
                if (playerInput.text.toString() != ""){
                    playerList.add(playerInput.text.toString())
                    if (player1.visibility == View.INVISIBLE) {
                        player1.text = playerInput.text.toString()
                        player1.visibility = View.VISIBLE
                        playerInput.text.clear()
                        val animation = AnimationUtils.loadAnimation(context, R.anim.challenge_animation)
                        player1.startAnimation(animation)
                    } else if (player2.visibility == View.INVISIBLE) {
                        player2.text = playerInput.text.toString()
                        player2.visibility = View.VISIBLE
                        playerInput.text.clear()
                        val animation = AnimationUtils.loadAnimation(context, R.anim.challenge_animation)
                        player2.startAnimation(animation)
                    } else if (player3.visibility == View.INVISIBLE) {
                        player3.text = playerInput.text.toString()
                        player3.visibility = View.VISIBLE
                        playerInput.text.clear()
                        val animation = AnimationUtils.loadAnimation(context, R.anim.challenge_animation)
                        player3.startAnimation(animation)
                    } else if (player4.visibility == View.INVISIBLE) {
                        player4.text = playerInput.text.toString()
                        player4.visibility = View.VISIBLE
                        playerInput.text.clear()
                        val animation = AnimationUtils.loadAnimation(context, R.anim.challenge_animation)
                        player4.startAnimation(animation)
                    } else if (player5.visibility == View.INVISIBLE) {
                        player5.text = playerInput.text.toString()
                        player5.visibility = View.VISIBLE
                        playerInput.text.clear()
                        val animation = AnimationUtils.loadAnimation(context, R.anim.challenge_animation)
                        player5.startAnimation(animation)
                    } else if (player6.visibility == View.INVISIBLE) {
                        player6.text = playerInput.text.toString()
                        player6.visibility = View.VISIBLE
                        playerInput.text.clear()
                        val animation = AnimationUtils.loadAnimation(context, R.anim.challenge_animation)
                        player6.startAnimation(animation)
                    } else if (player7.visibility == View.INVISIBLE) {
                        player7.text = playerInput.text.toString()
                        player7.visibility = View.VISIBLE
                        playerInput.text.clear()
                        val animation = AnimationUtils.loadAnimation(context, R.anim.challenge_animation)
                        player7.startAnimation(animation)
                    } else if (player8.visibility == View.INVISIBLE) {
                        player8.text = playerInput.text.toString()
                        player8.visibility = View.VISIBLE
                        playerInput.text.clear()
                        val animation = AnimationUtils.loadAnimation(context, R.anim.challenge_animation)
                        player8.startAnimation(animation)
                    } else if (player9.visibility == View.INVISIBLE) {
                        player9.text = playerInput.text.toString()
                        player9.visibility = View.VISIBLE
                        playerInput.text.clear()
                        val animation = AnimationUtils.loadAnimation(context, R.anim.challenge_animation)
                        player9.startAnimation(animation)
                    } else if (player10.visibility == View.INVISIBLE) {
                        player10.text = playerInput.text.toString()
                        player10.visibility = View.VISIBLE
                        playerInput.text.clear()
                        val animation = AnimationUtils.loadAnimation(context, R.anim.challenge_animation)
                        player10.startAnimation(animation)
                    } else {
                        Toast.makeText(activity, R.string.full_players_toast, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            clearButton.setOnClickListener {
                playerInput.text.clear()
                playerList.clear()
                player1.visibility = View.INVISIBLE
                player2.visibility = View.INVISIBLE
                player3.visibility = View.INVISIBLE
                player4.visibility = View.INVISIBLE
                player5.visibility = View.INVISIBLE
                player6.visibility = View.INVISIBLE
                player7.visibility = View.INVISIBLE
                player8.visibility = View.INVISIBLE
                player9.visibility = View.INVISIBLE
                player10.visibility = View.INVISIBLE
            }

            playButton.setOnClickListener{
                if (playerList.size < 3){
                    Toast.makeText(activity, R.string.invite_more, Toast.LENGTH_SHORT).show()
                }
                else {
                    val playerArray = playerList.toTypedArray()
                    findNavController().navigate(EnterPlayersDirections.startGame(playerArray, deck))
                }
            }
        }
    }
}