package BST_A2;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;

	BST_Node(String data){ this.data=data; }

	// --- used for testing  ----------------------------------------------
	//
	// leave these 3 methods in, as is

	public String getData(){ return data; }
	public BST_Node getLeft(){ return left; }
	public BST_Node getRight(){ return right; }

	// --- end used for testing -------------------------------------------


	// --- fill in these methods ------------------------------------------
	//
	// at the moment, they are stubs returning false 
	// or some appropriate "fake" value
	//
	// you make them work properly
	// add the meat of correct implementation logic to them

	// you MAY change the signatures if you wish...
	// make the take more or different parameters
	// have them return different types
	//
	// you may use recursive or iterative implementations

	public boolean isNode(String s) {
		if(data.equals(s)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean containsNode(String s) {
		if(data.compareTo(s) > 0) {
			if(left == null) {
				return false;
			}
			else {
				return left.containsNode(s);
			}
		}
		else if(data.compareTo(s) < 0) {
			if(right == null) {
				return false;
			}
			else {
				return right.containsNode(s);
			}
		}
		else {
			return true;
		}
	}

	public BST_Node retrieveParentOfNode(String s) {
		if(left != null && left.containsNode(s)) {
			if(left.isNode(s)) {
				return this;
			}
			else {
				return left.retrieveParentOfNode(s);
			}
		}
		else{
			if(right.isNode(s)) {
				return this;
			}
			else {
				return right.retrieveParentOfNode(s);
			}
		}
	}

	public boolean insertNode(String s){
		int length;
		if(s.length() <= data.length()) {
			length = s.length();
		}
		else {
			length = data.length();
		}
		for(int i = 0; i < length; i++) {
			if(data.charAt(i) > s.charAt(i)) {
				if(left == null) {
					left = new BST_Node(s);
					return true;
				}
				else {
					return left.insertNode(s);
				}
			}
			else if(data.charAt(i) < s.charAt(i)) {
				if(right == null) {
					right = new BST_Node(s);
					return true;
				}
				else {
					return right.insertNode(s);
				}
			}
			else {
			}
		}
		return false;
	}

	public boolean removeNode(String s) {
		if(this.containsNode(s) && !this.isNode(s)) {
			BST_Node parent = this.retrieveParentOfNode(s);
			if(parent.getLeft() != null && parent.getLeft().isNode(s)) {
				BST_Node target = parent.getLeft();
				if(target.childNum() == 0) {
					parent.left = null;
					return true;
				}
				else if(target.childNum() == 1) {
					if(target.getLeft() == null) {
						parent.left = target.getRight();
					}
					else {
						parent.left = target.getLeft();
					}
					return true;
				}
				else {
					BST_Node max = target.getLeft().findMax();
					if(target.removeNode(max.getData())) {
						target.data = max.data;
						return true;
					}
					else {
						return false;
					}
				}
			}
			else {
				BST_Node target = parent.getRight();
				if(target.childNum() == 0) {
					parent.right = null;
					return true;
				}
				else if(target.childNum() == 1) {
					if(target.getLeft() == null) {
						parent.right = target.getRight();
					}
					else {
						parent.right = target.getLeft();
					}
					return true;
				}
				else {
					BST_Node max = target.getLeft().findMax();
					if(target.removeNode(max.getData())) {
						target.data = max.data;
						return true;
					}
					else {
						return false;
					}
				}
			}
		}
		else {
			return false;
		}
	}

	public int childNum() {
		if(left != null && right != null) {
			return 2;
		}
		else if(left != null || right != null) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public BST_Node findMin() {
		if(left == null) {
			return this;
		}
		else {
			return left.findMin();
		}
	}

	public BST_Node findMax() {
		if(right == null) {
			return this;
		}
		else {
			return right.findMax();
		}
	}
	public int getHeight() {
		if(left == null && right == null) {
			return 0;
		}
		else if(left == null) {
			return 1 + right.getHeight();
		}
		else if(right == null) {
			return 1 + left.getHeight();
		}
		else {
			if(left.getHeight() >= right.getHeight()) {
				return 1 + left.getHeight();
			}
			else {
				return 1 + right.getHeight();
			}
		}
	}


	// --- end fill in these methods --------------------------------------


	// --------------------------------------------------------------------
	// you may add any other methods you want to get the job done
	// --------------------------------------------------------------------

	public String toString(){
		return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
				+",Right: "+((this.right!=null)?right.data:"null");
	}
}