package linkedlist.main;

public class LinkedList {
    private Cell head;
    private int size;
    public LinkedList(){
        this.head = new Cell(-1);    // sentinel node init
        this.size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        head = new Cell(-1);
        size = 0;
    }
    public void add(int value){
        Cell current = head;
        while (current.next != null){
            current = current.next;
        }
        current.next = new Cell(value);
        size++;
    }

    // Sentinels are placeholder nodes that don't store actual data but simplify handling edge cases in the linked list
    // we did that by replacing top with top.next
    public Cell findCellBefore(int target){
        Cell current = head;
        while(current.next != null){
            if(current.next.value == target) return current;
            current = current.next;
        }
        return null;
    }

    public Cell findCell(int value) {
        Cell current = head;
        while (current != null) {
            if (current.value == value) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public int getValueAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        Cell current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }
    public void addAtBeginning(Cell newCell){
        newCell.next = head.next;
        head.next = newCell;
        size++;
    }
    public void addAtEnd(Cell newCell){
        Cell current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = newCell;
        newCell.next = null;
    }

    public void insertAfter(Cell cellBefore, int value){
        if (cellBefore == null){
            throw new IllegalArgumentException("The provided cell is null.");
        }
        Cell newCell = new Cell(value);
        newCell.next = cellBefore.next;
        cellBefore.next = newCell;
        size++;
    }

    public void deleteCell(Cell cellBefore){
        if (cellBefore == null || cellBefore.next == null) {
            throw new IllegalArgumentException("Invalid cell provided for deletion.");
        }
        Cell targetCell = cellBefore.next;
        cellBefore.next = targetCell.next;
        targetCell.next = null;
        size--;
    }
    // insert a cell into a (asc) sorted linked list
    public void insertCellToSorted(Cell newCell){
        Cell current = head;

        while(current != null && current.next.value < newCell.value){
            current = current.next;
            }
        newCell.next = current.next;
        current.next = newCell;
        size++;
    }
}