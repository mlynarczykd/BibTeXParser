package lab;


/**
 * Klasa kierujaca tworzeniem rekordow
 */
public class EntryDirector {
    private EntryBuilder entryBuilder;

    public void setEntryBuilder(EntryBuilder entryBuilder) {
        this.entryBuilder = entryBuilder;
    }
    public Entry getEntry(){
        return entryBuilder.getEntry();
    }

    /**
     * @param lines parsowany rekord
     * @param name nazwa kategorii rekordu
     * @param count numer przetwarzanego rekordu
     */
    public void constructEntry(String lines, String name, int count){
        entryBuilder.createNewEntry();
        entryBuilder.buildRequired();
        entryBuilder.buildOptional();
        entryBuilder.buildFields(lines,name);
        entryBuilder.check(count);
    }
}
