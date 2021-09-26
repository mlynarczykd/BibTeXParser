package lab;

/**
 * Klasa przechowujaca pojedynczych autorow i edytorow - ich pelna nazwe oraz samo nazwisko
 */
public class Person {
    private String fullName;
    private String lastName;

    /**
     * Konstruktor "osoby"
     * @param fullName pelne imie i nazwisko
     * @param lastName nazwisko
     */
    public Person(String fullName, String lastName){
        this.fullName = fullName;
        this.lastName = lastName;
    }

    /**
     * @return pelna nazwa osoby
     */

    public String getFullName() {
        return fullName;
    }

    /**
     * @return nazwisko osoby
     */

    public String getLastName() {
        return lastName;
    }

}
