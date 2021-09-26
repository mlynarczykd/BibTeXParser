package lab;

import java.util.HashMap;
import java.util.Map;


/**
 * Klasa tworzaca rekord MISC
 * @see EntryBuilder
 */

public class MiscBuilder extends EntryBuilder{

    /**
     * @see EntryBuilder#buildRequired()
     */
    @Override
    public void buildRequired() {
        Map<String, String> required = new HashMap<>();
        required.put(null,null);
        entry.setRequired(required);
    }

    /**
     * @see EntryBuilder#buildOptional()
     */

    @Override
    public void buildOptional() {
        Map <String, String> optional = new HashMap<>();
        optional.put("title",null);
        optional.put("howpublished",null);
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
    }
}
