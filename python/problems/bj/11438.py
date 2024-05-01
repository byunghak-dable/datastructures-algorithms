import sys
import math


def dfs(node, height):
    vis[node] = True
    depth[node] = height
    for c in graph[node]:
        if not vis[c]:
            parent[c][0] = node
            dfs(c, height + 1)


def set_parent():
    for i in range(1, LOG):
        for j in range(1, n + 1):
            parent[j][i] = parent[parent[j][i - 1]][i - 1]


def lca(a, b):
    if depth[a] > depth[b]:
        a, b = b, a
    for i in range(LOG - 1, -1, -1):
        if depth[b] - depth[a] >= 1 << i:
            b = parent[b][i]
    if a == b:
        return a
    for i in range(LOG - 1, -1, -1):
        if parent[a][i] != parent[b][i]:
            a = parent[a][i]
            b = parent[b][i]
    return parent[a][0]


sys.setrecursionlimit(int(1e5))
input = sys.stdin.readline

n = int(input())
LOG = math.ceil(math.log2(n))
graph = [[] for _ in range(n + 1)]
vis = [False] * (n + 1)
depth = [0] * (n + 1)
parent = [[0] * LOG for _ in range(n + 1)]

for _ in range(n - 1):
    p, c = map(int, input().split())
    graph[p].append(c)
    graph[c].append(p)

dfs(1, 0)
set_parent()
for _ in range(int(input())):
    n1, n2 = map(int, input().split())
    print(lca(n1, n2))
