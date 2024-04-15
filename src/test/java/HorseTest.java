import org.junit.Assert;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void nullNameConstructor(){
        Assert.assertThrows(IllegalArgumentException.class, ()-> new Horse(null,1));
    }

    @Test
    void nullNameCannotBe(){
        try{
            new Horse(null,1,1);
        }catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.",e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings ={ "", " "})
    void blankTestConstructor(String string){
        assertThrows(IllegalArgumentException.class, ()->new Horse(string,1,1));
    }

    @ParameterizedTest
    @ValueSource(strings = {""," "})
    void testCannotBeBlank(String string){
        try{
            new Horse(string, 1,1);
        }catch (IllegalArgumentException e){
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }
    @Test
    void negativeSpeedCons(){
        assertThrows(IllegalArgumentException.class, ()->new Horse("name",-1,1));
    }
    @Test
    void negativeSpeedText(){
        try{
            new Horse("name",-1,1);
        }catch (IllegalArgumentException e){
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }
    @Test
    void negativeDistanceCons(){
        assertThrows(IllegalArgumentException.class, ()->new Horse("name",1,-1));
    }
    @Test
    void negativeDistanceText(){
        try{
            new Horse("name",1,-1);
        }catch (IllegalArgumentException e){
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }
    @Test
    void getName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("name", 1, 1);
        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String horseName = (String) name.get(horse);
        assertEquals("name",horseName);
    }
    @Test
    void getSpeed() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("name",5,1);
        Field speed = Horse.class.getDeclaredField("speed");
        speed.setAccessible(true);
        double speedHorse = (double) speed.get(horse);
        assertEquals(5,speedHorse);
    }
    @ParameterizedTest
    @ValueSource(doubles = {1,0})
    void getDistance(double doubles) throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("name",1, doubles);
        Field distance = Horse.class.getDeclaredField("distance");
        distance.setAccessible(true);
        double distanceHorse = (double) distance.get(horse);
        assertEquals(doubles, distanceHorse);

    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.5, 0.7, 0.8})
    void testMoveDistance(double arg){
        try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            Mockito.when(Horse.getRandomDouble(0.2, 0.9)).thenReturn(arg);
            Horse horse = new Horse("Horse", 1.0);
            horse.move();
            assertEquals(horse.getSpeed()*arg, horse.getDistance());
        }
    }
}