import java.util.Collections;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.IntStream;

public class Deck {
    private final Stack<Card> deckCards;
    private Deck(){
        this.deckCards = initDeck();
    }

    private Stack<Card> initDeck() {

        final Stack<Card> deckCards = new Stack<>();

        for (final Card.Suit suit : Card.Suit.values()) {
            for (final Card.Rank rank : Card.Rank.values()){
                deckCards.push(Card.getCard(rank, suit));
            }
        }
        Collections.shuffle(deckCards);
        return deckCards;
    }

    public static void main(String args[]) {
        final Deck deck = new Deck();
        final int numCardsToDeal = 52;
        IntStream.range(0, 60).forEach( v ->{
                Optional<Card> card = deck.deal();
                if (card.isPresent()){
                    System.out.println(card.get());
                } else {
                    System.out.println("All cards taken");
                }
        });
    }

    public Optional<Card> deal(){
        return this.deckCards.empty() ? Optional.empty() :
                Optional.of(this.deckCards.pop());
    }
}
