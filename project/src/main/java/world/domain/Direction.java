package world.domain;

public enum Direction {
    NORTH("n"),
    EAST("e"),
    SOUTH("s"),
    WEST("w"),
    NORTH_EAST("ne"),
    NORTH_WEST("nw"),
    SOUTH_EAST("se"),
    SOUTH_WEST("sw"),
    UP("u"),
    DOWN("d");

    private String abbreviation;

    Direction(String abbreviation) {
        this.abbreviation = abbreviation;
    }


    public static Direction fromNameOrAbbreviation(String text) {
        Direction[] values = values();
        for (Direction value : values) {
            if (value.name().equalsIgnoreCase(text) || value.abbreviation.equalsIgnoreCase(text)) {
                return value;
            }
        }
        return null;
    }
}
