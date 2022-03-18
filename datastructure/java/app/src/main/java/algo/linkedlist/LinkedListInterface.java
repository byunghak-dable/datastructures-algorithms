package algo.linkedlist;

public interface LinkedListInterface<T> {
  int size();
  boolean isEmpty();
  void add(int index, T data);
  void addFirst(T data);
  void addLast(T data);
  T remove(int index);
  T removeFirst();
  T removeLast(); 
  T get(int index);
  boolean contains(T data);
}
