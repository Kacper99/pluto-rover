import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    Rover rover;

    @BeforeEach
    void before() {
        rover = new Rover();
    }

    @Test
    void simpleMoves() {
        String path = "FFFRFFFLFFFRBBB"; //(6, 0)
        rover.move(path);
        assertEquals(0, rover.getX());
        assertEquals(6, rover.getY());
        assertEquals(1, rover.getDirection());
    }

    @Test
    void testNorthWrap() {
        rover = new Rover(10, 10, 0, 9);
        String path = "F";
        rover.move(path);
        assertEquals(0, rover.getY());
    }

    @Test
    void testSouthWrap() {
        rover = new Rover(10, 10, 0, 0);
        rover.move("B");
        assertEquals(9, rover.getY());
    }

    @Test
    void testEastWrap() {
        rover = new Rover(10, 10, 9, 0);
        rover.move("RF");
        assertEquals(0, rover.getX());
    }

    @Test
    void testWestWrap() {
        rover = new Rover(10, 10, 0, 0);
        rover.move("LF");
        assertEquals(9, rover.getX());
    }

    /**
     * Should return to the same position and direction
     */
    @Test
    void circlePath() {
        rover = new Rover(10, 10, 0, 0);
        rover.move("FFFRFFFRFFFRFFFR");
        assertEquals(0, rover.getX());
        assertEquals(0, rover.getY());
        assertEquals(0, rover.getDirection());
    }

    @Test
    void perimeterWalk() {
        rover = new Rover(5,5,0,0);
        rover.move("FFFFFRFFFFFRFFFFFRFFFFFR");
        assertEquals(0, rover.getX());
        assertEquals(0, rover.getY());
        assertEquals(0, rover.getDirection());
    }

    @Test
    void testNorthernMovement() {
        rover = new Rover(10,10,0,0);
        rover.move("FFFFF");
        assertEquals(0, rover.getX());
        assertEquals(5, rover.getY());
        assertEquals(0, rover.getDirection());
    }

    @Test
    void testEasternMovement() {
        rover = new Rover(10,10,0,0);
        rover.move("RFFFFF");
        assertEquals(5, rover.getX());
        assertEquals(0, rover.getY());
        assertEquals(1, rover.getDirection());
    }

    @Test
    void testWesternMovement() {
        rover = new Rover(10,10,0,0);
        rover.move("LFFFFFF");
        assertEquals(4, rover.getX());
        assertEquals(0, rover.getY());
        assertEquals(3, rover.getDirection());
    }

    @Test
    void testSouthernMovement() {
        rover = new Rover(10,10,0,0);
        rover.move("BBBBBBB");
        assertEquals(0, rover.getX());
        assertEquals(3, rover.getY());
        assertEquals(0, rover.getDirection());
    }

    @Test
    void testObstacleDetection() {
        rover = new Rover(10,10,0,0);
        rover.addObstacle(0, 1);
        rover.move("F");
        assertEquals(0, rover.getX());
        assertEquals(0, rover.getY());
        assertEquals(0, rover.getDirection());
    }

    @Test
    void testNotMovingIfObstacle() {
        rover = new Rover(10,10,0,0);
        rover.addObstacle(2, 2);
        rover.move("FFRFFFF"); //Try moving beyond the obstacle
        assertEquals(1, rover.getX());
        assertEquals(2, rover.getY());
        assertEquals(1, rover.getDirection());
    }

    @Test
    void testTrapped() {
        rover = new Rover(10, 10, 3, 3);
        rover.addObstacle(4, 3);
        rover.addObstacle(2, 3);
        rover.addObstacle(3, 2);
        rover.addObstacle(3, 4);
        rover.move("F");
        assertEquals(3, rover.getX());
        assertEquals(3, rover.getY());
        assertEquals(0, rover.getDirection());
        rover.move("B");
        assertEquals(3, rover.getX());
        assertEquals(3, rover.getY());
        assertEquals(0, rover.getDirection());
        rover.move("RF");
        assertEquals(3, rover.getX());
        assertEquals(3, rover.getY());
        assertEquals(1, rover.getDirection());
        rover.move("B");
        assertEquals(3, rover.getX());
        assertEquals(3, rover.getY());
        assertEquals(1, rover.getDirection());
    }

    @Test
    void testWrappedObstacle() {
        rover = new Rover(10,10,0,0);
        rover.addObstacle(9, 0);
        rover.move("LF");
        assertEquals(0, rover.getX());
        assertEquals(0, rover.getY());
        assertEquals(3, rover.getDirection());
    }
}