package BST_A2;

public class BST implements BST_Interface {
	public BST_Node root;
	int size;

	public BST(){ size=0; root=null; }

	@Override
	//used for testing, please leave as is
	public BST_Node getRoot(){ return root; }

	@Override
	public boolean insert(String s) {
		if(root == null) {
			root = new BST_Node(s);
			size = 1;
			return true;
		}
		else {
			if(root.insertNode(s)) {
				size++;
				return true;
			}
			else {
				return false;
			}
		}
	}

	@Override
	public boolean remove(String s) {
		if(size == 0) {
			return false;
		}
		else if(root.isNode(s)) {
			if(root.childNum() == 0) {
				root = null;
				size = 0;
				return true;
			}
			else if(root.childNum() == 1) {
				if(root.getLeft() == null) {
					root = root.getRight();
				}
				else {
					root = root.getLeft();
				}
				size--;
				return true;
			}
			else {
				BST_Node max = root.getLeft().findMax();
				if(root.removeNode(max.getData())) {
					root.data = max.data;
					size--;
					return true;
				}
				else {
					return false;
				}
			}
		}
		else {
			if(root.removeNode(s)) {
				size--;
				return true;
			}
			else {
				return false;
			}
		}
	}

	@Override
	public String findMin() {
		if(size == 0) {
			return null;
		}
		else {
			return root.findMin().getData();
		}
	}

	@Override
	public String findMax() {
		if(size == 0) {
			return null;
		}
		else {
			return root.findMax().getData();
		}
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
		if(size == 0) {
			return false;
		}
		else {
			return root.containsNode(s);
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		if(size == 0) {
			return -1;
		}
		else {
			return root.getHeight();
		}
	}

}