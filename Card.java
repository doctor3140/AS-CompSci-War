public class Card {
    
    private String name;
    private int rank;
    
    public Card(String name, int rank){
        //in war cards only require rank
        this.name = name;
        this.rank = rank;
    }
    
    public int getRank(){
        return rank;
    }
    
    public String getName(){
        return name;
    }
    //returns true if first card is higher rank than param card
    public boolean compare(Card other){
        return getRank()>other.getRank();
    }
    
    //@param Card 1, Card 2
    //return the number of the card with the highest rank, or 3 if they are equal, and -1 as an error
    public static int compare(Card c1, Card c2){
        if(c1.getRank()>c2.getRank())
            return 1;
        else if(c2.getRank()>c1.getRank())
            return 2;
        else if(c2.getRank()==c1.getRank())
            return 3;
        else
            return -1;
    }
    
}