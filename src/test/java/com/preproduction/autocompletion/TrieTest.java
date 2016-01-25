package com.preproduction.autocompletion;

import com.preproduction.autocompletion.tuple.Tuple;
import com.preproduction.autocompletion.trie.RWayTrie;
import com.preproduction.autocompletion.trie.Trie;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Irbis
 */
public class TrieTest {

    Trie trie = new RWayTrie();
  
    @Test
    public void testContains_IsContainsWorkCorrect() {
        trie.add(new Tuple("qwe", "qwe".length()));
        assertTrue(trie.contains("qwe"));
    }
    
    @Test
    public void testSize_IsSizeWorkCorrect() {        
        trie.add(new Tuple("qwe", "qwe".length()));
        assertEquals(trie.size(), 1);
    }
    
    @Test
    public void testAdd_IsAddIncrementSize() {
        trie.add(new Tuple("qwe", "qwe".length()));
        trie.add(new Tuple("qwer", "qwer".length()));
        assertEquals(trie.size(), 2);
    }
    
    @Test
    public void testAdd_IsAddExistingValueNotIncrementSize() {
        trie.add(new Tuple("qwe", "qwe".length()));
        trie.add(new Tuple("qwe", "qwe".length()));
        assertEquals(trie.size(), 1);
    }    
    
    @Test
    public void testAdd_IsAddNullDoNotChangeSize() {
        trie.add(new Tuple("qwe", "qwe".length()));
        trie.add(null);
        assertEquals(trie.size(), 1);
    }
    
    @Test
    public void testDelete_IsDeleteWorkCorrect() {
        trie.add(new Tuple("qwe", "qwe".length()));
        trie.delete("qwe");
        assertFalse(trie.contains("qwe"));
    }
    
    @Test
    public void testDelete_IsDeleteDecrementSize() {
        trie.add(new Tuple("qwe", "qwe".length()));
        trie.delete("qwe");
        assertFalse(trie.contains("qwe"));
    }    
        
}
