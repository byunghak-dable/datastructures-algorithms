"""
부분합

"""

import sys, math


def sol(n, s, arr):
    b, e = 0, 0
    sum = 0
    ans = math.inf
    while e >= b and e < n:
        sum += arr[e] if sum < s else -arr[b - 1]
        if sum >= s:
            ans = min(e - b + 1, ans)
            b += 1
        else:
            e += 1
    return 0 if ans >= math.inf else ans


input = sys.stdin.readline
n, s = map(int, input().split())
arr = list(map(int, input().split()))
print(sol(n, s, arr))
