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
    @Test
    public void isSafety_Longer() {

        String text = "Agora é uma boa hora para fazer essa verificacao";

        boolean results = ToolBox.getSafeSubstring(text, 10).length() <= 14;

        assertEquals(true, results);
    }

    @Test
    public void isSafety_Shorter() {

        String text = "Agora é uma";

        boolean results = ToolBox.getSafeSubstring(text, 10).length() <= 30;

        assertEquals(true, results);
    }

    @Test
    public void removeAccente() {
        String text = "Agora é nóis";

        String results = ToolBox.AccentMapper(text);

        assertEquals("Agora e nois", results);
    }

    @Test
    public void getFirstLine() {
        String text = "Now is The Time\nFor You My Master";

        String results = ToolBox.getBreakNewLine(text);

        assertEquals("Now is The Time", results);
    }

    @Test
    public void getDays() {
        String refDate = "2018-07-10";
        String resDate = "2018-08-04";

        String results = ToolBox.sDays("yyyy-MM-dd", refDate, 25);

        assertEquals(resDate, results);
    }

}