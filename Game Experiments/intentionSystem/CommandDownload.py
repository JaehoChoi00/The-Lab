import sys
import time

print("Downloading package...")
for i in range(0, 101, 5):
    print(chr(13) + "Progress: [", end = '')

    for j in range (0, i//5):
        print('#', end = '')
    
    for j in range (i//5, 20):
        print('.', end = '')
    
    print(f"] {i}%", end = '')
    sys.stdout.flush()
    time.sleep(0.15)

print("\nDownloadComplete!")
