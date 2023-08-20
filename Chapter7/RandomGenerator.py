
import sys, random

def rng04():
    return random.randint(0,4);

def rng03():
    while True:
        i = rng04()
        if i<4:
            return i

def rng01():
    while True:
        i = rng04()
        if i<4:
            return i%2

def rng07():
    i = rng03()
    j = rng01()
    return i*2+j

if __name__ == "__main__":
    for i in range(10000):
        print( rng07() )
    sys.exit()