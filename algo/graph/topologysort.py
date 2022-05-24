"""
위상 정렬
"""


from collections import deque


def tropology_sort(graph, e_arr):
    q = deque()
    r = []
    for i in range(1, len(e_arr)):
        if e_arr[i] == 0:
            q.append(i)
    for _ in range(1, len(e_arr)):
        if not q:
            print("error")
            return
        n = q.popleft()
        r.append(n)
        for to in graph[n]:
            e_arr[to] -= 1
            if e_arr[to] == 0:
                q.append(to)

    return r


n = 7
t1 = [(1, 2), (1, 5), (2, 3), (3, 4), (4, 6), (5, 6), (6, 7)]
graph = [[] for _ in range(n + 1)]
e_arr = [0 for _ in range(n + 1)]
for fr, to in t1:
    e_arr[to] += 1
    graph[fr].append(to)
print(tropology_sort(graph, e_arr))
