package lab;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Klasa przechowujaca wszystkie rekordy z pliku oraz wybrane rekordy spelniajace wybrane kryteria {@link EntryFinder}
 */

public class BibtexEntries {
    private static List<Entry> entries = new LinkedList<>();
    private static Map<String,Entry> found = new LinkedHashMap<>();

    /**
     * @return lista wszystkich rekordow
     */

    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * @param key klucz cytowania
     * @return rekord o podanym kluczu
     */

    public Entry getEntryWithKey (String key){
        for(Entry entry: entries){
            if(entry.getKey().equals(key))
                return entry;
        }
        return null;
    }

    /**
     * Metoda dodajaca rekord do listy rekordow
     * @param entry rekord, ktory chcemy dodac do listy
     */
    public void assemble(Entry entry) {
        entries.add(entry);
    }


    public Map<String,Entry> getFound() {
        return found;
    }

    /**
     * Metoda wyswietlajaca wszystkie rekordy
     */

    public void displayAll(){
        for (Entry en: entries)
            displayEntry(en);
    }

    /**
     * Metoda wyswietlajaca rekordy spelniajace podane kryteria
     */

    public void displayFound(){
        for (Entry en: found.values())
            displayEntry(en);
    }

    /**
     * Metoda wyswietlajaca jeden rekord
     * @param entry rekord do wyswietlenia
     */

    public void displayEntry(Entry entry){
        List<Person> list = entry.getAuthorList();
        List<Person> list2 = entry.getEditorList();
        printSep();
        System.out.println(String.format("| %-147s|",entry.getName().toUpperCase() + " (" + entry.getKey() + ")"));
        printSep();
        if(entry.isAuthor()) {
            if (list != null) {
                if (list.size() > 1) {
                    System.out.println(String.format("| %-20s| * %-123s|", "author", list.get(0).getFullName()));
                    for (int i = 1; i < list.size(); i++)
                        System.out.println(String.format("| %-20s| * %-123s|", "", list.get(i).getFullName()));
                    printSep();
                } else if (list.size() == 1) {
                    System.out.println(String.format("| %-20s| * %-123s|", "author", list.get(0).getFullName()));
                    printSep();
                }
            }
        }
        if(entry.isEditor()) {
                if (list2 != null) {
                    if (list2.size() > 1) {
                        System.out.println(String.format("| %-20s| * %-123s|", "editor", list2.get(0).getFullName()));
                        for (int i = 1; i < list2.size(); i++)
                            System.out.println(String.format("| %-20s| * %-123s|", "", list2.get(i).getFullName()));
                        printSep();
                    } else if (list2.size() == 1) {
                        System.out.println(String.format("| %-20s| * %-123s|", "editor", list2.get(0).getFullName()));
                        printSep();
                    }
                }
            }
        for (String key: entry.getKeySet()){
            System.out.println(String.format("| %-20s| %-125s|", key, entry.getValue(key)));
            printSep();
        }
        System.out.println();
    }

    /**
     * Metoda wyswietlajaca separator
     */

    public void printSep(){
        for(int i = 0; i <150 ; i++)
            System.out.print('-');
        System.out.println();
    }

    /**
     * Metoda wyszukujaca rekordy spelniajace podane kryteria
     * @param finder {@link EntryFinder}
     * @param names nazwy, ktorych szukamy
     */

    public void search(EntryFinder finder, String[] names){
        for (Entry entry : entries) {
            if (finder.found(entry,names)){
                found.put(entry.getKey(),entry);
            }
        }
    }

}

