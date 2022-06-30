import sys


def sol(n, m):
    return min(
        cal(n, 5) - cal(m, 5) - cal(n - m, 5),
        cal(n, 2) - cal(m, 2) - cal(n - m, 2),
    )


def cal(n, k):
    cnt = 0
    while n >= k:
        n //= k
        cnt += n
    return cnt


input = sys.stdin.readline
n, m = map(int, input().split())  # nCm
print(sol(n, m))
