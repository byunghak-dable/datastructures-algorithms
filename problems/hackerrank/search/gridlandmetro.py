"""
그리드 랜드 메트로

https://www.hackerrank.com/challenges/gridland-metro/problem?isFullScreen=true
"""

from collections import defaultdict


def gridlandMetro(n, mv, k, track):
    # Write your code here
    m = defaultdict(list)
    for r, c1, c2 in track:
        c1, c2 = min(c1, c2), max(c1, c2)
        if r not in m:
            m[r].append((c1, c2))
            continue
        add(m, r, c1, c2)
    total = 0
    for arr in m.values():
        for c1, c2 in arr:
            total += c2 - c1 + 1
    return n * mv - total


def add(m, r, c1, c2):
    for i, (ec1, ec2) in enumerate(m[r]):
        if ec1 <= c1 <= ec2:
            c1 = c1 if ec1 > c1 else ec1
            c2 = c2 if ec2 < c2 else ec2
            m[r][i] = (c1, c2)
            return
    m[r].append((c1, c2))
