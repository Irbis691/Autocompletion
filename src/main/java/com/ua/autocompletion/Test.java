package com.ua.autocompletion;

/**
 *
 * @author Irbis
 */
public class Test {
    
    public static void main(String[] args) {
        Trie<Integer> trie = new RWayTrie<>();
        PrefixMatches match = new PrefixMatches(trie);
        match.add("ab");
        match.add("abc");
        match.add("abd");
        match.add("abo");
        match.add("abl");
        match.add("abcd");
        match.add("abcde");
        match.add("abcdef");
        match.add("cfa");
        match.add("cfai");
        match.add("cfaie");
        match.add("cfaieu");
        for(Object str: match.wordsWithPrefix("ab")) {
            System.out.println(str.toString());
        }
    }
}
