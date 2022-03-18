package algo.queue;


public class Queue<E> implements QueueInterface<E>  {

	@Override
	public boolean offer() {
		return false;
	}

	@Override
	public E poll() {
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
