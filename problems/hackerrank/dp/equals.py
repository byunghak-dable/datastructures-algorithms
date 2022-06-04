"""
한 명을 뺸 나머지에게 1, 2, 5개 중 한 가지 방법으로 초콜릿을 줄 수 있을 떄
모두 같은 초콜릿을 가질 수 있는 최소 가지 수 구하기
"""


def sol(arr):
    m = min(arr)
    t = [0] * 5
    for i in range(len(t)):
        for v in arr:
            v -= m - i
            t[i] += v // 5 + (v % 5) // 2 + (v % 5) % 2
    return min(t)
