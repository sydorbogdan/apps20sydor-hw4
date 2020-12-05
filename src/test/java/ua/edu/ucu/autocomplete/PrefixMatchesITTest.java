
package ua.edu.ucu.autocomplete;

import static org.hamcrest.Matchers.containsInAnyOrder;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import ua.edu.ucu.tries.RWayTrie;

/**
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm;

    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie(26, 96));
        pm.load("abc", "abce", "abcd", "abcde", "abcdef", "a");
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";

        Iterable<String> result = pm.wordsWithPrefix(pref);

        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testDelete() {
        String pref = "abc";
        pm.delete("abcd");
        Iterable<String> result = pm.wordsWithPrefix(pref);

        String[] expResult = {"abc", "abce", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
        assertEquals(true, pm.delete("abc"));
        assertEquals(true, pm.delete("abce"));
        assertEquals(true, pm.delete("abcde"));
        assertEquals(false, pm.delete("abcdef"));
    }

    @Test
    public void tesContains() {
        assertEquals(true, pm.contains("abc"));
        assertEquals(false, pm.contains("aaaaa"));
        assertEquals(false, pm.contains("a"));

    }

    @Test
    public void tesSize() {
        assertEquals(5, pm.size());
    }
}
