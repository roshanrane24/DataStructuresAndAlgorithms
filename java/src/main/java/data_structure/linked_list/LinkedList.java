package data_structure.linked_list;

public interface LinkedList<T> {
    void add(int index, T element);

    void addAtFront(T element);

    void addAtRear(T element);

    T removeAll(T element);

    T remove(int index);

    T removeFromFront();

    T removeFromRear();

    boolean search(T element);

    T[] getAllElements();

    boolean isEmpty();
}
