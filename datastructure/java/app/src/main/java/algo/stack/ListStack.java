package algo.stack;

import java.util.LinkedList;
import java.util.EmptyStackException;
import java.util.Iterator;

public class ListStack<E> implements StackInterface<E>, Iterable<E> {
  private LinkedList<E> list = new LinkedList<E>();

  @Override
  public void push(E item) {
    list.add(item);
  }

  @Override
  public E pop() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    return list.pop();
  }

  @Override
  public E peek() {
    return list.get(list.size() - 1);
  }

  @Override
  public boolean isEmpty() {
    return list.size() == 0;
  }

  @Override
  public Iterator<E> iterator() {
    return list.iterator();
  }

}
