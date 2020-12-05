package ua.edu.ucu.tries;

import immutable.Queue;

public class RWayTrie implements Trie {
    private static int R = 26;
    private Node root = new Node();
    private int shift = 96;

    private static class Node {
        private Integer val;
        private Node[] next = new Node[R];
    }


    @Override
    public void add(Tuple t) {
        this.root = put(root, t.term, t.weight, 0);
    }

    private Node put(Node x, String key, int val, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c - shift] = put(x.next[c - shift], key, val, d + 1);
        return x;
    }

    @Override
    public boolean contains(String word) {
        Node x = get(this.root, word, 0);
        if (x == null) {
            return false;
        }
        return x.val != null;
    }


    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c - shift], key, d + 1);
    }

    @Override
    public boolean delete(String word) {
        this.root = delete(this.root, word, 0);
        return this.root != null;
    }

    private Node delete(Node x, String key, int d) {
        if (d == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c - shift] = delete(x.next[c - shift], key, d + 1);
        }
        if (x.val != null) {
            return x;
        }
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue<String> q = new Queue<String>();
        collect(get(root, s, 0), s, q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) {
            return;
        }
        if (x.val != null) {
            q.enqueue(pre);
        }
        for (char c = (char) shift; c < shift + R; c++) {
            collect(x.next[c - shift], pre + c, q);
        }
    }

    public Iterable<String> wordsWithPrefix(String s, int k) {
        Queue<String> q = new Queue<String>();
        collect(get(root, s, 0), s, q, s.length() + k - 1);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q, int k) {
        if (x == null) {
            return;
        }
        if (x.val <= k) {
            q.enqueue(pre);
        }
        for (char c = (char) shift; c < shift + R; c++) {
            collect(x.next[c - shift], pre + c, q, k);
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        int counter = 0;
        if (x == null) {
            return counter;
        }
        if (x.val != null) {
            counter++;
        }
        for (char c = 0; c < R; c++) {
            counter += size(x.next[c]);
        }
        return counter;
    }

}
