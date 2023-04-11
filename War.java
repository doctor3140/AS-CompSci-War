import java.util.Scanner;

public class War
{
    public static void main(String[] args)
    throws InterruptedException{
        int wars=0;
        int deaths=0;
        int cardsLost=0;
        int cardsGained=0;
	int lackOfCards=0;
        int turns=0;
	int wins=0;
	int loses=0;
	int ties=0;
	int games=1;
        
        String[] cliArgs = {"--help", "-h", "--rules","--setTime", "--worksCited", "--interact", "--setGames", "--noDisplay", "--noSuspense"};
        
            
	if((args.length>0)&&((args[0].equals("--help"))||(args[0].equals("-h")))){
		System.out.println("Usage: java War [args...]");
		System.out.println("--help or -h \t print this help message");
		System.out.println("--rules \t print rules of the game");
		System.out.println("--worksCited \t prints link to sources used for the project");
		System.out.println("--setTime <int>  sets the time inbetween 'suspense dots'");
		System.out.println("--interact \t requires manual inputs for drawing cards");
		System.out.println("--setGames <int> sets the amount of games of war to play");
		System.out.println("--noDisplay \t turns off display for turns");
		System.out.println("--noSuspense \t removes all suspense");
        }

	else if((args.length>0)&&((args[0].equals("--rules")))){
		System.out.println("This a simulation of the card game War. To start the game simply run the game without the --help or -h parameter or with another valid parameter and press enter to begin. The goal of the game is to end with all the cards between both decks. Two of 52 decks are used for this game, each shuffled before play. Each player draws a card and places it down. Whichever player has the higher value card in order <Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2> take back their own card and get the other player's card, returning both to the back of their deck. If the cards tie then 'War' begins. Players draw two more cards and each reveal one. The player with the higher value card will take all 6 cards. If there is another tie all cards will 'die.' This is not a typical rule for War, but results in faster games. One exception for enter 'War' is if a player has less than 3 cards. If this is the case the player with only 3 cards loses. If somehow both players have less than 3 cards the one with more cards win. A tie should only result from both players having equal cards after a failing to go to 'War'. Enjoy and try out other parameters to change the game experience!"); 
	}

	else if((args.length>0)&&((args[0].equals("--worksCited")))){
		System.out.println("https://github.com/doctor3140/AS-CompSci-War/blob/main/README.md");
	}

	else if((args.length>0)&&(invalidArg(args, cliArgs))){
		System.out.println("Try using the -help or -h argument for more options. EX: java War --help");
	}
 
        else{
	int time=1;
	int time2=500;
	int time3=700;

	if((args.length>0)&&(indexArg(args, "--setTime")!=-1)){
		time=Integer.valueOf(args[indexArg(args, "--setTime")+1]);
	}

	if((args.length>0)&&(indexArg(args, "--noSuspense")!=-1)){
		time=0;
		time2=0;
		time3=0;
	}

	if((args.length>0)&&(indexArg(args, "--setGames")!=-1)){
		games=Integer.valueOf(args[indexArg(args, "--setGames")+1]);
	}
	for(int g=0; g<games; g++){
        Deck mine = new Deck();
        //mine.printDeck();
        mine.shuffle();
        //System.out.println("Mine: \n");
        //mine.printDeck();
        //System.out.println("\n"+mine.getSize());
        Deck theirs = new Deck();
        //theirs.printDeck();
        theirs.shuffle();
        //System.out.println("Theirs: \n");
        //theirs.printDeck();
        //System.out.println("\n"+theirs.getSize());
       
	Scanner input = new Scanner(System.in); 
	String n = "";

	
	if((indexArg(args, "--interact")==-1)&&(g<1)){
        	System.out.println("Press Enter to Play War: ");
        	n = input.nextLine();
	} 

        while(!mine.lose()&&!theirs.lose()){
		
		if(indexArg(args, "--interact")!=-1){
        		System.out.println("Press Enter to Draw: ");
			n = input.nextLine();
		} 
		
            //Card mineCurrent = mine.getNextCard();
            //Card theirsCurrent = theirs.getNextCard();
	    if(indexArg(args, "--noDisplay")==-1){
            System.out.println("You Drew a "+mine.getNextCard().getName());
            suspense(time);
            System.out.println("Opponent Drew a "+theirs.getNextCard().getName());
	    }
            if(theirs.getNextCard().compare(mine.getNextCard())){
		if(indexArg(args, "--noDisplay")==-1){
                System.out.println("You lost the Exchange\n");
		}
                mine.giveCards(theirs);
                cardsLost++;
                turns++;
            }
            else if(mine.getNextCard().compare(theirs.getNextCard())){
		if(indexArg(args, "--noDisplay")==-1)
                	System.out.println("You won the Exchange\n");
                theirs.giveCards(mine);
                cardsGained++;
                turns++;
            }
            else if((theirs.getSize()>2)&&(mine.getSize()>2)){
		if(indexArg(args, "--noDisplay")==-1)
                	System.out.println("WAR!!");
                wars++;
		if(indexArg(args, "--noDisplay")==-1){
                System.out.println("You Drew a "+mine.getNextCard(1).getName());
                suspense(time);
                System.out.println("Opponent Drew a "+theirs.getNextCard(1).getName());
		}
                if(theirs.getNextCard(1).compare(mine.getNextCard(1))){
		if(indexArg(args, "--noDisplay")==-1){
                    System.out.println("You lost the Exchange\n");
		}
                    mine.giveCards(theirs);
                    mine.giveCards(theirs);
                    mine.giveCards(theirs);
                    cardsLost+=3;
                    turns++;
                }
                else if(mine.getNextCard(1).compare(theirs.getNextCard(1))){
		if(indexArg(args, "--noDisplay")==-1){
                    System.out.println("You won the Exchange\n");
		}
                    theirs.giveCards(mine);
                    theirs.giveCards(mine);
                    cardsGained+=3;
                    turns++;
                }
                else{
		if(indexArg(args, "--noDisplay")==-1){
                    System.out.println("Death Claims the Battlefield...\n");                    	}
                    theirs.death();
                    mine.death();
                    deaths++;
                    cardsLost+=3;
                    turns++;
                }
	    	}
            else{
		if(indexArg(args, "--noDisplay")==-1)
                	System.out.println("Cannot fight");
                lackOfCards++;
                break;
            }
	if(indexArg(args, "--noDisplay")==-1){
            System.out.println("Your Cards: "+mine.getSize());
            System.out.println("Their Cards: "+theirs.getSize());
	}
        }
        if(theirs.lose()||(mine.getSize()>theirs.getSize())){
	if(indexArg(args, "--noDisplay")==-1){
            suspense(time3);
            System.out.println("You Win!");
	}
	    wins++;
        }
        else if(mine.lose()||(theirs.getSize()>mine.getSize())){
	if(indexArg(args, "--noDisplay")==-1){
            suspense(time3);
            System.out.println("You Lose!");
	}
	    loses++;
        }
        else{
	if(indexArg(args, "--noDisplay")==-1){
            suspense(time3);
            System.out.println("War has claimed both sides");
	}
	    ties++;
        }
	}
        System.out.print("Game Stats: ");
        suspense(time2);
        System.out.print("Cards lost: "+cardsLost+"\nCards Gained: "+cardsGained+"\nWars: "+wars+"\nDeaths: "+deaths+"\nTotal Turns: "+turns);
        suspense(time2);

	if(games>1){
	System.out.println("\n# of Games: "+games);
	System.out.println("Wins: "+wins);
	System.out.println("Loses: "+loses);
	System.out.println("Ties: "+ties);
	suspense(time2);
	System.out.print("Average Stats per Game: ");
        suspense(time2);
        System.out.print("Cards lost: "+(double)cardsLost/games+"\nCards Gained: "+(double)cardsGained/games+"\nWars: "+(double)wars/games+"\nDeaths: "+(double)deaths/games+"\nTurns: "+(double)turns/games);
        suspense(time2);
	}

        System.out.print("\nWar");
        suspense(time3);
        System.out.print("war never changes");
        suspense(time3);
        }
    	}

    public static void suspense(int time)
    throws InterruptedException{
	if(time>0){
        for(int i=0; i<3;i++){
        Thread.sleep(time);
        System.out.print(".");
        }
	}
        System.out.print("\n");
    }

    public static boolean invalidArg(String[] args, String[] cliArgs){
    boolean arg=true;
    	for(int argsI=0; argsI<args.length; argsI++){
	for(int cliArgsI=0; cliArgsI<cliArgs.length; cliArgsI++){
		if(cliArgs[cliArgsI].equals(args[argsI]))
			arg=false;
	}
	}
		return arg;
    }

    public static int indexArg(String[] args, String arg){
    int index=-1;
    	for(int argsI=0; argsI<args.length; argsI++){
		if(arg.equals(args[argsI]))
			index=argsI;
	}
		return index;
    }
}
