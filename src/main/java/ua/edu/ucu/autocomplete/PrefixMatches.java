package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

/**
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches() {
        trie = new RWayTrie();
    }

    public PrefixMatches(Trie inpTrie) {
        trie = inpTrie;
    }

    public int load(String... strings) {
        int cnt = 0;
        for (String s : strings) {
            String[] splitter = s.split("\\s+");
            for (String sSec : splitter)
                if (sSec.length() > 2) {
                    trie.add(new Tuple(sSec, sSec.length()));
                    cnt++;
                }
        }
        return cnt;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        return trie.wordsWithPrefix(pref, k);
    }

    public int size() {
        return trie.size();
    }
}
