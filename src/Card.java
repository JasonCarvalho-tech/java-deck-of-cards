import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Card {
    private final Rank rank;
    private final Suit suit;

    private final static Map<String, Card> CARD_CACHE = initCache();

    private static Map<String, Card> initCache() {
        final Map<String, Card> cache = new HashMap<>();
        for(final Suit suit : Suit.values()){
            for (final Rank rank : Rank.values()) {
                cache.put(cardKey(rank, suit), new Card(rank, suit));
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static String cardKey(final Rank rank, final Suit suit) {
        return rank + "  of " + suit;
    }

    private Card(final Rank rank,
                 final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString(){
        return  String.format("%s of %s", this.rank, this.suit);
    }

    public static Card getCard(final Rank rank, final Suit suit){
        final Card card = CARD_CACHE.get(cardKey(rank,suit));

        if(card != null){
            return card;
        }

        throw new RuntimeException("Invalid card " + rank + suit.toString());
    }

    enum Suit {
        DIAMONDS,
        CLUBS,
        HEARTS,
        SPADES;

        @Override
        public String toString(){
            if (this == Suit.CLUBS) {
                return "Clubs";
            } else if (this == Suit.DIAMONDS){
                return "Diamonds";
            } else if (this == Suit.HEARTS) {
                return "Hearts";
            } else if (this == Suit.SPADES) {
                return "Spades";
            }
            return "";
        }
    }

    enum Rank {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(14);

        private final int rankValue;

        Rank(final int rankVlaue){
            this.rankValue = rankVlaue;
        }
    }
}
