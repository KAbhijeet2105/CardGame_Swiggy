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






        System.out.println("ok");

    }



}
