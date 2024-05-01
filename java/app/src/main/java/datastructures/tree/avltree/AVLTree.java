package datastructures.tree.avltree;

import datastructures.tree.Tree;
import datastructures.tree.TreePrinter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class Node<T> implements TreePrinter {
  int bf; // balance factor : H(L) - H(R)
  int height; // node height
  Node<T> left, right;
  T elem;

  public Node(T elem) {
    this.elem = elem;
  }

  @Override
  public TreePrinter getLeft() {
    return left;
  }

  @Override
  public TreePrinter getRight() {
    return right;
  }

  @Override
  public String getElem() {
    return elem.toString();
  }

  public void print() {
    T leftElem = left == null ? null : left.elem;
    T rightElem = right == null ? null : right.elem;
    System.out.println(leftElem + " " + elem + " " + rightElem);
  }
}

// Balanced Tree
// - Balance Factor: B(x) = H(L) - H(R)
public class AVLTree<T extends Comparable<T>> implements Tree<T> {
  private Node<T> root;
  private int size = 0;

  public AVLTree() {}

  @Override
  public int size() {
    return size;
  }

  @Override
  public int height() {
    if (root == null) return 0;
    return root.height;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean contains(T elem) {
    return contains(root, elem);
  }

  private boolean contains(Node<T> node, T elem) {
    if (node == null) return false;
    int cmp = elem.compareTo(node.elem);
    if (cmp == 0) return true;

    return cmp < 0 ? contains(node.left, elem) : contains(node.right, elem);
  }

  @Override
  public boolean add(T elem) {
    if (contains(root, elem)) return false;
    root = add(root, elem);
    size++;
    return true;
  }

  @Override
  public boolean remove(T elem) {
    if (!contains(elem)) return false;
    root = remove(root, elem);
    size--;
    return true;
  }

  private Node<T> remove(Node<T> node, T elem) {
    int cmp = elem.compareTo(node.elem);

    if (cmp < 0) {
      node.left = remove(node.left, elem);
    } else if (cmp > 0) {
      node.right = remove(node.right, elem);
    } else {
      if (node.left == null) return node.right;
      if (node.right == null) return node.left;
      if (node.left.height > node.right.height) {
        Node<T> successor = findMax(node.left);
        node.elem = successor.elem;
        node.left = remove(node.left, node.elem);
      } else {
        Node<T> successor = findMin(node.right);
        node.elem = successor.elem;
        node.right = remove(node.right, node.elem);
      }
    }
    update(node);
    return balance(node);
  }

  private Node<T> findMin(Node<T> node) {
    while (node.left != null) node = node.left;
    return node;
  }

  private Node<T> findMax(Node<T> node) {
    while (node.right != null) node = node.right;
    return node;
  }

  private Node<T> add(Node<T> node, T elem) {
    if (node == null) return new Node<T>(elem);
    int cmp = elem.compareTo(node.elem);

    if (cmp < 0) node.left = add(node.left, elem);
    if (cmp > 0) node.right = add(node.right, elem);
    update(node);
    return balance(node);
  }

  private void update(Node<T> node) {
    int leftHeight = node.left == null ? -1 : node.left.height;
    int rightHeight = node.right == null ? -1 : node.right.height;

    node.height = Math.max(leftHeight, rightHeight) + 1;
    node.bf = leftHeight - rightHeight;
  }

  private Node<T> balance(Node<T> node) {
    // left heavy subtree
    if (node.bf == 2) {
      return node.left.bf >= 0 ? leftLeftCase(node) : leftRightCase(node);
      // right heavy subtree
    } else if (node.bf == -2) {
      return node.right.bf <= 0 ? rightRightCase(node) : rightLeftCase(node);
    }
    // balanced
    return node;
  }

  private Node<T> leftLeftCase(Node<T> node) {
    return rightRotation(node);
  }

  private Node<T> leftRightCase(Node<T> node) {
    node.left = leftRotation(node.left);
    return leftLeftCase(node);
  }

  private Node<T> rightLeftCase(Node<T> node) {
    node.right = leftRotation(node.right);
    return rightRightCase(node);
  }

  private Node<T> rightRightCase(Node<T> node) {
    return leftRotation(node);
  }

  private Node<T> leftRotation(Node<T> node) {
    Node<T> newParent = node.right;
    node.right = newParent.left;
    newParent.left = node;
    update(node);
    update(newParent);
    return newParent;
  }

  private Node<T> rightRotation(Node<T> node) {
    Node<T> newParent = node.left;
    node.left = newParent.right;
    newParent.right = node;
    update(node);
    update(newParent);
    return newParent;
  }

  public Iterator<T> traverse() {
    Queue<Node<T>> queue = new LinkedList<Node<T>>();
    queue.add(root);
    return new Iterator<T>() {

      @Override
      public boolean hasNext() {
        return !queue.isEmpty();
      }

      @Override
      public T next() {
        Node<T> node = queue.poll();
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
        node.print();
        return node.elem;
      }
    };
  }
}
