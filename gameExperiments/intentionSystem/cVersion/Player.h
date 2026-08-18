#ifndef PLAYER_H
#define PLAYER_H

struct World; // Forward declaration

typedef struct Player Player;

struct Player{
    char name[50];
    int hitpoint;
    int positionX;
    int positionY;
    char symbol;
    struct World *world; // Pointer connection

    void (*changePlayerName)(Player* self, char newPlayerName[]);
    void (*changePlayerHealth)(Player* self, int newPlayerHealth);
    void (*changePlayerPositionX)(Player* self, int newPlayerPositionX);
    void (*changePlayerPositionY)(Player* self, int newPlayerPositionY);
    void (*controller)(Player* self, char intent[]);
    void (*registerWorld)(Player* self, World* world);
    void (*unregisterWorld)(Player* self);
    void (*printPlayerStats)(Player* self);
};

Player generatePlayer(const char* name, int hp, int positionX, int positionY, char symbol);

#endif