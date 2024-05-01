"""
두개의 요소의 합이 k가 아닌 부분집합 구하기

1. left == 0 => 1개 추가 가능
2. left == k / 2 => 1개 추가 가능
3. 위 조건 x and left < k => max(left의 개수, k - left의 개수)
"""

from collections import Counter


def sol(k: int, s: list[int]) -> int:
    counter = Counter([v % k for v in s])
    ans = 0
    if counter[0] > 0:
        ans += 1
    for i in range(1, k):
        if counter[i] == 0:
            continue
        if 2 * i == k:
            ans += 1
        else:
            ans += max(counter[i], counter[k - i])
            counter[k - i] = 0

    return ans
