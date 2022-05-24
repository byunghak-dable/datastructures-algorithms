"""
연산자 끼워넣기
"""

import sys, math


def sol(n, nums, opts):
    dfs(n - 1, nums, [], opts)


def dfs(n, nums, curr, left):
    global mi, ma
    if n == 0:
        x = nums[0]
        for i, opt in enumerate(curr):
            x = cal(x, nums[i + 1], opt)
        mi = min(mi, x)
        ma = max(ma, x)
    for i in range(4):
        if opts[i] == 0:
            continue
        curr.append(i)
        left[i] -= 1
        dfs(n - 1, nums, curr, left)
        curr.pop()
        left[i] += 1


def cal(x, y, opt):
    if opt == 0:
        return x + y
    elif opt == 1:
        return x - y
    elif opt == 2:
        return x * y
    elif opt == 3:
        return -(abs(x) // y) if x < 0 else x // y


input = sys.stdin.readline
n = int(input())
nums = list(map(int, input().split()))
opts = list(map(int, input().split()))
mi, ma = math.inf, -math.inf
sol(n, nums, opts)
print(ma)
print(mi)
