import java.util.*;
public class Test2 {
	
	Board board = new Board();
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		

		Player player = new Player("Nick");
		Player opponent = new Player("loser");

		
//		opponent.board.changeCoord(1, 1, "A");
//		opponent.board.changeCoord(1, 2, "B");
//		opponent.board.changeCoord(1, 3, "C");
//		opponent.board.changeCoord(1, 4, "D");
//		opponent.board.changeCoord(1, 5, "S");
//		
//		while(true){
//		opponent.board.getBoard();
//		player.fire(opponent);
//		}
		
		for(int i = 1; i < player.aircraftCarrier.getLength() + 1; i++){
			player.aircraftCarrier.hit();
			player.aircraftCarrier.setSunk();
			System.out.println("hit " + i + " on " + player.aircraftCarrier.getShipName() + " ship is sunk: "
					+ player.aircraftCarrier.getSunk());
			System.out.println("game is over " + player.allSunk(player));
		}
		
		for(int i = 1; i < player.battleship.getLength() + 1; i++){
			player.battleship.hit();
			player.battleship.setSunk();
			System.out.println("hit " + i + " on " + player.battleship.getShipName() + " ship is sunk: "
					+ player.battleship.getSunk());
			System.out.println("game is over " + player.allSunk(player));
		}
		
		for(int i = 1; i < player.cruiser.getLength() + 1; i++){
			player.cruiser.hit();
			player.cruiser.setSunk();
			System.out.println("hit " + i + " on " + player.cruiser.getShipName() + " ship is sunk: "
					+ player.cruiser.getSunk());
			System.out.println("game is over " + player.allSunk(player));
		}
		
		for(int i = 1; i < player.destroyer.getLength() + 1; i++){
			player.destroyer.hit();
			player.destroyer.setSunk();
			System.out.println("hit " + i + " on " + player.destroyer.getShipName() + " ship is sunk: "
					+ player.destroyer.getSunk());
			System.out.println("game is over " + player.allSunk(player));
		}
		
		for(int i = 1; i < player.submarine.getLength() + 1; i++){
			player.submarine.hit();
			player.submarine.setSunk();
			System.out.println("hit " + i + " on " + player.submarine.getShipName() + " ship is sunk: "
					+ player.submarine.getSunk());
			System.out.println("game is over " + player.allSunk(player));
		}
		
		//board.getBoard();

		
	}


}
