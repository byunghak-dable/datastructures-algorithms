package algo.tree;

public interface TreeInterface<T> {
  boolean isEmpty();

  int size();

  boolean add(T data); // if exist return false

  boolean remove(T data);

  boolean contains(T data);

  int height();
}
