package br.com.nmsystems.baireslistdemo;

import org.junit.Test;

import br.com.nmsystems.baireslistdemo.util.ToolBox;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    /**
     * Long text should be resized and include ... at the end
     */
    @Test
    public void isSafety_Longer() {

        String text = "Now is a good time to do that check";

        boolean results = ToolBox.getSafeSubstring(text, 10).length() <= 14;

        assertEquals(true, results);
    }

    /**
     * Short text should no be modified
     */
    @Test
    public void isSafety_Shorter() {

        String text = "Now is a";

        boolean results = ToolBox.getSafeSubstring(text, 10).length() <= 30;

        assertEquals(true, results);
    }

    /**
     * Text in portugues. The accent should be replaced by a another character without the accent
     */
    @Test
    public void removeAccente() {
        String text = "Agora é certo";

        String results = ToolBox.AccentMapper(text);

        assertEquals("Agora e certo", results);
    }

    /**
     * The text to be returned should go until the first "\n"
     */
    @Test
    public void getFirstLine() {
        String text = "Now is The Time\nFor You My Master";

        String results = ToolBox.getBreakNewLine(text);

        assertEquals("Now is The Time", results);
    }

    /**
     *  Generate a Date 25 days after the referenced date
     */
    @Test
    public void getDays() {
        String refDate = "2018-07-10";
        String resDate = "2018-08-04";

        String results = ToolBox.sDays("yyyy-MM-dd", refDate, 25);

        assertEquals(resDate, results);
    }

}