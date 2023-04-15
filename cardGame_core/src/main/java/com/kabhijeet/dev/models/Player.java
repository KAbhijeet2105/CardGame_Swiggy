package com.kabhijeet.dev.models;

import java.util.ArrayList;


//we will be having multiple player upto 4. // min players: 2, max players : 4
public class Player {

    private int player_id;

    // each player will have their cards in hand.
    private ArrayList<Card> cards = new ArrayList<>();

    public Player() {
    }

    public Player(int player_id) {
        this.player_id = player_id;
    }


    //getters and setters.

    /*
    * add card : adding cards in player hand. if he has to drawn from deck in conditions,
    *  like his cards not matching with top of discard deck. or any player use action card.
    *
    * remove card: if any player's card is matching with top of discard deck.
    *               i.e. player used the card
    *
    *
    * */

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }


    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    //class to string method
    @Override
    public String toString() {
        return "Player{" +
                "player_id=" + player_id +
                ", cards=" + cards +
                '}';
    }
}
