def arr_dfs(arr, vis, i, j):
    if vis[i][j]:
        return
    vis[i][j] = True

    for ni, nj in [(i + 1, j), (i - 1, j), (i, j + 1), (i, j - 1)]:
        if 0 <= ni < len(arr) and 0 <= nj < len(arr[i]):
            arr_dfs(arr, vis, i, j)


def permu(curr, left):
    if not left:
        print(curr)
        return
    for i, v in enumerate(left):
        curr.append(v)
        del left[i]
        permu(curr, left)
        curr.pop()
        left.insert(i, v)


def combi(curr, left):
    if not left:
        print(curr)
        return

    n_left = left[:]
    for v in left:
        del n_left[0]
        curr.append(v)
        combi(curr, n_left)
        curr.pop()


def choice(curr, left):
    if not left:
        print(curr)
        return

    for i, v in enumerate(left):
        curr.append(v)
        n_left = [left[j] for j in range(i + 1, len(left))]
        choice(curr, n_left)
        curr.pop()
