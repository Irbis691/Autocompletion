package com.ua.autocompletion;

import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Irbis
 */


public class PrefixMatchesTest {
    
    PrefixMatches example;
    Trie trie;
    
    @Before 
    public void setUp() {
        trie = Mockito.mock(Trie.class);
        example = new PrefixMatches(trie);
        MockitoAnnotations.initMocks(this);
    }
            
    @Test
    public void testContains() {                
        when(trie.contains("test")).thenReturn(true);        
        
    }
    
    @Test
    public void testDelete() {                
        when(trie.delete("test")).thenReturn(true);        
        
    }
    
    @Test
    public void testSize() {                
        when(trie.size()).thenReturn(1);        
        
    }
}
