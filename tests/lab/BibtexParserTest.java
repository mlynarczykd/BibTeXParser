package lab;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import static org.junit.Assert.*;

public class BibtexParserTest {

    @Test
    public void parse() throws IOException {
        BibtexParser parser = new BibtexParser();
        parser.parse("C:\\Users\\Dominika\\Documents\\Studia\\Obiektowe\\test.bib");
        List<Entry> list = parser.getBibtexEntries().getEntries();
        String[] arr = {"ARTICLE","INBOOK","PHDTHESIS"};
        String[] arr2 = {"12a","23b","34c"};
        String[] arr3 = {"Leslie A. Aamport","Donald E. Knuth","F. Phidias Phony-Baloney"};
        String[] arr4 = {"Preparation System","Fundamental Algorithms","Fighting Fire with Fire"};
        String[] arr5 = {"1999", "1973", "1988"};
        assertTrue(list.size() == 3);
        for (int i = 0; i < list.size(); i++){
            assertEquals(arr[i], list.get(i).getName());
            assertEquals(arr2[i], list.get(i).getKey());
            assertEquals(arr3[i], list.get(i).getAuthorList().get(0).getFullName());
            assertEquals(arr4[i], list.get(i).getValue("title"));
            assertEquals(arr5[i], list.get(i).getValue("year"));
        }
        assertEquals("G-Animal's Journal", list.get(0).getValue("journal"));
        assertEquals("3", list.get(1).getValue("chapter"));
        assertEquals("Addison-Wesley", list.get(1).getValue("publisher"));
        assertEquals("10~January", list.get(1).getValue("month"));
        assertEquals("Fanstord University", list.get(2).getValue("school"));
    }


}