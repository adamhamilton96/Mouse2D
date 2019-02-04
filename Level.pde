class Level {
    int[][] levelGrid;
    Table table;
    
    Level() {
        levelGrid = new int[len / gridWidth][hei / gridHeight];
        table = loadTable("E:/Programming/Processing/Mouse/LevelEditor/level/level1.csv", "header");
        for (int i = 0; i < gridSize; i++) {
            for(int j = 0; j < gridSize; j++) {
                levelGrid[i][j] = table.getRow(i).getInt(j);
            }
        }
    }

    public int[][] getGrid() {
        return levelGrid;
    }

}