fileInput = open("Opdracht1/input.txt", "r")

def slicelines(fileinput):
    linelist = [[], []]
    for line in fileinput.readlines():
        parts = line.strip().split()
        if len(parts) >= 2:
            linelist[0].append(int(parts[0]))
            linelist[1].append(int(parts[1]))
    return linelist

def divergelist(singlelist):
    list1, list2 = singlelist[0], singlelist[1]
    list1.sort()
    list2.sort()
    combined = [[a, b] for a, b in zip(list1, list2)]
    return combined

def difference(num1, num2):
    return abs(int(num1) - int(num2))

print(sum(difference(item[0], item[1]) for item in divergelist(slicelines(fileInput))))
