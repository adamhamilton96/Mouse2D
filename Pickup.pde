class Pickup {
    int x, y;
    int type; // 1 = Cheese
    boolean exists;

    Pickup(int x_, int y_) {
        x = x_;
        y = y_;
        exists = true;
        grid[x][y] = 2;
    }

    void show() {
        if(exists == true) {
            stroke(0);
            fill(255, 255, 0);
            ellipse(x * 64 + 32, y * 64 + 32, 32, 32);
        }
    }

    void randomLoc() {
        x = int(random(0, 19));
        y = int(random(0, 14));
        grid[x][y] = 2;
        exists = true;
    }
}