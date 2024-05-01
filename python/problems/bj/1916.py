"""
최소비용 구하기
"""

import sys, math
from collections import defaultdict
from heapq import heappush, heappop


def sol(n, fr, to, graph):
    vis = [False for _ in range(n + 1)]
    dist = [0 if i == fr else math.inf for i in range(n + 1)]
    pq = []
    heappush(pq, (0, fr))
    while pq:
        cost, idx = heappop(pq)
        if idx == to:
            return dist[to]
        if dist[idx] < cost:
            continue
        vis[idx] = True

        for nb_idx, nb_cost in graph[idx]:
            if vis[nb_idx]:
                continue
            n_cost = cost + nb_cost
            if dist[nb_idx] > n_cost:
                dist[nb_idx] = n_cost
                heappush(pq, (n_cost, nb_idx))

    return dist[to]


input = sys.stdin.readline
n = int(input())
m = int(input())

graph = defaultdict(list)
for _ in range(m):
    fr, to, cost = map(int, input().split())
    graph[fr].append((to, cost))
fr, to = map(int, input().split())
print(sol(n, fr, to, graph))
