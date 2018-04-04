package ru.ifmo.rain.nachkin.arrayset;

import java.util.*;

public class ArraySet<E> extends AbstractSet<E> implements SortedSet<E> {
    private final List<E> elements;
    private final Comparator<? super E> comparator;

    public ArraySet() {
        elements = Collections.emptyList();
        comparator = null;
    }

    public ArraySet(Collection<E> collection) {
        this(collection, null);
    }

    public ArraySet(Collection<E> collection, Comparator<? super E> comp) {
        TreeSet<E> treeSet = new TreeSet<>(comp);
        treeSet.addAll(collection);
        elements = new ArrayList<>(treeSet);
        comparator = comp;
    }

    private ArraySet(List<E> list, Comparator<? super E> comp, boolean sort) {
        elements = list;
        comparator = comp;
    }

    @Override
    public Iterator<E> iterator() {
        return Collections.unmodifiableList(elements).iterator();
    }

    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public E first() {
        if (isEmpty()) {
            throw new NoSuchElementException("No first element");
        }
        return elements.get(0);
    }

    @Override
    public E last() {
        if (isEmpty()) {
            throw new NoSuchElementException("No last element");
        }
        return elements.get(size() - 1);
    }

    @Override
    @SuppressWarnings("Unchecked")
    public boolean contains(Object o) {
        return Collections.binarySearch(elements, (E)o, comparator) >= 0;
    }

    private int positionCheck(int i) {
        if (i < 0) {
            return (-i - 1);
        }
        return i;
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        int posTo = positionCheck(Collections.binarySearch(elements, toElement, comparator));
        return new ArraySet<>(elements.subList(0, posTo), comparator, true);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        int posFrom = positionCheck(Collections.binarySearch(elements, fromElement, comparator));
        return new ArraySet<>(elements.subList(posFrom, size()), comparator, true);
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        int posFrom = positionCheck(Collections.binarySearch(elements, fromElement, comparator)), posTo = positionCheck(Collections.binarySearch(elements, toElement, comparator));
        if (posFrom > posTo) {
            return new ArraySet<>();
        }
        return new ArraySet<>(elements.subList(posFrom, posTo), comparator, true);
    }
}

// java -cp D:\Programms\Java\Java_Technologies\HW2_ArraySet\lib\*;D:\Programms\Java\Java_Technologies\HW2_ArraySet\out\production\HW2_ArraySet;D:\Programms\Java\Java_Technologies\HW2_ArraySet\artifacts\ArraySetTest.jar info.kgeorgiy.java.advanced.arrayset.Tester SortedSet ru.ifmo.rain.ionov.arrayset.ArraySet
