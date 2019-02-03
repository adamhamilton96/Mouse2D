class Pickup {
    int x, y;
    int type; // 1 = Cheese
    int img;
    boolean exists;

    PImage cheeseUp;
    PImage cheeseLeft;
    PImage cheeseRight;
    PImage cheeseDown;

    Pickup(int x_, int y_) {
        x = x_;
        y = y_;
        exists = true;
        grid[x][y] = 2;
        img = 1;
        cheeseUp = loadImage("/Images/Cheese/cheeseUp.png");
        cheeseLeft = loadImage("/Images/Cheese/cheeseLeft.png");
        cheeseRight = loadImage("/Images/Cheese/cheeseRight.png");
        cheeseDown = loadImage("/Images/Cheese/cheeseDown.png");
    }

    void show() {
        if(exists == true) {
            stroke(0);
            fill(255, 255, 0);
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
        }
    }

    void randomLoc() {
        x = int(random(0, 19));
        y = int(random(0, 14));
        if(grid[x][y] == 0) grid[x][y] = 2;
        else randomLoc();
        exists = true;
        img = int(random(1, 5));
    }
}