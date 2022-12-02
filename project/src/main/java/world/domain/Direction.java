package world.domain;

public enum Direction {
    NORTH("n", 1),
    EAST("e",2),
    SOUTH("s",3),
    WEST("w",4),
    NORTH_EAST("ne",5),
    NORTH_WEST("nw",6),
    SOUTH_EAST("se",7),
    SOUTH_WEST("sw",8),
    UP("u",9),
    DOWN("d",10);

    private String abbreviation;
    private int order;

    Direction(String abbreviation, int order) {
        this.abbreviation = abbreviation;
        this.order = order;
    }

    public int getOrder() {
        return order;
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
