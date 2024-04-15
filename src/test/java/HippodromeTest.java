import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {




    @Test
    void nullConsTest(){
        assertThrows(IllegalArgumentException.class, ()->new Hippodrome(null));
    }
    @Test
    void ConsCannotBeNull(){
        try {
            new Hippodrome(null);
        }catch (IllegalArgumentException e){
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }
    @Test
    void listIsEmptyException(){
        List<Horse> list = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, ()->new Hippodrome(list));
    }
    @Test
    void listIsEmptyText(){
        List<Horse> list = new ArrayList<>();
        try{
            new Hippodrome(list);
        }catch (IllegalArgumentException e){
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    void getHorses() throws NoSuchFieldException, IllegalAccessException {
        List<Horse> listHors = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3),
                new Horse("name",1),
                new Horse("name1", 1),
                new Horse("name2",1),
                new Horse("name3", 2.4),
                new Horse("name4", 2.5),
                new Horse("name5", 2.6),
                new Horse("name6", 2.7),
                new Horse("name7", 2.8),
                new Horse("name8", 2.9),
                new Horse("name9", 3),
                new Horse("name10",1),
                new Horse("name11", 1),
                new Horse("name12",1),
                new Horse("name13", 2.4),
                new Horse("name14", 2.5),
                new Horse("name15", 2.6),
                new Horse("name16", 2.7),
                new Horse("name17", 2.8),
                new Horse("name18", 2.9),
                new Horse("name19", 3),
                new Horse("name20",1),
                new Horse("name21", 1),
                new Horse("name22",1)
        );
        Hippodrome hippodromeList = new Hippodrome(listHors);
        Field field = Hippodrome.class.getDeclaredField("horses");
        field.setAccessible(true);
        List<Horse> horsesList = (List<Horse>) field.get(hippodromeList);
        assertEquals(listHors, horsesList);
    }


    @Test
    void move() {
        List<Horse> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(list);
        hippodrome.move();

        for (Horse horse : list){
            Mockito.verify(horse).move();
        }
    }
    @Test
    void getWinner() {
           List<Horse> list = List.of(
                   new Horse("Name1", 1, 1),
                   new Horse("Name2", 2, 567),
                   new Horse("Name1",3,2)
           );
        Hippodrome hippodrome = new Hippodrome(list);
        assertEquals("Name2",hippodrome.getWinner().getName());
    }
}