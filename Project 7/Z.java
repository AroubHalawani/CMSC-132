package model;

import java.util.ArrayList;

/**
 * 
 *  Piece: Z
 *  Orientation:  
 *      up       left     down     right
 *     _____     _____     _____    _____ 
 *  0 |     |  0|   * |  0|     | 0|   * | 
 *  1 | **  |  1|  ** |  1| **  | 1|  ** |
 *  2 |  ** |  2|  *  |  2|  ** | 2|  *  |
 *  3 |_____|  3|_____|  3|_____| 3|_____|
 *     01234     01234     01234    01234
 */

public class Z extends Tetromino{
  /**
   * Constructor. You may want to modify
   * @param game used in the call to super constructor
   */
  public Z(Game game) {
    super(game, "Z", Cell.RED);
    layout[0] = new ArrayList<Coordinate>();
	layout[0].add(new Coordinate(0, 0));
	layout[0].add(new Coordinate(-1, 0));
	layout[0].add(new Coordinate(0, 1));
	layout[0].add(new Coordinate(1, 1));
	layout[1] = new ArrayList<Coordinate>();
	layout[1].add(new Coordinate(0, 0));
	layout[1].add(new Coordinate(1, 0));
	layout[1].add(new Coordinate(0, 1));
	layout[1].add(new Coordinate(1, -1));
	layout[2] = new ArrayList<Coordinate>();
	layout[2].add(new Coordinate(0, 0));
	layout[2].add(new Coordinate(-1, 0));
	layout[2].add(new Coordinate(0, 1));
	layout[2].add(new Coordinate(1, 1));
	layout[3] = new ArrayList<Coordinate>();
	layout[3].add(new Coordinate(0, 0));
	layout[3].add(new Coordinate(1, 0));
	layout[3].add(new Coordinate(0, 1));
	layout[3].add(new Coordinate(1, -1));
  }

  /**
   * rotates the piece counter-clockwise. See above orientation
   * for reference on which tile to rotate around. 
   */
  @Override
  public boolean rotate() {
	  orientation++;
	  orientation %= 4;
		
	  if (!setOrigin(getOrigin())) {
		  orientation--; 
		  return false;
	  }
	  return true;
  }
}
