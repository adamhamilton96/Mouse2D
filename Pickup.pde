class Pickup {
    int x, y;
    int type; // 1 = Cheese, 2 = Key
    int img;
    boolean exists;

    PImage cheeseUp;
    PImage cheeseLeft;
    PImage cheeseRight;
    PImage cheeseDown;
    PImage keyUp;

    Pickup(int x_, int y_, int type_) {
        loadImages();
        x = x_;
        y = y_;
        type = type_;
        exists = true;
        if(type == 1) {
            grid[x][y] = 3;
            img = 1;
        } else if(type == 2) {
            grid[x][y] = 4;
        }

    }

    void show() {
        if(exists == true) {
            stroke(0);
            fill(255, 255, 0);
            if(type == 1) {
                switch(img) {
                    case 1: image(cheeseUp, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                            break;
                    case 2: image(cheeseLeft, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                            break;
                    case 3: image(cheeseRight, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                            break;
                    case 4: image(cheeseDown, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                            break;
                }
            } else if(type == 2) {
                image(keyUp, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
            }
        }
    }

    void randomLoc() {
        x = int(random(0, 19));
        y = int(random(0, 14));
        if(grid[x][y] == 0) grid[x][y] = 3;
        else randomLoc();
        exists = true;
        img = int(random(1, 5));
        score++;
    }

    void loadImages() {
        cheeseUp = loadImage("/Images/Cheese/cheeseUp.png");
        cheeseLeft = loadImage("/Images/Cheese/cheeseLeft.png");
        cheeseRight = loadImage("/Images/Cheese/cheeseRight.png");
        cheeseDown = loadImage("/Images/Cheese/cheeseDown.png");
        keyUp = loadImage("/Images/Keys/keyUp.png");
    }
}