"""
KMP(Knuth-Morris-Pratt) 알고리즘

문자열 비교는 O(n)의 시간복잡도를 가질 수 있다. 
"""


def kmp(s, p):
    p_arr = make_pattern(p)
    j = 0
    for i, v in enumerate(p):
        while j > 0 and p[j] != v:
            j = p_arr[j - 1]

        if p[j] == v:
            if j == len(p) - 1:
                print("matched")
                j = p_arr[j]
            else:
                j += 1


def make_pattern(p):
    p_arr = [0 for _ in range(len(p))]
    j = 0
    for i in range(len(p)):
        while j > 0 and p[i] != p[j]:
            j = p_arr[j - 1]

        if p[i] == p[j]:
            j += 1
            p_arr[i] = j
    return p_arr
