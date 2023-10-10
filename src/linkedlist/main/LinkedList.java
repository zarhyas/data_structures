package linkedlist.main;

public class LinkedList implements Comparable<LinkedList>{
    private Cell head;
    private int size;
    public LinkedList(){
        this.head = new Cell(-1);    // sentinel node init
        this.size = 0;
    }

    public LinkedList(LinkedList other) {
        this.head = new Cell(-1);
        Cell copy = other.head.next;
        Cell original = this.head;
        this.size = 0;

        while (copy != null) {
            original.next = new Cell(copy.value);
            original = original.next;
            copy = copy.next;
            this.size++;
        }
        original.next = null;
    }

    public int getSize(){
        return size;
    }

    public Cell getHead(){
        return head;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        head = new Cell(-1);
        size = 0;
    }

    public void populate(int[] values){
        for (int value: values){
            addByValue(value);
        }
        size = values.length;
    }
    public void addByValue(int value){
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
    public void prepend(Cell newCell){
        newCell.next = head.next;
        head.next = newCell;
        size++;
    }
    public void append(Cell newCell){
        Cell current = head.next;
        while(current.next != null){
            current = current.next;
        }
        current.next = newCell;
        newCell.next = null;
        size++;
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
        Cell current = head.next;

        while(current != null && current.next.value < newCell.value){
            current = current.next;
            }
        newCell.next = current.next;
        current.next = newCell;
        size++;
    }

    public static LinkedList copyList(LinkedList original){
        LinkedList copy = new LinkedList();

        Cell current = original.getHead().next;
        Cell currentInCopy = copy.getHead();

        while(current != null){
            currentInCopy.next = new Cell(current.value);
            currentInCopy = currentInCopy.next;
            current = current.next;
        }
        currentInCopy.next = null;
        return copy;
    }

    public void insertionSort(){
        Cell current = head.next;

        while(current != null && current.next != null){
            if(current.next.value < current.value) {
                Cell toInsert = current.next;       // first keep
                current.next = current.next.next;   // then remove the node to be inserted

                Cell restart = head;

                // loop to find insertion point
                while (restart.next != null && restart.next.value < toInsert.value) {
                    restart = restart.next;
                }

                // insert the node
                toInsert.next = restart.next;
                restart.next = toInsert;
            }
            else {
                current = current.next;
                }
            }
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Elements of the linked list: ");

        Cell current = head.next;
        while(current != null){
            builder.append(current.value);
            if (current.next != null) {
                builder.append(" , ");
            }
            current = current.next;
        }
        return builder.toString();
    }


    @Override
    public int compareTo(LinkedList list) {
        Cell c1 = list.head;
        Cell c2 = head;

        while(c1.next != null && c2.next != null){
            if(c1.next.value != c2.next.value) {
                return c1.value - c2.value;
            }
                c1 = c1.next;
                c2 = c2.next;
        }
        if(list.getSize() == size) return 0;
        if(c1.next != null) return 1;
        return -1;
    }
}