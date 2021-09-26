package lab;

/**
 * Klasa szukajaca publikacji nalezacych do podanych kategorii
 * @see EntryFinder
 */

public class CategoryFinder implements EntryFinder {


    /**
     *
     * @param entry przetwarzany rekord
     * @param categories nazwy szukanych kategorii
     * @return true jesli przetwarzany rekord nalezy do wybranej kategorii
     */
    @Override
    public boolean found(Entry entry, String[] categories) {
        for(String category: categories) {
            if (entry.getName().equalsIgnoreCase(category))
                return true;
        }
        return false;
    }
}
