package com.preproduction.autocompletion.matcher;

import com.preproduction.autocompletion.tuple.Tuple;
import com.preproduction.autocompletion.trie.Trie;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        for (String str : strings) {
            str = str.trim();
            String[] subWords = str.split("\\s+");
            for (String newStr : subWords) {
                if (newStr.length() > 2 && !trie.contains(newStr)) {                    
                    trie.add(new Tuple<>(newStr, newStr.length()));
                }
            }
        }
        return trie.size() - wordCount;
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
        return () -> new Iterator<String>() {
            int currLength = 0;
            int counter = 0;
            Iterator<String> iter = trie.wordsWithPrefix(pref).iterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext() && counter <= k;
            }

            @Override
            public String next() {
                String str = (String) iter.next();
                if (currLength != str.length()) {
                    currLength = str.length();
                    counter++;
                }
                while (iter.hasNext() && counter <= k) {
                    return str;
                }
                throw new NoSuchElementException();
            }
        };
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
