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
enum TreeColor {
  RED,
  BLACK,
}

class Node<T> {
  TreeColor color;
  Node<T> parent, left, right;
  T elem;

  Node(TreeColor color, T elem) {
    this.color = color;
    this.elem = elem;
  }
}

public class RedBlackTree<T extends Comparable<T>> implements TreeInterface<T> {

  private int size = 0;
  private Node<T> root = null;
  private Node<T> NIL;

  public RedBlackTree() {
    NIL = new Node<T>(TreeColor.BLACK, null);
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

  public Node<T> rebalanceInsert(Node<T> node) {
    // case 1. Double Red and uncle "s" is black
    // case 2. Double Red and undle "s" is red
    return null;
  }

  @Override
  public boolean add(T elem) {
    Node<T> node = new Node<T>(TreeColor.RED, elem);
    // add
    // rebalance
    rebalanceInsert(node);
    return false;
  }

  @Override
  public boolean remove(T elem) {
    Node<T> node = search(elem, root);
    // rebalance
    reblanceDelete(node);
    return false;
  }

  private void reblanceDelete(Node<T> node) {}

  private Node<T> search(T elem, Node<T> currentNode) {
    return null;
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
