import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rover {

    enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    int x, y;
    int sizeX, sizeY; //Size of the map
    int direction; //0 = North, 1 = East, 2 = South, 3 = West
    Map<Integer, ArrayList<Integer>> obstalces = new HashMap<>(); //Represent obstacle positions. i.e. [0, [1,2,3]] represents all the obstacles at x = 0;

    public Rover() {
        this.x = this.y = 0;
        this.direction = 0;
        this.sizeX = this.sizeY = 100;
    }

    public void move(String instructions) {
        char[] moves = instructions.toCharArray();
        for (char m: moves) {
            switch (m) {
                case 'F':
                    if (direction == 0)
                        y = (y + 1) % this.sizeY;
                    if (direction == 1)
                        x = (x + 1) % this.sizeX;
                    if (direction == 2)
                        y = (y - 1) % this.sizeY;
                    if (direction == 3)
                        x = (x - 1) & this.sizeX;
                    break;

                case 'B':
                    if (direction == 0)
                        y = (y - 1) % this.sizeY;
                    if (direction == 1)
                        x = (x - 1) % this.sizeX;
                    if (direction == 2)
                        y = (y + 1) % this.sizeY;
                    if (direction == 3)
                        x = (x + 1) & this.sizeX;
                    break;

                case 'L':
                    direction = (direction - 1) % 4;
                    break;

                case 'R':
                    direction = (direction + 1) % 4;
                    break;
            }
        }
    }
}
