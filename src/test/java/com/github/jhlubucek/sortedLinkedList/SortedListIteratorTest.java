package com.github.jhlubucek.sortedLinkedList;

import java.util.*;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class SortedListIteratorTest {
    @org.junit.jupiter.api.Test
    void hasNext() {
        SortedLinkedList<String> list = new SortedLinkedList<>();

        ListIterator<String> emptyIterator = list.listIterator();
        assertFalse(emptyIterator.hasNext());

        list.add("Item1");
        list.add("Item2");

        ListIterator<String> iterator = list.listIterator();

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }
    @org.junit.jupiter.api.Test
    void hasPrevious() {
        SortedLinkedList<String> list = new SortedLinkedList<>();

        ListIterator<String> emptyIterator = list.listIterator();
        assertFalse(emptyIterator.hasPrevious());
        list.add("Item1");
        list.add("Item2");

        ListIterator<String> iterator = list.listIterator();
        assertFalse(iterator.hasPrevious());

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasPrevious());

        iterator.next();
        assertTrue(iterator.hasPrevious());

        iterator.previous();
        assertTrue(iterator.hasPrevious());

        iterator.previous();
        assertFalse(iterator.hasPrevious());


    }
    @org.junit.jupiter.api.Test
    void next() {
        SortedLinkedList<String> list = new SortedLinkedList<>();

        ListIterator<String> emptyIterator = list.listIterator();
        assertThrows(java.util.NoSuchElementException.class, emptyIterator::next);

        list.add("Item1");
        list.add("Item2");
        ListIterator<String> iterator = list.listIterator();

        assertTrue(iterator.hasNext());

        assertEquals("Item1", iterator.next());
        assertTrue(iterator.hasNext());

        assertEquals("Item2", iterator.next());
        assertFalse(iterator.hasNext());

        assertThrows(java.util.NoSuchElementException.class, iterator::next);
    }
    @org.junit.jupiter.api.Test
    void previous() {
        SortedLinkedList<String> list = new SortedLinkedList<>();

        ListIterator<String> emptyIterator = list.listIterator();
        assertThrows(java.util.NoSuchElementException.class, emptyIterator::previous);

        list.add("Item1");
        list.add("Item2");
        ListIterator<String> iterator = list.listIterator();

        assertFalse(iterator.hasPrevious());
        assertTrue(iterator.hasNext());

        assertEquals("Item1", iterator.next());
        assertTrue(iterator.hasPrevious());

        assertEquals("Item2", iterator.next());
        assertFalse(iterator.hasNext());
        assertTrue(iterator.hasPrevious());

        assertEquals("Item2", iterator.previous());
        assertTrue(iterator.hasNext());
        assertEquals("Item1", iterator.previous());
        assertFalse(iterator.hasPrevious());

        assertThrows(java.util.NoSuchElementException.class, iterator::previous);
    }
    @org.junit.jupiter.api.Test
    void nextIndex() {
        SortedLinkedList<String> list = new SortedLinkedList<>();
        ListIterator<String> emptyIterator = list.listIterator();
        assertEquals(0, emptyIterator.nextIndex());

        list.add("Item1");
        list.add("Item2");
        list.add("Item3");
        ListIterator<String> iterator = list.listIterator();

        assertEquals(0, iterator.nextIndex());

        iterator.next();
        assertEquals(1, iterator.nextIndex());

        iterator.next();
        assertEquals(2, iterator.nextIndex());

        iterator.next();
        assertEquals(3, iterator.nextIndex());

    }
    @org.junit.jupiter.api.Test
    void previousIndex() {
        SortedLinkedList<String> list = new SortedLinkedList<>();
        ListIterator<String> emptyIterator = list.listIterator();
        assertEquals(-1, emptyIterator.previousIndex());

        list.add("Item1");
        list.add("Item2");
        list.add("Item3");
        ListIterator<String> iterator = list.listIterator();

        assertEquals(-1, iterator.previousIndex());

        iterator.next();
        assertEquals(0, iterator.previousIndex());

        iterator.next();
        assertEquals(1, iterator.previousIndex());

        iterator.next();
        assertEquals(2, iterator.previousIndex());

        iterator.previous();
        assertEquals(1, iterator.previousIndex());
    }
    @org.junit.jupiter.api.Test
    void remove() {
        SortedLinkedList<String> list = new SortedLinkedList<>();
        ListIterator<String> emptyIterator = list.listIterator();

        assertThrows(IllegalStateException.class, emptyIterator::remove);

        list.add("Item1");
        list.add("Item2");
        list.add("Item3");
        ListIterator<String> iterator = list.listIterator();

        iterator.next();
        assertEquals("Item1", iterator.previous());

        iterator.remove();

        assertEquals(2, list.size());
        assertFalse(list.contains("Item1"));

        assertTrue(list.contains("Item2"));
        assertTrue(list.contains("Item3"));
        assertThrows(IllegalStateException.class, emptyIterator::remove);

    }
    @org.junit.jupiter.api.Test
    void set() {
        SortedLinkedList<String> list = new SortedLinkedList<>();
        ListIterator<String> iterator = list.listIterator();
        assertThrows(UnsupportedOperationException.class, () -> {iterator.set("Item1");});
    }
    @org.junit.jupiter.api.Test
    void add() {
        SortedLinkedList<String> list = new SortedLinkedList<>();
        ListIterator<String> iterator = list.listIterator();
        assertThrows(UnsupportedOperationException.class, () -> {iterator.set("Item1");});
    }
    @org.junit.jupiter.api.Test
    void forEachRemaining() {
        SortedLinkedList<String> list = new SortedLinkedList<>();
        list.add("Item1");
        list.add("Item2");
        list.add("Item3");
        ListIterator<String> iterator = list.listIterator();

        List<String> collectedElements = new ArrayList<>();
        iterator.next();

        Consumer<String> elementConsumer = collectedElements::add;
        iterator.forEachRemaining(elementConsumer);

        assertEquals(2, collectedElements.size());
        assertTrue(collectedElements.contains("Item2"));
        assertTrue(collectedElements.contains("Item3"));
    }
    @org.junit.jupiter.api.Test
    void checkForComodification() {

    }
}