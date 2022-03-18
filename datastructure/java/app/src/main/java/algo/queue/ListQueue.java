package algo.queue;

import java.util.Iterator;
import java.util.LinkedList;

public class ListQueue<E> implements QueueInterface<E>, Iterable<E>  {
  private LinkedList<E> list = new LinkedList<E>();
  
	@Override
	public void offer(E item) {
    list.add(item);
	}

	@Override
	public E poll() {
    if (isEmpty()) {
      throw new RuntimeException("Queue is empty");
    }
		return list.removeFirst();
  }

	@Override
	public E peek() {
		return list.get(0);
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
