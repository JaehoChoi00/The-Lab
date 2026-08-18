#ifndef WORLD_H
#define WORLD_H

struct People; // Forward declaration

typedef struct World world;

struct World{
    char name[50];
    int matrixSizeX;
    int matrixSizeY;
    struct World *world; // Pointer connection

    void (*changeWorldName)(World* self, char neweWorldName[]);
    void (*modifyWorld)(World* self, int newSizeX, int newSizeY);
    void (*controller)(World* self, char intent[]);
    void (*registerWorld)(World* self, World* world);
    void (*unregisterWorld)(World* self);
    void (*printPlayerStats)(World* self);
};

World generateWorld(const char* name, int matrixSizeX, int matrixSizeY);

#endif