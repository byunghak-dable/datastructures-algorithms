package algo;

import algo.tree.BinarySearchTree;
import algo.tree.TreeTraversalOrder;
import java.util.Iterator;

// binary heap => PQueue => Union Find
public class App {
  public static void main(String[] args) {
    BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
    bst.add(5);
    bst.add(1);
    bst.add(7);
    bst.add(2);
    Iterator<Integer> iter = bst.traverse(TreeTraversalOrder.LEVEL_ORDER);
    while (iter.hasNext()) {
      System.out.println(iter.next());
    }
  }
}
