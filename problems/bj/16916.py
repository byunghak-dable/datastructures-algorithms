"""
부분 문자열
"""

import sys


def sol(s, p):
    p_arr = make_pattern(p)
    j = 0
    for i, v in enumerate(s):
        while j > 0 and p[j] != v:
            j = p_arr[j - 1]

        if p[j] == v:
            if j == len(p) - 1:
                return 1
            j += 1
    return 0


def make_pattern(p):
    p_arr = [0 for _ in range(len(p))]
    j = 0
    for i in range(1, len(p)):
        while j > 0 and p[i] != p[j]:
            j = p_arr[j - 1]

        if p[i] == p[j]:
            j += 1
            p_arr[i] = j
    return p_arr


input = sys.stdin.readline
s = input()
p = input()
print(sol(s.strip(), p.strip()))
