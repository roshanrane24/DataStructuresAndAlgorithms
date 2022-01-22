package data_structure.linked_list.linear;

import data_structure.linked_list.LinkedList;

import java.lang.reflect.Array;

public class SLinkedList<T> implements LinkedList<T> {
    // Node for LL
    private class Node {
        T data;
        Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head;
    Node tail;
    int size;

    public SLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    @Override
    public void addAtFront(T element) {
        // Create node & set it's next to head
        Node newNode = new Node(element);
        newNode.next = this.head;

        // Set head to newNode
        this.head = newNode;

        if (this.tail == null)
            this.tail = newNode;

        // increase size
        this.size++;
    }

    @Override
    public void addAtRear(T element) {
        // Create node
        Node newNode = new Node(element);

        // when list empty
        if (this.tail == null) {
            this.tail = newNode;
            this.head = newNode;
        } else {
            // current point to newNode & set tail to newNode
            this.tail.next = newNode;
            this.tail = newNode;
        }

        // increase size
        this.size++;
    }

    @Override
    public void add(int index, T element) {
        // Add at front
        if (index == 0) {
            this.addAtFront(element);
            return;
        }

        // Add at rear
        if (index == -1) {
            this.addAtRear(element);
            return;
        }

        // find node to add before
        Node current = this.head;
        Node previous = null;

        while (index != 0 && current != null) {
            previous = current;
            current = current.next;
            index--;
        }

        if (current == null) {
            this.addAtRear(element);

            // index is out of bound or not at end
            if (index != 0)
                System.out.println("Index out of bound adding element at rear.");

            return;
        }

        // Increment Size
        this.size++;

        // Add before Node
        Node newNode = new Node(element);
        previous.next = newNode;
        newNode.next = current;

    }

    @Override
    public T removeAll(T element) {
        // Empty List
        if (this.isEmpty())
            return null;

        T deleted = null;

        Node current = this.head;
        Node previous = null;

        // loop through list
        while (current != null) {
            if (current.data == element) {
                // Decrement size
                this.size--;

                deleted = current.data;
                if (current == this.head) {
                    // remove head
                    if (this.head == this.tail) {
                        // only one element
                        this.head = null;
                        this.tail = null;
                    } else {
                        this.head = this.head.next;
                    }
                } else {
                    // remove
                    previous.next = current.next;
                    // if deleting tail
                    if (current == this.tail) {
                        this.tail = previous;
                    }
                }
            }
            // move to next element
            previous = current;
            current = current.next;
        }

        return deleted;
    }

    @Override
    public T remove(int index) {
        // Remove at front
        if (index == 0)
            return this.removeFromFront();

        // Add at rear
        if (index == -1)
            return this.removeFromRear();

        // find node to remove
        Node current = this.head;
        Node previous = null;

        while (index != 0 && current != null) {
            previous = current;
            current = current.next;
            index--;
        }

        if (current == null)
            return null;

        // Decrement size
        this.size--;

        // remove node
        previous.next = current.next;
        // if deleting tail
        if (current == this.tail)
            this.tail = previous;

        return current.data;
    }

    @Override
    public T removeFromFront() {
        if (this.isEmpty())
            return null;

        T deleted = this.head.data;
        // Decrement size
        this.size--;


        // Single element
        if (this.tail == this.head) {
            this.head = null;
            this.tail = null;
            return deleted;
        }

        // Remove front
        this.head = this.head.next;

        return deleted;
    }

    @Override
    public T removeFromRear() {
        if (this.isEmpty())
            return null;

        T deleted = this.tail.data;
        // Decrement size
        this.size--;


        // Single element
        if (this.tail == this.head) {
            this.head = null;
            this.tail = null;
            return deleted;
        }

        // Remove rear
        Node current = this.head;

        // Find previous node to tail
        while (current.next != this.tail)
            current = current.next;

        // Remove & set new tail
        current.next = null;
        this.tail = current;

        return deleted;
    }

    @Override
    public boolean search(T element) {
        if (this.isEmpty())
            return false;

        // Loop until empty or found
        Node current = this.head;

        while (current != null && current.data != element)
            current = current.next;

        // Is Not found
        return current != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] getAllElements() {
        T[] result = null;
        Node current = this.head;
        int index = 0;

        // Initialize result array if list in not empty
        if (current != null)
            result = (T[]) Array.newInstance(current.data.getClass(), this.size);

        // loop through LL & add elements in result
        while (current != null) {
            result[index++] = current.data;
            current = current.next;
        }

        return result;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }
}
