import java.util.Scanner;

public class Board {

	Scanner scan = new Scanner(System.in);
	private String gameBoard[][] = new String[11][11];
	private String mysteryBoard[][] = new String[11][11];


	public Board(){

		//sets up the row headings of the board
		gameBoard[1][0] = "A";
		gameBoard[2][0] = "B";
		gameBoard[3][0] = "C";
		gameBoard[4][0] = "D";
		gameBoard[5][0] = "E";
		gameBoard[6][0] = "F";
		gameBoard[7][0] = "G";
		gameBoard[8][0] = "H";
		gameBoard[9][0] = "I";
		gameBoard[10][0] = "J";

		gameBoard[0][0] = " ";

		//sets up the column headings of the board
		for(int i = 1; i < 10; i++){
			Integer myInt = i;
			String s = myInt.toString();
			gameBoard[0][i] = s;
		}
		gameBoard[0][10] = "0";

		//sets an empty board with just .
		for(int x = 1; x < 11; x++){
			for(int y = 1; y < 11; y++){
				gameBoard[x][y] = ".";
			}
		}
		
		//sets up the row for the opponents board
		mysteryBoard[1][0] = "A";
		mysteryBoard[2][0] = "B";
		mysteryBoard[3][0] = "C";
		mysteryBoard[4][0] = "D";
		mysteryBoard[5][0] = "E";
		mysteryBoard[6][0] = "F";
		mysteryBoard[7][0] = "G";
		mysteryBoard[8][0] = "H";
		mysteryBoard[9][0] = "I";
		mysteryBoard[10][0] = "J";

		mysteryBoard[0][0] = " ";

		//sets up the column headings of the opponent's board
		for(int i = 1; i < 11; i++){
			Integer myInt = i;
			String s = myInt.toString();
			mysteryBoard[0][i] = s;
		}
		mysteryBoard[0][10] = "0";

		//sets an empty board with just ? for the opponent's board
		for(int x = 1; x < 11; x++){
			for(int y = 1; y < 11; y++){
				mysteryBoard[x][y] = "?";
			}
		}
	}
		

	/**
	 * Takes in an x and a y coordinate as an int, and a string. Updates the player's board array with the string at those
	 * coordinates
	 * @param x
	 * @param y
	 * @param s
	 */
	public void changeCoord(int x, int y, String s){
		gameBoard[x][y] = s;
	}
	
	/**
	 * TAkes in an x and a y coordinate as an int and a string. Updates the mystery board array with the string at those
	 * coordinates
	 * @param x
	 * @param y
	 * @param s
	 */
	public void changeMysteryCoord(int x, int y, String s){
		mysteryBoard[x][y] = s;
	}

	/**
	 * Returns the players gameboard[][] array.
	 * @return
	 */
	public String[][] getBoardArray(){
		return gameBoard;
	}


	/**
	 * Prints an individual player's board next to the mystery board
	 */
	public void getBoard(){
		System.out.println("      Your board                  Your Opponent's board");
		for(int x = 0; x < 11; x++){
			System.out.println();
			for(int y = 0; y < 11; y++){
				System.out.print(gameBoard[x][y] + " ");
			}
			System.out.print("          ");
			for(int y = 0; y <11; y++){
				System.out.print(mysteryBoard[x][y] + " ");
			}
		}
		System.out.println();
	}

	/**
	 * Places the users ship. Asks the user to re place ship if the coordinates aren't valid
	 * @param ship
	 */
	public void placeShip(Ship ship, String[][] gameBoard){
		String coordinates;
		int x, y, direction;
		boolean check, up, down, left, right;

		System.out.println("Enter the starting coordinate of your ship: ");
		coordinates = scan.next();
		x = Board.translateX(coordinates);
		y = Board.translateY(coordinates);
		check = Board.checkValid(x, y, gameBoard);

		while(check == false){
			System.out.println("Not a valid coordinate, try again: ");
			coordinates = scan.next();
			x = Board.translateX(coordinates);
			y = Board.translateY(coordinates);
			check = Board.checkValid(x, y, gameBoard);
		}

		up = Board.checkUp(x, y, ship.getLength(), gameBoard);
		down = Board.checkDown(x, y, ship.getLength(), gameBoard);
		left = Board.checkLeft(x, y, ship.getLength(), gameBoard);
		right = Board.checkRight(x, y, ship.getLength(), gameBoard);

		System.out.print("Options for placing ship:");
		if(up == true){
			System.out.print("\npress 1 for up ");
		}
		if(down == true){
			System.out.print("\npress 2 for down ");
		}
		if(left == true){
			System.out.print("\npress 3 for left ");
		}
		if(right == true){
			System.out.print("\npress 4 for right ");
		}
		direction = scan.nextInt();

		Board.fillShip(x, y, direction, ship, gameBoard);

	}


