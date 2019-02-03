final int len = 1280, hei = 960;
Mouse M;
Pickup Cheese;
int gridSize;
int[][] grid;

void setup() {
  gridSize = 64;
  grid = new int[len / gridSize][hei / gridSize]; // 20W / 15T 
  size(1280,960);
  surface.setResizable(true);
  M = new Mouse(3, 3);
  Cheese = new Pickup(5, 5);
}

void draw() {
  map();
  M.show();
  Cheese.show();
}

void map() {
  background(0);
  stroke(0);
  fill(195, 82, 19);
  for(int i = 0; i < len/gridSize; i++) { 
    for(int j = 0; j < hei/gridSize; j++) {
      rect(i * gridSize, j * gridSize, gridSize, gridSize);
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
    } else if (keyCode == DOWN && M.y < 14) {
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