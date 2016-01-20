package com.ua.autocompletion;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Irbis
 */

@RunWith(MockitoJUnitRunner.class)
public class PrefixMatchesTest {
    
    @Mock
    Trie trie;
    
    @InjectMocks
    PrefixMatches example;    
    
    @Test
    public void testContains_Verify() {
        example.contains("qwe");
        verify(trie).contains("qwe");
    }
    
    @Test
    public void testDelete_Verify() {                
        example.delete("qwe");
        verify(trie).delete("qwe");
    }
    
    @Test
    public void testSize_Verify() {        
        example.size();
        verify(trie).size();
    }
    
    @Test
    public void testWordWithPrefix_Verify() {
        example.wordsWithPrefix("qwe");
        verify(trie).wordsWithPrefix("qwe");
    }
    
//    @Test
//    public void testAdd_Verify() {
//        String word = "qwe";
//        example.add(word);
//        verify(trie).add(new Tuple(word, word.length()));
//    }
}
