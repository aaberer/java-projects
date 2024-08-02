package cs250.hw4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BinaryTree implements TreeStructure {

    public TreeNode<Integer> root;
    public class TreeNode<Integer> {
        public Integer element;
        public Long systemTime;
        public TreeNode<Integer> rightChild;
        public TreeNode<Integer> leftChild;
    
        public TreeNode(Integer element) {
            this.element = element;
            this.rightChild = null;
            this.leftChild = null;
            this.systemTime = null;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("cs250/hw4/input");
        FileReader fReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fReader);
        TreeStructure tree = new BinaryTree();
        Random rng = new Random(42);
        ArrayList<Integer> nodesToRemove = new ArrayList<>();
        ArrayList<Integer> nodesToGet = new ArrayList<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            Integer lineInt = Integer.parseInt(line);
            tree.insert(lineInt);
            Integer rand = rng.nextInt(10);
            if (rand < 5) nodesToRemove.add(lineInt);
            else if (rand >= 5) nodesToGet.add(lineInt);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        for (int i = 0; i < 10; i++) {
            System.out.println(nodesToGet.get(i) + " inserted at " + tree.get(nodesToGet.get(i)));
        }
        System.out.println("Max depth: " + tree.findMaxDepth());
        System.out.println("Min depth: " + tree.findMinDepth());
        for (Integer num : nodesToRemove) {
            tree.remove(num);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(nodesToGet.get(i) + " inserted at " + tree.get(nodesToGet.get(i)));
        }
        System.out.println("Max depth: " + tree.findMaxDepth());
        System.out.println("Min depth: " + tree.findMinDepth());
    }

    @Override
    public void insert(Integer num) {
        TreeNode<Integer> newNode = new TreeNode<>(num);
        newNode.systemTime = System.nanoTime();
        if (root == null) root = newNode;
        else {
            TreeNode<Integer> parent = null, current = root;
            while (true) {
                parent = current;
                if (num < current.element) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    @Override
    public Boolean remove(Integer num) {
        if (root == null) return false;
        root = removeHelp(root, num);
        return root != null;
    }

    public TreeNode<Integer> removeHelp(TreeNode<Integer> nde, Integer num) {
        if (nde == null) {return null;}
        int checkVal = num.compareTo(nde.element);
        if (checkVal < 0) nde.leftChild = removeHelp(nde.leftChild, num);
        else if (checkVal > 0) nde.rightChild = removeHelp(nde.rightChild, num);
        else {
            if (nde.leftChild == null) return nde.rightChild;
            else if (nde.rightChild == null) return nde.leftChild;
            nde.element = leftMost(nde.rightChild);
            nde.rightChild = removeHelp(nde.rightChild, nde.element);
        }
        return nde;
   }

    public Integer leftMost(TreeNode<Integer> nde) {
        Integer l = nde.element;
        while (nde.leftChild != null) {
            l = nde.leftChild.element;
            nde = nde.leftChild;
        }
        return l;
    }

   @Override
    public Long get(Integer num) {
        return getRecursive(root, num);
    }

    public Long getRecursive(TreeNode<Integer> node, Comparable num){
        if (node == null) return null;
        int cmp = num.compareTo(node.element);
        if (cmp < 0) return getRecursive(node.leftChild, num);
        else if (cmp > 0) return getRecursive(node.rightChild, num);
        else return node.systemTime;
    }

    @Override
    public Integer findMaxDepth() {
        return calculateMaxDepth(root);
    }

    public Integer calculateMaxDepth(TreeNode<Integer> node) {
        if (node == null) return 0;
        else {
            int leftDepth = calculateMaxDepth(node.leftChild);
            int rightDepth = calculateMaxDepth(node.rightChild);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    @Override
    public Integer findMinDepth() {
        return calculateMinDepth(root)
    }

    public Integer calculateMinDepth(TreeNode<Integer> node) {
        if (node == null) return 0;
        else {
            int leftDepth = calculateMinDepth(node.leftChild);
            int rightDepth = calculateMinDepth(node.rightChild);
            if (leftDepth == 0) return rightDepth + 1;
            else if (rightDepth == 0) return leftDepth + 1;
            else return Math.min(leftDepth, rightDepth) + 1;
        }
    }
}