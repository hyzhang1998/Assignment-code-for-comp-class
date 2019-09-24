package SPLT_A4;


public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;
	BST_Node par;
	boolean justMade;

	BST_Node(String data){
		this.data = data;
		this.justMade = true;
	}

	BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
		this.data=data;
		this.left=left;
		this.right=right;
		this.par=par;
		this.justMade=true;
	}

	// --- used for testing  ----------------------------------------------
	//
	// leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

	public String getData(){
		return data;
	}
	public BST_Node getLeft(){
		return left;
	}
	public BST_Node getRight(){
		return right;
	}
	// --- end used for testing -------------------------------------------
	//note: I personally find it easiest to make this return a Node,(that being the node splayed to root), you are however free to do what you wish.
	//Really same logic as above note
	//I personal do not use the removeNode internal method in my impl since it is rather easily done in SPLT, feel free to try to delegate this out, however we do not "remove" like we do in BST

	private BST_Node splay(BST_Node toSplay) { 
		if(toSplay.par != null) {
			if(toSplay == toSplay.par.right){
				toSplay.RotateLeft();
			}
			else {
				toSplay.RotateRight();
			}
		}
		else {
			return toSplay;
		}
		if(toSplay.par != null) {
			return splay(toSplay);
		}
		else {
			return toSplay;
		}
	} //you could have this return or take in whatever you want..so long as it will do the job internally. As a caller of SPLT functions, I should really have no idea if you are "splaying or not"
	//I of course, will be checking with tests and by eye to make sure you are indeed splaying
	//Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!


	// --- end example methods --------------------------------------


	public BST_Node containsNode(String s){ //it was me
		if(data.equals(s)) {
			justMade = false;
			return splay(this);
		}
		if(data.compareTo(s)>0){//s lexiconically less than data
			if(left==null) {
				justMade = false;
				return splay(this);
			}
			return left.containsNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null) {
				justMade = false;
				return splay(this);
			}
			return right.containsNode(s);
		}
		return null; //shouldn't hit
	}
	public BST_Node insertNode(String s){
		if(data.compareTo(s)>0){
			if(left==null){
				left=new BST_Node(s);
				left.par = this;
				return splay(left);
			}
			return left.insertNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null){
				right=new BST_Node(s);
				right.par = this;
				return splay(right);
			}
			return right.insertNode(s);
		}
		return containsNode(s);//ie we have a duplicate
	}
	public BST_Node removeNode(String s){ //DIO
		BST_Node target = containsNode(s);
		if(target.getData() != s) {
			return target;
		}
		else {
			if(target.left != null && target.right != null) {
				target.left.par = null;
				target.right.par = null;
				target.left = target.left.findMax();
				target.left.right = target.right;
				target.right.par = target.left;
				return target.left;
			}
			else if(target.left != null) {
				target.left.par = null;
				return target.left;
			}
			else if(target.right != null) {
				target.right.par = null;
				return target.right;
			}
			else {
				return null;
			}
		}
	}
	public BST_Node findMin(){
		if(left!=null)return left.findMin();
		splay(this);
		return this;
	}
	public BST_Node findMax(){
		if(right!=null)return right.findMax();
		splay(this);
		return this;
	}
	public int getHeight(){
		int l=0;
		int r=0;
		if(left!=null)l+=left.getHeight()+1;
		if(right!=null)r+=right.getHeight()+1;
		return Integer.max(l, r);
	}
	public String toString(){
		return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")+",Right: "+((this.right!=null)?right.data:"null");
	}
	public void RotateLeft() {
		par.right = left;
		if(left != null) {
			left.par = par;
		}
		BST_Node grandpar = par.par;
		par.par = this;
		left = par;
		if(grandpar != null) {
			if(par == grandpar.right) {
				par = grandpar;
				grandpar.right = this;
			}
			else if(par == grandpar.left) {
				par = grandpar;
				grandpar.left = this;
			}
		}
		else {
			par = null;
		}
	}
	public void RotateRight() {
		par.left = right;
		if(right != null) {
			right.par = par;
		}
		BST_Node grandpar = par.par;
		par.par = this;
		right = par;
		if(grandpar != null) {
			if(par == grandpar.right) {
				par = grandpar;
				grandpar.right = this;
			}
			else if(par == grandpar.left) {
				par = grandpar;
				grandpar.left = this;
			}
		}
		else {
			par = null;
		}
	}
}
