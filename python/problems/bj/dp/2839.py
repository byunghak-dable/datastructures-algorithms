import sys


def sol(v):
    n5 = v // 5
    left = v % 5
    while left % 3 != 0 and n5 > 0:
        n5 -= 1
        left = v - 5 * n5

    return n5 + left // 3 if left % 3 == 0 else -1


input = sys.stdin.readline
param = int(input())
print(sol(param))
