"""
빗물(https://www.acmicpc.net/problem/14719)

* 알고리즘 분류
구현, 시뮬레이션
"""

import sys


def sol(h, w, blocks):
    arr = [[1 if h - i <= blocks[j] else 0 for j in range(w)] for i in range(h)]
    ans = 0
    for vi in arr:
        tmp = 0
        isValid = False
        for vj in vi:
            if vj == 1:
                ans += tmp
                tmp = 0
                isValid = True
            elif vj == 0:
                tmp += 1 if isValid else 0
    return ans


input = sys.stdin.readline
h, w = map(int, input().split())
blocks = list(map(int, input().split()))
print(sol(h, w, blocks))
