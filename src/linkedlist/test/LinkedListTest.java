package linkedlist.test;

import linkedlist.main.LinkedList;
import linkedlist.main.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    private LinkedList list;

    @BeforeEach
    public void setUp() {
        list = new LinkedList();
    }

    @Test
    public void testAddToEmptyList() {
        list.add(5);
        Cell head = list.findCell(5);
        assertNotNull(head);
        assertEquals(5, head.value);
    }

    @Test
    public void testAddMultipleValues() {
        list.add(5);
        list.add(10);
        list.add(15);

        Cell first = list.findCell(5);
        Cell second = list.findCell(10);
        Cell third = list.findCell(15);

        assertNotNull(first);
        assertNotNull(second);
        assertNotNull(third);

        assertEquals(5, first.value);
        assertEquals(10, second.value);
        assertEquals(15, third.value);
    }

    @Test
    public void testFindCellInEmptyList() {
        Cell result = list.findCell(5);
        assertNull(result);
    }

    @Test
    public void testFindCellNotInList() {
        list.add(5);
        list.add(10);
        list.add(15);

        Cell result = list.findCell(20);
        assertNull(result);
    }

    @Test
    public void testFindCellInList() {
        list.add(5);
        list.add(10);
        list.add(15);

        Cell result = list.findCell(10);
        assertNotNull(result);
        assertEquals(10, result.value);
    }

    @Test
    public void testGetSize() {
        assertEquals(0, list.getSize());
        list.add(5);
        assertEquals(1, list.getSize());
        list.add(10);
        assertEquals(2, list.getSize());
    }

    @Test
    public void testClear() {
        list.add(5);
        list.add(10);
        list.clear();
        assertEquals(0, list.getSize());
        assertNull(list.findCell(5));
        assertNull(list.findCell(10));
    }

    @Test
    public void testGetValueAt() {
        list.add(5);
        list.add(10);
        list.add(15);

        assertEquals(5, list.getValueAt(0));
        assertEquals(10, list.getValueAt(1));
        assertEquals(15, list.getValueAt(2));

        assertThrows(IndexOutOfBoundsException.class, () -> list.getValueAt(3));
    }
}
