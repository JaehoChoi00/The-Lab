#include <string.h>
#include <stdio.h>

#include "Player.h"

static void changePlayerName(Player* self, const char newPlayerName[]) {
    strncpy(self->name, newPlayerName, sizeof(self->name) - 1);
    self->name[sizeof(self->name) - 1] = '\0'; // Ensure null-termination
}

static void changePlayerHealth(Player* self, int newPlayerHealth) {
    self->hitpoint = newPlayerHealth;
}

static void changePlayerPositionX(Player* self, int newPlayerPositionX) {
    self->positionX = newPlayerPositionX;
}

static void changePlayerPositionY(Player* self, int newPlayerPositionY) {
    self->positionY = newPlayerPositionY;
}

static void controller(Player* self, const char intent[]) {
    if (strcmp(intent, "w") == 0)    self->positionY--;
    if (strcmp(intent, "s") == 0)  self->positionY++;
    if (strcmp(intent, "d") == 0)  self->positionX--;
    if (strcmp(intent, "a") == 0) self->positionX++;
}

static void registerWorld(Player* self, struct World* world) {
    self->world = world;
}

static void unregisterWorld(Player* self) {
    self->world = NULL;
}

static void printPlayerStats(Player* self) {
    printf("=== PLAYER STATS ===\n");
    printf("Name:     %s\n", self->name);
    printf("HP:       %d\n", self->hitpoint);
    printf("Position: (%d, %d)\n", self->positionX, self->positionY);
    printf("Symbol:   %c\n", self->symbol);
    printf("World:    %s\n", self->world ? "Connected" : "None");
    printf("====================\n");
}

// --- CONSTRUCTOR ---

Player generatePlayer(const char* name, int hp, int positionX, int positionY, char symbol) {
    Player p;
    
    // Assign basic data fields
    strncpy(p.name, name, sizeof(p.name) - 1);
    p.name[sizeof(p.name) - 1] = '\0';
    p.hitpoint = hp;
    p.positionX = positionX;
    p.positionY = positionY;
    p.symbol = symbol;
    p.world = NULL; // Default to no world map attached

    // Hook up all 8 internal function pointers
    p.changePlayerName      = changePlayerName;
    p.changePlayerHealth    = changePlayerHealth;
    p.changePlayerPositionX = changePlayerPositionX;
    p.changePlayerPositionY = changePlayerPositionY;
    p.controller            = controller;
    p.registerWorld         = registerWorld;
    p.unregisterWorld       = unregisterWorld;
    p.printPlayerStats      = printPlayerStats;

    return p;
}
