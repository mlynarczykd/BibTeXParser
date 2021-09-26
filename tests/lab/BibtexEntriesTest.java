package lab;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class BibtexEntriesTest {

    @Test
    public void searchA() {
        Entry entry = new Entry();
        entry.setName("Book");
        entry.setKey("123");
        entry.setAuthor(true);
        People ppl = new People();
        Person person = new Person("Daniel Humpfrey","Humpfrey");
        List<Person> list = new LinkedList<>();
        list.add(person);
        Entry entry2 = new Entry();
        entry2.setName("Inbook");
        entry2.setKey("12");
        entry.setAuthor(true);
        People ppl2 = new People();
        Person person2 = new Person("Charles Bass","Bass");
        List<Person> list2 = new LinkedList<>();
        list2.add(person2);
        ppl.setAuthors(list);
        ppl2.setAuthors(list2);
        entry.setAuthors(ppl);
        entry2.setAuthors(ppl2);
        BibtexEntries entries = new BibtexEntries();
        entries.assemble(entry);
        entries.assemble(entry2);
        entries.search(new AuthorFinder(), new String[] {"Humpfrey"});
        assertEquals("Daniel Humpfrey", entries.getFound().get("123").getAuthorList().get(0).getFullName());
        assertFalse(entries.getFound().containsKey("12"));
    }


    @Test
    public void searchC () {
        Entry entry1 = new Entry();
        entry1.setName("Book");
        entry1.setKey("123");
        Entry entry2 = new Entry();
        entry2.setName("Inbook");
        entry2.setKey("12");
        Entry entry3 = new Entry();
        entry3.setName("Manual");
        entry3.setKey("1");
        BibtexEntries entries = new BibtexEntries();
        entries.assemble(entry1);
        entries.assemble(entry2);
        entries.assemble(entry3);
        entries.search(new CategoryFinder(), new String[] {"Book", "Manual"});
        assertEquals("Book", entries.getFound().get("123").getName());
        assertFalse(entries.getFound().containsKey("12"));
        assertEquals("Manual", entries.getFound().get("1").getName());
    }
}