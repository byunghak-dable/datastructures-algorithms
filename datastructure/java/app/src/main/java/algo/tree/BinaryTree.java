package algo.tree;

public class BinaryTree<T extends Comparable<T>> {
  class Node {
    Node left;
    Node right;
    T data;
  }

  private Node root = null;

  public BinaryTree(Node left, Node right, T elem) {

  }
}

