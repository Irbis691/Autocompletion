package com.ua.autocompletion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Irbis
 */
public class PrefixMatches {

    //Data structure for in-memory dictionary
    private Trie trie;

    /**
     * Default Constructor
     *
     * @param trie
     */
    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    // Формирует in-memory словарь слов. Метод принимает слово, строку, массив слов/строк. Если приходит строка, то она разбивается на слова по пробелам.
    // В словарь должны добавляться слова длиннее 2х символов. Предполагается что знаки пунктуации отсутствуют.
    public int add(String... strings) {
        int wordCount = trie.size();
        Pattern p = Pattern.compile("\\s+");
        for (String str : strings) {
            String trim = str.trim();            
            Matcher m = p.matcher(trim);
            if (m.matches()) {
                String[] subWords = trim.split(p.pattern());
                for (String newStr : subWords) {
                    if(newStr.length() > 2) {
                        addToTrie(newStr);
                    }                                 
                }
            }
            if(trim.length() > 2) {
                addToTrie(str);
            }
        }
        return trie.size() - wordCount;
    }

    private void addToTrie(String newStr) {
        Tuple<Integer> tuple = new Tuple<>(newStr, newStr.length());
        trie.add(tuple);
    }

    // есть ли слово в словаре
    public boolean contains(String word) {
        return trie.contains(word);
    }

    // удаляет слово из словаря
    public boolean delete(String word) {
        return trie.delete(word);
    }

    // к-во слов в словаре
    public int size() {
        return trie.size();
    }

    // если введенный pref длиннее или равен 2м символам, то возвращает набор слов k разных длин начиная с минимальной, и начинающихся с данного префикса pref.
    // Пример, даны слова следующей длины и pref='abc':
    // abc 3
    // abcd 4
    // abce 4
    // abcde 5
    // abcdef 6
    // - при k=1 возвращаются 'abc'
    // - при k=2 возвращаются 'abc', 'abcd', 'abce'
    // - при k=3 возвращаются 'abc', 'abcd', 'abce', 'abcde'
    // - при k=4 возвращаются 'abc', 'abcd', 'abce', 'abcde', 'abcdef'
    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (prefToSmall(pref)) {
            return Collections.EMPTY_LIST;
        }
        List<String> result = new ArrayList<>();
        Iterable<String> iter = trie.wordsWithPrefix(pref);
        int currLength = 0;
        int counter = 0;
        for (String str : iter) {
            if (counter < k) {
                if (currLength != str.length()) {
                    currLength = str.length();
                    counter++;
                }
                result.add(str);
            }
        }
        return result;
    }

    // если введенный pref длиннее или равен 2м символам, то возвращает набор слов k=3 разных длин начиная с минимальной, и начинающихся с данного префикса pref.
    public Iterable<String> wordsWithPrefix(String pref) {        
        return wordsWithPrefix(pref, 3);
    }

    private boolean prefToSmall(String pref) {
        if (pref.length() < 1) {
            System.out.println("Input more long prefix");
            return true;
        }
        return false;
    }

}
