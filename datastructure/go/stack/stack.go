package stack

type Stack struct {
	list []interface{}
}

func NewStack() *Stack {
	return &Stack{
		list: []interface{}{},
	}

}
