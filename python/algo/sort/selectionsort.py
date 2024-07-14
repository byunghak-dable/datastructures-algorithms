def sort(input_arr: list[int]) -> list[int]:
    arr = input_arr[:]

    for i in range(len(arr)):
        min_idx = i

        for j in range(i + 1, len(arr)):
            if arr[min_idx] > arr[j]:
                min_idx = j

        arr[i], arr[min_idx] = arr[min_idx], arr[i]

    return arr


arr = [8, 5, 6, 2, 4]
sorted_arr = sort(arr)

print("original", arr)
print("sorted", sorted_arr)
