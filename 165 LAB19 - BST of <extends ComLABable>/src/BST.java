/* Binary Search Tree Class
*  Made for CS165 at CSU
*  A basic binary search tree of integers, without a remove method.
*/

public class BST<E extends Comparable<E>> implements Tree<E> {

    private TreeNode<E> root;
    private int size;

    public class TreeNode<E> {
        public E element;
        public TreeNode<E> rightChild;
        public TreeNode<E> leftChild;

        public TreeNode(E element) {
            this.element = element;
            this.rightChild = null;
            this.leftChild = null;
        }
    }

    public BST() {
        this.root = null;
        size = 0;
    }

    public BST(E item) {
        super();
        insert(item);
    }

    public BST(E[] items) {
        for (E item : items) {
            insert(item);
        }
    }

    @Override
    public boolean search(E item) {
        TreeNode<E> nde = root;
        while (nde != null) {
            if (item.compareTo(nde.element) == 0) {
                return true;
            } else if (item.compareTo(nde.element) < 0) {
                nde = nde.leftChild;
            } else {
                nde = nde.rightChild;
            }
        }
        return false;
    }

    @Override
    public boolean insert(E item) {
        if (root == null) {
            root = new TreeNode<>(item);
            ++size;
            return true;
        }
        TreeNode<E> nde = root;
        TreeNode<E> ndeInsert = null;
        while (nde != null) {
            ndeInsert = nde;
            if (item.compareTo(nde.element) == 0) {
                return false;
            } else if (item.compareTo(nde.element) < 0) {
                nde = nde.leftChild;
            } else {
                nde = nde.rightChild;
            }
        }
        if (item.compareTo(ndeInsert.element) < 0) {
            ndeInsert.leftChild = new TreeNode<>(item);
        } else {
            ndeInsert.rightChild = new TreeNode<>(item);
        }
        ++size;
        return true;
    }
/*      
            Find the node to be removed then follow one of the following procedures:
                If the node is a leaf
                    set it to null
                If the node only has one child
                    Swap up
                If the node has two children
                    Find the node's inorder successor*
                    Swap the node to be deleted with its inorder successor *
                    Delete the node at its new position
         */
   @Override
   public boolean remove(E item) {
        if (root == null) {return false;}
        root = removeHelp(root, item);
        return root != null;
   }

   public TreeNode<E> removeHelp(TreeNode<E> nde, E item){
        if (nde == null) {return null;}
        int checkVal = item.compareTo(nde.element);
        if (checkVal < 0) {
            nde.leftChild = removeHelp(nde.leftChild, item);
        } else if (checkVal > 0) {
            nde.rightChild = removeHelp(nde.rightChild, item);
        } else {
            if (nde.leftChild == null) {
                return nde.rightChild;
            } else if (nde.rightChild == null) {
                return nde.leftChild;
            }
            nde.element = leftMost(nde.rightChild);
            nde.rightChild = removeHelp(nde.rightChild, nde.element);
        }
        return nde;
   }

   public E leftMost(TreeNode<E> nde){
        E l = nde.element;
        while (nde.leftChild != null) {
            l = nde.leftChild.element;
            nde = nde.leftChild;
        }
        return l;
   }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void inorder() {
        inOrderRecursion(root);
    }

    public void inOrderRecursion(TreeNode<E> nde){
        if (nde == null){return;}
        inOrderRecursion(nde.leftChild); //left
        System.out.print(nde.element + " "); //root
        inOrderRecursion(nde.rightChild); //right
    }

    @Override
    public void postorder() {
        postOrderRecursion(root);
    }

    public void postOrderRecursion(TreeNode<E> nde){
        if (nde == null){return;}
        postOrderRecursion(nde.leftChild); //left
        postOrderRecursion(nde.rightChild); //right
        System.out.print(nde.element + " "); //root
    }

    @Override
    public void preorder() {
        preOrderRecursion(root);
    }

    public void preOrderRecursion(TreeNode<E> nde){
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
    public TreeNode<E> getRoot() {
        return root;
    }

}
