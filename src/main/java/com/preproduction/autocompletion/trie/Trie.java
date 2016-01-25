/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preproduction.autocompletion.trie;

import com.preproduction.autocompletion.tuple.Tuple;

/**
 *
 * @author Irbis
 */
public interface Trie<T> {

        // Добавляет в Trie пару слово - term, и его вес - weight.
        // В качестве веса используйте длину слова
        void add(Tuple tuple);

        // есть ли слово в Trie
        boolean contains(String word);

        // удаляет слово из Trie
        boolean delete(String word);

        // итератор по всем словам, обход в ширину
        Iterable<String> words();

        // итератор по всем словам, начинающимся с pref, обход в ширину
        Iterable<String> wordsWithPrefix(String pref);

        // к-во слов в Trie
        int size();

}
