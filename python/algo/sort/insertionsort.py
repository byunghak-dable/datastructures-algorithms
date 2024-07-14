def sort(input_arr: list[int]) -> list[int]:
    arr = input_arr[:]

    for i in range(1, len(arr)):
        j = i

        while j > 0 and arr[j - 1] > arr[j]:
            arr[j - 1], arr[j] = arr[j], arr[j - 1]
            j -= 1

    return arr


arr = [8, 5, 6, 2, 4]
sorted_arr = sort(arr)

print("original", arr)
print("sorted", sorted_arr)
