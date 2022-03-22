package algo.tree.redblacktree;

import algo.tree.Tree;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

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

  Node(TreeColor color, T elem, Node<T> parent, Node<T> left, Node<T> right) {
    this.color = color;
    this.elem = elem;
    this.parent = parent;
    this.left = left;
    this.right = right;
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

  void print() {
    System.out.println(
        color + " " + parent.elem + "  " + left.elem + " " + elem + " " + right.elem);
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
    if (elem == null) {
      throw new IllegalArgumentException("Red | Black tree does not allow null value.");
    }

    Node<T> tmpTrav = root;
    Node<T> trav = NIL;
    while (!tmpTrav.isNil()) {
      trav = tmpTrav;
      int cmp = elem.compareTo(trav.elem);

      if (cmp == 0) return false;
      tmpTrav = cmp < 0 ? trav.left : trav.right;
    }

    Node<T> node = new Node<T>(TreeColor.RED, elem, trav, NIL, NIL);
    if (trav.isNil()) {
      root = node;
    } else {
      int cmp = elem.compareTo(trav.elem);
      if (cmp < 0) {
        trav.left = node;
      } else {
        trav.right = node;
      }
    }
    balanceInsert(node);
    size++;
    return true;
  }

  private void balanceInsert(Node<T> node) {
    while (node.parent.color == TreeColor.RED) {
      Node<T> parent = node.parent;
      Node<T> grandParent = parent.parent;
      Node<T> uncle = parent == grandParent.right ? grandParent.left : grandParent.right;

      // case 1 uncle is red
      if (uncle.color == TreeColor.RED) {
        grandParent.color = TreeColor.RED;
        parent.color = TreeColor.BLACK;
        uncle.color = TreeColor.BLACK;
        node = grandParent;
        continue;
      }
      boolean isLL = node == parent.left && parent == grandParent.left;
      boolean isRR = node == parent.right && parent == grandParent.right;
      boolean isLine = isLL || isRR;
      // case 2. uncle is black && shape line -> rotate grandparent(opposite of target) + recolor
      if (!isLine) {
        boolean isRL = node == parent.left && parent == grandParent.right;
        node = node.parent;
        parent = node.parent;
        grandParent = parent.parent;
        if (isRL) {
          rightRotation(node);
        } else {
          leftRotation(node);
        }
      }
      // case 3. uncle is black && shape triancle -> rotate parent(opposite of target)
      parent.color = TreeColor.BLACK;
      grandParent.color = TreeColor.RED;
      if (isLL) {
        rightRotation(node.parent.parent);
      } else {
        leftRotation(node.parent.parent);
      }
    }
    // case 0. node is root
    root.color = TreeColor.BLACK;
  }

  @Override
  public boolean remove(T elem) {
    if (elem == null) {
      throw new IllegalArgumentException();
    }
    Node<T> node = search(root, elem);
    if (node.isNil()) return false;
    if (node.left.isNil()) {
      swapElem(node, node.right);
    } else if (node.right.isNil()) {
      swapElem(node, node.left);
    } else {
      Node<T> successor = findMax(node.left);
      swapElem(node, successor);
    }
    balanceRemove(node);
    prune(node, elem);
    return true;
  }

  private void swapElem(Node<T> target, Node<T> subject) {
    T targetElem = target.elem;
    T subjectElem = subject.elem;

    target.elem = subjectElem;
    subject.elem = targetElem;
  }

  private void prune(Node<T> node, T elem) {
    System.out.println("pruning" + node.elem + " " + elem);
    if (node.isNil()) return;
    int cmp = elem.compareTo(node.elem);
    if (cmp < 0) {
      prune(node.left, elem);
      return;
    }
    if (cmp > 0) {
      prune(node.right, elem);
      return;
    }
    if (!node.left.isNil()) node.left.parent = node.parent;
    if (!node.right.isNil()) node.right.parent = node.parent;
  }

  // Terminology
  // - nephew : sibling's child same direction as node
  // - niece : sibling's child opposite direction as node
  private void balanceRemove(Node<T> node) {
    System.out.println(node.elem);
    // case 2. black is deleted
    while (true) {
      if (node == root || node.color == TreeColor.RED) {
        System.out.println(node == root);
        System.out.println(node.color);
        System.out.println("break");
        break;
      }
      Node<T> parent = node.parent;
      Node<T> sibling = parent.left == node ? parent.right : parent.left;
      boolean isLeft = parent.left == node;
      Node<T> nephew, niece;
      if (isLeft) {
        nephew = sibling.left;
        niece = sibling.right;
      } else {
        nephew = sibling.right;
        niece = sibling.left;
      }
      // case 1. red: sibling
      // sol) go to case 2, 3, 4
      //  1) color: parent=>red, sibling=>black
      //  2) rotate: sibling to parent
      if (sibling.color == TreeColor.RED) {
        System.out.println("case 1");
        parent.color = TreeColor.RED;
        sibling.color = TreeColor.BLACK;
        if (isLeft) {
          leftRotation(parent);
          continue;
        }
        rightRotation(parent);
        continue;
      }
      // case 2. black: sibling | red: nephew
      // sol) final
      //  1) color: sibling => parent, parent => black, nephew => black
      //  2) rotate: sibling to parent
      //  3) node = root & break
      if (sibling.color == TreeColor.BLACK && nephew.color == TreeColor.RED) {
        System.out.println("case 2");
        sibling.color = parent.color;
        parent.color = nephew.color = TreeColor.BLACK;
        if (isLeft) {
          rightRotation(parent);
          continue;
        }
        leftRotation(parent);
        node = root;
        break;
      }
      // case 3. red: niece
      // sol) go to case 2
      //  1) color: niece=>black, sibling=>red
      //  2) rotate: niece to sibling
      if (niece.color == TreeColor.RED) {
        System.out.println("case 3");
        niece.color = TreeColor.BLACK;
        sibling.color = TreeColor.RED;
        if (isLeft) {
          rightRotation(sibling);
          continue;
        }
        leftRotation(sibling);
        continue;
      }
      System.out.println("case 4");
      // case(else) 4. black: sibling, niece, nephew
      // sol) go to case 1, 2, 3, 4
      //  1) color: sibling red
      //  2) node = parent
      sibling.color = TreeColor.RED;
      node = parent;
    }
    // node => black
    node.color = TreeColor.BLACK;
  }

  private Node<T> findMax(Node<T> node) {
    while (!node.right.isNil()) node = node.right;
    return node;
  }

  private void leftRotation(Node<T> node) {
    Node<T> newParent = node.right;
    node.right = newParent.left;
    newParent.left = node;
    if (!node.right.isNil()) node.right.parent = node;

    newParent.parent = node.parent;
    node.parent = newParent;
    if (newParent.parent.isNil()) root = newParent;
    if (newParent.parent.left == node) newParent.parent.left = newParent;
    if (newParent.parent.right == node) newParent.parent.right = newParent;
  }

  private void rightRotation(Node<T> node) {
    Node<T> newParent = node.left;
    node.left = newParent.right;
    newParent.right = node;
    if (!node.left.isNil()) node.left.parent = node;

    newParent.parent = node.parent;
    node.parent = newParent;
    if (newParent.parent.isNil()) root = newParent;
    if (newParent.parent.left == node) newParent.parent.left = newParent;
    if (newParent.parent.right == node) newParent.parent.right = newParent;
  }

  @Override
  public boolean contains(T elem) {
    if (search(root, elem) == null) return false;
    return true;
  }

  private Node<T> search(Node<T> node, T elem) {
    if (node.isNil()) return NIL;
    int cmp = elem.compareTo(node.elem);
    if (cmp < 0) return search(node.left, elem);
    if (cmp > 0) return search(node.right, elem);
    return node;
  }

  public Iterator<T> traverse() {
    Queue<Node<T>> queue = new LinkedList<Node<T>>();
    queue.offer(root);
    return new Iterator<T>() {
      @Override
      public boolean hasNext() {
        return !queue.isEmpty();
      }

      @Override
      public T next() {
        Node<T> node = queue.poll();
        node.print();
        if (!node.left.isNil()) queue.offer(node.left);
        if (!node.right.isNil()) queue.offer(node.right);
        return node.elem;
      }
    };
  }
}
