def sort(original_arr: list[int]) -> list[int]:
    arr = original_arr[:]
    for i in range(len(arr)):
        minIdx = i
        for j in range(i + 1, len(arr)):
            if arr[minIdx] > arr[j]:
                minIdx = j
        arr[i], arr[minIdx] = arr[minIdx], arr[i]

    return arr


arr = [8, 5, 6, 2, 4]
sorted_arr = sort(arr)

print("original", arr)
print("sorted", sorted_arr)
