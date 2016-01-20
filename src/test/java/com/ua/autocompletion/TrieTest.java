package com.ua.autocompletion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Irbis
 */
public class TrieTest {

    String[] test;
    Trie trie = new RWayTrie();

    @Before
    public void setUp() {
        File file = new File("words-333333.txt");
        try {
            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                String s;
                int size = Integer.parseInt(in.readLine());                
                int counter = 0;
                test = new String[size];
                while ((s = in.readLine()) != null) {
                    String trim = s.trim();                    
                    test[counter++] = (trim.split("	")[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void testContains_IsContainsWorkCorrect() {
        trie.add(new Tuple(test[0], test[0].length()));
        assertTrue(trie.contains(test[0]));
    }
    
    @Test
    public void testSize_IsSizeWorkCorrect() {        
        trie.add(new Tuple(test[0], test[0].length()));
        assertEquals(trie.size(), 1);
    }
    
    @Test
    public void testAdd_IsAddIncrementSize() {
        trie.add(new Tuple(test[0], test[0].length()));
        trie.add(new Tuple(test[1], test[1].length()));
        assertEquals(trie.size(), 2);
    }
    
    @Test
    public void testAdd_IsAddExistingValueNotIncrementSize() {
        trie.add(new Tuple(test[0], test[0].length()));
        trie.add(new Tuple(test[0], test[0].length()));
        assertEquals(trie.size(), 1);
    }    
    
    @Test
    public void testAdd_IsAddNullDoNotChangeSize() {
        trie.add(new Tuple(test[0], test[0].length()));
        trie.add(null);
        assertEquals(trie.size(), 1);
    }
    
    @Test
    public void testDelete_IsDeleteWorkCorrect() {
        trie.add(new Tuple(test[50], test[50].length()));
        trie.delete(test[50]);
        assertFalse(trie.contains(test[50]));
    }
    
    @Test
    public void testDelete_IsDeleteDecrementSize() {
        trie.add(new Tuple(test[50], test[50].length()));
        trie.delete(test[50]);
        assertFalse(trie.contains(test[50]));
    }    
        
}
