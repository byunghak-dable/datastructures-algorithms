"""
사탕 게임
"""

import sys


def check(n, arr, ans):
    for i in range(n):
        column = arr[i]
        row = [arr[j][i] for j in range(n)]
        r_cnt, c_cnt = 1, 1
        for j in range(n - 1):
            if column[j] == column[j + 1]:
                c_cnt += 1
            else:
                ans = max(ans, c_cnt)
                c_cnt = 1
            if row[j] == row[j + 1]:
                r_cnt += 1
            else:
                ans = max(ans, r_cnt)
                r_cnt = 1
        ans = max(ans, r_cnt, c_cnt)
    return ans


def sol(n, arr):
    ans = 0
    for i in range(n):
        for j in range(n):
            for n_i, n_j in [(i, j + 1), (i + 1, j)]:
                if n_i < n and n_j < n and arr[i][j] != arr[n_i][n_j]:
                    arr[i][j], arr[n_i][n_j] = arr[n_i][n_j], arr[i][j]
                    ans = check(n, arr, ans)
                    if ans == n:
                        return ans
                    arr[i][j], arr[n_i][n_j] = arr[n_i][n_j], arr[i][j]
    return ans


input = sys.stdin.readline
n = int(input())
arr = [list(input().rstrip()) for _ in range(n)]
print(sol(n, arr))
