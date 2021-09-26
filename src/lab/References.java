package lab;

import java.util.List;

/**
 * Klasa obslugujaca cross-reference
 */

public class References{

    /**
     * @param entries lista rekordow przed obsluzeniem referencji
     * @return lista rekordow po obsluzeniu odwolywania się rekordow do siebie
     */
    public List<Entry> checkRef(List<Entry> entries) {
        for(Entry entry: entries) {
            if (entry.getFields().containsKey("crossref")) {
               String ref = entry.getFields().get("crossref");
                for (Entry entryRefTo: entries){
                    if(entryRefTo.getKey().equals(ref)){
                        if(entryRefTo.isAuthor()) {
                            entry.setAuthor(true);
                            entry.setAuthors(entryRefTo.getAuthors());
                        }
                        else if(entryRefTo.isEditor()) {
                            entry.setEditor(true);
                            entry.setAuthors(entryRefTo.getAuthors());
                        }
                        for(String key: entryRefTo.getFields().keySet()){
                            if(entry.getRequired().containsKey(key) || entry.getOptional().containsKey(key)) {
                                entry.getFields().putIfAbsent(key, entryRefTo.getValue(key));
                            }
                        }
                    }
                }
            }
        }
        return entries;
    }

}