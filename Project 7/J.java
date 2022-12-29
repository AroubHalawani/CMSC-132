package model;

/**
 * 
 *  Piece: J
 *  Orientation:  
 *        up       left      down      right
 *       _____     _____     _____     _____
 *     0|  *  |  0|     |  0|  ** |  0| *   |
 *     1|  *  |  1| *** |  1|  *  |  1| *** | 
 *     2| **  |  2|   * |  2|  *  |  2|     |
 *     3|_____|  3|_____|  3|_____|  3|_____|
 *       01234     01234     01234     01234
 */

import java.util.ArrayList;

public class J extends Tetromino{

  public J(Game game) {
    /**
     * Constructor. You may want to modify
     * @param game used in the call to super constructor
     */
    super(game, "J", Cell.BLUE);
    layout[0] = new ArrayList<Coordinate>();
	layout[0].add(new Coordinate(0, 0));
	layout[0].add(new Coordinate(0, 1));
	layout[0].add(new Coordinate(0, 2));
	layout[0].add(new Coordinate(-1, 2));
	layout[1] = new ArrayList<Coordinate>();
	layout[1].add(new Coordinate(0, 0));
	layout[1].add(new Coordinate(1, 0));
	layout[1].add(new Coordinate(2, 0));
	layout[1].add(new Coordinate(2, 1));
	layout[2] = new ArrayList<Coordinate>();
	layout[2].add(new Coordinate(0, 0));
	layout[2].add(new Coordinate(1, 0));
	layout[2].add(new Coordinate(0, 1));
	layout[2].add(new Coordinate(0, 2));
	layout[3] = new ArrayList<Coordinate>();
	layout[3].add(new Coordinate(0, 0));
	layout[3].add(new Coordinate(0, 1));
	layout[3].add(new Coordinate(1, 1));
	layout[3].add(new Coordinate(2, 1));
	  
  }
  private boolean helper(int c, int r) {
	if(!setOrigin(getOrigin().translate(c, r))){
		orientation--; 
		return false;
	}
	return true;
  }

  /**
   * rotates the piece counter-clockwise. See above orientation
   * for reference on which tile to rotate around. 
   */
  @Override
  public boolean rotate() {

	  orientation++;
	  orientation %= 4;
		
	  if(orientation==0) {
		  helper(1,0);
	  }else if(orientation==1) {
		  helper(-1,1);
	  }else if(orientation==2) {
		  helper(1,-1);
	  }else if(orientation ==3) {
		  helper(-1,0);
	  }
	  return true;
  }
}
