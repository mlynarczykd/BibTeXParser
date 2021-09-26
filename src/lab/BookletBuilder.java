package lab;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa tworzaca rekord BOOKLET
 * @see EntryBuilder
 */

public class BookletBuilder extends EntryBuilder {

    /**
     * @see EntryBuilder#buildRequired()
     */
    @Override
    public void buildRequired() {
        Map<String, String> required = new HashMap<>();
        required.put("title",null);
        entry.setRequired(required);
    }


    /**
     * @see EntryBuilder#buildOptional()
     */

    @Override
    public void buildOptional() {
        Map <String, String> optional = new HashMap<>();
        optional.put("howpublished",null);
        optional.put("address",null);
        optional.put("month",null);
        optional.put("year",null);
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
        if(!entry.getFields().containsKey("crossref")) {
            for (String key : entry.getRequired().keySet()) {
                if (!entry.getFields().containsKey(key)) {
                    throw new IllegalArgumentException("Brak pól wymaganych: Rekord nr " + count + " - " + entry.getName() + " - " + key + ".");
                }
            }
        }
    }


}
