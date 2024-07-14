def sort(input_arr: list[int]) -> list[int]:
    arr = input_arr[:]

    for i in range(len(arr) - 1):
        j = i

        while j >= 0 and arr[j] > arr[j + 1]:
            arr[j], arr[j + 1] = arr[j + 1], arr[j]
            j -= 1

    return arr


arr = [8, 5, 6, 2, 4]
sorted_arr = sort(arr)

print("original", arr)
print("sorted", sorted_arr)
