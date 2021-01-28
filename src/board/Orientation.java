package board;

import java.util.Random;

public enum Orientation {
	NORTH, EAST, WEST, SOUTH;

	public Orientation[] getValue() {
		return Orientation.values();
	}
	public static Orientation randomOrientation() {
	    int pick = new Random().nextInt(Orientation.values().length);
	    return Orientation.values()[pick];
	}
}
