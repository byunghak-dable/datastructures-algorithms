"""
동전 1
"""

import sys


def sol(k, arr):
    dp = [1] + [0] * k
    nDp = dp[:]
    for c in reversed(arr):
        for j in range(k + 1):
            nDp[j] = dp[j]
            if j >= c:
                nDp[j] += nDp[j - c]
        dp, nDp = nDp, dp
    return dp[-1]


input = sys.stdin.readline
n, k = map(int, input().split())
arr = [int(input()) for _ in range(n)]
print(sol(k, arr))
