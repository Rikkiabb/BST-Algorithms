import  edu.princeton.cs.introcs.*;
import  edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;

public class BST_D4<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

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
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }


   /**********************************************************************
    * Assignment part.
    * TODO: Implement
    *       [ ] avgCompares
    *       [ ] nLeaves
    *       [ ] floor
    *********************************************************************/

    public int avgCompares()
    {
        // TODO: Implement
        return 0;
    }

    public int nLeaves()
    {
        // TODO: Implement
        return 0;
    }

    public Key floor(Key key) {
        // TODO: Implement
        return null;
    } 

   /***********************************************************************
    *  Insert key-value pair into BST
    *  If key already exists, update with new value
    ***********************************************************************/
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
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

   /***********************************************************************
    *  Delete
    ***********************************************************************/

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else { 
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        } 
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    } 


   /***********************************************************************
    *  Min
    ***********************************************************************/
    public Key min() {
        if (isEmpty()) return null;
        return min(root).key;
    } 

    private Node min(Node x) { 
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 

   /*****************************************************************************
    *  Test client
    *****************************************************************************/
    public static void test_floor() {
        /**
         *  Each line in the test case starts with some operation, either "F"
         *  for "floor" and "P" for "put". For "floor", a key of type string
         *  will follow which will be the parameter to a function call to
         *  floor.  For "put", a key of type string and value of type int will
         *  follow which then are inserted into the tree.
         */
        BST_D4<String, Integer> st = new BST_D4<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String operation = StdIn.readString();
            String key = StdIn.readString();
            if (operation.equals("F")){
                StdOut.printf("Calling floor(\"%s\") = \"%s\"\n", key, st.floor(key));
            } else {
                int value = StdIn.readInt();
                st.put(key, value);
                StdOut.printf("Inserting node with key=\"%s\" and value=%d\n", key, value);
            }
        }

        StdOut.println();

        StdOut.printf("avgCompares %d\n", st.avgCompares());
    }

    public static void test_nLeaves() {
        /**
         * Each line in the test case consists of a key of type string and
         * value of type int that are inserted into the tree.
         */
        BST_D4<String, Integer> st = new BST_D4<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            int value = StdIn.readInt();
            st.put(key, value);
            StdOut.printf("Inserting node with key=\"%s\" and value=%d\n", key, value);
        }

        StdOut.println();

        StdOut.printf("nLeaves %d\n", st.nLeaves());
    }

    public static void test_avgCompares() {
        /**
         * Each line in the test case consits of a key of type string and value
         * of type int that are inserted into the tree.
         */
        BST_D4<String, Integer> st = new BST_D4<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            int value = StdIn.readInt();
            st.put(key, value);
            StdOut.printf("Inserting node with key=\"%s\" and value=%d\n", key, value);
        }

        StdOut.println();

        StdOut.printf("avgCompares %d\n", st.avgCompares());
    }

    public static void main(String[] args) {
        /**
         * The test client reads in the name of the function to test, which can
         * be one of "avgCompares", "nLeaves", "floor".
         */
        String test_name = StdIn.readString();
        if("floor".equals(test_name)){
            test_floor();
        } else if("avgCompares".equals(test_name)){
            test_avgCompares();
        } else if("nLeaves".equals(test_name)){
            test_nLeaves();
        }
    }
}
