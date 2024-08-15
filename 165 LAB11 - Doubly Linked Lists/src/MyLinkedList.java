/** Linked List Lab
 * Made by Andrew Aberer 10/6/23
 * For CS165 at CSU
 */

public class MyLinkedList<E> implements MiniList<E>{
    /* Private member variables that you need to declare:
     ** The head pointer
     ** The tail pointer
     */
    private Node head;
    private int tail;

    public class Node {
        // declare member variables (data, prev and next)
        E data;
        Node prev;
        Node next;
        // finish these constructors
        public Node(E data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
        public Node(E data) {
            this(data, null, null);
        }
    }

    // Initialize the head and tail pointer
    public MyLinkedList() {
        head = null;
        tail = 0;
     }

    @Override
    public boolean add(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        tail += 1;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if ((index < 0) || (index > tail)){
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(element);
        if (index == 0){
            newNode.next = head;
            head = newNode;
        } else {
            Node curr = head;
            for (int i = 0; i < index - 1; i++){
                curr = curr.next;
            }
            newNode.next = curr.next;
            curr.next = newNode;
        }
        tail += 1;
    }

    @Override
    public E remove() {
        if (isEmpty()) return null;
        E remData = head.data;
        head = head.next;
        tail -= 1;
        return remData;
    }

    @Override
    public E remove(int index) {
        if ((index < 0) || (index > tail)){
            throw new IndexOutOfBoundsException();
        }
        if (index == 0){
            E remData = head.data;
            head = head.next;
            tail -= 1;
            return remData;
        } else {
            Node curr = head;
            for (int i = 0; i < index - 1; i++){
                curr = curr.next;
            }
            E remData = curr.next.data;
            curr.next = curr.next.next;
            tail -= 1;
            return remData;
        }
    }

    @Override
    public boolean remove(E item) {
        if (isEmpty()) return false;
        if (head.data == item){
            head = head.next;
            tail -= 1;
            return true;
        } else {
            Node curr = head;
            while (curr.next != null){
                if (curr.next.data == item){
                    curr.next = curr.next.next;
                    tail -= 1;
                    return true;
                }
                curr = curr.next;
            }
            return false;
        }
    }

    @Override
    public void clear() {
        head = null;
        tail = 0;
    }

    @Override
    public boolean contains(E item) {
        Node curr = head;
        while (curr != null){
            if (curr.data == item){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        if ((index < 0) || (index > tail)){
            throw new IndexOutOfBoundsException();
        }
        Node curr = head;
        for (int i = 0; i < index; ++i){
            curr = curr.next;
        }
        return curr.data;
    }

    @Override
    public int indexOf(E item) {
        Node curr = head;
        int idx = 0;
        while (curr != null){
            if (curr.data == item) {
                return idx;
            }
            curr = curr.next;
            ++idx;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (tail == 0) return true;
        return false;
    }

    @Override
    public int size() {
        return tail;
    }

    // Uncomment when ready to test
   @Override
   public String toString() {
       String ret = "";
       Node curr = head;
       while (curr != null) {
           ret += curr.data + " ";
           curr = curr.next;
       }
       return ret;
   }

}