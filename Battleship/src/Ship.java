
public class Ship {
	
	private int length;
	int hits;
	boolean sunk = false;
	String shipLetter, shipName;
	
	public Ship(int length, String shipLetter, String shipName){
		this.length = length;
		this.shipLetter = shipLetter;
		this.shipName = shipName;
	}
	
	/**
	 * Returns the name of the ship
	 * @return
	 */
	public String getShipName(){
		return shipName;
	}
	
	
	/**
	 * Increments the number of hits that a battle ship has
	 */
	public void hit(){
		hits++;
	}
	
	/**
	 * Checks if the ship has been sunk
	 */
	public void setSunk(){
		if (hits == length){
			sunk = true;
		}
	}
	
	/**
	 * Returns sunk, which determines whether the ship has been sunk or not.
	 * @return
	 */
	public boolean getSunk(){
		return sunk;
	}
	
	/**
	 * Returns the length of the ship
	 * @return
	 */
	public int getLength(){
		return length;
	}
	
	
	/**
	 * Returns the ship letter for this ship
	 * @return
	 */
	public String getShipLetter(){
		return shipLetter;
	}
	
}
