package cs250.hw4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BTree implements TreeStructure {

    public Node root;
    public class Node {
        Integer key;
        Long insertTime;
        Node left, right;

        public Node(Integer item) {
            key = item;
            insertTime = System.nanoTime();
            left = right = null;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileName = "cs250/hw4/input";
        File file = new File(fileName);
        // File file = new File(args[0]);
        FileReader fReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fReader);
        TreeStructure tree = new BTree();
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
    public void insert(Integer key) {
        root = insertRec(root, key);
    }

    public Node insertRec(Node root, Integer key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key) root.left = insertRec(root.left, key);
        else if (key > root.key) root.right = insertRec(root.right, key);
        return root;
    }

    @Override
    public Boolean remove(Integer key) {
        if (search(root, key) != null) {
            root = removeRec(root, key);
            return true;
        } else return false;
    }

    public Node removeRec(Node root, Integer key) {
        if (root == null) return root;
        if (key < root.key) root.left = removeRec(root.left, key);
        else if (key > root.key) root.right = removeRec(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            root.key = minValue(root.right);
            root.right = removeRec(root.right, root.key);
        }
        return root;
    }

    public Integer minValue(Node root) {
        Integer minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    @Override
    public Long get(Integer key) {
        Node node = search(root, key);
        return (node != null) ? node.insertTime : -1;
    }

    public Node search(Node root, Integer key) {
        if (root == null || root.key.equals(key)) return root;
        if (root.key > key) return search(root.left, key);
        else return search(root.right, key);
    }

    @Override
    public Integer findMaxDepth() {
        return maxDepth(root);
    }

    public Integer maxDepth(Node node) {
        if (node == null) return 0;
        else {
            Integer lDepth = maxDepth(node.left);
            Integer rDepth = maxDepth(node.right);
            if (lDepth > rDepth) return (lDepth + 1);
            else return (rDepth + 1);
        }
    }

    @Override
    public Integer findMinDepth() {
        return minDepth(root);
    }

    public Integer minDepth(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        if (node.left == null) return minDepth(node.right) + 1;
        if (node.right == null) return minDepth(node.left) + 1;
        else return Math.min(minDepth(node.left), minDepth(node.right)) + 1;
    }
}