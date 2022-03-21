package algo.tree;

/**
 * @category --- Definition ---
 * @cond 1. each node get Red | Black color
 * @cond 2. root node : Black
 * @cond 3. each leaf node : Black(nil if empty)
 * @cond 4. if Red node then children are Black nodes
 * @cond 5. each node has same number of black node to descendant leaves
 * @category --- Performance ---
 * @cond Search : O(log(n))
 * @cond Insert : O(log(n))
 * @cond Delete : O(log(n))
 * @reference prove lecture - https://www.youtube.com/watch?v=SHdYv41iCmE
 * @category --- Inserting ---
 * @cond 1. Double Red and uncle "s" is black
 * @solution : rotaion
 * @cond 2. Double Red and uncle "s" is red
 * @solution : p[target] to black, p[p[target]] to red and so on~
 */
public class RedBlackTree<T extends Comparable<T>> implements TreeInterface<T> {
  private static final boolean RED = true;
  private static final boolean BLACK = false;

  class Node {
    boolean color;
    Node parent, left, right;
    T elem;

    Node(boolean color, T elem) {
      this.color = color;
      this.elem = elem;
    }
  }

  private int size = 0;
  private Node root = null;
  private Node NIL;

  public RedBlackTree() {
    NIL = new Node(BLACK, null);
    NIL.parent = NIL;
    NIL.left = NIL;
    NIL.right = NIL;

    root = NIL;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public Node rebalance(Node node) {
    // case 1. Double Red and uncle "s" is black
    // case 2. Double Red and undle "s" is red
    return null;
  }

  @Override
  public boolean add(T elem) {
    Node node = new Node(true, elem);
    // add
    // rebalanceInsert
    rebalance(node);
    return false;
  }

  @Override
  public boolean remove(T elem) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean contains(T elem) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int height() {
    // TODO Auto-generated method stub
    return 0;
  }
}
