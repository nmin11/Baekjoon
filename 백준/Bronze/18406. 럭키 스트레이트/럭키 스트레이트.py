n = input()

mid = len(n) // 2
a = [int(char) for char in n[mid:]]
b = [int(char) for char in n[:mid]]

result = 'READY'
if sum(a) == sum(b):
    result = 'LUCKY'

print(result)
