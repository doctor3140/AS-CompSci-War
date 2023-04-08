import java.util.ArrayList;
import java.util.Random;
//import java.lang.Math;

public class Deck {
    private ArrayList<Card> cardDeck = new ArrayList<Card>();
    private String[] face = {"Ace", "King", "Queen", "Jack"};
    private int[] value = {14, 13, 12, 11};
    
    private String[] type = {" of Spades", " of Clubs", " of Hearts", " of Diamonds"};
    
    
    public Deck(){
        //numbered cards
        for(int t=0; t<type.length; t++){
            for(int n=2; n<11; n++){
                //Card ""+n+type[t] = new Card(""+n+type[t], n);
                cardDeck.add(new Card(""+n+type[t], n));
            }
        }
            
        for(int t=0; t<type.length; t++){
            for(int f=0; f<face.length; f++){
                //Card f+type[t] = new Card(f+type[t], value[f]);
                cardDeck.add(new Card(face[f]+type[t], value[f]));
            }
        }
    }
    
    public void printDeck(){
        for(int i=0; i<cardDeck.size(); i++){
            System.out.println(cardDeck.get(i).getName());
        }
    }

    private void move(int locStart, int locEnd){
        cardDeck.add(locEnd, cardDeck.get(locStart));
        cardDeck.remove(locStart);
    }
    
    public void moveBack(){
        move(0, cardDeck.size()-1);
    }
    
    public void shuffle(){
        for(int j=0; j<52; j++){
        for(int i=0; i<52; i++){
        double randomNum = Math.random()*(52-i);
        int randomInt = (int)randomNum;
            move(i, randomInt+i);
        }
        }
    }
    
    public Card getNextCard(){
        return cardDeck.get(0);
    }
    
    public Card getNextCard(int index){
        return cardDeck.get(index);
    }
    
    public void addCard(Card card){
        cardDeck.add(card);
    }
    
    public void giveCards(Deck other){
        other.addCard(getNextCard());
        other.move(0, other.getSize()-1);
        cardDeck.remove(0);
    }
    
    public int getSize(){
        return cardDeck.size();
    }
    
    public void printCard(int index){
        System.out.println(cardDeck.get(index).getName());
    }
    
    public void death(){
        for(int i=0; i<3;i++){
            cardDeck.remove(0);
        }
    }
    
    //returns true if player's deck is zero, meaning they have lost the game
    public boolean lose(){
        if(cardDeck.size()==0)
            return true;
        else
            return false;
    }
}        //removeAll();