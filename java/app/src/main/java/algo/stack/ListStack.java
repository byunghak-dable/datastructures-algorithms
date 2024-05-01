package algo.stack;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class ListStack<E> implements StackInterface<E>, Iterable<E> {
  private LinkedList<E> list = new LinkedList<E>();

  @Override
  public void push(E elem) {
    list.add(elem);
  }

  @Override
  public E pop() {
    if (isEmpty()) throw new EmptyStackException();
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
