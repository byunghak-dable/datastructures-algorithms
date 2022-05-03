# 참고 문제 

- https://programmers.co.kr/learn/courses/30/lessons/1835
- 이진탐색 : https://programmers.co.kr/learn/courses/30/lessons/72412?language=python3

# TIP
  - sorting 문제는 보통 문제에 답이 있다. + stable sort 확인하기
  - 이진 탐색 연습하기
  - 문자열 regex 사용법 외우기

# 알고리즘
  - 투 포인터(Two Pointer)

  - 누적 합(Prefix Sum)

  - 비트 마스킹

  - CPU 스케쥴러(SJF)
    https://jhnyang.tistory.com/155
    https://programmers.co.kr/learn/courses/30/lessons/42627

  - 미니맥스 알고리즘(Minimax Algo)

  - kruskal Algo(Minimum Spanning Tree) -> Union Find(Cycle check, Graph)
    https://lipcoder.tistory.com/entry/%ED%81%AC%EB%9F%AC%EC%8A%A4%EC%BB%AC-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Kruskal-Algorithm
    https://programmers.co.kr/learn/courses/30/lessons/42861
  - 다익스트라(Dijkstra Algo)
  - 벨만-포드 알고리즘(Bellman-Ford Algo)
  - 플로이드 워셜 알고리즘(Floyd-Warshall Algo)

# 자료 구조
  - 펜윅 트리/바이너리 인덱스 트리(Fanwick Tree/Binary Indexed Tree)

# 백준 문제 
  - dfs / bfs : 유기농 배추(1012)
  - 완전 탐색 : 연구소(14502), 영화감독(1436), 컴백홈(1189), 주사위 윷놀이(17825)
  - 백트래킹 : 색종이 붙이기(17136)

# python 유용한 메소드
  - 코딩 인터뷰 유용한 방식 
    https://realpython.com/python-coding-interview-tips/#use-list-comprehensions-instead-of-map-and-filter
    
  - set 교집합, 차집합, 합집합
    교집합 : set1 & set2
    합집합 : set1 | set2
    차집합 : set1 - set2
    대칭 차집합 : set1 ^ set2

  - startswith 접두어 포함 여부
    "abcd".startswith("ab")

  - reduce
    reduce(lambda x, y: x*y, arr) => 리스트에 모든 곱
    
  - sorted 
    set, map에 sorted 사용 시 soted list 반환

  - Dupl Counter 
    collections.Counter(list) => map(key: val, val: count)

  - Sort Dict
    sorted(map) => sorted key

  - Queue
    from collectoins import deque => Queue 자료형

  - Custom Comparator
    from functools import cmp_to_key => sort(key=cmp_to_key(custom_cmp_func))

  - enumerate
    for i, v in enumerate(list) => [5, 2, 3] => 0, 5 - 1, 2 - 2, 3

  - Combination, Permutation 
    from itertools import product, permutation, combination => https://ourcstory.tistory.com/414

  - Factorial 
    math.factorial(5) => 5!

  - Regex 
    import re => https://greeksharifa.github.io/%EC%A0%95%EA%B7%9C%ED%91%9C%ED%98%84%EC%8B%9D(re)/2018/08/04/regex-usage-05-intermediate/
