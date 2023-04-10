import java.util.Scanner;
//import java.util.concurrent.TimeUnit;


public class War
{
    public static void main(String[] args)
    throws InterruptedException{
        int wars=0;
        int deaths=0;
        int cardsLost=0;
        int cardsGained=0;
        int turns=0;
        
        //int tests=0;
        
        String[] cliArgs = {"--help", "-h", "--rules","--setTime", "--worksCited"};
        
        //if((args.length>0)&&(args[0].equals(cliArgs))){
            
	if((args.length>0)&&((args[0].equals("--help"))||(args[0].equals("-h")))){
		System.out.println("This a simulation of the card game War. To start the game simply run the game without the --help or -h parameter or with another valid parameter and press enter to begin.");
        }

	else if((args.length>0)&&(invalidArg(args, cliArgs))){
		System.out.println("Try using the -help or -h argument for more options. EX: java War --help");
	}
        
        else{
	int time=1;
	if((args.length>0)&&(indexArg(args, "--setTime")!=-1))
		time=Integer.valueOf(args[indexArg(args, "--setTime")+1]);
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
        System.out.println("Press Enter to Play War: ");
        String NA = input.nextLine();
        
        while(!mine.lose()&&!theirs.lose()){
            //Card mineCurrent = mine.getNextCard();
            //Card theirsCurrent = theirs.getNextCard();
            System.out.println("You Drew a "+mine.getNextCard().getName());
            suspense(time);
            System.out.println("Opponent Drew a "+theirs.getNextCard().getName());
            
            if(theirs.getNextCard().compare(mine.getNextCard())){
                System.out.println("You lost the Exchange\n");
                mine.giveCards(theirs);
                cardsLost++;
                turns++;
            }
            else if(mine.getNextCard().compare(theirs.getNextCard())){
                System.out.println("You won the Exchange\n");
                theirs.giveCards(mine);
                cardsGained++;
                turns++;
            }
            else if((theirs.getSize()>2)&&(mine.getSize()>2)){
                System.out.println("WAR!!");
                wars++;
                System.out.println("You Drew a "+mine.getNextCard(1).getName());
                suspense(time);
                System.out.println("Opponent Drew a "+theirs.getNextCard(1).getName());
                if(theirs.getNextCard(1).compare(mine.getNextCard(1))){
                    System.out.println("You lost the Exchange\n");
                    mine.giveCards(theirs);
                    mine.giveCards(theirs);
                    mine.giveCards(theirs);
                    cardsLost+=3;
                    turns++;
                }
                else if(mine.getNextCard(1).compare(theirs.getNextCard(1))){
                    System.out.println("You won the Exchange\n");
                    theirs.giveCards(mine);
                    theirs.giveCards(mine);
                    cardsGained+=3;
                    turns++;
                }
                else{
                    System.out.println("Death Claims the Battlefield...\n");                    
                    theirs.death();
                    mine.death();
                    deaths++;
                    cardsLost+=3;
                    turns++;
                }
                }
            else{
                System.out.println("Cannot fight");
                //tests++;
                break;
            }
                
            System.out.println("Your Cards: "+mine.getSize());
            System.out.println("Their Cards: "+theirs.getSize());
            
        }
        if(theirs.lose()||(mine.getSize()>theirs.getSize())){
            suspense(700);
            System.out.println("You Win!");
        }
        else if(mine.lose()||(theirs.getSize()>mine.getSize())){
            suspense(700);
            System.out.println("You Lose!");
        }
        else{
            suspense(700);
            System.out.println("War has claimed both sides");
        }
        System.out.print("Stats: ");
        suspense(500);
        System.out.print("Cards lost: "+cardsLost+"\nCards Gained: "+cardsGained+"\nWars: "+wars+"\nDeaths: "+deaths+"\nTotal Turns: "+turns);
        suspense(500);
        System.out.print("\nWar");
        suspense(700);
        System.out.print("war never changes");
        suspense(700);
        //System.out.print(tests);
        }
        }
    public static void suspense(int time)
    throws InterruptedException{
        //int time = 10; //500 default
        for(int i=0; i<3;i++){
        Thread.sleep(time);
        System.out.print(".");
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
