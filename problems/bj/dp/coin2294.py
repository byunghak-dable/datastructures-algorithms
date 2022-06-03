"""
동전 2 - 최소 개수
"""

import sys


def sol(k, arr):
    dp = [0] + [k + 1] * k
    for i in range(1, k + 1):
        for c in arr:
            if i >= c:
                dp[i] = min(dp[i], 1 + dp[i - c])
    return dp[-1] if dp[-1] != k + 1 else -1


input = sys.stdin.readline
n, k = map(int, input().split())
arr = [int(input()) for _ in range(n)]
print(sol(k, arr))
