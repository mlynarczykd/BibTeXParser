package lab;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa przechowujaca autorow i edytorow pojedynczego rekordu
 */
public class People {
    private List<Person> authors;
    private List<Person> editors;

    public List<Person> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Person> authors) {
        this.authors = authors;
    }

    public List<Person> getEditors() {
        return editors;
    }

    public void setEditors(List<Person> editors) {
        this.editors = editors;
    }

    /**
     * Metoda przetwarzajaca imiona i nazwiska autorow i edytorow
     * @param name parsowane imiona i nazwiska
     * @return {@link Person}
     */

    public Person personalise(String name){
        int ind1 = name.indexOf('|');
        int ind2 = name.indexOf('|',ind1 +1);
        Pattern pattern = Pattern.compile("(?:(?:.* *)* )?(?<last>.+)+");
        String first;
        String last = null;
        String jr;
        StringBuilder strB = new StringBuilder();
            if(ind1 > -1 && ind2 == -1){
                first = name.substring(ind1 +1).trim();
                last = name.substring(0,ind1).trim();
                strB.append(first + " " + last);
            }
            else if(ind1 > -1 && ind2 > -1){
                first = name.substring(ind2 +1).trim();
                last = name.substring(0,ind1).trim();
                jr = name.substring(ind1 +1, ind2).trim();
                strB.append(first + " " + last + ", " + jr);
            }
            else if(ind1 == -1){
                Matcher matcher = pattern.matcher(name.trim());
                if(matcher.matches())
                    last = matcher.group("last");
                strB.append(name.trim());
            }
            Person person = new Person(strB.toString(),last);
            return person;
    }

    /**
     * Metoda tworzaca liste autorow
     * @param names parsowane imiona i nazwisko
     */

    public void createAuthors(String names){
        List<Person> authors = new LinkedList<>();
        String[] tab = names.split(" and ");
        for (String str: tab){
            authors.add(personalise(str));
        }
        setAuthors(authors);
    }

    /**
     * Metoda tworzaca liste edytorow
     * @param names parsowane imiona i nazwisko
     */

    public void createEditors(String names){
        List<Person> editors = new LinkedList<>();
        String[] tab = names.split(" and ");
        for (String str: tab){
            editors.add(personalise(str));
        }
        setEditors(editors);
    }
}
