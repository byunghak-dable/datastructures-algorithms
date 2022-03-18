package algo.linkedlist;

import java.util.Iterator;

public class SinglyLinkedList<T> implements LinkedListInterface<T>, Iterable<T> {
  class Node<K> {
    Node<K> next;
    K data;

    Node(K data) {
      this.data = data;
    }

    Node(Node<K> next, K data) {
      this.next = next;
      this.data = data;
    }
  }

  private int size = 0;
  private Node<T> head, tail = null;

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void addFirst(T data) {
    Node<T> node = new Node<T>(data);
    if (head == null) {
      head = tail = node;
    } else {
      node.next = head;
      head = node;
    }
    size++;
  }

  @Override
  public void addLast(T data) {
    Node<T> node = new Node<T>(data);
    if (tail == null) {
      head = tail = node;
    } else {
      tail.next = node;
      tail = node;
    }
    size++;
  }

  @Override
  public void add(int index, T data) {
    if (index > size - 1) {
      throw new RuntimeException("not available index");
    }
    Node<T> trav = head;
    for (int i = 0; i <= index; i++) {
      if (index == i + 1) {
        Node<T> node = new Node<T>(data);
        node.next = trav.next;
        trav.next = node;
      }
      trav = trav.next;
    }
  }

  @Override
  public T removeFirst() {
    if (isEmpty()) {
      throw new RuntimeException("empty linked list");
    }
    Node<T> trav = head;
    T data = trav.data;
    head = trav.next;
    if (size == 1) {
      tail = null;
    }
    trav = null;
    size--;
    return data;
  }

  @Override
  public T removeLast() {
    if (isEmpty()) {
      throw new RuntimeException("empty linked list");
    }
    Node<T> prevTrav = null;
    Node<T> trav = head;
    while (trav.next != null) {
      prevTrav = trav;
      trav = trav.next;
    }
    T data = trav.data;
    prevTrav.next = null;
    tail = prevTrav;
    if (size == 1) {
      head = null;
    }
    trav = null;
    size--;
    return data;
  }

  @Override
  public T remove(int index) {
    if (index > size - 1) {
      throw new RuntimeException("index not available");
    }
    Node<T> trav = head;
    T data = null;
    for (int i = 0; i < index; i++) {
      if (i == index - 1) {
        break;
      }
      trav = trav.next;
    }
    Node<T> target = trav.next;
    data = target.data;
    target = null;
    trav.next = trav.next.next;
    return data;
  }

  @Override
  public boolean contains(T data) {
    Node<T> trav = head;
    while (trav != null) {
      if (trav.data == data) {
        return true;
      }
      trav = trav.next;
    }
    return false;
  }

  @Override
  public T get(int index) {
    if (index >= size) {
      return null;
    }
    Node<T> trav = head;
    for (int i = 0; i <= index; i++) {
      if (i == index) {
        break;
      }
      trav = trav.next;
    }
    return trav.data;
  }

  public void printAll() {
    Node<T> trav = head;
    while (trav != null) {
      System.out.println(trav.data);
      trav = trav.next;
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Node<T> trav = head;

      @Override
      public boolean hasNext() {
        return trav != null;
      }

      @Override
      public T next() {
        T data = trav.data;
        trav = trav.next;
        return data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
}
