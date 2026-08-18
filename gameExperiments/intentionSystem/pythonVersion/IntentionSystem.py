import copy
import os
import time
import math

# /usr/local/bin/python3 IntentionSystem.py

def systemPrint(stringToPrint):
    # Log
    print(stringToPrint)

def generatePlayer(name, hitpoint, positionX, positionY, symbol):
    playerStats = {"name": name, "hitpoint": hitpoint, "positionX" : positionX, "positionY" : positionY, "symbol" : symbol}
    newPlayer = Player(playerStats)
    return newPlayer

def generateWorld(name, matrixSizeX, matrixSizeY):
    worldStats = {"name": name, "matrixSizeX": matrixSizeX, "matrixSizeY": matrixSizeY}
    newWorld = World(worldStats)
    return newWorld

class Player:
    def __init__(self, playerStats):
        self.playerStats = playerStats
        self.world = None

    def changePlayerName(self, newPlayerName):
        if self.playerStats["name"] != newPlayerName:
            self.playerStats["name"] = newPlayerName
            return True
        else:
            systemPrint("[System Report]: Same as previous player name. No changes")
            return False

    def changePlayerHealth(self, newPlayerHealth):
        if self.playerStats["hitpoint"] != newPlayerHealth:
            self.playerStats["hitpoint"] = newPlayerHealth
            return True
        else:
            systemPrint("[System Report]: Same as previous player health. No changes")
            return False

    def changePlayerPositionX(self, newPlayerPositionX):
        if 0 <= newPlayerPositionX < self.world.worldStats["matrixSizeX"]:
            self.playerStats["positionX"] = newPlayerPositionX
            return True
        else:
            systemPrint("[System Report]: Boundary Hit on X movement. Cannot move outside map.")
            return False

    def changePlayerPositionY(self, newPlayerPositionY):
        if 0 <= newPlayerPositionY < self.world.worldStats["matrixSizeY"]:
            self.playerStats["positionY"] = newPlayerPositionY
            return True
        else:
            systemPrint("[System Report]: Boundary Hit on Y movement. Cannot move outside map.")
            return False

    def controller(self, intent):
        moved = False
        match (intent):
            case 'd':
                moved = self.changePlayerPositionX(self.playerStats["positionX"] + 1)
            case 'a':
                moved = self.changePlayerPositionX(self.playerStats["positionX"] - 1)
            case 'w':
                moved = self.changePlayerPositionY(self.playerStats["positionY"] - 1)
            case 's':
                moved = self.changePlayerPositionY(self.playerStats["positionY"] + 1)
        return moved

    def registerWorld(self, world):
        self.world = world

    def unregisterWorld(self):
        self.world = None

    def printPlayerStats(self ):
        print(f"Player name: {self.playerStats['name']}")
        print(f"Player hitpoint: {self.playerStats['hitpoint']}")
        print(f"Player positionX: {self.playerStats['positionX']}")
        print(f"Player positionY: {self.playerStats['positionY']}")
        print(f"Player symbol: {self.playerStats['symbol']}")
        print("-------------")

class World:
    def __init__(self, worldStats):
        self.worldStats = worldStats
        self.players = set()
        self.emptyMap = [[0] * self.worldStats["matrixSizeX"] for _ in range(self.worldStats["matrixSizeY"])]
        self.renderedMap = copy.deepcopy(self.emptyMap)
        
    def changeWorldName(self, newWorldName):
        if self.worldStats["name"] != newWorldName:
            self.worldStats["name"] = newWorldName
            return True
        else:
            systemPrint("[System Report]: Same as previous world name. No changes")
            return False

    def swapMap(self, newWorldMatrix):
        self.renderedMap = newWorldMatrix.copy()

    def addPlayer(self, playerToAdd):
        playerToAdd.registerWorld(self)
        self.players.add(playerToAdd)

    def removePlayer(self, playerToRemove):
        playerToRemove.unregisterWorld()
        self.players.remove(playerToRemove)

    def modifyWorld(self, newSizeX, newSizeY):
        if (newSizeX < 1 or newSizeY < 1) :
            return
        oldSizeX = self.worldStats["matrixSizeX"]
        oldSizeY = self.worldStats["matrixSizeY"]
        self.worldStats["matrixSizeX"] = newSizeX
        self.worldStats["matrixSizeY"] = newSizeY
        self.emptyMap = [[0] * self.worldStats["matrixSizeX"] for _ in range(self.worldStats["matrixSizeY"])]
        self.renderedMap = copy.deepcopy(self.emptyMap)

        for player in self.players:
            if (player.playerStats['positionX'] >= newSizeX) :
                player.changePlayerPositionX(player.playerStats['positionX'] - abs(oldSizeX - newSizeX))
            if (player.playerStats['positionY'] >= newSizeY) :
                player.changePlayerPositionY(player.playerStats['positionY'] - abs(oldSizeY - newSizeY))
        

    def refreshWorldRender(self):
        self.renderedMap = copy.deepcopy(self.emptyMap) 

    def renderPlayers(self):
        self.refreshWorldRender()

        for player in self.players:
            self.renderedMap[player.playerStats['positionY']][player.playerStats['positionX']] = player.playerStats['symbol']

    def printPlayers(self):
        for player in self.players:
            player.printPlayerStats()

    def printWorldStats(self):
        os.system('clear' if os.name == 'posix' else 'cls') 
        print(f"--- WORLD STATE: {self.worldStats['name'].upper()} ---")
        print("-------------")
        for row in self.renderedMap:
            print(" ".join(map(str, row)))
        print("-------------")

    def printGameStats(self):
        os.system('clear' if os.name == 'posix' else 'cls') 
        print(f"\n--- PLAYER STATS: Count [{len(self.players)}] ---")
        self.printPlayers()
        print(f"--- WORLD STATE: {self.worldStats['name'].upper()} ---")
        print("-------------")
        for row in self.renderedMap:
            print(" ".join(map(str, row)))
        print("-------------")

Bob = generatePlayer("Bob", 100, 0, 0, 'B')

world = generateWorld("world", 10, 10)
world.addPlayer(Bob)

world.renderPlayers()
world.printGameStats()

intent = input()
while (intent != "end"):
    
    if len(intent) < 2 and Bob.controller(intent):
        world.renderPlayers()
        world.printGameStats()
        systemPrint(f"[Executing]: {intent}")

    elif intent == "scaleUp":
        newSizeX = world.worldStats['matrixSizeX'] + 1
        newSizeY = world.worldStats['matrixSizeY'] + 1
        world.modifyWorld(newSizeX, newSizeY)
        world.renderPlayers()
        world.printGameStats()
        
    elif intent == "scaleDown":
        newSizeX = world.worldStats['matrixSizeX'] - 1
        newSizeY = world.worldStats['matrixSizeY'] - 1
        world.modifyWorld(newSizeX, newSizeY)
        world.renderPlayers()
        world.printGameStats()

    elif len(intent) > 1:
        for instruction in intent:
            if (Bob.controller(instruction)):
                world.renderPlayers()
                world.printGameStats()
                systemPrint(f"[Executing]: {instruction}")
                
            time.sleep(0.3)
        
    intent = input()

print("")