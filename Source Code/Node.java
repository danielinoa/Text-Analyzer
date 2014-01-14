


public class Node {

	private Node next; // Next node (for reference use) 
	private Object item; // Object to hold
	private int count=1; // Count ---> used for occurrence
	private int length; // --- used for String length of the Object held
	
	
	// Constructor 1
	public Node() {
		this.item = null;
		this.next = null;
	}

	// Constructor 2
	public Node(Node next) {
		this.next=next;
	}

	// Constructor 3
	public Node(Object item) {
		this.item = item;
		length = ((String)item).length();
	}

	// Constructor 4
	public Node(Object item, Node next) {
		this.item = item;
		this.next = next;
		length = ((String)item).length();
	}

	// Constructor 5
	public Node(Node next, Object item){
		this.item = item;
		this.next = next;
		length = ((String)item).length();
	}

	// Set the reference to the inner node
	public void setNext(Node next) {
		this.next = next;
	}

	// Get the reference from the inner node
	public Node getNext() {
		return this.next;
	}

	// Set the object to be held
	public void setItem(Object item) {
		this.item = item;
	}

	// Get the object held
	public Object getItem() {
		return this.item;
	}
	
	// Get count
	public int getCount() {
		return count;
	}
	
	// Get length
	public int getLength() {
		return length;
	}
	
	// Increment the count by one
	public void incrementCount() {
		count++;
	}
	
	// Decrease the count by one
	public void decrementCount(){
		count--;
	}
	
}
