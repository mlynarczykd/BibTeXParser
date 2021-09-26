package lab;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReferencesTest {

    @Test
    public void checkRef() {
        EntryBuilder inbookBuilder = new InbookBuilder();
        EntryDirector entryDirector = new EntryDirector();
        entryDirector.setEntryBuilder(inbookBuilder);
        entryDirector.constructEntry("@INBOOK{123,chapter=\"2\",note = \"This is an example of crossref.\",crossref = 456}","INBOOK",0);
        BibtexEntries entries = new BibtexEntries();
        entries.assemble(entryDirector.getEntry());
        EntryBuilder bookBuilder = new BookBuilder();
        entryDirector.setEntryBuilder(bookBuilder);
        entryDirector.constructEntry("@BOOK{456,author = \"Daniel Humpfrey\",title=\"Inside\",publisher=\"UES\",year=2011,note=\"Referred To\",}","BOOK",0);
        entries.assemble(entryDirector.getEntry());
        new References().checkRef(entries.getEntries());
        Entry entry = entries.getEntryWithKey("123");
        assertEquals("Daniel Humpfrey", entry.getAuthorList().get(0).getFullName());
        assertEquals("Inside", entry.getValue("title"));
        assertEquals("UES",entry.getValue("publisher"));
        assertEquals("2011", entry.getValue("year"));
        assertEquals("2", entry.getValue("chapter"));
        assertEquals("This is an example of crossref.",entry.getValue("note"));

    }
}