package lab;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringsTest {


    @Test
    public void substitute() {
        Strings str = new Strings();
        assertEquals("July", str.substitute("jul"));
        assertEquals("January-February", str.substitute("jan # \"-\" # feb"));
        assertFalse(str.substitute("\"aug\"").equals("August"));
        str.getStringMap().put("green","Wake me up");
        String test = "green # \", when \" # sep # \" ends.\"";
        assertEquals("Wake me up, when September ends.", str.substitute(test));
        assertFalse(str.substitute("\"To be green with envy\"").equals("To be Wake me up with envy"));

    }

    @Test
    public void addString() {
        Strings str = new Strings();
        str.addString("@STRING{IV = \"ENDGAME\"}");
        str.addString("@string { III = \"Infinity War\" }");
        assertEquals("ENDGAME",str.getStringMap().get("IV"));
        assertEquals("Infinity War", str.getStringMap().get("III"));
    }
}