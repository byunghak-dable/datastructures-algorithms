package singly

type node struct {
	next *node
	data interface{}
}

type LinkedList struct {
	head *node
	tail *node
	size int
}

func (l *LinkedList) AddFirst(data interface{}) {

}

func (l *LinkedList) AddLast(data interface{}) {

}

func (l *LinkedList) Add(index int, data interface{}) {

}

func (l *LinkedList) RemoveFirst(data interface{}) {

}

func (l *LinkedList) RemoveLast(data interface{}) {

}

func (l *LinkedList) Remove(index int, data interface{}) {

}
