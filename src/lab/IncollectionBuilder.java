package lab;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa tworzaca rekord INCOLECTION
 * @see EntryBuilder
 */

public class IncollectionBuilder extends EntryBuilder{

    /**
     * @see EntryBuilder#buildRequired()
     */
    @Override
    public void buildRequired() {
        Map<String, String> required = new HashMap<>();
        required.put("title",null);
        required.put("booktitle",null);
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
        optional.put("type",null);
        optional.put("chapter",null);
        optional.put("pages",null);
        optional.put("address",null);
        optional.put("edition",null);
        optional.put("month",null);
        optional.put("note",null);
        optional.put("key",null);
        optional.put("crossref",null);
        entry.setOptional(optional);
    }


}
