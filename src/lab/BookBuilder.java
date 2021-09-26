package lab;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa tworzaca rekord BOOK
 * @see EntryBuilder
 */

public class BookBuilder extends EntryBuilder {

    /**
     * @see EntryBuilder#buildRequired()
     */

    @Override
    public void buildRequired() {
        Map <String, String> required = new HashMap<>();
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
        optional.put("volume",null);
        optional.put("number",null);
        optional.put("series",null);
        optional.put("address",null);
        optional.put("edition",null);
        optional.put("month",null);
        optional.put("note",null);
        optional.put("key",null);
        optional.put("crossref",null);
        entry.setOptional(optional);
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
