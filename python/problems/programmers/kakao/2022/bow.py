answer = (0, [])


def solution(n, info):
    search(info, set(), [i for i in range(11)], n)
    if not answer[1]:
        return [-1]
    result = [0 for i in range(11)]
    for v in answer[1]:
        target = info[v] + 1
        n -= target
        result[v] = info[v] + 1 if n >= 0 else target + n
    return result


def search(info, curr, left, cnt):
    global answer
    if cnt == 0:
        score = check(curr, info)
        if score > answer[0] or (
            answer[1] and score == answer[0] and len(curr) > len(answer[1])
        ):
            answer = (score, curr.copy())
        return

    n_left = left.copy()
    for i, v in enumerate(left):
        l_cnt = cnt - info[v] - 1
        del n_left[0]
        curr.add(v)
        if l_cnt >= 0 or i == len(left) - 1:
            search(info, curr, n_left, l_cnt if l_cnt >= 0 else 0)
        curr.remove(v)


def check(curr, info):
    score = 0
    for i, v in enumerate(info):
        score += 10 - i if i in curr else i - 10 if v > 0 else 0
    return score
