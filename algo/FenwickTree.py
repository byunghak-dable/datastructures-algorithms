"""
데이터 업데이트가 가능한 상황에서의 구각 함 구하기
"""


def pre_sum(tree, i):
    result = 0
    while i > 0:
        result += tree[i]
        i -= i & -i
    return result


def update(tree, n, i, mod):
    while i <= n:
        tree[i] += mod
        i += i & -i


def interval_sum(tree, start, end):
    return pre_sum(tree, end) - pre_sum(tree, start - 1)


n = 10
arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
tree = [0] * (n + 1)

for i, v in enumerate(arr):
    update(tree, n, i + 1, v)
