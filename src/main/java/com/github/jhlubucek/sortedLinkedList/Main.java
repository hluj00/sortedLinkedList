package com.github.jhlubucek.sortedLinkedList;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //SortedLinkedList String
        SortedLinkedList<String> stringList = new SortedLinkedList<>(SortedLinkedList.Order.ASC);
        stringList.add("apple");
        stringList.add("cherry");
        stringList.add("kiwi");
        stringList.add("banana");
        stringList.add(null);
        stringList.add("grape");

        System.out.println(stringList);

        //SortedLinkedList Integer
        SortedLinkedList<Integer> intList = new SortedLinkedList<>(SortedLinkedList.Order.DESC);
        intList.add(2);
        intList.add(1);
        intList.add(2);
        intList.add(4);
        intList.add(3);
        intList.add(null);
        intList.add(-2);
        intList.add(5);

        System.out.println(intList);

        //add element
        intList.add(1);

        //remove element
        intList.remove(Integer.valueOf(4));
        intList.remove(4);

        //add collection
        List<Integer> elementsToAdd = Arrays.asList(1, 10);
        intList.addAll(elementsToAdd);

        //get element on index
        System.out.println(intList.get(1));

        //get index of element
        System.out.println(intList.indexOf(2));

        //get last index of element
        System.out.println(intList.lastIndexOf(2));

        //create sublist
        System.out.println(intList);
        SortedLinkedList<Integer> subList = intList.subList(1,3);
        System.out.println(subList);

        //copy
        SortedLinkedList<Integer> clonedList = intList.clone();
        System.out.println(clonedList);

    }
}