


public class MergeSortList extends List implements Comparable{

	public MergeSortList() {
		super(); // calls parent class
	}
	
	//
	//	The following method sorts the whole list.
	//	It makes use of a utility method which implements merge sort.
	public void sort(){
		root = mergeSort(root);
	}

	//
	//	The following method reverse sorts the whole list.
	//	It makes use of a utility method which implements merge sort.
	//
	public void reverseSort() {
		root = reverseMergeSort(root);
	}
	
	
	//
	//	The following method sorts a list using merge sort.
	//
	protected Node mergeSort(Node someHead){
		Node result;						//	To define the resulting list
		int size = length(someHead);		//	Size of the list
		if (size <= 1)						//	If there is 1 element (or fewer)
			result = someHead;				//		the list is returned unchanged
		else {
			Node head1=someHead, head2=null, previous=someHead;	// head1 points to root, as well as previous
			for(int i=0;i!=(size/2)-1;i++) {
				previous = previous.getNext(); // previous then walks to the node center of the list
			}
			head2 = previous.getNext(); // head2 will be the root to the second half of the list
			previous.setNext(null); // the two lists are separated.
			
			head1 = mergeSort(head1);		//	The 2 recursive "merge sort" steps,
			head2 = mergeSort(head2);		//		one for each of the sub-lists,
			result = merge(head1, head2);	//		followed by the actual merge
		}
		return result;
	}

	//
	//	The following method sorts a list using merge sort.
	//
	protected Node reverseMergeSort(Node someHead){
		Node result;						//	To define the resulting list
		int size = length(someHead);		//	Size of the list
		if (size <= 1)						//	If there is 1 element (or fewer)
			result = someHead;				//		the list is returned unchanged
		else {
			Node head1=someHead, head2=null, previous=someHead;	// head1 points to root, as well as previous
			for(int i=0;i!=(size/2)-1;i++) {
				previous = previous.getNext(); // previous then walks to the node center of the list
			}
			head2 = previous.getNext(); // head2 will be the root to the second half of the list
			previous.setNext(null); // the two lists are separated.
			
			head1 = reverseMergeSort(head1);		//	The 2 recursive "merge sort" steps,
			head2 = reverseMergeSort(head2);		//		one for each of the sub-lists,
			result = reverseMerge(head1, head2);	//		followed by the actual merge
		}
		return result;
	}
	
	
	//
	//	The following method returns a single list resulting from merging
	//		two given sub-lists (assumed to be sorted in increasing order
	//		-- implied by the compareTo methods).
	//
	protected Node merge(Node head1, Node head2){
		Node result;						//	The head of the resulting list
		Node last;							//	The last node of the resulting list

		//	We build the resulting list one node at a time.
		//	At each step, either head1 or head2 "moves forwards" by one node,
		//		e.g., head1 = head1.getNext().

		if (((String) head1.getItem()).compareTo((String)head2.getItem()) > 0) { // <=
			result = head1;
			head1 = head1.getNext();
		} else {
			result = head2;
			head2 = head2.getNext();
		}

		last = result;						//	So far the last node is the head
		last.setNext(null);
		while ((head1 != null) &&			//	As long as neither list is empty
				(head2 != null)) {
			if (((String) head1.getItem()).compareTo((String)head2.getItem()) > 0) {
				last.setNext(head1);		//		we choose the next node from
				head1 = head1.getNext();	//		the remaining nodes in head1
			}
			else {
				last.setNext(head2);		//		or the remaining nodes in head2
				head2 = head2.getNext();
			}
			last = last.getNext();			//	The node that we just added
			last.setNext(null);				//		now becomes the last node
		}

		if (head1 != null)					//	When one list becomes empty,
			last.setNext(head1);			//		we transfer the rest of the
		else								//		elements from the other list
			last.setNext(head2);

		return result;
	}

	//
	//	The following method returns a single list resulting from merging
	//		two given sub-lists (assumed to be sorted in increasing order
	//		-- implied by the compareTo methods).
	//
	protected Node reverseMerge(Node head1, Node head2){
		Node result;						//	The head of the resulting list
		Node last;							//	The last node of the resulting list

		//	We build the resulting list one node at a time.
		//	At each step, either head1 or head2 "moves forwards" by one node,
		//		e.g., head1 = head1.getNext().

		if (((String) head1.getItem()).compareTo((String)head2.getItem()) <= 0) { // <=
			result = head1;
			head1 = head1.getNext();
		}
		else {
			result = head2;
			head2 = head2.getNext();
		}

		last = result;						//	So far the last node is the head
		last.setNext(null);
		while ((head1 != null) &&			//	As long as neither list is empty
				(head2 != null)) {
			if (((String) head1.getItem()).compareTo((String)head2.getItem()) <=
				0) {
				last.setNext(head1);		//		we choose the next node from
				head1 = head1.getNext();	//		the remaining nodes in head1
			}
			else {
				last.setNext(head2);		//		or the remaining nodes in head2
				head2 = head2.getNext();
			}
			last = last.getNext();			//	The node that we just added
			last.setNext(null);				//		now becomes the last node
		}

		if (head1 != null)					//	When one list becomes empty,
			last.setNext(head1);			//		we transfer the rest of the
		else								//		elements from the other list
			last.setNext(head2);

		return result;
	}
	
	
	@Override
	public int compareTo(Object someObject) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
