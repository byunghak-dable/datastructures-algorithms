def sort(arr: list[int]) -> list[int]:
    if len(arr) <= 1:
        return arr[:]

    mid = len(arr) // 2
    left, right = sort(arr[:mid]), sort(arr[mid:])

    return merge(left, right)


def merge(left: list[int], right: list[int]) -> list[int]:
    sorted: list[int] = []
    left_idx = right_idx = 0

    while left_idx < len(left) and right_idx < len(right):
        if left[left_idx] < right[right_idx]:
            sorted.append(left[left_idx])
            left_idx += 1
        else:
            sorted.append(right[right_idx])
            right_idx += 1

    return sorted + left[left_idx:] + right[right_idx:]


arr = [8, 5, 6, 2, 4]
sorted_arr = sort(arr)

print("original", arr)
print("soted", sorted_arr)
