n = int(input())
people = list(map(int, input().split()))

result = 0
people.sort()

for i in range(n):
    result += sum(people[0:i+1])

print(result)
