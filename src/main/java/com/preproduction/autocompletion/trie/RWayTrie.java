package com.preproduction.autocompletion.trie;

import com.preproduction.autocompletion.tuple.Tuple;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 *
 * @author Irbis
 * @param <T>
 */
public class RWayTrie<T> implements Trie<T> {

    // Size of english alphabet
    private static final int ALPHABET_SIZE = 26;

    // First letter of english alphabet
    private static final char ALPHABET_START = 'a';

    // root of trie
    private Node root;

    // number of keys in trie
    private int N;

    // R-way trie node
    private static class Node {

        //Node value
        private Object val;
        //Array of node's links
        private final Node[] next = new Node[ALPHABET_SIZE];
    }

    /**
     * Initializes an empty string symbol table.
     */
    public RWayTrie() {

    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old
     * value with the new value if the key is already in the symbol table. If
     * the value is <tt>null</tt>, this effectively deletes the key from the
     * symbol table.
     *
     * @param tuple - tuple with word / word length pair
     */
    @Override
    public void add(Tuple tuple) {
        if (tuple == null) {
            return;
        }
        if (tuple.getWord() == null) {
            delete(tuple.getWord());
        } else {
            root = add(root, tuple.getWord(), (T) tuple.getValue(), 0);
        }
    }

    private Node add(Node x, String key, T val, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            if (x.val == null) {
                N++;
            }
            x.val = val;
            return x;
        }
        char c = (char) (key.charAt(d) - ALPHABET_START);
        x.next[c] = add(x.next[c], key, val, d + 1);
        return x;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param word - required word
     * @return the word if it is in the symbol table and <tt>null</tt> if it is
     * not in the symbol table
     */
    public T get(String word) {
        Node x = get(root, word, 0);
        if (x == null) {
            return null;
        }
        return (T) x.val;
    }

    /**
     * Does this symbol table contain the given word?
     *
     * @param word - required word
     * @return <tt>true</tt> if this symbol table contains <tt>word</tt> and
     * <tt>false</tt> otherwise
     */
    @Override
    public boolean contains(String word) {
        return get(word) != null;
    }

    private Node get(Node x, String word, int d) {
        if (x == null || word == null) {
            return null;
        }
        if (d == word.length()) {
            return x;
        }
        char c = (char) (word.charAt(d) - ALPHABET_START);
        return get(x.next[c], word, d + 1);
    }

    /**
     * Removes the key from the set if the key is present.
     *
     * @param key the key
     * @return true
     */
    @Override
    public boolean delete(String key) {
        if (key == null) {
            return false;
        }
        root = delete(root, key, 0);
        return root != null;
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            if (x.val != null) {
                N--;
            }
            x.val = null;
        } else {
            char c = (char) (key.charAt(d) - ALPHABET_START);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (x.val != null) {
            return x;
        }
        for (int c = 0; c < ALPHABET_SIZE; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }

    /**
     * Returns all words in the symbol table as an <tt>Iterable</tt>. To iterate
     * over all of the keys in the symbol table named <tt>st</tt>, use the
     * foreach notation: <tt>for (Key key : st.keys())</tt>.
     *
     * @return all keys in the sybol table as an <tt>Iterable</tt>
     */
    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    /**
     * Returns iterator for trie that made BFS
     *
     * @param pref the prefix
     * @return iterator for trie that made BFS
     */
    @Override
    public Iterable<String> wordsWithPrefix(String pref) {
        return () -> new Iterator<String>() {
            private Node current = get(root, pref, 0);
            private Queue<Tuple> queue = new LinkedList<Tuple>() {
                {
                    if(current != null) {
                        add(new Tuple<>(pref, current));
                    }
                }
            };

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public String next() {
                while (!queue.isEmpty()) {
                    Tuple t = queue.poll();
                    current = (Node) t.getValue();                    
                    StringBuilder str = new StringBuilder(t.getWord());
                    for (char c = 0; c < ALPHABET_SIZE; c++) {
                        Node n = current.next[c];
                        if (n != null) {
                            queue.add(new Tuple(str.append((char) (c + ALPHABET_START)).toString(), n));
                            str.deleteCharAt(str.length() - 1);
                        }
                    }
                    if (current.val != null) {
                        return str.toString();
                    }
                }
                throw new NoSuchElementException();
            }            
        };
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    @Override
    public int size() {
        return N;
    }

}
