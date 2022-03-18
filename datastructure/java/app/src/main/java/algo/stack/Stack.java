package algo.stack;

import java.util.LinkedList;

public class Stack<E> implements StackInterface<E> {
  private LinkedList<E> list = new LinkedList<E>();
  private int size = 0;

	@Override
	public void push(E item) {
	}

	@Override
	public E pop() {
		return null;
	}

	@Override
	public E peek() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
