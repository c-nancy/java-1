import java.util.*;
/**
 * A class to play a simple board game.
   */  
public class Game
{
    private Player P1;
    private Player P2;
    private Dice roll;
    
	public static void main(String[] args) {
		Game newGame = new Game();
		newGame.start();
		}
    
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        P1 = new Player();
        P2 = new Player();
        roll = new Dice();
    }
    
    /** 
     * Each player roll a dice to play a round of game
     */
    public void rollDice()
    {
        int move1 = 0;
        int move2 = 0;
        move1 = roll.rollDice();
        move2 = roll.rollDice();
        int position1 = 0;
        int position2 = 0;
        int lastPosition1 = 0;
        int lastPosition2 = 0;
        lastPosition1 = P1.getPosition();
        lastPosition2 = P2.getPosition();
        if (lastPosition1 <= 50 &&
            lastPosition2 <= 50)
        {
            position1 = move1 + lastPosition1;
            P1.setPosition(position1);
            if ((position1 / 10) == (position1 % 10)  && 
				position1 < 50)
            {    
                position1 = position1 - 5;
                P1.setPosition(position1);
                System.out.print(P1.getName() + " rolled a " + move1 + ", and moves from position ");
                System.out.println(lastPosition1 + " to " + position1 + " (penality)");
            }
            else
            {
                System.out.print(P1.getName() + " rolled a " + move1 + ", and moves from position ");
                System.out.println(lastPosition1 + " to " + position1);
            }
            position2 = move2 + lastPosition2;
            P2.setPosition(position2);
            if ((position2 / 10) == (position2 % 10) && 
				position2 < 50)
            {    
                position2 = position2 - 5;
                P2.setPosition(position2);
                System.out.print(P2.getName() + " rolled a " + move2 + ", and moves from position ");
                System.out.println(lastPosition2 + " to " + position2 + " (penality)");
            }
            else
            {
                System.out.print(P2.getName() + " rolled a " + move2 + ", and moves from position ");
                System.out.println(lastPosition2 + " to " + position2);
            }
        }
        winnerCheck(P1.getPosition(), P1.getName());
        winnerCheck(P2.getPosition(), P2.getName());
    }
    
    /**
     * Display the game menu
     */
    public void displayMenu()
    {
        System.out.println('\n' + "Welcome to the Simple Board Game");
        System.out.println("============================");
        System.out.println("(1) Start/Restart a Game ");
        System.out.println("(2) Play One Round");
        System.out.println("(3) Display Players' Positions");
        System.out.println("(4) Display Game Help");
        System.out.println("(5) Exit Game");
        System.out.println("Choose an option to continue : ");
    }
    
    /**
     * Displays the name of winner(s)
     */
    public void displayWinner()
    {
        String winner = "";
        if (P1.getPosition() >= 50 &&
            P2.getPosition() >= 50)
            winner = P1.getName() + " and " + P2.getName();
        else if (P1.getPosition() >= 50 &&
                 P2.getPosition() < 50) 
            winner = P1.getName();
        else if (P1.getPosition() < 50 &&
                 P2.getPosition() >= 50)
            winner = P2.getName();   
        
        if (!winner.equals(""))
        {
            System.out.println("Game finished. Choose option (1) to start a new game");
            System.out.println("The last game was won by Player " + winner);
        }
    }
    
    /**
     * Displays some brief instructions on how to play the game.
     */
    public void gameHelp()
    {
        System.out.println("================================================================");
        System.out.println("*** Welcome to the Simple Board Game ***");
        System.out.println("This is the instruction on how to play." + '\n');
        System.out.println("In the game, players take turn to roll a dice, and move");
        System.out.println(" several steps forward corresponding to the rolling points.");
        System.out.println("If any player reaches the special positions 11/22/33/44,");
        System.out.println(" he will be penalised by having 5 subtracted from current position.");
        System.out.print("The winner is the one who reaches a final position");
        System.out.println("of 50 on the board first.");
        System.out.println('\n' + '\n' + "Option (1)");
        System.out.println("To start the game, set two new players for it.");
        System.out.println("User name can be letters or numbers, but could not be blank.");
        System.out.println("The game could also be restarted by choosing this option.");
        System.out.println( '\n' + "Option (2)");
        System.out.println("Take turn to roll the dice");
        System.out.println(" (Only available when players have already been set up).");
        System.out.println("Players move into new position according to the rolling points.");
        System.out.println("After each round, recent position of players would be dispalyed.");
        System.out.println("A player is considered a winner if he reaches position 50 or more.");
        System.out.println("Players could not roll again when the game was already finished.");
        System.out.println('\n' + "Option (3)");
        System.out.println("Displays the current positions of both players");
        System.out.println(" (Only available when players have already been set up).");
        System.out.println( '\n' + "Option (4)");
        System.out.println("Some brief instruction on how to play the game.");
        System.out.println( '\n' + "Option (5)");
        System.out.println("Finish the game and exit this program.");
        System.out.println("================================================================");
        System.out.println("");
    }
    
    /**
     * Get the option chose by player.
     */
    public void getOption()
    {
        String option = "";
        boolean loop = true;
        while (loop != false)
        {
            displayMenu();
            Scanner sc1 = new Scanner(System.in);
            option = sc1.nextLine();
            switch (option)
            {
                case "1":
                    if (playerSet() == true)
                    {
                        setPlayer1();
                        setPlayer2();
                        playerExist();
                    }
                    break;
                case "2":
                    if (playerExist() == true)
                    {
                         if (P1.getPosition() >= 50 ||
                             P2.getPosition() >= 50)
                             displayWinner();
                         else
                             rollDice();
                    }
                    break;
                case "3":
                    if (playerExist() == true)
                    {
                        getPosition();                
                        if (P1.getPosition() >= 50 ||
                            P2.getPosition() >= 50)
                            displayWinner();
                    }
                    break;
                case "4":
                    gameHelp();
                    break;
                case "5":
                    System.out.println("Do you want to exit game? (Y/N)");
                    Scanner sc4 = new Scanner(System.in);
                    String sw0 = sc4.nextLine();
                    if (sw0.toUpperCase().equals("Y"))
                    {
                        loop = false;
                        System.out.println('\n' + "Thank you for participating.");
                        System.out.println("Bye!");
                    }
                    else if (!sw0.toUpperCase().equals("N"))
                        System.out.println("Error : Invalid option!");
                    break;
                default:
                    System.out.println("Error : Invalid option!");
                    break;
            }
        }
    }
    
    /**
     * Display the current position of players.
     */
    public void getPosition()
    {
        int positionP1 = 0;
        int positionP2 = 0;
        positionP1 = P1.getPosition();
        positionP2 = P2.getPosition();
        System.out.println("Player " + P1.getName() + " is on position " + positionP1);
        System.out.println("Player " + P2.getName() + " is on position " + positionP2);
    }
    
    /**
     * To check if player(s) has already been set up.
     */
    public boolean playerExist()
    {
        boolean exist = true;
        if (P1.getName().equals("") ||
            P2.getName().equals(""))
            {
                System.out.println("Error : Player(s) has not been set up!");
                exist = false;
            }
        else
            exist = true;
        return exist;
    }
    
    /**
     * For players to decide whether to set new players or not.
     */
    public boolean playerSet()
    {
        boolean reset = true;
        if (!P1.getName().equals("") &&
            !P2.getName().equals("") && 
            P1.getPosition() <= 50 && 
            P2.getPosition() <= 50)
        {
            System.out.println("Players have already been set up.");
            System.out.println("Do you want to restart game? (Y/N)");
            Scanner sc = new Scanner(System.in);
            String sw = sc.nextLine();
            if (sw.toUpperCase().equals("N"))
               reset = false;
            else if (sw.toUpperCase().equals("Y"))
               reset = true;
            else
            {
               System.out.println("Error : Invalid option!");
               reset = false;
            }
        }
        return reset;
    }
    
    /**
     * Set the first player.
     */
    public void setPlayer1()
    {
        System.out.println("Enter First Player's Name: ");
        Scanner sc2 = new Scanner(System.in);
        String player1 = "";
        player1 = sc2.nextLine().trim();
        P1.setName(player1);
        P1.setPosition(0);
    }
    
    /**
     * Set the second player.
     */
    public void setPlayer2()
    {
        System.out.println("Enter Second Player's Name: ");
        Scanner sc3 = new Scanner(System.in);
        String player2 = "";
        player2 = sc3.nextLine().trim();
        P2.setName(player2);
        P2.setPosition(0);
    }
    
    /**
     * To start the game.
     */
    public void start()
    {
        P1.setName("");
        P1.setPosition(0);
        P2.setName("");
        P2.setPosition(0);
        getOption();
    }
    
    /**
     * Check if there is a winner.
	 * @param winPosition  the recent position of the player to be checked
	 * @param winName  the name of the player to be checked
     */
    public void winnerCheck(int winPosition, String winName)
    {
        if (winPosition >= 50)
            System.out.println("** Congratulations, " + winName + " have WON this game. **");
    }
}





class Dice
{
    private int point;
	private int face;
    
    /**
     * Constructor for objects of class Dice.
     */
    public Dice()
    {
        point = 0;
		face = 6;
    }
    
    /**
     * To roll a dice.
     */
    public int rollDice()
    {
        point = (int)(Math.random() * face+ 1);
        return point;
    }   
}


/*
* The player class
*/
class Player 
{
    private String name;
    private int position;
    
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        name = "";
        position = 0;
    }
    
    /**
     * Get the name of player object.
	 * return  the name of player object
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get the position of player object.
	 * return  the position of player object
     */
    public int getPosition()
    {
        return position;
    }
    
    /**
     * Set the name of player object.
	 * @param newName   the name of player to set
     */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /**
     * Set the position of player object.
	 * @param newPosition  the position of player to set
     */
    public void setPosition(int newPosition)
    {
        position = newPosition;
    }
}