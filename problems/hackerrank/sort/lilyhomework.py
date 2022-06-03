"""
정렬 문제

https://www.hackerrank.com/challenges/lilys-homework/problem?isFullScreen=true
"""


def sol(arr):
    sArr = sorted(arr)
    return min(mod(arr[:], sArr), mod(arr[:], sArr[::-1]))


def mod(arr, tArr):
    m = {v: i for i, v in enumerate(arr)}
    cnt = 0
    for i in range(len(arr)):
        v = arr[i]
        if arr[i] != tArr[i]:
            swapIdx = m[tArr[i]]
            m[arr[i]] = swapIdx
            m[arr[swapIdx]] = i
            arr[i], arr[swapIdx] = arr[swapIdx], arr[i]
            cnt += 1
    return cnt
