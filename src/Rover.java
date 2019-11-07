import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rover {

    enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    private int x, y; //Rover position
    private int sizeX, sizeY; //Size of the map
    private int direction; //0 = North, 1 = East, 2 = South, 3 = West
    private Map<Integer, ArrayList<Integer>> obstalces = new HashMap<>(); //Represent obstacle positions. i.e. [0, [1,2,3]] represents all the obstacles at x = 0;

    public Rover() {
        this.x = this.y = 0;
        this.direction = 0;
        this.sizeX = this.sizeY = 100;
    }

    public Rover(int sizeX, int sizeY, int x, int y) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.x = x;
        this.y = y;
    }

    public Rover(int sizeX, int sizeY, int x, int y, Map obstacles) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.x = x;
        this.y = y;
        this.obstalces = obstacles;
    }

    public void printPosition() {
        String directionName = "";
        switch (direction) {
            case 0:
                directionName = "North";
                break;

            case 1:
                directionName = "East";
                break;

            case 2:
                directionName = "South";
                break;

            case 3:
                directionName = "West";
                break;
        }
        System.out.println("(" + x + ", " + y + ") - " + directionName);
    }

    public void move(String instructions) {
        char[] moves = instructions.toCharArray();
        for (char m: moves) {
            int newX = this.x, newY = this.y, newDirection = this.direction;
            switch (m) {
                case 'F':
                    if (direction == 0)
                        newY = Math.floorMod(y + 1, this.sizeY);
                    if (direction == 1)
                        newX = Math.floorMod(x + 1, this.sizeX);
                    if (direction == 2)
                        newY = Math.floorMod(y - 1, this.sizeY);
                    if (direction == 3)
                        newX = Math.floorMod(x - 1, this.sizeX);
                    break;

                case 'B':
                    if (direction == 0)
                        newY = Math.floorMod(y - 1, this.sizeY);
                    if (direction == 1)
                        newX = Math.floorMod(x - 1, this.sizeX);
                    if (direction == 2)
                        newY = Math.floorMod(y + 1, this.sizeY);
                    if (direction == 3)
                        newX = Math.floorMod(x + 1, this.sizeX);
                    break;

                case 'L':
                    newDirection = Math.floorMod(direction - 1, 4);
                    break;

                case 'R':
                    newDirection = Math.floorMod(direction + 1, 4);
                    break;
            }
            if (obstalces.containsKey(newX)) {
                ArrayList<Integer> ys = obstalces.get(newX);
                if (ys.contains(newY)) {
                    return; //Stop if an obstacle is found
                }
            }
            this.x = newX;
            this.y = newY;
            this.direction = newDirection;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }
}
