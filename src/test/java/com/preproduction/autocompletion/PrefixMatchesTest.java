package com.preproduction.autocompletion;

import com.preproduction.autocompletion.matcher.PrefixMatches;
import com.preproduction.autocompletion.trie.Trie;
import com.preproduction.autocompletion.tuple.Tuple;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

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
    public void testAdd_VerifyAdd() {
        example.add("qwe");
        verify(trie).add(any(Tuple.class));
    }
    
    @Test
    public void testAdd_VerifyContains() {        
        example.add("qwe");
        verify(trie).contains("qwe");
    }        
    
    @Test
    public void testAdd_IsNotAddWordsSmallerThan3Simbols() {
        example.add("");
        example.add("q");
        example.add("qw");
        verify(trie, never()).add(any(Tuple.class));
    }
    
    @Test
    public void testAdd_IsCorrectAddStringWithMultipleWordsAndSpaces() {
        example.add("qwe qwer  qwert");
        verify(trie, times(3)).add(any(Tuple.class));
    }
    
    @Test
    public void testAdd_IsCorrectAddArrayOfStrings() {
        example.add(new String[]{"qwe", "qwer", "qwert"});
        verify(trie, times(3)).add(any(Tuple.class));        
    }
    
    @Test
    public void testWordsWithPrefixWithShortPrefix() {
        example.wordsWithPrefix("");
        example.wordsWithPrefix("q");
        verify(trie, never()).wordsWithPrefix(anyString());
    }
    
    
    
}
