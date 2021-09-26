package lab;

/**
 * Klasa szukajaca publikacji autorow o podanych nazwiskach
 * @see EntryFinder
 */
public class AuthorFinder implements EntryFinder {

    /**
     * @param entry przetwarzany rekord
     * @param names nazwiska szukanych autorow
     * @return true jesli przetwarzany rekord ma autora o wybranycm nazwisku
     */

    @Override
    public boolean found(Entry entry, String[] names) {
        for(String name: names) {
            if (entry.isAuthor()) {
                for (Person author : entry.getAuthorList()) {
                    if (author.getLastName().equalsIgnoreCase(name))
                        return true;
                }
            }
        }
        return false;
    }
}
