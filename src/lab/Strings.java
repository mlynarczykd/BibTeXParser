package lab;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Klasa przechowujaca zadeklarowane zmienne napisowe oraz obslugujaca zmienne napisowe i konkatenacje
 */

public class Strings {
    private static Map<String,String> stringMap = new TreeMap<>(Collections.reverseOrder());

    public Map<String, String> getStringMap() {
        return stringMap;
    }


    public Strings(){
        stringMap.put("jan", "January");
        stringMap.put("feb", "February");
        stringMap.put("mar", "March");
        stringMap.put("apr", "April");
        stringMap.put("may", "May");
        stringMap.put("jun", "June");
        stringMap.put("jul", "July");
        stringMap.put("aug", "August");
        stringMap.put("sep", "September");
        stringMap.put("oct", "October");
        stringMap.put("nov", "November");
        stringMap.put("dec", "December");
    }

    public String getValue(String key){
        return stringMap.get(key);
    }

    /**
     * Metoda dodajaca zmienna napisowa i jej wartosc do mapy
     * @param tabLine parsowana deklaracja zmiennej napisowej
     */

    public void addString(String tabLine) {
        Pattern pattern3 = Pattern.compile(".*\\{ *(.+) *= *\"(.+)\".*}");
        Matcher matcher3 = pattern3.matcher(tabLine);
        if (matcher3.find()) {
            stringMap.put(matcher3.group(1).trim(), substitute(matcher3.group(2).trim()));
        }
    }

    /**
     * Metoda podmieniajaca zmiennne i obslugujaca konkatenacje
     * @param value wartosc przetwarzanego pola
     * @return przetworzone pole z obsluzonymi zmiennymi napisowymi i konkatenacja
     */

    public String substitute(String value){
        String[] tabStr = value.split("#");
        Pattern pattern4 = Pattern.compile("\"(.*)\"");
        Matcher matcher4;
        StringBuilder strB = new StringBuilder();
        for(String str: tabStr){
            str = str.trim();
            matcher4 = pattern4.matcher(str);
            if(matcher4.find()){
                strB.append(matcher4.group(1));
            }
            else if (stringMap.containsKey(str)){
                String newValue = getValue(str);
                strB.append(newValue);
            }
            else {
                strB.append(str);
            }
        }
        return strB.toString();
    }

}
