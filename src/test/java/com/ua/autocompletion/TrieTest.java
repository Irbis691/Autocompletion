/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public void testAdd_IsAddAddedWord() {
        trie.add(new Tuple(test[0], test[0].length()));
        assertTrue(trie.contains(test[0]));
    }
    
    @Test
    public void testAdd_IsAddIncrementSize() {
        trie.add(new Tuple(test[0], test[0].length()));
        assertEquals(trie.size(), 1);
    }
    
    @Test
    public void testAdd_IsAddNullDoNotIncrementSize() {
        trie.add(null);
        assertEquals(trie.size(), 0);
    }
}
