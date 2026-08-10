# /usr/local/bin/python3 HoneyComb.py

firstPattern = "/ \\_"
secondPattern = "\\_/ "

widthSize = 12
heightSize = 17

switch = 0

for height in range (heightSize * 2): 
    print("")
    for width in range (widthSize):
        match (switch): 
            case 0:
                print(f"{firstPattern}" , end = '')
            case 1:
                print(f"{secondPattern}" , end = '')
    switch = 1 - switch