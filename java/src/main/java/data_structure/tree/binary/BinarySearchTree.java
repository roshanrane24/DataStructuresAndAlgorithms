package data_structure.tree.binary;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> implements SearchTree<T> {

    //  BST Node
    private class Node {
        T data;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
        }

        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void insert(T element) {
        // Create new Node
        Node newNode = new Node(element);
        this.size++;

        // tree empty
        if (this.isEmpty()) {
            this.root = newNode;
            return;
        }

        // find parent of node
        Node previous = null;
        Node current = this.root;

        while (current != null) {
            previous = current;
            if (element.compareTo(current.data) <= 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // add child to parent
        if (element.compareTo(previous.data) <= 0) {
            previous.left = newNode;
        } else {
            previous.right = newNode;
        }
    }

    @Override
    public T remove(T element) {
        // tree empty
        if (this.isEmpty())
            return null;

        T deleted = null;
        Node previous = null;
        Node current = this.root;

        // find node to delete
        while (current != null && !current.data.equals(element)) {
            previous = current;
            if (element.compareTo(current.data) < 0)
                current = current.left;
            else
                current = current.right;
        }

        // Is element found
        if (current == null)
            return deleted;

        deleted = current.data;
        // delete current
        while (!current.isLeaf()) {
            Node tempNode = current;
            previous = current;

            if (current.left != null) {
                current = current.left;
                // Find predecessor
                while (current.right != null) {
                    previous = current;
                    current = current.right;
                }
            } else if (current.right != null) {
                current = current.right;
                // Find successor
                while (current.left != null) {
                    previous = current;
                    current = current.left;
                }
            }
            tempNode.data = current.data;
        }

        //decrement size
        this.size--;

        // deleting root node
        if (previous == null) {
            this.root = null;
            return current.data;
        }

        // Deleting leaf node
        if (current == previous.left)
            previous.left = null;
        else
            previous.right = null;

        return deleted;
    }

    @Override
    public int removeAll(T element) {
        int count = 0;

        // remove until no node is removed
        while (this.remove(element) != null)
            count++;

        return count;
    }

    @Override
    public boolean search(T element) {

        // tree empty
        if (this.isEmpty())
            return false;

        Node current = this.root;

        // find node
        while (current != null && !current.data.equals(element)) {
            if (element.compareTo(current.data) < 0)
                current = current.left;
            else
                current = current.right;
        }

        // Is element found
        return current != null;
    }

    @Override
    public T[] getInorder() {
        if (this.isEmpty())
            return null;

        // create result array
        //noinspection unchecked
        T[] result = (T[]) Array.newInstance(this.root.data.getClass(), this.size);
        int count = 0;

        // TODO : Use user made stack
        Stack<Node> stack = new Stack<>();

        // Get until array empty
        Node current = this.root;
        while (current != null || !stack.isEmpty()) {
            // Find left most unprocessed node in subtree
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // get node from stack Process current node
            if (!stack.isEmpty()) {
                current = stack.pop();
                result[count++] = current.data;
                current = current.right;
            }
        }

        return result;
    }

    @Override
    public T[] BFS() {
        if (this.isEmpty()) return null;

        @SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(this.root.data.getClass(), this.size);
        int count = 0;

        // Setup queue
        // TODO : Use user made queue
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);

        // process Queue
        while (!queue.isEmpty()) {
            Node current = queue.remove();

            // process removed node
            result[count++] = current.data;

            // Add children to queue
            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
        }

        return result;
    }

    private boolean isEmpty() {
        return this.root == null;
    }
}
