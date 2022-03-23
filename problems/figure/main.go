package main

import (
	"errors"
	"log"
)

type Position struct {
	x int
	y int
}

func main() {
	solution([]int{1, 2, 6, 4})
}

/**
- 도형을 만드는 방법
1. no round trip
2. come back to
*/
func solution(arrows []int) {
	positions := []Position{}
	err := makeFootPrint(arrows, &positions)
	if err != nil {
		log.Println(err)
		return
	}
}

// make slice of footprints with arrows
func makeFootPrint(arrows []int, positions *[]Position) error {
	var currentPos Position
	lastPos := Position{0, 0}
	*positions = append(*positions, lastPos)
	for _, direction := range arrows {
		switch direction {
		case 0:
			currentPos = Position{lastPos.x, lastPos.y + 1}
		case 1:
			currentPos = Position{lastPos.x + 1, lastPos.y + 1}
		case 2:
			currentPos = Position{lastPos.x + 1, lastPos.y}
		case 3:
			currentPos = Position{lastPos.x + 1, lastPos.y - 1}
		case 4:
			currentPos = Position{lastPos.x, lastPos.y - 1}
		case 5:
			currentPos = Position{lastPos.x - 1, lastPos.y - 1}
		case 6:
			currentPos = Position{lastPos.x - 1, lastPos.y}
		case 7:
			currentPos = Position{lastPos.x - 1, lastPos.y + 1}
		default:
			return errors.New("wrong arrow")
		}
		*positions = append(*positions, currentPos)
		lastPos = currentPos
	}
	return nil
}
