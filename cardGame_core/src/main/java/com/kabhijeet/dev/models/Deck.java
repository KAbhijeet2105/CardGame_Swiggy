package com.kabhijeet.dev.models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck; //deck is collection of cards.
    //we will create deck when game starts and will shuffle the cards/deck.


    //creating the deck.
    public Deck() {

        deck = new ArrayList<Card>();
//add cards in deck.
        for (Suits st : Suits.values())
        {
            for(int i = 1; i < 14; i++)
            {
                deck.add(new Card(i,st));
            }
        }

            //shuffle the deck.
        Collections.shuffle(deck);

    }

    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }
}
