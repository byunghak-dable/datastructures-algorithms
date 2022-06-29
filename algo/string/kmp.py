"""
KMP(Knuth-Morris-Pratt) 알고리즘

문자열 비교는 O(n)의 시간복잡도를 가질 수 있다. 
"""


def kmp(haystack, needle):
    lps = make_lps(needle)
    i, j = 0, 0  # i : haystack, j : needle
    while i < len(haystack):
        if haystack[i] == needle[j]:
            i, j = i + 1, j + 1
        else:
            if j == 0:
                i += 1
            else:
                j = lps[j - 1]

        # found a match do something
        if j == len(needle):
            print("match")
            j = 0


# finding matching prefix, suffix
# @caution prefix cannot be entire string
def make_lps(needle):
    lps = [0] * len(needle)
    prev, curr = 0, 1
    while curr < len(needle):
        if needle[curr] == needle[prev]:
            prev += 1
            lps[curr] = prev
            curr += 1
            continue
        if prev == 0:
            curr += 1
            continue
        prev = lps[prev - 1]
    return lps


# kmp("sd", "sdfsdf")
print(kmp("sdfsdf", "sd"))
