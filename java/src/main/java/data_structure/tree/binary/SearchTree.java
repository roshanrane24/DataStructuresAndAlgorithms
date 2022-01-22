package data_structure.tree.binary;

public interface SearchTree<T> {
    void insert(T element);

    T remove(T element);

    int removeAll(T element);

    boolean search(T element);

    T[] getInorder();

    T[] BFS();
}
