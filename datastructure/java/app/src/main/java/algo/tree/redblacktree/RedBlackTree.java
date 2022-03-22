package algo.tree.redblacktree;

import algo.tree.Tree;

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

  boolean isNil() {
    return elem == null;
  }

  boolean isBlack() {
    return color == TreeColor.BLACK;
  }

  boolean isRed() {
    return color == TreeColor.RED;
  }

  void setColor(TreeColor color) {
    if (isNil()) {
      this.color = TreeColor.BLACK;
      return;
    }
    this.color = color;
  }
}

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
public class RedBlackTree<T extends Comparable<T>> implements Tree<T> {

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

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int height() {
    return height(root);
  }

  private int height(Node<T> node) {
    return Math.max(height(node.left), height(node.right)) + 1;
  }

  @Override
  public boolean add(T elem) {
    if (elem == null)
      throw new IllegalArgumentException("Red | Black tree does not allow null value.");
    Node<T> x = root;
    Node<T> y = NIL;
    while (x != NIL) {
      y = x;
      int cmp = elem.compareTo(y.elem);

      if (cmp == 0) return false;
      x = cmp < 0 ? x.left : x.right;
    }
    // Node<T> node = new Node<T>(TreeColor.RED, elem);
    // add
    // rebalance
    return false;
  }

  @Override
  public boolean remove(T elem) {
    Node<T> node = search(elem, root);
    // rebalance
    return false;
  }

  private Node<T> search(T elem, Node<T> currentNode) {
    return null;
  }

  @Override
  public boolean contains(T elem) {
    Node<T> node = root;
    if (node == null || node.elem == null) return false;

    while (node != NIL) {
      int cmp = elem.compareTo(node.elem);

      if (cmp == 0) return true;
      node = cmp < 0 ? node.left : node.right;
    }
    return false;
  }
}
