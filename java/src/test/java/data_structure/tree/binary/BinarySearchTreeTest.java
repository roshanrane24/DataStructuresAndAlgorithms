package data_structure.tree.binary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    SearchTree<Integer> bstEmpty;
    SearchTree<Integer> bst;

    @BeforeEach
    void setUp() {
        bstEmpty = new BinarySearchTree<>();
        bst = new BinarySearchTree<>();

        bst.insert(40);
        bst.insert(4);
        bst.insert(45);
        bst.insert(34);
        bst.insert(55);
        bst.insert(14);
        bst.insert(48);
        bst.insert(13);
        bst.insert(15);
        bst.insert(47);
        bst.insert(49);

    }

    @Test
    void getInorder() {
        assertNull(bstEmpty.getInorder());
        assertArrayEquals(new Integer[]{4, 13, 14, 15, 34, 40, 45, 47, 48, 49, 55}, bst.getInorder());
    }

    @Test
    void insert() {
        bstEmpty.insert(40);

        // Single element
        assertArrayEquals(new Integer[]{40}, bstEmpty.getInorder());

        // populate tree level 1
        bstEmpty.insert(4);
        bstEmpty.insert(45);

        assertArrayEquals(new Integer[]{4, 40, 45}, bstEmpty.getInorder());

        // populate tree
        bstEmpty.insert(34);
        bstEmpty.insert(55);
        bstEmpty.insert(14);
        bstEmpty.insert(48);
        bstEmpty.insert(13);
        bstEmpty.insert(15);
        bstEmpty.insert(47);
        bstEmpty.insert(49);

        assertArrayEquals(new Integer[]{4, 13, 14, 15, 34, 40, 45, 47, 48, 49, 55}, bstEmpty.getInorder());

        // duplicate element
        bstEmpty.insert(40);
        assertArrayEquals(new Integer[]{4, 13, 14, 15, 34, 40, 40, 45, 47, 48, 49, 55}, bstEmpty.getInorder());
    }

    @Test
    void remove() {
        // Non existing element
        assertNull(bst.remove(10));

        // delete leaf
        bst.remove(49);
        assertArrayEquals(new Integer[]{4, 13, 14, 15, 34, 40, 45, 47, 48, 55}, bst.getInorder());

        // delete Single child node
        bst.remove(48);
        assertArrayEquals(new Integer[]{4, 13, 14, 15, 34, 40, 45, 47, 55}, bst.getInorder());

        // delete Node with 2 child
        bst.remove(14);
        assertArrayEquals(new Integer[]{4, 13, 15, 34, 40, 45, 47, 55}, bst.getInorder());

        // delete root node
        bst.remove(40);
        assertArrayEquals(new Integer[]{4, 13, 15, 34, 45, 47, 55}, bst.getInorder());
    }

    @Test
    void removeAll() {
        // duplicate values
        bst.insert(34);
        bst.insert(34);
        bst.insert(34);
        bst.insert(34);

        assertArrayEquals(new Integer[]{4, 13, 14, 15, 34, 34, 34, 34, 34, 40, 45, 47, 48, 49, 55}, bst.getInorder());

        // remove all with duplicates
        assertEquals(5, bst.removeAll(34));
        assertArrayEquals(new Integer[]{4, 13, 14, 15, 40, 45, 47, 48, 49, 55}, bst.getInorder());
    }

    @Test
    void search() {
        assertTrue(bst.search(48));
        assertFalse(bst.search(50));
    }

    @Test
    void BFS() {
        assertArrayEquals(new Integer[] {40, 4, 45, 34, 55, 14, 48, 13, 15, 47, 49}, bst.BFS());
    }
}