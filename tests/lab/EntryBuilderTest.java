package lab;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class EntryBuilderTest {

    @Test
    public void buildFields() {
        EntryBuilder articleBuilder = new ArticleBuilder();
        EntryDirector entryDirector = new EntryDirector();
        entryDirector.setEntryBuilder(articleBuilder);
        entryDirector.constructEntry("@ARTICLE{789,author=\"Heller|Isaac\", title = \"Merlin\", journal =\"Once Upon a Time\",month = dec,year=1966,}","ARTICLE",0);
        Entry entry = entryDirector.getEntry();
        assertEquals("ARTICLE",entry.getName());
        assertEquals("789", entry.getKey());
        assertEquals("Isaac Heller", entry.getAuthorList().get(0).getFullName());
        assertEquals("Merlin", entry.getValue("title"));
        assertEquals("Once Upon a Time", entry.getValue("journal"));
        assertEquals("December", entry.getValue("month"));
        assertEquals("1966", entry.getValue("year"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void lackOfRequiredFields() throws IllegalArgumentException{
        EntryBuilder entryBuilder = new UnpublishedBuilder();
        Map<String,String> fields = new LinkedHashMap<>();
        fields.put("note", "this is a test");
        fields.put("year","1998");
        entryBuilder.createNewEntry();
        entryBuilder.buildRequired();
        entryBuilder.getEntry().setFields(fields);
        entryBuilder.check(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void alternativeRequiredFields() throws IllegalArgumentException{
        EntryBuilder entryBuilder = new BookBuilder();
        entryBuilder.createNewEntry();
        entryBuilder.buildRequired();
        entryBuilder.buildOptional();
        entryBuilder.buildFields("@BOOK{123,author = \"J. R. R. Tolkien\",editor = \"Jamie Selkirk\",title = \"The Return of the King\",year=1955,publisher = \"George Allen & Unwin\"}","BOOK");
        entryBuilder.check(0);
    }
}