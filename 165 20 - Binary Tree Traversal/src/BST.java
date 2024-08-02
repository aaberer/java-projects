/* Binary Search Tree Class
*  Made for CS165 at CSU
*  A basic binary search tree of integers, without a remove method.
*/

public class BST implements Tree<Integer> {

    private TreeNode<Integer> root;
    private int size;

    public class TreeNode<Integer> {
        public Integer element;
        public TreeNode<Integer> rightChild;
        public TreeNode<Integer> leftChild;

        public TreeNode(Integer element) {
            this.element = element;
            this.rightChild = null;
            this.leftChild = null;
        }
    }

    public BST() {
        this.root = null;
        size = 0;
    }

    public BST(Integer item) {
        super();
        insert(item);
    }

    public BST(Integer[] items) {
        for (Integer item : items) {
            insert(item);
        }
    }

    @Override
    public boolean search(Integer item) {
        TreeNode<Integer> nde = root;
        while (nde != null) {
            if (item == nde.element) {
                return true;
            } else if (item < nde.element) {
                nde = nde.leftChild;
            } else {
                nde = nde.rightChild;
            }
        }
        return false;
    }

    @Override
    public boolean insert(Integer item) {
        if (root == null) {
            root = new TreeNode<>(item);
            ++size;
            return true;
        }
        TreeNode<Integer> nde = root;
        TreeNode<Integer> ndeInsert = null;
        while (nde != null) {
            ndeInsert = nde;
            if (item == nde.element) {
                return false;
            } else if (item < nde.element) {
                nde = nde.leftChild;
            } else {
                nde = nde.rightChild;
            }
        }
        if (item < ndeInsert.element) {
            ndeInsert.leftChild = new TreeNode<>(item);
        } else {
            ndeInsert.rightChild = new TreeNode<>(item);
        }
        ++size;
        return true;
    }

    // for the next lab
//    @Override
//    public boolean remove(Integer item) {
//        return false;
//    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void inorder() {
        inOrderRecursion(root);
    }

    public void inOrderRecursion(TreeNode<Integer> nde){
        if (nde == null){return;}
        inOrderRecursion(nde.leftChild); //left
        System.out.print(nde.element + " "); //root
        inOrderRecursion(nde.rightChild); //right
    }

    @Override
    public void postorder() {
        postOrderRecursion(root);
    }

    public void postOrderRecursion(TreeNode<Integer> nde){
        if (nde == null){return;}
        postOrderRecursion(nde.leftChild); //left
        postOrderRecursion(nde.rightChild); //right
        System.out.print(nde.element + " "); //root
    }

    @Override
    public void preorder() {
        preOrderRecursion(root);
    }

    public void preOrderRecursion(TreeNode<Integer> nde){
        if (nde == null){return;}
        System.out.print(nde.element + " "); //root
        preOrderRecursion(nde.leftChild); //left
        preOrderRecursion(nde.rightChild); //right
    }

    @Override
    public boolean isEmpty() {
        boolean check = (root == null);
        return check;
    }

    /* Returns the root node of the tree */
    public TreeNode<Integer> getRoot() {
        return root;
    }
}
