# Intention

[Return to Hub](/gameExperiments/GameExperiments.md)

## Section
> * [State](#state)
> * [Control](#control)
> * [Intent](#intent)

--- 

### [State](#section)

``` py
playerStats = {
    "name": "Bob",
    "hitpoint": 100,
    "positionX": 0,
    "positionY": 0
}

worldStats = {
    "name": name,
    "matrixSizeX": matrixSizeX,
    "matrixSizeY": matrixSizeY
}
```

### [Control](#section)

```
[Control]

currentPosition
        │
        ▼
newPosition
```

### [Intent](#section)

```
MovePlayerPosition
     (Intent)
        │
        ▼
Simulation
        │
        ├── Collision
        ├── Physics
        ├── Game Rules
        ├── AI
        ├── Events
        └── Permissions
        │
        ▼
currentPosition
        │
        ▼
newPosition
```
