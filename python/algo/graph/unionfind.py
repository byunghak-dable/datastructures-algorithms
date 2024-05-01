def union(arr, n1, n2):
    r1 = find(arr, n1)
    r2 = find(arr, n2)
    arr[r1] = r2
    return r1 != r2


def find(arr, n):
    r = n
    while r != arr[r]:
        r = arr[r]

    while n != r:
        n = arr[n]
        arr[n] = r

    return r


n = 5
t1 = [(0, 1, 4), (0, 2, 1), (2, 1, 2), (1, 3, 1), (2, 3, 5), (3, 4, 3)]
arr = [i for i in range(5)]
for n1, n2, cost in sorted(t1, key=lambda x: x[2]):
    print(union(arr, n1, n2), n1, n2)
print(arr)
