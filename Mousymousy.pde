// Grid Allocations 0 = EMPTY, 1 = MOUSE, 2 = WALL, 3 = CHEESE, 4 = KEY
final int len = 640, hei = 640;
Mouse M;
Pickup Cheese;
Pickup Key;
Level level1;
int gridHeight;
int gridWidth;
int[][] grid;
int score;

void setup() {
  gridHeight = height / 20;
  gridWidth = width / 20;
  size(640,640);
  surface.setResizable(true);
  level1 = new Level();
  grid = level1.getGrid();
  //grid = new int[len / gridWidth][hei / gridHeight]; //20x20
  M = new Mouse(3, 3);
  Cheese = new Pickup(5, 5, 1);
  Key = new Pickup(9, 5, 2);
  score = 0;
}

void draw() {
  gridHeight = height / 20;
  gridWidth = width / 20;
  map();
  M.show();
  Cheese.show();
  Key.show();
  fill(255);
  textSize(32);
  text(score, 0, 30);
}

void map() {
  background(0);
  stroke(0);
  for(int i = 0; i < 20; i++) { 
    for(int j = 0; j < 20; j++) {
      if(grid[i][j] == 2) {
        fill(0);
      } else fill(195, 82, 19);
      rect(i * gridWidth, j * gridHeight, gridWidth, gridHeight);
    }
  } 
}

void keyPressed() {
  if (key == CODED) {
    if (keyCode == UP && M.y > 0 && grid[M.x][M.y - 1] != 2) {
      M.dir = 1;
      grid[M.x][M.y] = 0;
      M.y--;
      if(grid[M.x][M.y] == 3) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } else if(grid[M.x][M.y] == 4) Key.exists = false;
      grid[M.x][M.y] = 1;
    } else if (keyCode == DOWN && M.y < 19 && grid[M.x][M.y + 1] != 2) {
      M.dir = 3;
      grid[M.x][M.y] = 0;
      M.y++;
      if(grid[M.x][M.y] == 3) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } else if(grid[M.x][M.y] == 4) Key.exists = false;
      grid[M.x][M.y] = 1;
    } else if (keyCode == LEFT && M.x > 0 && grid[M.x - 1][M.y] != 2) {
      M.dir = 4;
      grid[M.x][M.y] = 0;
      M.x--;
      if(grid[M.x][M.y] == 3) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } else if(grid[M.x][M.y] == 4) Key.exists = false;
      grid[M.x][M.y] = 1;
    } else if (keyCode == RIGHT && M.x < 19 && grid[M.x + 1][M.y] != 2) {
      M.dir = 2;
      grid[M.x][M.y] = 0;
      M.x++;
      if(grid[M.x][M.y] == 3) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } else if(grid[M.x][M.y] == 4) Key.exists = false;
      grid[M.x][M.y] = 1;
    }
  }
}