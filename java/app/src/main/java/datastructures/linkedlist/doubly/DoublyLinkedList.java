package datastructures.linkedlist.doubly;

import datastructures.linkedlist.LinkedListInterface;

class Node<T> {
  Node<T> prev;
  Node<T> next;
  T data;
}

public class DoublyLinkedList<T> implements LinkedListInterface<T> {

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void add(int index, T data) {
    // TODO Auto-generated method stub
  }

  @Override
  public void addFirst(T data) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addLast(T data) {
    // TODO Auto-generated method stub

  }

  @Override
  public T remove(int index) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public T removeFirst() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public T removeLast() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public T get(int index) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean contains(T data) {
    // TODO Auto-generated method stub
    return false;
  }
}
