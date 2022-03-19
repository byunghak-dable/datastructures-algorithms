package algo;

import algo.stack.ListStack;
import algo.tree.BinarySearchTree;
import algo.tree.TreeTraversalOrder;
import java.util.Iterator;

// binary heap => PQueue => Union Find
public class App {
  public static void main(String[] args) {
    // SinglyLinkedList<String> singlyList = new SinglyLinkedList<String>();
    // singlyList.addLast("yes1");
    // singlyList.addLast("yes2");
    // singlyList.addLast("yes3");
    // singlyList.addLast("yes4");
    // singlyList.addFirst("first1");
    // singlyList.addFirst("first2");
    // ListStack<String> stack = new ListStack<String>();
    // System.out.println(stack.isEmpty());
    // stack.push("yes");
    // stack.push("yes1");
    // stack.push("yes2");
    // for (String string : stack) {
    // System.out.println(string);
    //
    // }
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
