"""
가르침(https://www.acmicpc.net/problem/1062)
"""

import sys, re


def sol(num, arr):
    num -= 5
    if num < 0:
        return 0
    f_arr = list(filter(lambda x: len(x) <= num, arr))
    left = set()
    for s in f_arr:
        left.update(s)
    dfs(num if len(left) > num else len(left), [], list(left), f_arr)
    return ans


def dfs(n, curr, left, arr):
    global ans
    if n == 0:
        st = set(curr)
        cnt = 0
        for s in arr:
            if len(st & s) == len(s):
                cnt += 1
        ans = max(ans, cnt)
        return

    nleft = left[:]
    for v in left:
        curr.append(v)
        del nleft[0]
        dfs(n - 1, curr, nleft, arr)
        curr.pop()


input = sys.stdin.readline
n, k = map(int, input().split())
arr = [set(list(re.sub("[a, n, t, i , c]", "", input().rstrip()))) for _ in range(n)]
ans = 0
print(sol(k, arr))
