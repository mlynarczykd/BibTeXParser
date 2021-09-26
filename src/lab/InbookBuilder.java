package lab;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Klasa tworzaca rekord INBOOK
 * @see EntryBuilder
 */

public class InbookBuilder extends EntryBuilder{

    /**
     * @see EntryBuilder#buildRequired()
     */
    @Override
    public void buildRequired() {
        Map<String, String> required = new LinkedHashMap<>();
        required.put("title",null);
        required.put("publisher",null);
        required.put("year",null);
        entry.setRequired(required);
    }

    /**
     * @see EntryBuilder#buildOptional()
     */


    @Override
    public void buildOptional() {
        Map <String, String> optional = new HashMap<>();
        optional.put("chapter",null);
        optional.put("pages",null);
        optional.put("volume",null);
        optional.put("number",null);
        optional.put("series",null);
        optional.put("type",null);
        optional.put("address",null);
        optional.put("edition",null);
        optional.put("month",null);
        optional.put("note",null);
        optional.put("key",null);
        optional.put("crossref",null);
        entry.setOptional(optional);
    }


    /**
     * @see EntryBuilder#buildFields(String, String)
     */
    @Override
    public void buildFields(String lines, String name) {
        Map<String, String> fields = new LinkedHashMap<>();
        entry.setName(name);
        String record = buildRecord(lines);
        String[] tabEntry = record.split(",");
        entry.setKey(tabEntry[0]);
        People authors = new People();
        boolean checking = false;
        for(int i = 1; i < tabEntry.length; i++){
            int index = tabEntry[i].indexOf('=');
            if (index < 0)
                throw new IllegalArgumentException("Niewłaściwa wartość pola.");
            String key = tabEntry[i].substring(0,index).trim().toLowerCase();
            String value = tabEntry[i].substring(index+1).trim();
            value = (new Strings()).substitute(value);
            if(key.equals("author")){
                entry.setAuthor(true);
                authors.createAuthors(value);
            }
            else if(key.equals("editor")){
                entry.setEditor(true);
                authors.createEditors(value);
            }
            else if(key.equals("chapter")){
                checking = true;
            }
            else if(key.equals("pages")){
                if(checking)
                    checking = false;
                else
                    checking = true;
            }
            if(entry.getRequired().containsKey(key) || entry.getOptional().containsKey(key)) {
                fields.put(key, value);
            }
        }
        if(!checking){
            throw new IllegalArgumentException("Błędne pola wymagane: INBOOK - chapter i/lub pages.");
        }
        entry.setFields(fields);
        entry.setAuthors(authors);
    }


    /**
     * @see EntryBuilder#check(int count)
     */


    @Override
    public void check(int count) {
        if (entry.isAuthor() && entry.isEditor()) {
            throw new IllegalArgumentException("Błędne pola wymagane: Rekord nr  " + count + " - " + entry.getName() + " - autor i edytor.");
        }
        if(!entry.getFields().containsKey("crossref")) {
            if(!entry.isAuthor() && !entry.isEditor()){
                throw new IllegalArgumentException("Brak pól wymaganych: Rekord nr  " + count + " - " + entry.getName() + " - autor lub edytor.");
            }
            for (String key : entry.getRequired().keySet()) {
                if (!entry.getFields().containsKey(key)) {
                    throw new IllegalArgumentException("Brak pól wymaganych: Rekord nr " + count + " - " + entry.getName() + " - " + key + ".");
                }
            }
        }
    }
}

