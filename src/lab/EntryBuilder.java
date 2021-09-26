package lab;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa tworzaca rekordy
 */

abstract class EntryBuilder {
    protected Entry entry;

    /**
     * @return utworzony rekord
     */
    public Entry getEntry() {
        return entry;
    }

    /**
     * Metoda tworzaca nowy rekord
     */

    public void createNewEntry(){
        entry = new Entry();
    }

    /**
     * Metoda tworzaca mape pol wymaganych
     */
    public abstract void buildRequired();

    /**
     * Metoda tworzaca mape pol opcjonalnych
     */
    public abstract void buildOptional();

    /**
     * @param lines caly parsowany rekord
     * @return rekord bez niepotrzebych znakow
     */
    public String buildRecord(String lines) {
        Pattern pattern2 = Pattern.compile("[^{]+\\{(.+?)}");
        Matcher matcher2 = pattern2.matcher(lines);
        matcher2.find();
        return matcher2.group(1);
    }

    /**
     * Metoda tworzaca rekord - tworząca mape z nazwami pol i ich wartosciami
     * @param lines cały parsowany rekord
     * @param name nazwa rekordu
     */
    public void buildFields(String lines, String name) {
        Map<String, String> fields = new LinkedHashMap<>();
        entry.setName(name);
        String record = buildRecord(lines);
        String[] tabEntry = record.split(",");
        entry.setKey(tabEntry[0]);
        People authors = new People();
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
            if(entry.getRequired().containsKey(key) || entry.getOptional().containsKey(key)) {
                fields.put(key, value);
            }
        }
        entry.setFields(fields);
        entry.setAuthors(authors);
    }

    /**
     * Metoda sprawdzajaca poprawnosc stworzonych rekordow
     * @param count numer parsowanego rekordu
     */

    public void check(int count) {
        if(!entry.getFields().containsKey("crossref")) {
            if (!entry.isAuthor())
                throw new IllegalArgumentException("Brak pól wymaganych: Rekord nr  " + count + " - " + entry.getName() + " - author.");
            for (String key : entry.getRequired().keySet()) {
                if (!entry.getFields().containsKey(key)) {
                    throw new IllegalArgumentException("Brak pól wymaganych: Rekord nr " + count + " - " + entry.getName() + " - " + key + ".");
                }
            }
        }
    }


}
