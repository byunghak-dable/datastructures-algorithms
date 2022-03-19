package algo.queue.pqueue;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/**
 * # Insertion
 * - Shape : left first
 * - Invarient : swap the node with parent node
 * # Deletion
 * - Shape : should be balanced, if both side is even then swap with right side
 * - Invarient : swap the node with sammler child
 */
// A min heap
public class BinaryListHeap<T extends Comparable<T>> {
  private List<T> heap = null;

  public BinaryListHeap() {
    this(1);
  }

  public BinaryListHeap(int size) {
    this.heap = new ArrayList<T>(size);
  }

  /**
   * heapify in O(n) time
   * http://www.cs.umd.edu/~meesh/351/mount/lectures/lect14-heapsort-analysis-part.pdf
   */
  public BinaryListHeap(T[] items) {
    int heapSize = items.length;
    heap = new ArrayList<T>(heapSize);

    for (T item : items) {
      heap.add(item);
    }
    // TODO heapify(sink)
    // heapify process, O(n)
  }

  public BinaryListHeap(Collection<T> items) {
    int heapSize = items.size();
    this.heap = new ArrayList<T>(heapSize);
    this.heap.addAll(items);
    // TODO heapify(sink)
    // heapify process, O(n)
  }

  public boolean isEmpty() {
    return heap.size() == 0;
  }

  public int size() {
    return heap.size();
  }

  public T peek() {
    if (isEmpty()) {
      return null;
    }
    return heap.get(0);
  }

  public T poll() {
    return removeAt(0);
  }

  public boolean contains(T elem) {
    for (T existElem : heap) {
      if (elem.equals(existElem)) {
        return true;
      }
    }
    return false;
  }

  public void add(T elem) {
    if (elem == null) {
      throw new IllegalArgumentException();
    }
    heap.add(elem);
    int lastIndex = size() - 1;
    swim(lastIndex);
  }

  public boolean remove(T elem) {
    for (int i = 0; i < size(); i++) {
      if (elem.equals(heap.get(i))) {
        removeAt(i);
        return true;
      }
    }
    return false;
  }

  public T removeAt(int target) {
    if (isEmpty()) {
      return null;
    }
    int lastIndex = size() - 1;

    if (target > lastIndex) {
      throw new IllegalArgumentException();
    }

    T removedNode = heap.get(target);
    swap(target, lastIndex);
    sink(target);
    heap.remove(lastIndex);

    if (target == lastIndex) {
      return removedNode;
    }

    T node = heap.get(target);
    swim(target);

    if (node.equals(heap.get(target))) {
      sink(target);
    }
    return removedNode;
  }

  // reheapify by going up
  private void swim(int target) {
    int parent = getParentIndex(target);

    while (target > 0 && isLess(target, parent)) {
      swap(target, parent);
      target = parent;
      parent = getParentIndex(target);
    }
  }

  // reheapify by going down
  private void sink(int target) {
    // left is smalles of three
    int heapSize = size();
    while (true) {
      int left = 2 * target + 1;
      int right = 2 * target + 2;
      int smallest = left; // assume that left is smallest

      if (right < heapSize && isLess(right, left)) {
        smallest = right;
      }
      if (left >= heapSize || isLess(target, smallest)) {
        break;
      }
      swap(target, smallest);
      target = smallest;
    }
  }

  /**
   * Left Child = 2 * parent + 1
   * Right Chidl = 2 * parent + 2
   */
  private int getParentIndex(int child) {
    return (child - 1) / 2;
  }

  private boolean isLess(int i, int j) {
    T target = heap.get(i);
    T subject = heap.get(j);

    return target.compareTo(subject) < 0;
  }

  private void swap(int i, int j) {
    T target = heap.get(i);
    T subject = heap.get(j);

    heap.set(i, subject);
    heap.set(j, target);
  }

}
