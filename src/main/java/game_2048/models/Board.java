package game_2048.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private final Tile[][] grid;
    private final int size;
    private final int base;
    private final Random random;

    public Board(int size, int base) {
        this.size = size;
        this.base = base;
        this.grid = new Tile[size][size];
        this.random = new Random();
    }

    public void initialize() {
        addRandomTile();
        addRandomTile();
    }

    public void addRandomTile() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col] == null) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }

        if (!emptyCells.isEmpty()) {
            int[] cell = emptyCells.get(random.nextInt(emptyCells.size()));
            grid[cell[0]][cell[1]] = new Tile(base * (random.nextInt(2) + 1));
        }
    }

    public boolean move(Direction direction) {
        boolean moved = false;
        switch (direction) {
            case UP -> moved = moveVertical(Direction.UP);
            case DOWN -> moved = moveVertical(Direction.DOWN);
            case LEFT -> moved = moveHorizontal(Direction.LEFT);
            case RIGHT -> moved = moveHorizontal(Direction.RIGHT);
        }
        return moved;
    }

    private boolean moveVertical(Direction verticalDirection) {
        boolean moved = false;
        for (int col = 0; col < size; col++) {
            List<Tile> originalColumnList = new ArrayList<>();
            List<Tile> columnListToMerge = new ArrayList<>();

            for (int row = 0; row < size; row++) {
                int index = verticalDirection == Direction.UP ? row : size - 1 - row;
                originalColumnList.add(grid[index][col]);
                if (grid[index][col] != null) {
                    columnListToMerge.add(grid[index][col]);
                }
            }
            List<Tile> mergedColumn = mergeTiles(columnListToMerge);
            if (!originalColumnList.equals(mergedColumn)) {
                moved = true;
            }
            for (int row = 0; row < size; row++) {
                int index = verticalDirection == Direction.UP ? row : size - 1 - row;
                grid[index][col] = row < mergedColumn.size() ? mergedColumn.get(row) : null;
            }
        }
        return moved;
    }

    private boolean moveHorizontal(Direction horizontalDirection) {
        boolean moved = false;
        for (int row = 0; row < size; row++) {
            List<Tile> rowListToMerge = new ArrayList<>();
            List<Tile> originalRowList = new ArrayList<>();

            for (int col = 0; col < size; col++) {
                int index = horizontalDirection == Direction.LEFT ? col : size - 1 - col;
                originalRowList.add(grid[row][index]);
                if (grid[row][index] != null) {
                    rowListToMerge.add(grid[row][index]);
                }
            }
            List<Tile> mergedRow = mergeTiles(rowListToMerge);
            if (!originalRowList.equals(mergedRow)) {
                moved = true;
            }
            for (int col = 0; col < size; col++) {
                int index = horizontalDirection == Direction.LEFT ? col : size - 1 - col;
                grid[row][index] = col < mergedRow.size() ? mergedRow.get(col) : null;
            }
        }
        return moved;
    }

    private List<Tile> mergeTiles(List<Tile> tiles) {
        List<Tile> mergedTiles = new ArrayList<>();
        for (int i = 0; i < tiles.size(); i++) {
            if (i < tiles.size() - 1 && tiles.get(i).getValue() == tiles.get(i + 1).getValue()) {
                mergedTiles.add(new Tile(tiles.get(i).getValue() * 2));
                i++;
            } else {
                mergedTiles.add(tiles.get(i));
            }
        }
        return mergedTiles;
    }

    public GameState getGameState(int winningValue) {
        boolean hasEmptyCell = false;
        boolean canMerge = false;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col] == null) {
                    hasEmptyCell = true;
                } else if (grid[row][col].getValue() == winningValue) {
                    return GameState.WON;
                }

                if (row < size - 1 && grid[row][col] != null && grid[row + 1][col] != null && grid[row][col].getValue() == grid[row + 1][col].getValue()) {
                    canMerge = true;
                }

                if (col < size - 1 && grid[row][col] != null && grid[row][col + 1] != null && grid[row][col].getValue() == grid[row][col + 1].getValue()) {
                    canMerge = true;
                }
            }
        }

        if (hasEmptyCell || canMerge) {
            return GameState.ONGOING;
        } else {
            return GameState.LOST;
        }
    }

    public Tile getTile(int row, int col) {
        return grid[row][col];
    }

    public int getSize() {
        return size;
    }
}
