fileInput = open("Opdracht1/input.txt", "r")


def slicelines(fileinput):
    linelist = [[], []]
    for line in fileinput.readlines():
        parts = line.strip().split()
        if len(parts) >= 2:
            linelist[0].append(int(parts[0]))
            linelist[1].append(int(parts[1]))
    return linelist


def numandamount(linelist):
    occurencelist = []
    for line in linelist[0]:
        occurencelist.append(linelist[1].count(line))
    combined = [[a, b] for a, b in zip(linelist[0], occurencelist)]
    return combined


finalAnswer = sum((item[0] * item[1]) for item in numandamount(slicelines(fileInput)))

print(finalAnswer)
