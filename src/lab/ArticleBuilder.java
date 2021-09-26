package lab;

import java.util.HashMap;
import java.util.Map;
/**
 * Klasa tworzaca rekord ARTICLE
 * @see EntryBuilder
 */

public class ArticleBuilder extends EntryBuilder {

    /**
     * @see EntryBuilder#buildRequired()
     */

    @Override
    public void buildRequired() {
        Map<String, String> required = new HashMap<>();
        required.put("title",null);
        required.put("journal",null);
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
        optional.put("pages",null);
        optional.put("month",null);
        optional.put("note",null);
        optional.put("key",null);
        optional.put("crossref",null);
        entry.setOptional(optional);
    }


}
