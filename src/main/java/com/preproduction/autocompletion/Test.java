package com.preproduction.autocompletion;

import com.preproduction.autocompletion.matcher.PrefixMatches;
import com.preproduction.autocompletion.trie.RWayTrie;
import com.preproduction.autocompletion.trie.Trie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Irbis
 */
public class Test {

    public static void main(String[] args) {
        Trie<Integer> trie = new RWayTrie<>();
        PrefixMatches matcher = new PrefixMatches(trie);
        String[] test;
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
        
        matcher.add(test);               
        
        for (Object str : matcher.wordsWithPrefix("ab")) {
            System.out.println(str.toString());
        }
    }
}
