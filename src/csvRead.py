import csv

def readCSV(filename):
    file = open(filename)
    csvreader = csv.reader(file)
    header = next(csvreader)
    print(header)
    data = [[],[]]
    for row in csvreader:
        data.append(row)
    file.close()
    return data