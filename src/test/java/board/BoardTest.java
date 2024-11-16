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
        assertEquals(false, b.enter());
    }


    @Test
    void roomBlockEnterTest(){
        RoomBlock b = new RoomBlock();
        assertEquals(true, b.enter());
    }

    @Test
    void wallEnterTest(){
        Wall w = new Wall();
        assertEquals(false, w.enter());
    }
}
