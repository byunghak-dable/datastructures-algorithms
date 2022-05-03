"""
음수 간선이 포함된 상황에서의 최단 거리 문제

- 음수 간선의 경우
    => 음수 간선 순환이 없는 경우
    => 음수 간선 순환이 있는 경우

- 시간 복잡도 O(VE)
- 음수 순환 감지 가능
"""


import math


def bf(n: int, edges: int, graph: list, start: int) -> tuple[list, bool]:
    dist = [0 if i == start else math.inf for i in range(n)]
    for i in range(n):
        for j in range(edges):
            curr, next, cost = graph[j]
            n_cost = dist[curr] + cost
            if dist[curr] != math.inf and dist[next] > n_cost:
                dist[next] = n_cost
                if i == n - 1:
                    return dist, True
    return dist, False


g1 = [
    (1, 2, 6),
    (1, 3, 2),
    (2, 3, 2),
    (2, 4, 2),
    (3, 5, 1),
    (5, 2, -4),
    (5, 4, 3),
    (4, 6, 2),
    (5, 6, 4),
]

t1 = [(0, 1, 4), (0, 2, 1), (2, 1, 2), (1, 3, 1), (2, 3, 5), (3, 4, 3)]
print(bf(7, 9, g1, 1))
