package algo.tree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

  public BinarySearchTree() {}

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
    if (contains(data)) return false;

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
    if (node == null) return null;

    int cmp = data.compareTo(node.data);
    if (cmp < 0) {
      node.left = remove(node.left, data);
    } else if (cmp > 0) {
      node.right = remove(node.right, data);
    } else {
      if (node.left == null && node.right == null) return null;
      if (node.left == null) return node.right;
      if (node.right == null) return node.left;

      Node tmp = findMin(node.right);
      node.data = tmp.data;
      node.right = remove(node.right, tmp.data);
    }
    return node;
  }

  private Node findMin(Node node) {
    while (node.left != null) node = node.left;
    return node;
  }

  private Node findMax(Node node) {
    while (node.right != null) node = node.right;
    return node;
  }

  @Override
  public boolean contains(T data) {
    return contains(root, data);
  }

  private boolean contains(Node node, T data) {
    if (node == null) return false;

    int cmp = data.compareTo(node.data);
    if (cmp == 0) return true;
    if (cmp < 0) return contains(node.left, data);
    if (cmp > 0) return contains(node.right, data);

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

  public Iterator<T> traverse(TreeTraversalOrder type) {
    switch (type) {
      case PRE_ORDER:
        return preOrderTraversal();
      case IN_ORDER:
        return inOrderTraversal();
      case POST_ORDER:
        return postOrderTraversal();
      case LEVEL_ORDER:
        return levelOrderTraversal();
      default:
        return null;
    }
  }

  public Iterator<T> preOrderTraversal() {
    final int expectSize = size;
    final Stack<Node> stack = new Stack<Node>();
    stack.push(root);

    return new Iterator<T>() {
      @Override
      public boolean hasNext() {
        if (expectSize != size) throw new ConcurrentModificationException();

        return root != null && !stack.isEmpty();
      }

      @Override
      public T next() {
        Node trav = stack.pop();
        if (trav.right != null) stack.push(trav.right);
        if (trav.left != null) stack.push(trav.left);

        return trav.data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  // TODO: try again
  public Iterator<T> inOrderTraversal() {
    int expectSize = size;
    Stack<Node> stack = new Stack<Node>();
    stack.push(root);

    return new Iterator<T>() {
      private Node trav = root;

      @Override
      public boolean hasNext() {
        if (expectSize != size) {
          throw new ConcurrentModificationException();
        }
        return root != null && !stack.isEmpty();
      }

      @Override
      public T next() {
        if (expectSize != size) {
          throw new ConcurrentModificationException();
        }
        while (trav != null && trav.left != null) {
          stack.push(trav.left);
          trav = trav.left;
        }
        Node node = stack.pop();
        if (node.right != null) {
          stack.push(node.right);
          trav = node.right;
        }
        return node.data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  // TODO: try again
  public Iterator<T> postOrderTraversal() {
    int expectSize = size;
    Stack<Node> stack = new Stack<Node>();
    Stack<Node> tmpStack = new Stack<Node>();
    tmpStack.push(root);
    while (!tmpStack.isEmpty()) {
      Node node = tmpStack.pop();
      if (node == null) continue;

      stack.push(node);
      if (node.left != null) tmpStack.push(node.left);
      if (node.right != null) tmpStack.push(node.right);
    }

    return new Iterator<T>() {
      @Override
      public boolean hasNext() {
        if (expectSize != size) throw new ConcurrentModificationException();

        return root != null && !stack.isEmpty();
      }

      @Override
      public T next() {
        if (expectSize != size) {
          throw new ConcurrentModificationException();
        }
        return stack.pop().data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  public Iterator<T> levelOrderTraversal() {
    int expectSize = size;
    Queue<Node> queue = new LinkedList<Node>();
    queue.offer(root);

    return new Iterator<T>() {
      @Override
      public boolean hasNext() {
        if (expectSize != size) throw new ConcurrentModificationException();

        return root != null && !queue.isEmpty();
      }

      @Override
      public T next() {
        if (expectSize != size) throw new ConcurrentModificationException();

        Node node = queue.poll();
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);

        return node.data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
}
