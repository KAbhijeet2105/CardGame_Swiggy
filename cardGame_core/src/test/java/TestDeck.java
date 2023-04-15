import com.kabhijeet.dev.models.Card;
import com.kabhijeet.dev.models.Deck;
import com.kabhijeet.dev.models.Suits;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestDeck {

    @Test
    public void checkDeckShuffled() //test for checking deck is shuffled or not.
    {
        //create un-shuffled deck.
        ArrayList<Card> unShuffledDeck = new ArrayList<>();

        for(Suits st : Suits.values()){

            for(int i = 1; i <= 13; i++){
                unShuffledDeck.add(new Card(i,st));
            }
        }

        Deck deck = new Deck(); //shuffled deck

        ArrayList<Card> shuffledDeck = deck.getDeck();

        //now check shuffled and un-shuffled deck is same or not
        //they should not be the same.

        Assert.assertFalse(unShuffledDeck.toString() == shuffledDeck.toString());

    }

}
