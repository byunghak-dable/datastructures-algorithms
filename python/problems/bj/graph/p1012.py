import sys

sys.setrecursionlimit(10000)
input = sys.stdin.readline


def sol(arr):
    answer = 0
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            if dfs(arr, i, j) > 0:
                answer += 1
    return answer


def dfs(arr, i, j):
    if arr[i][j] == 0 or arr[i][j] == -1:
        return 0
    cnt = 1
    arr[i][j] = -1

    for (n_i, n_j) in [(i + 1, j), (i - 1, j), (i, j + 1), (i, j - 1)]:
        if 0 <= n_i < len(arr) and 0 <= n_j < len(arr[0]):
            cnt += dfs(arr, n_i, n_j)
    return cnt


def dfs_stack(arr, i, j):
    stack = [(i, j)]
    cnt = 0
    while stack:
        i, j = stack.pop()
        if arr[i][j] < 1:
            continue
        cnt += 1
        arr[i][j] = 0

        for ni, nj in [(i + 1, j), (i - 1, j), (i, j + 1), (i, j - 1)]:
            if 0 <= ni < len(arr) and 0 <= nj < len(arr[0]):
                stack.append((ni, nj))
    return cnt


def case():
    m, n, k = map(int, input().split())
    arr = [[0] * n for _ in range(m)]

    for _ in range(k):
        i, j = map(int, input().split())
        arr[i][j] = 1
    return sol(arr)


cnt = int(input())

for _ in range(cnt):
    print(case())
