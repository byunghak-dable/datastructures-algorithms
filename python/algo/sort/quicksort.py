def sort(arr: list[int]) -> list[int]:
    if len(arr) <= 1:
        return arr[:]

    pivot = arr[len(arr) // 2]
    left = [v for v in arr if v < pivot]
    middle = [v for v in arr if v == pivot]
    right = [v for v in arr if v > pivot]

    return sort(left) + middle + sort(right)


arr = [3, 5, 9, 7, 1]
sorted_arr = sort(arr)

print("original", arr)
print("sorted", sorted_arr)
