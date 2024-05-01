import sys


def sol(n):
    cnt = 0
    while n >= 5:
        cnt += n // 5
        n //= 5
    return cnt


input = sys.stdin.readline
n = int(input())
print(sol(n))
