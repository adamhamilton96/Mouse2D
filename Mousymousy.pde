final int len = 640, hei = 640;
Mouse M;
Pickup Cheese;
int gridHeight;
int gridWidth;
int[][] grid;

void setup() {
  gridHeight = height / 20;
  gridWidth = width / 20;
  grid = new int[len / gridWidth][hei / gridHeight]; //20
  size(640,640);
  surface.setResizable(true);
  M = new Mouse(3, 3);
  Cheese = new Pickup(5, 5);
}

void draw() {
  gridHeight = height / 20;
  gridWidth = width / 20;
  map();
  M.show();
  Cheese.show();
}

void map() {
  background(0);
  stroke(0);
  fill(195, 82, 19);
  for(int i = 0; i < 20; i++) { 
    for(int j = 0; j < 20; j++) {
      rect(i * gridWidth, j * gridHeight, gridWidth, gridHeight);
    }
  } 
}

void keyPressed() {
  if (key == CODED) {
    if (keyCode == UP && M.y > 0) {
      M.dir = 1;
      grid[M.x][M.y] = 0;
      M.y--;
      if(grid[M.x][M.y] == 2) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } 
      grid[M.x][M.y] = 1;
    } else if (keyCode == DOWN && M.y < 19) {
      M.dir = 3;
      grid[M.x][M.y] = 0;
      M.y++;
      if(grid[M.x][M.y] == 2) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } 
      grid[M.x][M.y] = 1;
    } else if (keyCode == LEFT && M.x > 0) {
      M.dir = 4;
      grid[M.x][M.y] = 0;
      M.x--;
      if(grid[M.x][M.y] == 2) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } 
      grid[M.x][M.y] = 1;
    } else if (keyCode == RIGHT && M.x < 19) {
      M.dir = 2;
      grid[M.x][M.y] = 0;
      M.x++;
      if(grid[M.x][M.y] == 2) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } 
      grid[M.x][M.y] = 1;
    }
  }
}