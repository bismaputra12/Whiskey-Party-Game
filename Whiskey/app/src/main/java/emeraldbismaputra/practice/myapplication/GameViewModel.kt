package emeraldbismaputra.practice.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.Random

class GameViewModel: ViewModel() {

    fun provideCards(deck: String, playerList: List<String>): MutableList<String> {
        val numOfPlayers: Int = playerList.size

        val funWithFriendsDeck: MutableList<String> = mutableListOf("Do a handstand for 5 seconds",
        "The person who have smoked weed, take 2 sips",
        "Bald players have to take 2 sips",
        "Take a sip if you've ever been pulled over by the police",
        "The youngest player has to drink 2 sips",
        "Shout I'm gay",
        "Find a random person, and moan next to them",
        "Would you rather have a gay son or a hoe daughter? everyone vote at once, losing group take 2 sips ",
        "Would you rather have X-ray vision or be able to teleport? everyone vote at once, losing group take 2 sips",
        "Would you rather hear the truth all the time or be lied to all the time? everyone vote at once, losing group take 2 sips",
        "Do a shot if you've ever stolen something from a store",
        "Do a shot if you've ever been arrested",
        "Drink if you've ever gotten into a physical fight",
        "Do a handstand for 5 seconds",
        "Drink 2 sips with ${playerList[random(1, numOfPlayers)]}",
        "Let ${playerList[random(1, numOfPlayers)]} make a dare for you",
        "Do a shot if you've ever do drugs",
        "All female players have to drink",
        "Make a rule, any player who breaks the rule have to take a shot",
        "Pick 2 players to drink with you",
        "Do 10 push up",
        "For the next 3 rounds, the players that are playing with their phone have to take a shot",
        "Hands down, everyone have to touch the floor. The last one to touch the floor have to drink",
        "If you can have any power, what would it be?",
        "Pick a number between 1 to 10, each player have to guess the number and each only have 1 guess. The players who guessed wrong have to take a shot",
        "Pick an animal, the other players have to guess the animal and they all have infinite guess. Everyone have to drink except for the first player who guessed right. You can give hints",
        "For the next 2 rounds, everytime ${playerList[random(1, numOfPlayers)]} drinks, you drink.",
        "Play rock-paper-scissors with ${playerList[random(1, numOfPlayers)]}, the loser have to take a shot",
        "When you say \"up\", everyone must raise their hand. The last person to raise their hand have to drink",
        "The shortest player in the game have to drink",
        "Pick a country, the other players have to guess the country and they all have infinite guess. Everyone have to drink except for the first player who guessed right. You can give hints",
        "Order a box of pizza (or other food) for everyone or you have to drink a whole glass",
        "The player with the biggest forehead have to take a shot",
        "Tickle Torture! Endure 1 minute of tickling or take 2 shots",
        "Tickle Torture! Endure 1 minute of tickling or take 2 shots",
        "Sing a song while twerking",
        "Give your best impersonation of the opposite sex",
        "Trade clothes with ${playerList[random(1, numOfPlayers)]}",
        "Send a random gif to the first person in your contact list with no explanation",
        "Try to hit on ${playerList[random(1, numOfPlayers)]} like you’re at a bar. You have 3 minutes",
        "Tell the group two truths and a lie, they have to guess which one is the lie. The players who guessed wrong have to drink",
        "Put 5 different liquids into a cup and drink it",
        "If you can have sex anywhere in public, where would it be?",
        "Walk down the street in your underwear",
        "Wear a blindfold and kiss whatever body part is put in front of you",
        "Keep this card and use it anytime to force another player to drink double of what they're supposed to",
        "Staring Contest with ${playerList[random(1, numOfPlayers)]}. The loser have to take a shot",
        "Thumb War with ${playerList[random(1, numOfPlayers)]}. The loser have to take a shot",
        "Pick a word, others must rhyme it or take 2 shots",
        "Let ${playerList[random(1, numOfPlayers)]} and ${playerList[random(1, numOfPlayers)]} slap your ass as hard as they can or take 2 shots",
        "Guess the color of ${playerList[random(1, numOfPlayers)]}\'s underwear. Take a shot if you're wrong",
        "You have to pay 5 dollars to ${playerList[random(1, numOfPlayers)]} or drink",
        "What is 24 x 7? drink 2 sips if you're wrong. You can't use calculator",
        "What is 47 x 5? drink 2 sips if you're wrong. You can't use calculator")

        val eighteenPlusDeck: MutableList<String> = mutableListOf("Hug ${playerList[random(1, numOfPlayers)]} for 10 seconds",
        "Give ${playerList[random(1, numOfPlayers)]} a hickey",
        "Make out with ${playerList[random(1, numOfPlayers)]} for 5 seconds",
        "Do a missionary dry hump to ${playerList[random(1, numOfPlayers)]}",
        "Do a cowboy dry hump to ${playerList[random(1, numOfPlayers)]}",
        "Do a doggy style dry hump to ${playerList[random(1, numOfPlayers)]}",
        "If you're a male player, drink 3 sips",
        "If you're a female player, drink 3 sips",
        "give a finger blowjob to ${playerList[random(1, numOfPlayers)]}",
        "Make 1 rule, the first to break the rule have to drink 3 sips",
        "Let ${playerList[random(1, numOfPlayers)]} give a dare to you, if you refuse to do the dare, drink 3 sips",
        "Twerk for 10 seconds",
        "Take off your pants for 2 rounds (you can still use your underwear)",
        "Drink 2 sips for everyone who have fucked someone they regret",
        "Drink 1 sip if anyone ever regret kissing someone",
        "Tell everyone your favorite sex story",
        "Tell everyone the craziest place you have had sex at",
        "Leave a s*xy voice note to your ex",
        "Call your crush and tell (him/her) that you like (him/her)",
        "Pick a song, and do a dance like you would do in a club",
        "Stand up and sing a song",
        "For the next two rounds, everytime ${playerList[random(1, numOfPlayers)]} drink, you drink the same amount",
        "Girls, take off your bra",
        "Show your tits to ${playerList[random(1, numOfPlayers)]} for 5 seconds",
        "Take off your shirt",
        "Lick ${playerList[random(1, numOfPlayers)]}'s neck",
        "Lick ${playerList[random(1, numOfPlayers)]}'s nipple",
        "Give a little kiss to ${playerList[random(1, numOfPlayers)]}",
        "PASS CARD (You can use this card to skip a challenge)",
        "JAIL TIME (Do everyone's dare this round)",
        "If you are a guy, take the L and go home",
        "let ${playerList[random(1, numOfPlayers)]} squeeze your ass",
        "Pick a person, lick one of (his/her) toe",
        "Give the person next to you a foot massage for 1 minute",
        "Reveal the person you are most sexually attracted with in this game",
        "Who is the most attractive person in this game according to you?",
        "Reveal your fetish",
        "Vote on who's the ugliest player, they have to take 2 sips",
        "Reveal someone else's secret",
        "The players that are smoking, take 2 sips",
        "Do 10 push ups",
        "Pick a person, that person has to drink 2 sips",
        "The last one to stand up take 2 sips",
        "Act like you are masturbating",
        "Make a joke, if no one laugh take a sip",
        "If anyone have been handcuffed, take 2 sips",
        "Chug a whole glass",
        "If you ever paid someone to fuck, take 2 sips",
        "If you have cheated before, take 2 sips",
        "Share your cheating experience",
        "Act like you are giving a blowjob to ${playerList[random(1, numOfPlayers)]}",
        "For everyone that are currently in a relationship, drink 2 sips",
        "The first person to pull out a condom can give out 2 penalties",
        "Whoever removes two items of clothing the fastest can give out 5 penalties",
        "Take 2 sips if you fucked in the last 3 days",
        "Take a shot if you've ever cheated on a significant other",
        "Take a shot if you've ever kissed someone of the same gender",
        "Take a shot if you've ever gone skinny dipping",
        "Do your best \"s*xy crawl\"",
        "Do your best fake orgasm while looking at ${playerList[random(1, numOfPlayers)]} in the eye",
        "Give a lap dance to someone of your choice",
        "Send a shirtless selfie to someone you’ve slept with",
        "Go around the room and guess everyone’s kinks",
        "Demonstrate your best oral s*x move on the nearest appropriate object")

        if (deck == "FUN WITH FRIENDS"){
            return funWithFriendsDeck
        }
        else if (deck == "18+"){
            return eighteenPlusDeck
        }
        else {
            return funWithFriendsDeck
        }
    }

    fun random(from: Int, to: Int) = (Math.random() * (to - from) + from).toInt()
}