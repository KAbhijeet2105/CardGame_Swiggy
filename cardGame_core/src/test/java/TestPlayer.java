import com.kabhijeet.dev.models.Card;
import com.kabhijeet.dev.models.Player;
import com.kabhijeet.dev.models.Suits;
import org.junit.Assert;
import org.junit.Test;

public class TestPlayer {

   //In this test we will check if we are able to add or remove cards for player.

    //add card to player

    @Test
    public void addCardTest()
    {
        Card crd = new Card(1,Suits.HEARTS);
        Player player = new Player(1);
        player.addCard(crd);
        Assert.assertEquals(1,player.getCards().size());
    }

    @Test
    public void removeCardTest()
    {
        Card crd = new Card(1,Suits.HEARTS);
        Player player = new Player(1);
        player.addCard(crd);
        player.removeCard(crd);
        Assert.assertEquals(0,player.getCards().size());
    }

}
