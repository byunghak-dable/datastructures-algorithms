package unionfind

import "log"

/**
Always Unify
Path Compression
  - compress along the way of union processing
*/

type UnionFind struct {
	id            []int // map with root id
	sz            []int // size of root's component
	size          int   // size of elements
	numComponents int   // number of component
}

func (u UnionFind) Find(target int) int {
	root := target
	for root != u.id[root] {
		root = u.id[root]
	}
	// TODO: path compression

	return root
}

func (u UnionFind) Unify(group1, group2 int) {
	// merge smaller to larger group
	root1 := u.Find(group1)
	root2 := u.Find(group2)

	if root1 == root2 {
		log.Println("already in same group")
		return
	}

}
