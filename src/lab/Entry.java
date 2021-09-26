package lab;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Klasa reprezentujaca pojedynczy rekord, przechowujaca jego pola wymagane, opcjonalne, autorow i edytorow
 */

public class Entry {
    private Map <String, String> required;
    private Map <String, String> optional;
    private Map <String, String> fields;
    private String name;
    private People authors;
    private boolean isAuthor;
    private boolean isEditor;
    private String key;

    /**
     * @return zbior kluczy
     */
    public Set<String> getKeySet(){
        return fields.keySet();
    }

    public void setAuthors(People authors) {
        this.authors = authors;
    }

    public People getAuthors(){
        return authors;
    }

    /**
     * @return lista autorow
     */

    public List<Person> getAuthorList(){
        return authors.getAuthors();
    }

    /**
     * @return lista edytorow
     */
    public List<Person> getEditorList(){
        return authors.getEditors();
    }

    /**
     * @param key nazwa pola
     * @return wartosc pola o podanej nazwie
     */
    public String getValue(String key){
        return fields.get(key);
    }

    /**
     * @return nazwa kategorii rekordu
     */
    public String getName() {
        return name;
    }

    /**
     * @param name nazwa kategorii rekordu
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return klucz cytowania rekordu
     */

    public String getKey() {
        return key;
    }


    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return true jesli publikacja ma autora
     */
    public boolean isAuthor() {
        return isAuthor;
    }

    public void setAuthor(boolean author) {
        isAuthor = author;
    }

    /**
     * @return true jesli publikacja ma edytora
     */
    public boolean isEditor() {
        return isEditor;
    }

    public void setEditor(boolean editor) {
        isEditor = editor;
    }

    /**
     * @return mapa pol wymaganych
     */
    public Map<String, String> getRequired() {
        return required;
    }

    public void setRequired(Map<String, String> required) {
        this.required = required;
    }

    /**
     * @return mapa pol opcjonalnych
     */
    public Map<String, String> getOptional() {
        return optional;
    }

    public void setOptional(Map<String, String> optional) {
        this.optional = optional;
    }

    /**
     * @return mapa pol rekordu
     */

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }


}
