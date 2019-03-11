import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BattleUnit {
	
	/*
	 * This test ensures that the translateX method is able to take in a string and pull out the character representing
	 * the spot on the X Axis and translate it to a int for the board[][] arrays. It returns 11 if the character does
	 * not fit in the arrays.
	 */
	@Test
	public void xConversion(){
		assertEquals(1, Board.translateX("A1"));
		assertEquals(11, Board.translateX("X1"));
		assertEquals(11, Board.translateX("11"));
	}
	
	/*
	 * This test ensures that the translateY method is able to take in a string and pull out the character representing 
	 * the spot on the Y Axis and translate it to an int for the board[][] arrays.
	 */
	@Test
	public void yConversion(){
		assertEquals(1, Board.translateY("A1"));
		assertEquals(12, Board.translateY("A12"));
		assertEquals(12, Board.translateY("A!"));
	}

	/*
	 * Tests that the .getSunk() method accurately sinks a ship after the correct number of hits
	 */
	@Test public void testGetSunk(){
		Ship ship = new Ship(3, "T", "Test Ship");
		
		assertEquals(false, ship.getSunk());
		
		ship.hit();
		ship.setSunk();
		assertEquals(false, ship.getSunk());

		ship.hit();
		ship.setSunk();
		assertEquals(false, ship.getSunk());

		ship.hit();
		ship.setSunk();
		assertEquals(true, ship.getSunk());
	}
	
	
	/*
	 * This test simulates a player who has had all 5 of his ships sunk and then tests the allSunk() method to see if
	 * it returns true.
	 */
	@Test
	public void testAllSunk(){
		Player player = new Player("Nick");
		
		for(int i = 1; i < player.aircraftCarrier.getLength() + 1; i++){
			player.aircraftCarrier.hit();
			player.aircraftCarrier.setSunk();
		}
		
		for(int i = 1; i < player.battleship.getLength() + 1; i++){
			player.battleship.hit();
			player.battleship.setSunk();
		}
		
		for(int i = 1; i < player.cruiser.getLength() + 1; i++){
			player.cruiser.hit();
			player.cruiser.setSunk();
		}
		
		for(int i = 1; i < player.destroyer.getLength() + 1; i++){
			player.destroyer.hit();
			player.destroyer.setSunk();
		}
		
		for(int i = 1; i < player.submarine.getLength() + 1; i++){
			player.submarine.hit();
			player.submarine.setSunk();
		}
		
		assertEquals(true, player.allSunk(player));
	}
	
	/**
	 * Tests the addShips() method. When prompted go to the green comments below and type in the items below separated
	 * by an enter between each line.
	 */
	@Test
	public void testAddShips(){
	Player player = new Player("Nick");
	
	/*
	 * A1
	 * 2
	 * A2
	 * 2
	 * A3
	 * 2
	 * A4
	 * 2
	 * A5
	 * 2
	 */
	player.addShips();
	
	assertEquals("A", player.board.getBoardArray()[1][1]);
	assertEquals("C", player.board.getBoardArray()[1][2]);
	assertEquals("B", player.board.getBoardArray()[1][3]);
	assertEquals("D", player.board.getBoardArray()[1][4]);
	assertEquals("S", player.board.getBoardArray()[1][5]);
	}
	
	/**
	 * Tests the fire() method to make sure that when an empty space on the board is fired on, "M" is returned 
	 */
	@Test
	public void testFire(){
		Player turn = new Player("Nick");
		Player opponent = new Player("Opponent");
		
		/*
		 * When prompted type the below and hit enter
		 * A1
		 */
		turn.fire(opponent, turn);
		assertEquals("M", opponent.board.getBoardArray()[1][1]);
	}
	
	/**
	 * Tests the changeCoord() method by updating a coordinate with the string "B" and checking that the board is updated
	 * properly
	 */
	@Test
	public void testChangeCoord(){
		Board board = new Board();
		
		board.changeCoord(1, 1, "B");
		
		assertEquals("B", board.getBoardArray()[1][1]);
	}
	
	/**
	 * Tests checkValid() method by entering a valid space and an invalid space
	 */
	@Test
	public void testCheckValid(){
		Board board = new Board();
		
		assertEquals(true, board.checkValid(1, 1, board.getBoardArray()));
		assertEquals(false, board.checkValid(12, 1, board.getBoardArray()));
	}
	
	/**
	 * Tests the checkUp() method by entering a ship and coordinates that pass, and a ship and coordinates that do not
	 * pass
	 */
	@Test
	public void testCheckUp(){
		Board board = new Board();
		
		assertEquals(true, board.checkUp(3, 1, 3, board.getBoardArray()));
		assertEquals(false, board.checkUp(2, 1, 3, board.getBoardArray()));
	}
	
	/**
	 * Tests the checkDown() method by entering a ship and coordinates that pass, and a ship and coordinates that do not
	 * pass
	 */
	@Test
	public void testCheckDown(){
		Board board = new Board();
		
		assertEquals(true, board.checkDown(8, 1, 3, board.getBoardArray()));
		assertEquals(false, board.checkDown(9, 1, 3, board.getBoardArray()));
	}


}
