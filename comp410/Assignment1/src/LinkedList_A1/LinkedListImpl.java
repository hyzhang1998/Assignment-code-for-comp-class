package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	Node sentinel; //this will be the entry point to your linked list (the head)

	public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
		sentinel=new Node(0); //Note that the root's data is not a true part of your data set!
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
	}

	//implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!

	public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
		return sentinel;
	}

	@Override
	public boolean insert(double elt, int index) {
		if(index <= this.size() && index >= 0) {
			Node iterate = sentinel;
			Node newNode = new Node(elt);
			for(int i = 0; i < index; i++) {
				iterate = iterate.getNext();
			}
			Node temp = iterate.getNext();
			iterate.next = newNode;
			temp.prev = newNode;
			newNode.prev = iterate;
			newNode.next = temp;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean remove(int index) {
		if(index < this.size() && index >= 0) {
			Node iterate = sentinel;
			for(int i = 0; i < index; i++) {
				iterate = iterate.getNext();
			}
			iterate.next = iterate.getNext().getNext();
			iterate.getNext().prev = iterate;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public double get(int index) {
		if(index < this.size() && index >= 0) {
			Node iterate = sentinel.getNext();
			for(int i = 0; i < index; i++) {
				iterate = iterate.getNext();
			}
			return iterate.getData();
		}
		else {
			return Double.NaN;
		}
	}

	@Override
	public int size() {
		Node temp = sentinel.getNext();
		int size = 0;
		while(temp != sentinel) {
			size++;
			temp = temp.getNext();
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(this.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void clear() {
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
	}
}