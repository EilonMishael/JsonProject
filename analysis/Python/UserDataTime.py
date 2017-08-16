import json
import time


current_milli_time = lambda: (time.time() * 1000)

def GetData():
    start_time = current_milli_time()
    # get input from the user
    #path = input("Enter the full path of the .json file: ")
    path = "C:\\CodeProjects\\Eclipse\\workspace\\MT_Python\\Ex2\\TestFile.json"
    
    # create a list that will contain the requested attributes
    list = []
    
    # open the given file and read from the file and load the file content into a list
    jsonFile = open(path, 'r')
    jsonFileString = jsonFile.read()
    jsonArray = json.loads(jsonFileString)
    
    # passing on the list content and choosing the required attributes 'username' and 'password'
    # and append it into the new list (note we will append the hashcode of the 'password')
    for attributes in jsonArray:        
        list.append(attributes['username'] + ':' + str(hash(attributes['password'])))
        
    # created a dictionary with key word as 'users' and as a value the composed list
    users = {'users' : list}
    
    # opened a new file and wrote the dictionary into it
    with open("users.txt", 'w') as outPutFile:
        outPutFile.write(json.dumps(users))
        
    #print("JSON Object: " + str(users))    
    #print("\nthe file as been saved in the project directory under the name users.txt")
    
    return float(current_milli_time() - start_time)
    


if __name__ == '__main__':
    print(GetData())





