"""
모든 노드에서 다른 모든 노드까지의 최단 경로를 모두 계산

- 단계별로 거쳐 가는 노드를 기준으로 알고리즘을 수행
- 2차원 테이블에 최단 거리 정보를 저장
- 다이너믹 프로그래밍
- D(ab) = min(D(ab), D(ak) + D(kb))
"""

import math


def floyd_warchall(n: int, link: list):
    # 1번 부터 출발 시 n + 1
    graph = [[0 if i == j else math.inf for j in range(n)] for i in range(n)]

    for n1, n2, cost in link:
        graph[n1][n2] = cost
        graph[n2][n1] = cost

    for m in range(n):
        for i in range(n):
            for j in range(n):
                graph[i][j] = min(graph[i][j], graph[i][m] + graph[m][j])

    return graph


def search(n, edges):
    graph = [[0 if i == j else math.inf for j in range(n)] for i in range(n)]
    for fr, to, cost in edges:
        graph[fr][to] = cost

    for m in range(n):
        for i in range(n):
            for j in range(n):
                graph[i][j] = min(graph[i][j], graph[i][m] + graph[m][j])

    return graph


t1 = [[0, 1, 4], [0, 2, 1], [2, 1, 2], [1, 3, 1], [2, 3, 5], [3, 4, 3]]

print(floyd_warchall(5, t1))
