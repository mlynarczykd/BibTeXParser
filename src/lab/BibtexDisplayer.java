package lab;

import java.io.IOException;

/**
 * Klasa wyswietlajaca rekordy
 */

public class BibtexDisplayer {
    private BibtexParser parser = new BibtexParser();

    /**
     * Metoda wyswietlajaca wszystkie rekordy z pliku
     * @param filePath sciezka do pliku bibtex
     * @throws IOException jesli podano blędna sciezke do pliku
     */

    public void display(String filePath) throws IOException{
        parser.parse(filePath);
        parser.getBibtexEntries().displayAll();
    }

    /**
     * Metoda wyswietlajaca publikacje podanych autorow
     * @param filePath sciezka do pliku bibtex
     * @param authors nazwiska autorow, ktorych publikacji szukamy
     * @throws IOException jesli podano blędna sciezkę do pliku
     */

    public void displayA(String filePath, String[] authors) throws IOException {
        parser.parse(filePath);
        parser.getBibtexEntries().search(new AuthorFinder(), authors);
        parser.getBibtexEntries().displayFound();
    }

    /**
     * Metoda wyswietlajaca publikacje nalezace do podanych kategorii
     * @param filePath sciezka do pliku bibtex
     * @param categories nazwy kategorii publikacji, ktorych szukamy
     * @throws IOException jesli podano bledna sciezkę do pliku
     */

    public void displayC(String filePath, String[] categories) throws IOException {
        parser.parse(filePath);
        parser.getBibtexEntries().search(new CategoryFinder(), categories);
        parser.getBibtexEntries().displayFound();
    }

    /**
     * Metoda wyswietlajaca publikacje podanych autorow lub nalezace do podanych kategorii
     * @param filePath sciezka do pliku bibtex
     * @param authors nazwiska autorow, ktorych publikacji szukamy
     * @param categories nazwy kategorii publikacji, ktorych szukamy
     * @throws IOException jesli podano bledna sciezke do pliku
     */

    public void display(String filePath, String[] authors, String[] categories) throws IOException {
        parser.parse(filePath);
        parser.getBibtexEntries().search(new CategoryFinder(), categories);
        parser.getBibtexEntries().search(new AuthorFinder(), authors);
        parser.getBibtexEntries().displayFound();
    }

}
