
# Java SortedLinkedList

## Overview

The Java SortedLinkedList is a custom data structure that provides a sorted list implementation in Java. Unlike a traditional linked list, this data structure automatically keeps its elements sorted in ascending order or descending order.

## Features
* Automatically maintains elements in ascending or descending order.
* Supports standard list operations like insertion, deletion, and retrieval.
* Provides a iterator for easy traversal.
* requires a comparable class.
* implements List<T> interface

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your system (Java 8 or higher).

### Usage

#### Download or clone the SortedLinkedList Java source code to your project
#### Create a SortedLinkedList object:
`SortedLinkedList<String> stringList = new SortedLinkedList<>();`  
optional parameter order can be used (default option is ascending)
`SortedLinkedList<String> stringList = new SortedLinkedList<>(SortedLinkedList.Order.ASC);`
#### Add elements to the list:
`sortedList.add(5);`  
`sortedList.add(2);`  
`sortedList.add(8);`
#### Retrieve elements from the list (by index):
`intList.get(1)`
#### Remove elements from the list (by index or specific object):
`intList.remove(Integer.valueOf(4));`  
`intList.remove(4);`
#### Get index or last index
`intList.indexOf(2)`  
`intList.lastIndexOf(2)`
#### Add collection to the list
`List<Integer> elementsToAdd = Arrays.asList(1, 10);`  
`intList.addAll(elementsToAdd);`
#### Create sublist
`SortedLinkedList<Integer> subList = intList.subList(1,3);`
#### Create copy
`SortedLinkedList<Integer> clonedList = intList.clone();`