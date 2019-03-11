import java.util.Scanner;

public class Player {

	Board board = new Board();
	String name;
	Scanner scan = new Scanner(System.in);

	Ship aircraftCarrier = new Ship(5, "A", "Aircraft Carrier");
	Ship battleship = new Ship(4, "B", "Battleship");
	Ship cruiser = new Ship(3, "C", "Cruiser");
	Ship destroyer = new Ship(2, "D", "Destroyer");
	Ship submarine = new Ship(3, "S", "Submarine");

	/**
	 * Creates a new player object
	 * @param name
	 */
	public Player(String name){
		this.name = name;


		board.getBoard();
	}
	
	/**
	 * Returns the player's name
	 * @return
	 */
	public String getName(){
		return name;
	}

	public void addShips(){
		System.out.println("Add your ships to the board");
		System.out.println("Ship 1 " + aircraftCarrier.getShipName() + " (" + aircraftCarrier.getLength() + " spaces)");
		board.placeShip(aircraftCarrier, board.getBoardArray());
		board.getBoard();

		System.out.println("Ship 2 " + cruiser.getShipName() + " (" + cruiser.getLength() + " spaces)");
		board.placeShip(cruiser, board.getBoardArray());
		board.getBoard();

		System.out.println("Ship 3 " + battleship.getShipName() + " (" + battleship.getLength() + " spaces)");
		board.placeShip(battleship, board.getBoardArray());
		board.getBoard();

		System.out.println("Ship 4 " + destroyer.getShipName() + " (" + destroyer.getLength() + " spaces)");
		board.placeShip(destroyer, board.getBoardArray());
		board.getBoard();

		System.out.println("Ship 5 " + submarine.getShipName() + " (" + submarine.getLength() + " spaces)");
		board.placeShip(submarine, board.getBoardArray());
		board.getBoard();



	}

	public void fire(Player opponent, Player turn){
		String coordinates;
		int x, y;
		boolean check = false;

		System.out.println("Enter the coordinates you would like to fire ");

		do{
			coordinates = scan.next();
			x = Board.translateX(coordinates);
			y = Board.translateY(coordinates);
			check = Board.checkValid(x, y, opponent.board.getBoardArray());
			if(check == false){
				System.out.println("Not a valid coordinate, try again: ");
			}
		}while(check == false);

		if(opponent.board.getBoardArray()[x][y] == "."){
			opponent.board.changeCoord(x, y, "M");
			System.out.println("You missed");
			turn.board.changeMysteryCoord(x, y, "M");
		}else if(opponent.board.getBoardArray()[x][y] == "M"){
			System.out.println("You already guessed that space, miss a turn");
		}else if(opponent.board.getBoardArray()[x][y] == "H"){
			System.out.println("You already hit a ship there, miss a turn");
		}	

		Player.hit(opponent, x, y, turn);
	}



/**
 * Takes in a player who is being fired on, an x coordinate and a y coordinate as an int, and another player who's 
 * turn it is. Check's the opponents board to see what the player who's turn it is hit. Updates the ship cooresponding
 * to the string in the opponents board[][] array.
 * @param opponent
 * @param x
 * @param y
 * @param turn
 */
	public static void hit(Player opponent, int x, int y, Player turn){


		if(opponent.board.getBoardArray()[x][y] == "A"){
			opponent.board.changeCoord(x, y, "H");
			System.out.println("You hit your opponents's Aircraft Carrier");
			opponent.aircraftCarrier.hit();
			opponent.aircraftCarrier.setSunk();
			turn.board.changeMysteryCoord(x, y, "A");
			Player.singleSunk(opponent.aircraftCarrier);
		}else if(opponent.board.getBoardArray()[x][y] == "B"){
			opponent.board.changeCoord(x, y, "H");
			System.out.println("You hit your opponents's Battleship");
			opponent.battleship.hit();
			opponent.battleship.setSunk();
			turn.board.changeMysteryCoord(x, y, "B");
			Player.singleSunk(opponent.battleship);
		}else if(opponent.board.getBoardArray()[x][y] == "C"){
			opponent.board.changeCoord(x, y, "H");
			System.out.println("You hit your opponents's Cruiser");
			opponent.cruiser.hit();
			opponent.cruiser.setSunk();
			turn.board.changeMysteryCoord(x, y, "C");
			Player.singleSunk(opponent.cruiser);
		}else if(opponent.board.getBoardArray()[x][y] == "D"){
			opponent.board.changeCoord(x, y, "H");
			System.out.println("You hit your opponents's Destroyer");
			opponent.destroyer.hit();
			opponent.destroyer.setSunk();
			turn.board.changeMysteryCoord(x, y, "D");
			Player.singleSunk(opponent.destroyer);
		}else if(opponent.board.getBoardArray()[x][y] == "S"){
			opponent.board.changeCoord(x, y, "H");
			System.out.println("You hit your opponents's Submarine");
			opponent.submarine.hit();
			opponent.submarine.setSunk();
			turn.board.changeMysteryCoord(x, y, "S");
			Player.singleSunk(opponent.submarine);
		}
	}

	/**
	 * Checks if the ship has been sunk and tells the player who's turn it is that it is sunk
	 * @param ship
	 */
	public static void singleSunk(Ship ship){

		if(ship.getSunk() == true){
			System.out.println("You sunk your opponent's " + ship.getShipName());
		}

	}

	/**
	 * Takes in a player and returns a boolean. True if all of the player's ships have been sunk, and false if they have
	 * not all been sunk. If true the game is over.
	 * @param player
	 * @return
	 */
	public boolean allSunk(Player player){
		if(player.aircraftCarrier.getSunk() == true && player.battleship.getSunk() == true && 
				player.cruiser.getSunk() == true && player.destroyer.getSunk() == true
				&& player.submarine.getSunk() == true){
			System.out.println("Game over! " + player.getName() + " loses");
			return true;
		}else{
			return false;
		}
	}



}
