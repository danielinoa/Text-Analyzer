



public class List{

	protected Node root; // head of the list
	private int words; // number of nodes in the list
	public List() {
		root = null;
	}

	/**
	 * Sets root
	 * @param root
	 */
	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * Adds node to the start of the list
	 * @param item
	 */
	public void addToFirst(Object item) {
		Node ph = new Node();
		ph.setItem(item);
		ph.setNext(root);
		root = ph;
		words++; // increment the word count
	}

	/**
	 * Adds node to the end of the list
	 * @param item
	 */
	public void addToLast(Object item) {

		Node temp = new Node(item);

		if(isEmpty()) {
			root=temp;
		} else {
			Node current = root;
			while(current.getNext() != null){
				current = current.getNext();
			}
			current.setNext(temp);
		}
		words++; // increments the word count
	}

	/**
	 * Removes the first node from the List
	 * @return Object from deleted Node
	 * @throws ListException
	 */
	public Object removeFirst() throws ListException {
		if(isEmpty())
			throw new ListException("Empty List");

		Object result = root.getItem();
		root = root.getNext();
		words--; // decrease the word count
		return result;
	}

	/**
	 * Removes the first node from the List
	 * @return Object from deleted Node
	 * @throws ListException
	 */
	public Object removeLast() throws ListException {

		if(isEmpty())
			throw new ListException("Empty List");

		Node current = root, previous = null;

		while(current!=null) {
			if(current.getNext()!=null)
				previous = current;
			current = current.getNext();
		}

		Object result = previous.getNext().getItem();
		previous.setNext(null);
		words--; // decrease the word count
		return result;
	}

	/**
	 * Removes the first node from the List
	 * @return Object from deleted Node
	 * @throws ListException
	 */
	public Object pop() throws ListException {

		if(isEmpty())
			throw new ListException("Empty List");

		Object ph = root.getItem();
		root = root.getNext();
		words--;
		return ph;
	}

	/**
	 * Get number of nodes in the list
	 * @return words
	 */
	public int getWordCount() {
		return words;
	}
	
	/**
	 * Determines whether the list is empty
	 * @return
	 */
	public boolean isEmpty() {
		return root==null;
	}

	/**
	 * Retuns the head of the list
	 * @return
	 */
	public Node getRoot() {
		return root;
	}

	public String toString() {
		Node current = root;
		String output = "";
		while(current != null){
			output += "[" + current.getItem().toString() + "] \n";
			current = current.getNext();
		}
		return output;
	}

	/**
	 * This method determines if the Object give through the formal parameters is
	 * represented by the objects in one of the nodes in the list 
	 * @param object
	 * @return
	 */
	public boolean contains(Object object) {
		boolean result=false;
		Node current = root;
		while(current!=null) {
			if(((String)current.getItem()).equals(object.toString())){ // if it is already represented in one of the nodes in the list
				result = true; // then we set result to be true
			}
			current = current.getNext(); // and keep looking for the other nodes
		}
		return result;
	}

	/**
	 * This method returns the length of a list
	 * @param someHead Node of the list
	 * @return
	 */
	public int length(Node someHead){
		int result;

		if (someHead == null)				//	If the sub-list is empty,
			result = 0;						//		there are no element
		else								//	otherwise, there is the first node
			result = 1						//		then the rest
			+ length(someHead.getNext());

		return result;
	}	


}
