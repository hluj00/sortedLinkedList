package com.github.jhlubucek.sortedLinkedList;

import java.util.*;
import java.util.function.Consumer;

public class SortedLinkedList<T extends Comparable<T>> extends AbstractSequentialList<T> implements Cloneable, List<T> {
    private Node<T> head = null;
    private Node<T> last = null;

    private Order order = Order.ASC;

    private transient int size = 0;

    public SortedLinkedList() {
    }

    public SortedLinkedList(Order order) {
        this.order = order;
    }

    static class Node<T> {
        final private T data;
        private Node<T> next;
        private Node<T> previous;
        Node(T data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    public Node<T> getHead() {
        return head;
    }

    enum Order {
        ASC,
        DESC
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        // todo
        return null;
    }


    @Override
    public Object[] toArray() {
        Object[] a = new Object[size];
        int i = 0;
        for (Node<T> x = head; x != null; x = x.next)
            a[i++] = x.data;
        return a;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size)
            a = (T1[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (Node<T> x = head; x != null; x = x.next)
            result[i++] = x.data;

        if (a.length > size)
            a[size] = null;

        return a;
    }

    @Override
    public boolean add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            last = newNode;
        }else if (shouldOverride(head.data,newNode.data)){
            newNode.next = head;
            head.previous = newNode;
            head = newNode;

        }else {
            Node<T> current = head;
            while (current.next != null && !shouldOverride(current.next.data, newNode.data)) {
                current = current.next;
            }


            if (current.next != null) {
                current.next.previous = newNode;
            }else {
                last = newNode;
            }

            newNode.next = current.next;
            current.next = newNode;
            newNode.previous = current;
        }
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<T> x = head; x != null; x = x.next) {
                if (x.data == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<T> x = head; x != null; x = x.next) {
                if (o.equals(x.data)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] a = c.toArray();


        if (java.util.Objects.equals(a.length, 0)) {
            return false;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") T e = (T) o;
            add(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void clear() {
        // Clearing all of the links between nodes is "unnecessary", but better for memory management.
        for (Node<T> x = head; x != null; ) {
            Node<T> next = x.next;
            x.next = null;
            x.previous = null;
            x = next;
        }
        head = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return node(index).data;
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);

        Node<T> x = head;
        for (int i = 0; i < index; i++)
            x = x.next;
        unlink(x);
        return x.data;

    }


    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<T> x = head; x != null; x = x.next) {
                if (x.data == null)
                    return index;
                index++;
            }
        } else {
            for (Node<T> x = head; x != null; x = x.next) {
                if (o.equals(x.data))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        int currentIndex = 0;
        if (o == null) {
            for (Node<T> x = head; x != null; x = x.next) {
                if (x.data == null)
                    index = currentIndex;
                currentIndex++;
            }
        } else {
            for (Node<T> x = head; x != null; x = x.next) {
                if (o.equals(x.data))
                    index = currentIndex;
                currentIndex++;
            }
        }
        return index;
    }

    public SortedLinkedList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex > toIndex){
            throw  new IndexOutOfBoundsException();
        }
        checkElementIndex(fromIndex);
        checkElementIndex(toIndex);

        SortedLinkedList<T> subList = new SortedLinkedList<>(this.order);
        Node<T> current = head;
        Node<T> x = head;
        for (int i = 0; i < fromIndex; i++) {
            current = current.next;
        }
        for (int i = fromIndex; i <= toIndex; i++) {
            subList.add(current.data);
            current = current.next;
        }
        return subList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        for (Node<T> x = head; x != null; x = x.next){
            sb.append(x.data);
            if (x.next != null){
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }


    @Override
    protected SortedLinkedList<T> clone() {
        SortedLinkedList<T> clonedList = new SortedLinkedList<>(this.order);
        for (Node<T> x = head; x != null; x = x.next){
            clonedList.add(x.data);
        }
        return clonedList;
    }

    public ListIterator<T> listIterator(int index) {
        checkPositionIndex(index);
        return new SortedListIterator(index);
    }

    private class SortedListIterator implements ListIterator<T> {
        private Node<T> lastReturned;
        private Node<T> next;
        private int nextIndex;
        SortedListIterator(int index) {
            // assert isPositionIndex(index);
            next = java.util.Objects.equals(index, size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.data;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? last : next.previous;
            nextIndex--;
            return lastReturned.data;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<T> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
        }

        public void set(T e) {
            throw  new UnsupportedOperationException();
        }

        public void add(T e) {
            throw  new UnsupportedOperationException();
        }

        public void forEachRemaining(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            while (nextIndex < size) {
                action.accept(next.data);
                lastReturned = next;
                next = next.next;
                nextIndex++;
            }
        }
    }



    private boolean shouldOverride(T dataA, T dataB){

        //null is treated as lowest possible value
        if (dataA == null){
            return order == Order.DESC;
        }
        if (dataB == null){
            return order == Order.ASC;
        }

        if (this.order == Order.ASC){
            return dataA.compareTo(dataB) >= 0;
        }else {
            return dataA.compareTo(dataB) <= 0;
        }
    }

    private void unlink(Node<T> o){
        final T element = o.data;
        final Node<T> next = o.next;
        final Node<T> prev = o.previous;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            o.previous = null;
        }

        if (next != null) {
            next.previous = prev;
            o.next = null;
        }

        size--;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private Node<T> node(int index){
         assert isElementIndex(index);


        if (index < size >> 1) {
            Node<T> x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        }else {
            Node<T> x = last;
            for (int i = size-1; i > index; i--) {
                    x = x.previous;
                }
                return x;
            }
    }
}
