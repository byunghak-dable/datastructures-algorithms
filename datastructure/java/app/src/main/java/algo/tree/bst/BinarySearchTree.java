package algo.tree.bst;

import algo.tree.TreeInterface;
import algo.tree.TreeTraversalOrder;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node<T> {
  Node<T> left;
  Node<T> right;
  T elem;

  Node(T elem) {
    this.elem = elem;
  }
}

public class BinarySearchTree<T extends Comparable<T>> implements TreeInterface<T> {

  private Node<T> root = null;
  private int size = 0;

  public BinarySearchTree() {}

  public BinarySearchTree(T elem) {
    this.root = new Node<T>(elem);
  }

  public BinarySearchTree(Node<T> left, Node<T> right, T elem) {
    this.root = new Node<T>(elem);
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
  public boolean add(T elem) {
    // TODO can be optimized
    if (contains(elem)) return false;

    root = add(root, elem);
    size++;
    return true;
  }

  private Node<T> add(Node<T> node, T elem) {
    if (node == null) {
      return new Node<T>(elem);
    }
    if (elem.compareTo(node.elem) < 0) {
      node.left = add(node.left, elem);
    } else {
      node.right = add(node.right, elem);
    }
    return node;
  }

  @Override
  public boolean remove(T elem) {
    if (contains(elem)) {
      root = remove(root, elem);
      size--;
      return true;
    }
    return false;
  }

  private Node<T> remove(Node<T> node, T elem) {
    if (node == null) return null;

    int cmp = elem.compareTo(node.elem);
    if (cmp < 0) {
      node.left = remove(node.left, elem);
    } else if (cmp > 0) {
      node.right = remove(node.right, elem);
    } else {
      if (node.left == null && node.right == null) return null;
      if (node.left == null) return node.right;
      if (node.right == null) return node.left;

      Node<T> tmp = findMin(node.right);
      node.elem = tmp.elem;
      node.right = remove(node.right, tmp.elem);
    }
    return node;
  }

  private Node<T> findMin(Node<T> node) {
    while (node.left != null) node = node.left;
    return node;
  }

  private Node<T> findMax(Node<T> node) {
    while (node.right != null) node = node.right;
    return node;
  }

  @Override
  public boolean contains(T elem) {
    return contains(root, elem);
  }

  private boolean contains(Node<T> node, T elem) {
    if (node == null) return false;

    int cmp = elem.compareTo(node.elem);
    if (cmp == 0) return true;
    if (cmp < 0) return contains(node.left, elem);
    if (cmp > 0) return contains(node.right, elem);

    return false;
  }

  @Override
  public int height() {
    return height(root);
  }

  private int height(Node<T> node) {
    if (node == null) return -1;
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
    final Stack<Node<T>> stack = new Stack<Node<T>>();
    stack.push(root);

    return new Iterator<T>() {
      @Override
      public boolean hasNext() {
        if (expectSize != size) throw new ConcurrentModificationException();

        return root != null && !stack.isEmpty();
      }

      @Override
      public T next() {
        Node<T> trav = stack.pop();
        if (trav.right != null) stack.push(trav.right);
        if (trav.left != null) stack.push(trav.left);

        return trav.elem;
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
    Stack<Node<T>> stack = new Stack<Node<T>>();
    stack.push(root);

    return new Iterator<T>() {
      private Node<T> trav = root;

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
        Node<T> node = stack.pop();
        if (node.right != null) {
          stack.push(node.right);
          trav = node.right;
        }
        return node.elem;
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
    Stack<Node<T>> stack = new Stack<Node<T>>();
    Stack<Node<T>> tmpStack = new Stack<Node<T>>();
    tmpStack.push(root);
    while (!tmpStack.isEmpty()) {
      Node<T> node = tmpStack.pop();
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
        return stack.pop().elem;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  public Iterator<T> levelOrderTraversal() {
    int expectSize = size;
    Queue<Node<T>> queue = new LinkedList<Node<T>>();
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

        Node<T> node = queue.poll();
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);

        return node.elem;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
}
