package com.kabhijeet.dev.controller;


import com.kabhijeet.dev.models.Card;
import com.kabhijeet.dev.models.Deck;
import com.kabhijeet.dev.models.Player;

import java.util.ArrayList;
import java.util.Scanner;

//this class will contain the main game logic.
public class GamePlay {

    private ArrayList<Card> deck; //card deck

    private ArrayList<Player> players; // min 2 max 4

    private ArrayList<Card> drawPile; //players will draw cards from this deck.

    private ArrayList<Card> discardPile; //all cards will

    Scanner sc;

    public GamePlay() {
        sc = new Scanner(System.in);
    }

    private int inputPlayers()
    {
        int players;
        System.out.println("Enter the number of players : ");
        players = sc.nextInt();

         if(players > 4 || players < 2)
         {
             System.out.println("Invalid number of players! \n minimum 2 players and maximum 4 players are allowed ");
             System.exit(0);
         }

        return players;
    }


    //setting up deck,players,draw and discard piles.
    private void gameSetup(int playerNum)
    {
        deck = new Deck().getDeck();// get shuffled deck of cards.

        players = new ArrayList<>();

            //giving each player 5-5 cards
        for (int itr = 1; itr <= playerNum; itr++ )
        {
            Player p = new Player(itr); //created player with id

            for(int crd= 1; crd <=5; crd++) //drawing last 5 cards and giving to player
            {
                p.addCard(deck.get(deck.size() -1));
                deck.remove(deck.size()-1);
            }

            players.add(p);
        }

        // now the top/first card from deck will act as first card of discard pile

        discardPile = new ArrayList<>();
        discardPile.add(deck.get(0));
        deck.remove(0);

        //now create the draw pile from remaining deck.

        drawPile = new ArrayList<>();

        drawPile.addAll(deck); //we can also use iteration to add cards.

    }


    public void playGame()
    {
        int playersNum = inputPlayers(); //get input number of players from user

        gameSetup(playersNum); // do initializations and setup for the game.

        // now start the game.

        int playerStrike = 0;//we need to acces players from arraylist. so index starts from 0
        int gameFlow = 1; // direction of player strikes its 1 or -1

        int numCardsDrawn = 1; // number of cards drawn from draw pile by one particular player
                                //e.g. if any player used action card Q or J then we will set variable value 2 or 4

        while (true)
        {

            if(drawPile.size() < numCardsDrawn) // pile is not sufficient match draw!
            {
                System.out.println("##########  Result : GAME DRAW! Draw pile empty!   ############");
                System.exit(0);
            }


            playerStrike = playerStrike % playersNum;

            if(playerStrike < 0) // if strike goes in negative value if we change game flow/direction
                playerStrike = playerStrike + playersNum;

            playerStrike = playerStrike % playersNum;


            //now check if current players card and top card of discard pile is matched or not.

            boolean cardMatched = false;
            int   matchNumber = -1;

            Card  topCard_Of_DiscardPile= discardPile.get(discardPile.size()-1); // we are going to try matching with this card.

            System.out.println(" Discard pile top card is : " + topCard_Of_DiscardPile);

            //now we will try to match this card with onStrike players card.

            for( Card strikePlayerCard : players.get(playerStrike).getCards())
            {
                //if discard pile top card and any of players card has same suit or number then its match.
                if(strikePlayerCard.getNum() == topCard_Of_DiscardPile.getNum() || strikePlayerCard.getSuit() == topCard_Of_DiscardPile.getSuit())
                {
                    int top_discard_card_num = topCard_Of_DiscardPile.getNum();
                    //we need to check if top card of discard pile is action card. cause action cards are not stackable
                    if (top_discard_card_num == 1 || top_discard_card_num == 11 || top_discard_card_num == 12 || top_discard_card_num == 13)
                    {
                        // player cannot play same action card even if available so he will skip.
                        if (strikePlayerCard.getNum() == top_discard_card_num)
                            continue;
                    }



                    //let's check if current player needs to draw extra cards because of previous player used some action card

                    if(numCardsDrawn > 1)//prev player used either Q or J
                    {
                        while (numCardsDrawn > 0)
                        {
                            Card drawnCard = drawPile.get(drawPile.size()-1);
                            int currentPlayer = players.get(playerStrike).getPlayer_id();
                            System.out.println("player "+ currentPlayer +" drawing card:"+drawnCard);
                            //add in players hand
                            players.get(playerStrike).addCard(drawnCard);
                            drawPile.remove(drawPile.size()-1);
                            numCardsDrawn--;
                        }
                        numCardsDrawn = 1; //reset the value to 1
                    }


                    //if card is matched then player puts the card in discard pile
                    System.out.println("### Player "+players.get(playerStrike).getPlayer_id()+"s card matched! ###");
                    System.out.println("### Now discard pile top card is : "+strikePlayerCard+" ####");

                    players.get(playerStrike).removeCard(strikePlayerCard);
                    discardPile.add(strikePlayerCard);
                    cardMatched = true;
                    matchNumber = strikePlayerCard.getNum();
                    break;
                }
            }//player card loop ends

            //if card is not matched then current player draws one card from draw pile

            if (cardMatched == false)
            {
                System.out.println("no matching card! player "+players.get(playerStrike).getPlayer_id()+" drawing "+numCardsDrawn+" cards from draw pile");
                    //if previous player used any action card then need to draw more cards

                while (numCardsDrawn > 0)
                {
                    players.get(playerStrike).addCard(drawPile.get(drawPile.size()-1));
                    drawPile.remove(drawPile.size()-1);
                    numCardsDrawn--;
                }
                numCardsDrawn = 1; //reset to 1
            }

            // if current players all cards are finished then he is the winner
            if (cardMatched == true && players.get(playerStrike).getCards().size() == 0)
            {
                System.out.println("### Congratulations player: " + players.get(playerStrike).getPlayer_id() + " won the match! ###");

                System.exit(0);
            }

            // action cards

            //if player used ace then next player strike will be skipped
            if(cardMatched == true && matchNumber == 1)
            {
                playerStrike = playerStrike + gameFlow;
            }

            //king : reverse the direction of game

            if(cardMatched == true && matchNumber == 13)
            {
                gameFlow = gameFlow * -1;
            }

            //Queen : next player needs to draw 2 cards

            if (cardMatched == true && matchNumber == 12) {
                numCardsDrawn = 2;
            }

            //Jack : next player needs to draw 4 cards
            if (cardMatched == true && matchNumber == 11) {
                numCardsDrawn = 4;
            }


            //determine next player's strike

            playerStrike += gameFlow;


            System.out.println("###################################################################################");
        }

    }

}