	/**
	 * Takes in an x coordinate, a y coordinate, a direction in int form, a ship, and a board array. It then fills
	 * the board with the ships letter in the direction the user requests
	 * @param x
	 * @param y
	 * @param direction
	 * @param ship
	 * @param gameBoard
	 */
	public static void fillShip(int x, int y, int direction, Ship ship, String gameBoard[][]){

		//places the ship up from the starting coordinates
		if(direction == 1){
			for(int i = 0; i < ship.getLength(); i++){
				gameBoard[x-i][y] = ship.getShipLetter();
			}
			//places the ship down from the starting coordinates
		} else if(direction == 2){
			for(int i = 0; i < ship.getLength(); i++){
				gameBoard[x+i][y] = ship.getShipLetter();
			}
			//places the ship left from the starting coordinates
		}else if(direction == 3){
			for(int i = 0; i < ship.getLength(); i++){
				gameBoard[x][y-i] = ship.getShipLetter();
			}
			//places the ship right from the starting coordinates
		}else if(direction == 4){
			for(int i = 0; i < ship.getLength(); i++){
				gameBoard[x][y+i] = ship.getShipLetter();
			}
		}
	}


	/**
	 * Takes in coordinates as a string and translates the X axis value from a letter to an int that can be 
	 * entered into the board array
	 * @param coordinates
	 * @return
	 */
	public static int translateX(String coordinates){
		String letter = coordinates.substring(0,1);
		int x;

		if(letter.equals("A")){
			x = 1;
		} else if(letter.equals("B")){
			x = 2;
		} else if(letter.equals("C")){
			x = 3;
		} else if(letter.equals("D")){
			x = 4;
		} else if(letter.equals("E")){
			x = 5;
		} else if(letter.equals("F")){
			x = 6;
		} else if(letter.equals("G")){
			x = 7;
		} else if(letter.equals("H")){
			x = 8;
		} else if(letter.equals("I")){
			x = 9;
		} else if(letter.equals("J")){
			x = 10;
		} else {
			x = 11;
		}

		return x;
	}

	/**
	 * Takes in coordinates as a string, then removes the x Axis and returns the y axis
	 * @param coordinates
	 * @return
	 */
	public static int translateY(String coordinates){
		String yAxis = coordinates.substring(1);
		int y;

		if(Character.isDigit(yAxis.charAt(0))){
			y = Integer.valueOf(yAxis);
			return y;
		}else{
			y = 12;
			return y;
		}
	}

	/**
	 * Takes in x and y coordinates as ints and checks to make sure they fall within the array
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean checkValid(int x, int y, String[][] gameBoard){

		if(x > gameBoard.length-1){
			return false;
		}else if(y > gameBoard[0].length-1){
			return false;
		}else{
			return true;
		}

	}



	/**
	 * Takes an x and y coordinates as well as the shipLength and checks if the ship direction can be up from the 
	 * starting coordinates
	 * @param x
	 * @param y
	 * @param shipLength
	 * @return
	 */
	public static boolean checkUp(int x, int y, int shipLength, String[][] gameBoard){

		if(x - shipLength < 0){
			return false;
		}else{

			for(int i = 1; i < shipLength; i++){
				if(gameBoard[x-i][y] != "."){
					return false;
				}
			}

			return true;
		}
	}

	/**
	 * Takes an x and y coordinates as well as the shipLength and checks if the ship direction can be down from the 
	 * starting coordinate
	 * @param x
	 * @param y
	 * @param shipLength
	 * @return
	 */
	public static boolean checkDown(int x, int y, int shipLength, String[][] gameBoard){

		if(x + shipLength > 11){
			return false;
		}else{

			for(int i = 1; i < shipLength; i++){
				if(gameBoard[x+i][y] != "."){
					return false;
				}
			}

			return true;
		}
	}

	/**
	 * Takes an x and y coordinates as well as the shipLength and checks if the ship direction can be left from the 
	 * starting coordinate
	 * @param x
	 * @param y
	 * @param shipLength
	 * @return
	 */
	public static boolean checkLeft(int x, int y, int shipLength, String[][] gameBoard){

		if(y - shipLength < 0){
			return false;
		}else{

			for(int i = 1; i < shipLength; i++){
				if(gameBoard[x][y-i] != "."){
					return false;
				}
			}

			return true;
		}
	}

	/**
	 * Takes an x and y coordinates as well as the shipLength and checks if the ship direction can be right from the 
	 * starting coordinates
	 * @param x
	 * @param y
	 * @param shipLength
	 * @return
	 */
	public static boolean checkRight(int x, int y, int shipLength, String[][] gameBoard){
		if(y + shipLength > 11){
			return false;
		}else{

			for(int i = 1; i < shipLength; i++){
				if(gameBoard[x][y+i] != "."){
					return false;
				}
			}

			return true;
		}
	}


}


