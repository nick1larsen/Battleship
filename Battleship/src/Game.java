import java.util.*;
public class Game {

	static Scanner scan = new Scanner(System.in);
	
/**
 * Battleship
 * Created by: Nick Larsen
 * SEIS 602-01
 * Presentation date: 12/15/16
 * 
 * Description:
 * This program simulates the game of Battleship. It allows two players to secretly place 5 ships on a board. Then 
 * the players take turns guessing coordinates to fire on. If the players guess all of the correct coordinates for a 
 * single ship, then that ship is sunk. The game ends when one of the players sinks all 5 of the other player's ships.
 * @param args
 */
	public static void main(String[] args) {


		String name;
		boolean over = false;

		System.out.println("Welcome to Battleship\n");

		//creating player1
		System.out.print("Please enter the name of Player1: ");
		name = scan.nextLine();
		Player p1 = new Player(name);
		System.out.println("Player1's name is " + p1.getName() + "\n");
		p1.addShips();
		Game.introTransition(p1);

		//creating player 2
		System.out.print("Please enter the name of Player2: ");
		name = scan.nextLine();
		Player p2 = new Player(name);
		System.out.println("Player2's name is " + p2.getName() + "\n");
		p2.addShips();
		Game.introTransition(p2);


		//loop that keeps playing the game while both players have at least one ship that isn't sunk
		while(over == false){
			if(over == false){
				Game.play(p1, p2);
				over = p1.allSunk(p2);
			}
			if(over == false){
				Game.play(p2, p1);
				over = p2.allSunk(p1);
			}
		}
	}
	
	/**
	 * Takes in a Player who's turn it is and is firing and  a Player who's being fired on. Plays one round of 
	 * Battleship. It prints the board before and after the turn. It then calls to transition.
	 * @param turn
	 * @param opponent
	 */
	public static void play(Player turn, Player opponent){
		System.out.println(turn.getName() + "'s turn");
		turn.board.getBoard();
		turn.fire(opponent, turn);
		turn.board.getBoard();
		Game.transition(turn, opponent);
	}

	/**
	 * Takes in a player object and clears the console so the other player does not see the first player's board.
	 * This is only used for the beginning of the game.
	 * @param player
	 */
	public static void introTransition(Player player){
		System.out.println(player.getName() + " is done placing ships. Hit Enter to clear the screen. "
				+ "Then pass the computer");
		scan.nextLine();
		for(int i = 0; i < 40; i ++){
			System.out.println();
		}
		System.out.println("Hit Enter once computer has been passed");
		scan.nextLine();
	}
	
	/**
	 * Takes in two players: the player who's turn it is, and the player who is being fired on (opponent). It prompts the
	 * player who's turn it is to press enter to clear the console of his board, and then give the computer to the second
	 * player for his turn.
	 * @param turn
	 * @param opponent
	 */
	public static void transition(Player turn, Player opponent){
		System.out.println(turn.getName() + "'s turn is over hit Enter to clear the screen");
		scan.nextLine();

		for(int i = 0; i < 40; i ++){
			System.out.println();
		}

		System.out.println("Pass the computer to " + opponent.getName() + ". Then " + opponent.getName() + " press enter");
		scan.nextLine();
	}
}
