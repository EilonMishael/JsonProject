import matplotlib.pyplot as plt

# create plot and bins for the histogram
fig, (ax0, ax1) = plt.subplots(ncols=2, figsize=(8, 4))
bins = [1,2,3,4,5,6,7,8,9,10]

# number of executions
EXEC_NUM = 50

# location of the execution data
pathJava   = "C:\\CodeProjects\\Eclipse\\workspace\\MT_Python\\Ex2\\tempJava.txt"
pathPython = "C:\\CodeProjects\\Eclipse\\workspace\\MT_Python\\Ex2\\tempPython.txt"


# reading the Java execution array from the given path
# and inserting the data inside the histogram
javaRunTimes = []

with open(pathJava, 'r') as file:
    for i in file.read().splitlines():
        javaRunTimes.append(int(i))

ax0.hist(javaRunTimes, bins, histtype='bar', facecolor='b', rwidth=0.9)
ax0.set_title('java')


# reading the Python execution array from the given path
# and inserting the data inside the histogram

pythonRunTimes = []

with open(pathPython, 'r') as file:
    for i in file.read().splitlines():
        pythonRunTimes.append(float(i))

ax1.hist(pythonRunTimes, bins,histtype='bar', facecolor='r', rwidth=0.9)
ax1.set_title('Python')

# showing the histogram
fig.tight_layout()
plt.show()