package main

import (
	"errors"
	"fmt"
	"log"
)

func main() {
	result, err := solution(11)
	if err != nil {
		log.Println(err)
		return
	}
	fmt.Println(result)
}

// N kg
func solution(N int) (int, error) {
	if N < 3 && N > 5000 {
		log.Println("not available n")
		return -1, errors.New("not available param")
	}

	// modulo val
	// x: 5kg, y : 3kg
	x := N / 5
	leftOver := N % 5
	var y int
	for {
		y = leftOver / 3
		leftOver = leftOver % 3
		if leftOver == 0 {
			return x + y, nil
		}
		if x < 0 {
			break
		}
		x = x - 1
		leftOver = N - 5*(x)
	}

	return -1, nil
}
