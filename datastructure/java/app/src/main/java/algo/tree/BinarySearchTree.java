package algo.tree;

public class BinarySearchTree<T extends Comparable<T>> implements TreeInterface<T> {
  class Node {
    Node left;
    Node right;
    T data;

    Node(T data) {
      this.data = data;
    }
  }

  private Node root = null;
  private int size = 0;

  public BinarySearchTree() {
  }

  public BinarySearchTree(T data) {
    this.root = new Node(data);
  }

  public BinarySearchTree(Node left, Node right, T data) {
    this.root = new Node(data);
    this.root.left = left;
    this.root.right = right;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean add(T data) {
    // TODO can be optimized
    if (contains(data)) {
      return false;
    }
    root = add(root, data);
    size++;
    return true;
  }

  private Node add(Node node, T data) {
    if (node == null) {
      return new Node(data);
    }
    if (data.compareTo(node.data) < 0) {
      node.left = add(node.left, data);
    } else {
      node.right = add(node.right, data);
    }
    return node;
  }

  @Override
  public boolean remove(T data) {
    if (contains(data)) {
      root = remove(root, data);
      size--;
      return true;
    }
    return false;
  }

  private Node remove(Node node, T data) {
    if (node == null) {
      return null;
    }
    int cmp = data.compareTo(node.data);
    if (cmp < 0) {
      node.left = remove(node.left, data);
    } else if (cmp > 0) {
      node.right = remove(node.right, data);
    } else {
      if (node.left == null && node.right == null) {
        return null;
      }
      if (node.left == null) {
        return node.right;
      }
      if (node.right == null) {
        return node.left;
      }
      Node tmp = findMin(node.right);
      node.data = tmp.data;
      node.right = remove(node.right, tmp.data);
    }
    return node;
  }

  private Node findMin(Node node) {
    while (node.left != null) {
      node = node.left;
    }
    return node;
  }

  private Node findMax(Node node) {
    while (node.right != null) {
      node = node.right;
    }
    return node;
  }

  @Override
  public boolean contains(T data) {
    return contains(root, data);
  }

  private boolean contains(Node node, T data) {
    if (node == null) {
      return false;
    }
    int cmp = data.compareTo(node.data);
    if (cmp == 0) {
      return true;
    }
    if (cmp < 0) {
      return contains(node.left, data);
    }
    if (cmp > 0) {
      return contains(node.right, data);
    }
    return false;
  }

  @Override
  public int height() {
    return height(root);
  }

  private int height(Node node) {
    if (node == null) {
      return 0;
    }
    return Math.max(height(node.left), height(node.right)) + 1;
  }
}
