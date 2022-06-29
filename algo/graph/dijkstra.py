"""
특정한 노드에서 출발하여 다른 모든 노드로 가는 최단 경로를 계산

- 음의 간선(edge)가 없을 때 정상 작동
- 매 상황에서 가장 비용이 적은 노드를 선택해 과정 반복(그리디)
- 시간 복잡도 O((E+V)log(V))
"""
import math
from collections import defaultdict
from heapq import heappush, heappop


def link(arr: list, doubly: bool = False) -> dict:
    m = defaultdict(list)
    for n1, n2, cost in arr:
        m[n1].append((n2, cost))
        if doubly:
            m[n2].append((n1, cost))
    return m


# Lazy Dijkstra
# @param n : 0~n-1(vertex)
# @param graph : linked info
def dijkstra(n: int, graph: dict, start: int, end: int = -1) -> tuple[list, list]:
    vis = [False for _ in range(n)]
    prev = [None for _ in range(n)]
    dist = [0 if i == start else math.inf for i in range(n)]
    pq = []
    heappush(pq, (0, start))  # (cost, vertex)
    while pq:
        cost, idx = heappop(pq)
        if idx == end:
            return dist, prev
        vis[idx] = True
        if dist[idx] < cost:
            continue

        for nb_idx, nb_cost in graph[idx]:
            if vis[nb_idx]:
                continue
            nb_cost += cost
            if nb_cost < dist[nb_idx]:
                dist[nb_idx] = nb_cost
                prev[nb_idx] = idx
                heappush(pq, (nb_cost, nb_idx))
    return dist, prev


def get_path(dist: list, prev: list, at: int) -> list:
    path = []
    if dist[at] == math.inf:
        return path

    curr = prev[at]
    while curr != None:
        path.append(curr)
        curr = prev[curr]
    return list(reversed(path))


t1 = [(0, 1, 4), (0, 2, 1), (2, 1, 2), (1, 3, 1), (2, 3, 5), (3, 4, 3)]
t2 = [
    [4, 1, 10],
    [3, 5, 24],
    [5, 6, 2],
    [3, 1, 41],
    [5, 1, 24],
    [4, 6, 50],
    [2, 4, 66],
    [2, 3, 22],
    [1, 6, 25],
]
g1 = link(t1)
g2 = link(t2, True)
print(g2)
dist, prev = dijkstra(7, g2, 4)
print(dist, prev)
