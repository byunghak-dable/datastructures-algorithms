from collections import deque, defaultdict


def bfs(n: int, graph: dict, start: int):
    q = deque([start])
    vis = [False for _ in range(n)]
    path = []
    while q:
        n = q.popleft()
        if vis[n]:
            continue
        path.append(n)
        vis[n] = True
        for nb in graph[n]:
            q.append(nb)
    return path


t1 = [(0, 1), (0, 2), (2, 1), (1, 3), (2, 3), (3, 4)]
m = defaultdict(list)

for fr, to in t1:
    m[fr].append(to)

print(bfs(5, m, 0))
