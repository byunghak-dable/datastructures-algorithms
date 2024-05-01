package datastructures;

import datastructures.tree.TreeTraversalOrder;
import datastructures.tree.bst.BinarySearchTree;
import java.util.Iterator;

public class App {
  public static void main(String[] args) {
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    bst.add(3);
    bst.add(5);
    bst.add(2);
    Iterator<Integer> iter = bst.traverse(TreeTraversalOrder.IN_ORDER);
    while (iter.hasNext()) {
      System.out.println(iter.next());
    }
  }
}
