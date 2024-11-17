package board;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class BoardTest {

    private Board mockBoard;


    @BeforeEach
    void setup(){

    }


    @Test
    void barrierBlockEnterTest(){
        BarrierBlock b = new BarrierBlock();
        BarrierBlock b2 = new BarrierBlock(0, 0, null);

        assertEquals(false, b.enter());
        assertEquals(false, b2.enter());
    }


    @Test
    void roomBlockEnterTest(){
        RoomBlock b = new RoomBlock();
        RoomBlock b2 = new RoomBlock(0, 0, null);

        assertEquals(true, b.enter());
        assertEquals(true, b2.enter());
    }

    @Test
    void wallEnterTest(){
        Wall w = new Wall();
        Wall w2 = new Wall(0, 0, null);
        
        assertEquals(false, w.enter());
        assertEquals(false, w2.enter());
    }
}
