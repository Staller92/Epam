package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
@RunWith(value = Parameterized.class)
public class GameTest {
        private String city;
        private char lastLetter;

        @Parameterized.Parameters
        public static Collection<Object[]> set_of_parameters() {

            return Arrays.asList(new Object[][]{
                    {"минск", 'к'},
                    {"сморгонь", 'н'},
                    {"свислочь", 'ч'}
            });

        }

        public GameTest(String city, char lastLetter) {
            this.city = city;
            this.lastLetter = lastLetter;
        }

    @Test
    public void getLastLetter() throws Exception {
        assertEquals(lastLetter, new Game().getLastLetter(city));
    }

}