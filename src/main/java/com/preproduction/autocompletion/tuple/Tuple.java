/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preproduction.autocompletion.tuple;

/**
 *
 * @author Irbis
 */
public class Tuple<T> {
    private T value;
    private String word;

    public Tuple(String word, T weight) {
        this.value = weight;
        this.word = word;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T weight) {
        this.value = weight;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }    
}
