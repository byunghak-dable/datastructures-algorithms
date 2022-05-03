import sys
import copy

input = sys.stdin.readline


answer = 0
virus = set()


def sol(arr):
    global survival
    empty = []
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            if arr[i][j] == 0:
                empty.append((i, j))
            elif arr[i][j] == 2:
                virus.add((i, j))
    survival = len(empty) - 3
    solve(arr, empty, 0)
    return answer


def solve(arr, empty, cnt):
    global answer, survival
    if cnt == 3:
        t_virus = 0
        for i, j in virus:
            t_virus += dfs(copy.deepcopy(arr), i, j)
        answer = max(answer, survival + len(virus) - t_virus)
        return

    n_empty = empty[:]
    for n_i, n_j in empty:
        del n_empty[0]
        arr[n_i][n_j] = 1
        solve(arr, n_empty, cnt + 1)
        arr[n_i][n_j] = 0


def dfs(arr, i, j):
    if arr[i][j] == 1 or arr[i][j] == -1:
        return 0
    cnt = 1
    arr[i][j] = -1

    for ni, nj in [(i + 1, j), (i - 1, j), (i, j + 1), (i, j - 1)]:
        if 0 <= ni < len(arr) and 0 <= nj < len(arr[i]):
            cnt += dfs(arr, ni, nj)
    return cnt


m, n = map(int, input().split())
arr = []
for _ in range(m):
    arr.append(list(map(int, input().split())))
print(sol(arr))
