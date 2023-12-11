from operator import index
import matplotlib.pyplot as plt
import csvRead
import pandas as pd


xBound = True
xMin = 1
xMax = 100

yBound = True
yMin = 0
yMax = 100

# files = ["primesTest.csv","li(x).csv","reimann(x).csv"] #approximate
# files = ["piFloor(x).csv","primesTest.csv"]
#files = ["primesTestMod.csv","testing(x).csv"]
# files = ["primesTestMod.csv","psi0(x).csv"]
#files = ["partitionsCount.csv"]
files = ["chebyshev(x).csv","psi0(x).csv","psi0Precise(x).csv"] #approximate
# files = ["primesTest.csv","li(x).csv"]
# files = ["primesTestMod.csv"]
#files = ["pi0(x).csv","primesTest.csv"]

fig, ax = plt.subplots()

for file in files:
    df = pd.read_csv(file)
    x = df['x']
    y = df['y']
    if (xBound):
        indexCount = 0
        xBounded = []
        yBounded = []
        for num in x:
            if num >= xMin and num <= xMax:
                xBounded.append(num)
                yBounded.append(y[indexCount])
            indexCount += 1
        x = xBounded
        y = yBounded
    if (yBound):
        indexCount = 0
        xBounded = []
        yBounded = []
        for num in y:
            if num >= yMin and num <= yMax:
                yBounded.append(num)
                xBounded.append(x[indexCount])
            indexCount += 1
        x = xBounded
        y = yBounded
    ax.plot(x,y)

plt.show()