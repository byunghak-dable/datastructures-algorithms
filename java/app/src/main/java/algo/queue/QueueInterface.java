package algo.queue;

public interface QueueInterface<E> {
  void offer(E item);

  E poll();

  E peek();

  boolean isEmpty();
}
