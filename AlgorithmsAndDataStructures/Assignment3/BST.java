/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 16/12/20
 *
 *  @author Sean Gaffney
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { 
      return size() == 0; 
    }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null)
         return 0;
        else 
          return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { 
          delete(key); 
          return; 
        }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: Running time is theta(N) must iteratae through eveery element
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() {
      
      if(!isEmpty()) {
        if(size(root) > 1) {
          return height(this.root);
        }
        else {
          return 0;
        }
      }
      return -1;
    }

    private int height(Node node) {
      if (node == null) {
        return -1;
      }
      int left = height(node.left);
      int right = height(node.right);
      if (left > right) {
        return left + 1;
      }
      return right + 1;
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {
      if (isEmpty())  {
        return null;
      }
      return median(this.root, (this.root.N+1)/2);
    }
    private Key median(Node node, int index) {
      int pos = size(node.left) + 1;
      if (pos == index) {
        return node.key;
      }
      else if (pos > index) {
        return median(node.left, index);
      }
      return median(node.right, index - pos);
    }


    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {
      if (isEmpty()) {
        return "()";
      } 
      return printKeysInOrder(this.root);
    }
    private String printKeysInOrder(Node node) {
      if (node == null) {
        return "()";
      }
      return ("(" + printKeysInOrder(node.left) + node.key + printKeysInOrder(node.right) + ")");
    }
    
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
      return prettyPrintKeys(this.root, "");
    }
    private String prettyPrintKeys(Node node, String string) {
      if (node == null) {
        return (string + "-null\n");
      }
      return (string + "-" + node.key + "\n" + prettyPrintKeys(node.left, string + " |") + prettyPrintKeys(node.right, string + "  "));
    }

    /**
     * Deteles a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {
      root = delete(root, key);
    }
    private Node delete(Node node, Key key) {
      if (node == null) {
        return null;
      }
      int pos = key.compareTo(node.key);
      if (pos > 0) {
        node.right = delete(node.right, key);
      }
      else if (pos < 0) {
        node.left = delete(node.left,key);
      }
      else {
        if (node.left == null) {
          return node.right;
        }
        if (node.right == null) {
          return node.left;
        }
        Node temp = node;
        node = getEnd(temp.left);
        node.left = deleteEnd(temp.left);
        node.right = temp.right;
      }
      node.N = size(node.left) + size(node.right) + 1;
      return node;
    }

    private Node deleteEnd(Node node) {
      return delete(node, getEnd(node).key);
    }

    private Node getEnd(Node node) {
      if(node.right == null) {
        return node;
      }
      return getEnd(node.right);
    }
}
