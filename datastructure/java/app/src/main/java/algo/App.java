package algo;

import algo.tree.redblacktree.RedBlackTree;
import java.util.Iterator;

class Test {
  int param = 9;
}
// binary heap => PQueue => Union Find
public class App {
  public static void main(String[] args) {
    RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
    tree.add(1);
    tree.add(2);
    tree.add(3);
    tree.add(4);
    tree.add(5);
    tree.add(6);
    tree.add(7);
    tree.add(8);
    print(tree.traverse());
    tree.remove(3);
    print(tree.traverse());
  }

  static <T> void print(Iterator<T> iter) {
    while (iter.hasNext()) {
      iter.next();
    }
  }
}
