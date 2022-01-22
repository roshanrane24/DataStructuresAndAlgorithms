package data_structure.linked_list.linear;

import data_structure.linked_list.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SLinkedListTest {
    static LinkedList<Integer> list;
    @BeforeEach
    void setUp() {
        list = new SLinkedList<>();
    }

    @Test
    void testEmpty () {
        assertTrue(list.isEmpty());
    }

    @Test
    void testAddAtFront() {
        list.addAtFront(5);
        list.addAtFront(2);
        list.addAtFront(7);
        list.addAtFront(4);
        list.addAtFront(6);
        list.addAtFront(9);

        assertArrayEquals(new Integer[] {9, 6, 4, 7, 2, 5}, list.getAllElements());
    }

    @Test
    void testAddAtRear() {
        list.addAtRear(5);
        list.addAtRear(2);
        list.addAtRear(7);
        list.addAtRear(4);
        list.addAtRear(6);
        list.addAtRear(9);

        assertArrayEquals(new Integer[] {5, 2, 7, 4, 6, 9}, list.getAllElements());
    }

    @Test
    void testAddAtIndex() {
        list.addAtRear(5);
        list.addAtRear(2);
        list.addAtRear(7);
        list.addAtRear(4);
        list.addAtRear(6);
        list.addAtRear(9);

        // test add at start
        list.add(0, 2);
        assertArrayEquals(new Integer[] {2, 5, 2, 7, 4, 6, 9}, list.getAllElements());

        // test add at end
        list.add(-1, 1);
        assertArrayEquals(new Integer[] {2, 5, 2, 7, 4, 6, 9, 1}, list.getAllElements());

        // test add within
        list.add(3, 6);
        assertArrayEquals(new Integer[] {2, 5, 2, 6, 7, 4, 6, 9, 1}, list.getAllElements());

        // test out of index
        list.add(13, 6);
        assertArrayEquals(new Integer[] {2, 5, 2, 6, 7, 4, 6, 9, 1, 6},
                list.getAllElements());
    }

    @Test
    void testRemoveFromFront() {
        list.addAtRear(5);
        list.addAtRear(2);
        list.addAtRear(7);
        list.addAtRear(4);
        list.addAtRear(6);
        list.addAtRear(9);

        list.removeFromFront();

        assertArrayEquals(new Integer[] {2, 7, 4, 6, 9}, list.getAllElements());
    }

    @Test
    void testRemoveFromRear() {
        list.addAtRear(5);
        list.addAtRear(2);
        list.addAtRear(7);
        list.addAtRear(4);
        list.addAtRear(6);
        list.addAtRear(9);

        list.removeFromRear();

        assertArrayEquals(new Integer[] {5, 2, 7, 4, 6}, list.getAllElements());
    }

    @Test
    void testRemoveFromIndex() {
        list.addAtRear(5);
        list.addAtRear(2);
        list.addAtRear(7);
        list.addAtRear(4);
        list.addAtRear(6);
        list.addAtRear(9);

        // remove mid
        assertEquals(7, list.remove(2));
        assertArrayEquals(new Integer[] {5, 2, 4, 6, 9}, list.getAllElements());

        // Remove Front
        assertEquals(5, list.remove(0));
        assertArrayEquals(new Integer[] {2, 4, 6, 9}, list.getAllElements());

        // Remove Rear
        assertEquals(9, list.remove(-1));
        assertArrayEquals(new Integer[] {2, 4, 6}, list.getAllElements());

        // remove out of index
        assertNull(list.remove(5));
        assertArrayEquals(new Integer[] {2, 4, 6}, list.getAllElements());
    }

    @Test
    void testRemoveAll() {
        list.addAtRear(5);
        list.addAtRear(2);
        list.addAtRear(7);
        list.addAtRear(4);
        list.addAtRear(2);
        list.addAtRear(6);
        list.addAtRear(9);
        list.addAtRear(2);

        assertArrayEquals(new Integer[] {5, 2, 7, 4, 2, 6, 9, 2}, list.getAllElements());

        // test remove all Occurrence
        list.removeAll(2);
        assertArrayEquals(new Integer[] {5, 7, 4, 6, 9}, list.getAllElements());
    }

    @Test
    void testSearch() {
        list.addAtRear(5);
        list.addAtRear(2);
        list.addAtRear(7);
        list.addAtRear(4);
        list.addAtRear(6);
        list.addAtRear(9);

        assertTrue(list.search(6));
        assertFalse(list.search(16));
    }
}