"""
줄 세우기

위상 정렬
"""

import sys
from collections import deque


def sol(n, graph, e_arr):
    q = deque()
    result = []
    for i in range(1, n + 1):
        if e_arr[i] == 0:
            q.append(i)
    for i in range(1, n + 1):
        n = q.popleft()
        result.append(n)
        for to in graph[n]:
            e_arr[to] -= 1
            if e_arr[to] == 0:
                q.append(to)
    return " ".join(map(str, result))


input = sys.stdin.readline
n, m = map(int, input().split())
graph = [[] for _ in range(n + 1)]
e_arr = [0 for _ in range(n + 1)]
for _ in range(m):
    s, t = map(int, input().split())
    e_arr[t] += 1
    graph[s].append(t)

print(sol(n, graph, e_arr))
