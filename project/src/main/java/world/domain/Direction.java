package world.domain;

public enum Direction {
    NORTH("n",0, -1),
    EAST("e", 1, 0),
    SOUTH("s", 0, 1),
    WEST("w", -1, 0),
    NORTH_EAST("ne",1,-1),
    NORTH_WEST("nw", -1, -1),
    SOUTH_EAST("se", 1,1),
    SOUTH_WEST("sw", -1, 1);

    private String abbreviation;
    private int xDiff;
    private int yDiff;

    Direction(String abbreviation, int xDiff, int yDiff) {
        this.abbreviation = abbreviation;
        this.xDiff = xDiff;
        this.yDiff = yDiff;
    }


}
