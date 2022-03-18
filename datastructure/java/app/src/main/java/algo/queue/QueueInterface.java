package algo.queue;

public interface QueueInterface<E> {
  boolean offer();
  E poll();
  E peek();
  boolean isEmpty();
}
