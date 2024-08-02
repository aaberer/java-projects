import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;
public class  MyLinkedList<E>{
    public class Node {
        E data;
        Node next;
        public Node(E data) {
            this.data = data;
        }
        @Override
        public String toString(){
            return data.toString();
        }
    }
    Node head;
	Node tail;
    int size;

    /**TODO - Complete this Method
     * The constructor should initialize head, tail and size
     *
     */
    public MyLinkedList(){
        head = null;
        tail = null;
        size = 0;
	}

    /**TODO - Complete this Method
     * This method should add at element to the end of the linked list
	 * Notice you have a tail pointer so adding to the end should be simple
     * Make sure to make special cases for an empty list!(Hint: initialize the Head variable)
     * @param item
     */
    public void add(E item){
        Node nNode = new Node(item);
        if (head == null){
            head = nNode;
            tail = head;
        }
        else{
            Node curr = head;
            while (curr.next != null){
                curr = curr.next;
            }
            curr.next = nNode;
        }
        ++size;
    }

    /**TODO - Complete this Method
     * This method should return the node at the given index
     * @param index
     */
    public Node getNode(int index){
    	if ((size <= 0) || (index > size)){
            throw new NoSuchElementException();
        }
        Node itr = head;
        for (int i = 0; i < index; ++i){
            itr = itr.next;
        }
        return itr;
	}
    
    public class MyIterator implements ListIterator<E> {
        int nextI;
        int size;
        Node nde;
        public MyIterator(Node head, int size) {
            nextI = size - 1;
            this.size = size;
            nde = head;
        }

        /** TODO - Complete this Method
         * Returns a boolean indicating if the iterator has a next element
         * @return if the iterator has a next element this should return True, Otherwise False
         */
        public boolean hasNext() {
            if (tail.next == null){
                return false;
            }
        	return nde != null;
		}

        /** TODO - Complete this Method
         * This should return the next item or NoSuchElementException if there are no more elements left
         * @return the next item
         * @throws java.util.NoSuchElementException
         */
        public E next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            E nxt = nde.data;
            nde = nde.next;
        	return nxt;
		}

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            return nextI;
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
    public MyIterator listIterator(){
        return new MyIterator(head, size);
    }
    public String toString(){
        String ret = "";
        Node cur = head;
        while(cur != null){
            ret += cur + " ";
            cur = cur.next;
        }
        return ret;
    }
    public static void main(String[] args){ 
		Random r = new Random(678); // 678, 2020
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for(int i = 0; i < 10; i++){
            int num = r.nextInt(1000);
            list.add(num);
        }
		System.out.println("Testing add():");
        System.out.println(list);
		System.out.println("size:\texpected: 10 -> actual: " + list.size);	
		if(list.size != 10) //exit if add/size doesn't work
			return;

		System.out.println("\nTesting getNode():");
		MyLinkedList<Integer> list2 = new MyLinkedList<>();
		list2.add(1);
		list2.add(2);
		list2.add(3);
		System.out.println("item at index 0:\texpected: 1 -> actual: " + list2.getNode(0).data);
		System.out.println("item at index 1:\texpected: 2 -> actual: " + list2.getNode(1).data);
		System.out.println("item at index 2:\texpected: 3 -> actual: " + list2.getNode(2).data);
        
		//Iterator Test Code
        System.out.println("\nTesting Iterator: ");
		ListIterator<Integer> iter = list.listIterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println("size: " + list.size);
        System.out.println("next Index: " + iter.nextIndex());
        try{
            iter.next();
        }catch(NoSuchElementException e){
            System.err.println("You threw the correct error!");
		}catch(Exception e){
			System.err.println("You throw the wrong type of Error. Make sure it is a NoSuchElementException.");
		}
    }
}
