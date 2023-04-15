package com.kabhijeet.dev.models;

public class Card {

    private int num; //card number
    private Suits suit;

    public Card(int num, Suits suit) {
        this.num = num;
        this.suit = suit;
    }

    public Card() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Suits getSuit() {
        return suit;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "number = " + num +
                ", suit = " + suit +
                '}';
    }
}
