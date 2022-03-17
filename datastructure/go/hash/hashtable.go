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
      & Issue of probing cycle(loop)
        => probing function used with these method are very specific
      - Linear probing
        - Inserting : H(k) + P(x) mod N
          => sol-cycle) P(x)=ax -> GCD(a, N)=1 -> Always find empty bucket!
        - removing : put tombstone instead of nil when delete
      - Quadratic probing
        - Inserting : H(k) + P(x) mod N
          => sol-cycle) P(x) = x^2, keep N a prime number > 3, alph <= 1/2
          => sol-cycle) P(x) = (x^2 + x)/2, N=power to two
          => sol-cycle) P(x) = (-1^x)x^2, N=prime N Where N === 3 mod 4
        - removing : put tombstone instead of nil when delete
      - Double hashing
        - Inserting : H1(k) + x*delta mod N -> delta=H2(k) mod N
          => sol-cycle) N=prime number, delta=H2(k) mod N, 1 <= delta < N and GCD(delta, N)=1
        - removing : put tombstone instead of nil when delete
      - Pseudo random number generator
    - Seperate chaining
      - each position of in the hash table array is linked list
        => when hash collision occurs add a node to linked list
  * Terminology
    - bucket : array that manage data
    - Greatest Common Denominator(GCD) : 최대 공약수
    - load factor : a measure that decides when to increase the HashTable capacity's
                    to maintain the search & insert complexity of O(1)
    - threshold = N(total size of table) * alpha(max load factor) : when should resize hash table count
*/
type HashTable struct {
}
