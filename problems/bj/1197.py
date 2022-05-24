"""
최소 스패닝 트리
"""

import sys
from heapq import heappush, heappop


def sol(n, pq):
    arr = [i for i in range(n + 1)]
    ans = 0
    while pq:
        cost, n1, n2 = heappop(pq)
        if union(arr, n1, n2):
            ans += cost
    return ans


def union(arr, n1, n2):
    r1 = find(arr, n1)
    r2 = find(arr, n2)
    arr[r1] = r2
    return r1 != r2


def find(arr, n):
    r = n
    while r != arr[r]:
        r = arr[r]

    while arr[n] != r:
        arr[n] = r
        n = arr[n]
    return r


input = sys.stdin.readline
v, e = map(int, input().split())
pq = []
for _ in range(e):
    n1, n2, cost = map(int, input().split())
    heappush(pq, (cost, n1, n2))
print(sol(v, pq))
