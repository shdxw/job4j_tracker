package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public Node<T> addRev(Node<T> newHead, T value) {
        Node<T> node = new Node<T>(value, null);
        if (newHead == null) {
            newHead = node;
            return newHead;
        }
        Node<T> tail = newHead;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        return newHead;
    }

    public void revert() {
        Node<T> previous = null;
        Node<T> current = head;
        Node<T> forward;

        while (current != null) {
            forward = current.next;
            current.next = previous;
            previous = current;
            current = forward;
        }
        head = previous;
    }

    public void add2(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        } else {
            node.next = head;
            head = node;
        }
    }

    public void deleteFirst() {
        if (head != null) {
            head = head.next;
            return;
        }
        throw new NoSuchElementException();
    }

    public T deleteLast() {
        Node<T> mark = head;
        Node<T> temp = head;
        if (mark != null) {
            if (mark.next == null) {
                T rsl = head.value;
                head = null;
                return rsl;
            }
            while (mark.next != null) {
                temp = mark;
                mark = mark.next;
            }
            temp.next = null;
            return mark.value;
        }
        throw new NoSuchElementException();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}