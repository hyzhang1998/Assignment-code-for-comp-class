package SPLT_A4;

public class SPLT implements SPLT_Interface{
  private BST_Node root;
  private int size;
  
  public SPLT() {
    this.size = 0;
  } 
  
  public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
    return root;
  }

@Override
public void insert(String s) {
	if(root == null) {
		root = new BST_Node(s);
		size = 1;
	}
	else {
		root = root.insertNode(s);
		if(root.justMade) {
			size++;
		}
	}
}

@Override
public void remove(String s) {
	root = root.removeNode(s);
	size--;
}

@Override
public String findMin() {
	root = root.findMin();
	return root.data;
}

@Override
public String findMax() {
	root = root.findMax();
	return root.data;
}

@Override
public boolean empty() {
	if(size == 0) {
		return true;
	}
	else {
		return false;
	}
}

@Override
public boolean contains(String s) {
	root = root.containsNode(s);
	if(root.data == s) {
		return true;
	}
	else {
		return false;
	}
}

@Override
public int size() {
	return size;
}

@Override
public int height() {
	return root.getHeight();
}  

}