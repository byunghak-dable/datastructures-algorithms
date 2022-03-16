package tree

/**
* Binaray Tree
  - a tree which every node has at most two child nodes
# Binary Search Tree
  * definition
    - Current node > left subtree's elements
    - Current node < right subtree's elements

  * find phase
    1. we hit null (no match)
    2. comparator value = 0 (match)
    3. comparator value < 0 (if exist value is in left subtree)
    4. comparator value > 0 (if exist value is in right subtree)

  * remove phase
    1. target node is leaf node
    2. target node has only right subtree
      - right node successor become the root
    3. target node has only left subtree
      - left node successor become the root
    4. target node has both left and right subtree
      - two possible successor
      - find (smallest node of right subtree | largest node of left subtree)
      - copy the found node and paste it to target node
      - remove original of copied node and do phase 1 or 2 or 3

  * removing
    1. find target element
    2. replace the target node with its successor to maintain the BST variant

TODO: need to try myself
# Binary tree traversals
  // Stack(Recursive)
  * Preorder
    - prints before the recursive calls
    - preorder():
        if node == null: return
          print(node.value)
          preorder(node.left)
          preorder(node.right)
  * Inorder : print values increasing order
    - prints between the recursive calls
    - inorder():
        if node == null: return
          inorder(node.left)
          print(node.value)
          inorder(node.right)
  * Postorder
    - prints after the recursive calls
    - postorder():
        if node == null: return
          postorder(node.left)
          postorder(node.right)
          print(node.value)
  // Queue
  * Level order
    - print the node one layer at a time as they appear
    - BFS(Breath First Search)
      > maintain a Queue of left to explore
      > at each iteration add left and then right child of current node to Queue

*/
type BinarayTree struct {
}
