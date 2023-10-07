package linkedlist.main;

public class LinkedList {
    private Cell head;
    private int size;
    public LinkedList(){
        this.head = null;
        this.size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        head = null;
        size = 0;
    }
    public void add(int value){
        if(head == null){
            head = new Cell(value);
        }
        else{
            Cell current = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = new Cell(value);
        }
        size++;
    }

    // Sentinels are placeholder nodes that don't store actual data but simplify handling edge cases in the linked list
    // we did that by replacing top with top.next
    public Cell findCellBefore(Cell head, int target){
        while(head.next != null){
            if(head.next.value == target) return head;
            head = head.next;
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
        Cell current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }
    public void addAtBeginning(Cell head, Cell newCell){
        newCell.next = head.next;
        head.next = newCell;
        size++;
    }
    public void addAtEnd(Cell head, Cell newCell){
        while(head.next!=null){
            head = head.next;
        }
        head.next = newCell;
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
}