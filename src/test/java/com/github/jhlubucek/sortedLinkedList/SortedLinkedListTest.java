package com.github.jhlubucek.sortedLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortedLinkedListTest {

    @org.junit.jupiter.api.Test
    void size() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();

        assertEquals(0, list.size());

        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());

        list.remove(2);
        assertEquals(2, list.size());

        list.clear();
        assertEquals(0, list.size());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();

        assertTrue(list.isEmpty());

        list.add(1);
        assertFalse(list.isEmpty());

        list.remove(Integer.valueOf(1));
        assertTrue(list.isEmpty());

        list.add(2);
        list.add(3);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void contains() {
        SortedLinkedList<String> list = new SortedLinkedList<>();

        assertFalse(list.contains("apple"));

        list.add("apple");
        list.add("banana");
        list.add("cherry");

        assertTrue(list.contains("apple"));
        assertTrue(list.contains("banana"));
        assertTrue(list.contains("cherry"));
        assertFalse(list.contains("grape"));
    }

    @org.junit.jupiter.api.Test
    void toArray() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();

        Object[] emptyArray = list.toArray();
        assertEquals(0, emptyArray.length);

        list.add(1);
        list.add(2);
        list.add(3);

        Object[] array = list.toArray();
        assertEquals(3, array.length);
        assertArrayEquals(new Integer[]{1, 2, 3}, array);

        list.remove(Integer.valueOf(2));
        array = list.toArray();
        assertEquals(2, array.length);
        assertArrayEquals(new Integer[]{1, 3}, array);

        list.clear();
        array = list.toArray();
        assertEquals(0, array.length);
    }

    @org.junit.jupiter.api.Test
    void testToArray() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        Integer[] firstAarray = new Integer[list.size()];
               firstAarray = list.toArray(firstAarray);



        assertEquals(3, firstAarray.length);
        assertArrayEquals(new Integer[]{1, 2, 3}, firstAarray);

        list.remove(Integer.valueOf(2));
        Integer[] secondAarray = new Integer[list.size()];
        secondAarray = list.toArray(secondAarray);
        assertEquals(2, secondAarray.length);
        assertArrayEquals(new Integer[]{1, 3}, secondAarray);
    }

    @org.junit.jupiter.api.Test
    void add() {

        SortedLinkedList<Integer> list = new SortedLinkedList<>(SortedLinkedList.Order.ASC);

        list.add(1);
        assertEquals(1, list.size());
        assertTrue(list.contains(1));

        list.add(3);
        list.add(2);
        list.add(5);
        list.add(1);
        list.add(null);

        assertTrue(list.contains(2));
        assertTrue(list.contains(3));

        //check list is sorted (ASC)
        Integer[] expectedResult = new Integer[]{null,1,1,2,3,5};
        Integer[] testArray = new Integer[list.size()];
        testArray = list.toArray(testArray);

        assertEquals(expectedResult.length, testArray.length);
        for (int i = 0; i<expectedResult.length; i++){
            assertEquals(expectedResult[i], testArray[i]);
        }

        //test DESC
        SortedLinkedList<Integer> listDesc = new SortedLinkedList<>(SortedLinkedList.Order.DESC);

        listDesc.add(3);
        listDesc.add(2);
        listDesc.add(5);
        listDesc.add(1);
        listDesc.add(1);
        listDesc.add(null);

        assertTrue(listDesc.contains(2));
        assertTrue(listDesc.contains(3));

        Integer[] expectedResultDesc = new Integer[]{5,3,2,1,1,null};
        Integer[] testArrayDesc = new Integer[listDesc.size()];
        testArrayDesc = listDesc.toArray(testArrayDesc);

        assertEquals(expectedResultDesc.length, testArrayDesc.length);
        for (int i = 0; i<expectedResultDesc.length; i++){
            assertEquals(expectedResultDesc[i], testArrayDesc[i]);
        }
    }

    @org.junit.jupiter.api.Test
    void addAll() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();

        List<Integer> elementsToAdd = Arrays.asList(1, 2, 3, 4, 5);

        assertTrue(list.addAll(elementsToAdd));
        assertEquals(5, list.size());

        for (Integer element : elementsToAdd) {
            assertTrue(list.contains(element));
        }

        List<Integer> emptyCollection = new ArrayList<>();
        assertFalse(list.addAll(emptyCollection));
        assertEquals(5, list.size());
    }

    @org.junit.jupiter.api.Test
    void testAddAll() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        List<Integer> elementsToAdd = Arrays.asList(1, 2, 3, 4, 5);
        assertThrows(UnsupportedOperationException.class, () -> {list.addAll(1,elementsToAdd); });
    }

    @org.junit.jupiter.api.Test
    void clear() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();

        List<Integer> elementsToAdd = Arrays.asList(1, 2, 3, 4, 5);

        assertTrue(list.addAll(elementsToAdd));
        assertEquals(5, list.size());

        for (Integer element : elementsToAdd) {
            assertTrue(list.contains(element));
        }

        List<Integer> emptyCollection = new ArrayList<>();
        assertFalse(list.addAll(emptyCollection));
        assertEquals(5, list.size());
    }

    @org.junit.jupiter.api.Test
    void get() {
        SortedLinkedList<String> list = new SortedLinkedList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });


        list.add("apple");
        list.add("banana");
        list.add("cherry");

        assertEquals("apple", list.get(0));
        assertEquals("banana", list.get(1));
        assertEquals("cherry", list.get(2));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(3);
        });
    }

    @org.junit.jupiter.api.Test
    void set() {
        SortedLinkedList<String> list = new SortedLinkedList<>();
        list.add("apple");

        assertThrows(UnsupportedOperationException.class, () -> {
            list.set(0, "banana");
        });

    }

    @org.junit.jupiter.api.Test
    void testAdd() {
        SortedLinkedList<String> list = new SortedLinkedList<>();
        list.add("apple");

        assertThrows(UnsupportedOperationException.class, () -> {
            list.add(1, "banana");
        });
    }

    @org.junit.jupiter.api.Test
    void remove() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();

        assertFalse(list.remove(Integer.valueOf(1)));

        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.remove(Integer.valueOf(2)));
        assertEquals(2, list.size());
        assertFalse(list.contains(2));

        assertFalse(list.remove(Integer.valueOf(4)));
        assertEquals(2, list.size());
    }

    @org.junit.jupiter.api.Test
    void testRemove() {
        SortedLinkedList<String> list = new SortedLinkedList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(0); });

        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");
        list.add("elderberry");

        assertEquals("cherry", list.remove(2));
        assertEquals(4, list.size());
        assertFalse(list.contains("cherry"));

        assertEquals("apple",list.remove(0));
        assertEquals(3, list.size());
        assertFalse(list.contains("apple"));

        assertEquals("elderberry",list.remove(2));
        assertEquals(2, list.size());
        assertFalse(list.contains("elderberry"));

        assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(-1);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(2);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(5);});
    }

    @org.junit.jupiter.api.Test
    void indexOf() {
        SortedLinkedList<String> list = new SortedLinkedList<>();

        assertEquals(-1, list.indexOf("apple"));

        list.add("apple");
        list.add("banana");
        list.add("cherry");

        assertEquals(0, list.indexOf("apple"));
        assertEquals(1, list.indexOf("banana"));
        assertEquals(2, list.indexOf("cherry"));

        assertEquals(-1, list.indexOf("grape"));
    }

    @org.junit.jupiter.api.Test
    void lastIndexOf() {
        SortedLinkedList<String> list = new SortedLinkedList<>(SortedLinkedList.Order.ASC);

        assertEquals(-1, list.lastIndexOf("apple"));

        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("apple");

        assertEquals(1, list.lastIndexOf("apple"));
        assertEquals(2, list.lastIndexOf("banana"));
        assertEquals(3, list.lastIndexOf("cherry"));

        assertEquals(-1, list.lastIndexOf("grape"));
    }

    @org.junit.jupiter.api.Test
    void subList() {
        SortedLinkedList<String> list = new SortedLinkedList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");
        list.add("elderberry");

        List<String> sub = list.subList(1, 4);

        assertEquals(4, sub.size());
        assertEquals("banana", sub.get(0));
        assertEquals("cherry", sub.get(1));
        assertEquals("date", sub.get(2));
        assertEquals("elderberry", sub.get(3));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();

        assertEquals("[]", list.toString());

        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals("[1, 2, 3]", list.toString());

        list.clear();
        assertEquals("[]", list.toString());
    }

    @org.junit.jupiter.api.Test
    void testClone() {
        SortedLinkedList<String> originalList = new SortedLinkedList<>();
        originalList.add("apple");
        originalList.add("banana");
        originalList.add("cherry");

        SortedLinkedList<String> clonedList = originalList.clone();

        assertNotSame(originalList, clonedList);
        assertEquals(originalList.size(), clonedList.size());

        for (int i = 0; i < originalList.size(); i++) {
            assertEquals(originalList.get(i), clonedList.get(i));
        }
    }
}