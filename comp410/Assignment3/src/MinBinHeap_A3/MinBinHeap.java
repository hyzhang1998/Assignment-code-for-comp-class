package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; //load this array
	private int size;
	private static final int arraySize = 10000; //Everything in the array will initially 
	//be null. This is ok! Just build out 
	//from array[1]

	public MinBinHeap() {
		size = 0;
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
		//of child/parent computations...
		//the book/animation page both do this.
	}

	//Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() { 
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		size++;
		if(size == 1) {
			array[1] = entry;
		}
		else {
			int pointer = size;
			while(pointer != 1) {
				if(array[pointer/2].getPriority() > entry.getPriority()) {
					array[pointer] = array[pointer/2];
					pointer /= 2;
					if(pointer == 1) {
						array[pointer] = entry;
					}
				}
				else {
					array[pointer] = entry;
					break;
				}
			}
		}
	}

	@Override
	public void delMin() {
		if(size == 0) {
			return;
		}
		EntryPair temp = array[size];
		array[size] = null;
		int pointer = 1;
		int smallerChildPointer;
		if(size == 2) {
			array[1] = temp;
		}
		while(pointer*2 < size) {
			if(array[pointer*2+1] != null) {
				if(array[pointer*2].getPriority() >= array[pointer*2+1].getPriority()) {
					smallerChildPointer = pointer*2+1;
				}
				else {
					smallerChildPointer = pointer*2;
				}
			}
			else {
				smallerChildPointer = pointer*2;
			}
			if(temp.getPriority() > array[smallerChildPointer].getPriority()) {
				array[pointer] = array[smallerChildPointer];
				pointer = smallerChildPointer;
				if(pointer*2 >= size) {
					array[pointer] = temp;
				}
			}
			else {
				array[pointer] = temp;
				break;
			}
		}
		size--;
	}

	@Override
	public EntryPair getMin() {
		if(size == 0) {
			return null;
		}
		else {
			return array[1];
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		size = entries.length;
		for(int i = 1; i <= entries.length; i++) {
			array[i] = entries[i-1];
		}
		int pointer = size;
		while(pointer != 1) {
			if(array[pointer/2].getPriority() > array[pointer].getPriority()) {
				EntryPair temp = array[pointer];
				array[pointer] = array[pointer/2];
				array[pointer/2] = temp;
				pointer--;
			}
			else {
				pointer--;
			}
			int newPointer = pointer + 1;
			int smallerChildPointer;
			while(newPointer*2 < size) {
				if(array[newPointer*2+1] != null) {
					if(array[newPointer*2].getPriority() >= array[newPointer*2+1].getPriority()) {
						smallerChildPointer = newPointer*2+1;
					}
					else {
						smallerChildPointer = newPointer*2;
					}
				}
				else {
					smallerChildPointer = newPointer*2;
				}
				if(array[newPointer].getPriority() > array[smallerChildPointer].getPriority()) {
					EntryPair newTemp = array[newPointer];
					array[newPointer] = array[smallerChildPointer];
					array[smallerChildPointer] = newTemp;
					newPointer = smallerChildPointer;
				}
				else {
					break;
				}
			}
		}
	}
}