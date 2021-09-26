package lab;


/**
 * Interfejs do wyszukiwania rekordow spelniajacych wybrane kryteria
 */
public interface EntryFinder {

    /**
     * @param entry przetwarzany rekord
     * @param names nazwy, ktorych szukamy w rekordach
     * @return true jesli przetwarzany spelnia podane kryteria
     */

    boolean found (Entry entry, String[] names);

}
