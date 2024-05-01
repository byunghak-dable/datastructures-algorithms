"""
수들의 합
"""

import sys


def sol(s):
    sum = 0
    for i in range(1, s + 1):
        sum += i
        if sum >= s:
            return i - 1 if sum > s else i
    return 0


s = int(sys.stdin.readline())
print(sol(s))
