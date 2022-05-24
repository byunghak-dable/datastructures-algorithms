"""
멀티탭 스케줄링

* 알고리즘 종류
Belady, Greedy
"""
import sys


def sol(n, k, left):
    curr_s = set()
    ans = 0
    for i in range(k):
        l_v = left[i]
        # 이미 꼽혀있으면 넘기기
        if l_v in curr_s:
            continue
        # 비어 있다면 넣기
        if len(curr_s) < n:
            curr_s.add(l_v)
            continue

        # 대기 중에 겹치는 게 없거나 가장 나중에 등장 삭제
        l_slice = left[i + 1 :]
        l_s = set(l_slice)
        rm_idx, rm_v = 0, None
        for v in curr_s:
            if v not in l_s:
                rm_v = v
                break
            rm_idx = max(l_slice.index(v), rm_idx)
            rm_v = l_slice[rm_idx]
        curr_s.remove(rm_v)
        curr_s.add(l_v)
        ans += 1
    return ans


input = sys.stdin.readline
n, k = map(int, input().split())
arr = list(map(int, input().split()))
print(sol(n, k, arr))
