package br.com.nmsystems.baireslistdemo;

import org.junit.Test;

import br.com.nmsystems.baireslistdemo.util.ToolBox;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void isSafety_Longer() {

        String text = "Agora é uma boa hora para fazer essa verificacao";

        ToolBox.getSafeSubstring(text, 10);

        assertEquals(true, (ToolBox.getSafeSubstring(text, 10).length()) <= 14);
    }

    @Test
    public void isSafety_Shorter() {

        String text = "Agora é uma";

        ToolBox.getSafeSubstring(text, 30);

        assertEquals(true, (ToolBox.getSafeSubstring(text, 10).length()) <= 30);
    }





}