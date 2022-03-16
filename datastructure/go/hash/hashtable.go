package hash

/**
# Hash table
  - provides a mapping from keys to values using "hashing"
  - ex) track frequencies

  * hash function
    - hash function H(x) maps a key 'x' to a whole number in a fixed range
    - it should be deterministic => keys used in hash table are immutable
  * Hash Collision
    - Open Addressing
    - Seperate chaining
      - each position of in the hash table array is linked list
        => when hash collision occurs add a node to linked list
*/
type HashTable struct {
}
