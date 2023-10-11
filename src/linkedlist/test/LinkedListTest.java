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
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.addByValue(5);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testAddToEmptyList() {
        list.addByValue(5);
        Cell head = list.findCell(5);
        assertNotNull(head);
        assertEquals(5, head.value);
    }

    @Test
    public void testAddMultipleValues() {
        list.addByValue(5);
        list.addByValue(10);
        list.addByValue(15);

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
    public void testAddAtBeginning() {
        list.addByValue(10);
        list.prepend(new Cell(5));
        assertEquals(5, list.getValueAt(0));
        assertEquals(10, list.getValueAt(1));
    }

    @Test
    public void testAddAtEnd() {
        list.addByValue(5);
        list.append(new Cell(10));
        assertEquals(5, list.getValueAt(0));
        assertEquals(10, list.getValueAt(1));
    }

    @Test
    public void testInsertAfter() {
        list.addByValue(5);
        list.addByValue(15);
        Cell cell5 = list.findCell(5);
        list.insertAfter(cell5, 10);
        assertEquals(5, list.getValueAt(0));
        assertEquals(10, list.getValueAt(1));
        assertEquals(15, list.getValueAt(2));
    }

    @Test
    public void testDeleteCell() {
        list.addByValue(5);
        list.addByValue(10);
        list.addByValue(15);
        Cell cell5 = list.findCellBefore(10);
        list.deleteCell(cell5);
        assertNull(list.findCell(10));
        assertEquals(2, list.getSize());
    }

    @Test
    public void testInsertCellToSorted() {
        list.addByValue(5);
        list.addByValue(15);
        list.insertCellToSorted(new Cell(10));
        assertEquals(5, list.getValueAt(0));
        assertEquals(10, list.getValueAt(1));
        assertEquals(15, list.getValueAt(2));
    }

    @Test
    public void testFindCellInEmptyList() {
        Cell result = list.findCell(5);
        assertNull(result);
    }

    @Test
    public void testFindCellNotInList() {
        list.addByValue(5);
        list.addByValue(10);
        list.addByValue(15);

        Cell result = list.findCell(20);
        assertNull(result);
    }

    @Test
    public void testFindCellInList() {
        list.addByValue(5);
        list.addByValue(10);
        list.addByValue(15);

        Cell result = list.findCell(10);
        assertNotNull(result);
        assertEquals(10, result.value);
    }

    @Test
    public void testGetSize() {
        assertEquals(0, list.getSize());
        list.addByValue(5);
        assertEquals(1, list.getSize());
        list.addByValue(10);
        assertEquals(2, list.getSize());
    }

    @Test
    public void testClear() {
        list.addByValue(5);
        list.addByValue(10);
        list.clear();
        assertEquals(0, list.getSize());
        assertNull(list.findCell(5));
        assertNull(list.findCell(10));
    }

    @Test
    public void testGetValueAt() {
        list.addByValue(5);
        list.addByValue(10);
        list.addByValue(15);

        assertEquals(5, list.getValueAt(0));
        assertEquals(10, list.getValueAt(1));
        assertEquals(15, list.getValueAt(2));

        assertThrows(IndexOutOfBoundsException.class, () -> list.getValueAt(3));
    }

    @Test
    public void testCopyList() {
        list.addByValue(1);
        list.addByValue(3);
        list.addByValue(5);
        list.addByValue(7);

        LinkedList copy = new LinkedList(list);

        Cell originalCurrent = list.getHead().next;
        Cell copyCurrent = copy.getHead().next;

        while (originalCurrent != null && copyCurrent != null) {
            assertEquals(originalCurrent.value, copyCurrent.value);
            originalCurrent = originalCurrent.next;
            copyCurrent = copyCurrent.next;
        }

        assertNull(originalCurrent);
        assertNull(copyCurrent);
    }

    @Test
    void testCopyConstructorSize(){
        list.addByValue(2);
        list.addByValue(5);
        list.addByValue(8);
        LinkedList other = new LinkedList(list);

        assertEquals(list.getSize(), other.getSize());

        list.addByValue(9);
        assertNotEquals(list.getSize(), other.getSize());
    }

    @Test
    void testInsertionSort(){
        list.populate(new int[]{34, 27, 3, 5, 31});
        list.insertionSort();
        LinkedList expected = new LinkedList();
        expected.populate(new int[]{3, 5, 27, 31, 34});

        assertEquals(0, list.compareTo(expected));
    }
    @Test
    void testInsertionSortEmptyList() {
        list.insertionSort();
        LinkedList expected = new LinkedList();

        assertEquals(0, list.compareTo(expected));
    }

    @Test
    void testInsertionSortSingleElement() {
        list.populate(new int[]{5});
        list.insertionSort();
        LinkedList expected = new LinkedList();
        expected.populate(new int[]{5});

        assertEquals(0, list.compareTo(expected));
    }

    @Test
    void testSelectionSort() {
        list.populate(new int[]{34, 27, 3, 5, 31});
        list.selectionSort();
        LinkedList expected = new LinkedList();
        expected.populate(new int[]{3, 5, 27, 31, 34});

        assertEquals(0, list.compareTo(expected));
    }

    @Test
    void testSelectionSortEmptyList() {
        list.selectionSort();
        LinkedList expected = new LinkedList();

        assertEquals(0, list.compareTo(expected));
    }

    @Test
    void testSelectionSortSingleElement() {
        list.populate(new int[]{5});
        list.selectionSort();
        LinkedList expected = new LinkedList();
        expected.populate(new int[]{5});

        assertEquals(0, list.compareTo(expected));
    }

    @Test
    void testReverseLinkedList(){
        list.populate(new int[]{1, 14, 5, 5, 7});
        list.reverse();

        LinkedList expected = new LinkedList();
        expected.populate(new int[]{7, 5, 5, 14, 1});

        assertEquals(0, list.compareTo(expected));
    }
}
