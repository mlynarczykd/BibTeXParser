package lab;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa tworzaca rekord UNPUBLISHED
 * @see EntryBuilder
 */

public class UnpublishedBuilder extends EntryBuilder {

    /**
     * @see EntryBuilder#buildRequired()
     */
    @Override
    public void buildRequired() {
        Map<String, String> required = new HashMap<>();
        required.put("title",null);
        required.put("note",null);
        entry.setRequired(required);
    }

    /**
     * @see EntryBuilder#buildOptional()
     */

    @Override
    public void buildOptional() {
        Map <String, String> optional = new HashMap<>();
        optional.put("month",null);
        optional.put("year",null);
        optional.put("key",null);
        optional.put("crossref",null);
        entry.setOptional(optional);
    }


}
