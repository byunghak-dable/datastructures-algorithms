package algo.hashtable;

public interface HashtableInterface<K, V> {
  V put(K key, V value);
  V get(K Key);
  V remove(K key);
  void clear();
  int size();
  boolean contains();
  boolean containsKey();
  boolean containsValue();
  boolean isEmpty();
}
