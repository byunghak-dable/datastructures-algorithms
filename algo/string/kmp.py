"""
KMP(Knuth-Morris-Pratt) 알고리즘

문자열 비교는 O(n)의 시간복잡도를 가질 수 있다. 
"""


def kmp(needle, haystack):
    p_arr = kmp_pattern(needle)
    idx = 0
    for v in haystack:
        while idx > 0 and haystack[idx] != v:
            idx = p_arr[idx - 1]

        if haystack[idx] == v:
            if idx == len(haystack) - 1:
                print("matched")
                idx = p_arr[idx]
            else:
                idx += 1


def kmp_pattern(p):
    lps = [0] * len(p)  # longest prefix sufix
    prev_lps = 0
    for i in range(1, len(p)):
        while prev_lps > 0 and p[i] != p[prev_lps]:
            prev_lps = lps[prev_lps - 1]
        if p[i] == p[prev_lps]:
            prev_lps += 1
            lps[i] = prev_lps
    return lps


kmp("sd", "sdfsdf")
