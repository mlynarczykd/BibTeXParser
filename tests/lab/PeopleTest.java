package lab;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PeopleTest {

    @Test
    public void personalise() {
        People person = new People();
        assertEquals("Hayley Nicole Williams", person.personalise("Williams | Hayley Nicole").getFullName());
        assertEquals("Taylor York, Jr", person.personalise("York|Jr|Taylor").getFullName());
        assertEquals("Zac Farro", person.personalise(" Zac Farro ").getFullName());
        assertEquals("Brendon Urie", person.personalise("Urie |Brendon").getFullName());

        assertEquals("Williams", person.personalise("Williams | Hayley Nicole").getLastName());
        assertEquals("York", person.personalise("York|Jr|Taylor").getLastName());
        assertEquals("Farro", person.personalise(" Zac Farro ").getLastName());
        assertEquals("Urie", person.personalise("Urie |Brendon").getLastName());
        assertEquals("Skłodowska-Curie", person.personalise("Maria Skłodowska-Curie").getLastName());
    }

    @Test
    public void CreateAuthors() {
        String authors = "Dan Humpfrey and Archibald|Jr|Nathaniel and Bass | Charles and Serena van der Woodsen and Waldorf | Blair Cornelia";
        String[] tab = {"Dan Humpfrey", "Nathaniel Archibald, Jr", "Charles Bass", "Serena van der Woodsen", "Blair Cornelia Waldorf"};
        List <String> expected = Arrays.asList(tab);
        People people = new People();
        people.createAuthors(authors);
        List <Person> names = people.getAuthors();
        int i = 0;
        for(Person author: names) {
            assertEquals(expected.get(i), author.getFullName());
            i++;
        }
    }
}