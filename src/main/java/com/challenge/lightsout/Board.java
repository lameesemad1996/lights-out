package com.challenge.lightsout;
public class Board {
    private int[][] cells;
    private int depth;

    public Board(int[][] initialCells, int depth) {
        this.cells = initialCells;
        this.depth = depth;
    }

    public boolean isSolved() {
        for (int[] row : cells) {
            for (int cell : row) {
                if (cell != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a piece can be placed at the specified position.
     *
     * @param piece The piece to place.
     * @param posX The x-coordinate of the top-left corner where the piece is to be placed.
     * @param posY The y-coordinate of the top-left corner where the piece is to be placed.
     * @return true if the piece can be placed, false otherwise.
     */
    public boolean canPlacePiece(Piece piece, int posX, int posY) {
        for (int[] inc : piece.getIncrements()) {
            int x = inc[0] + posX;
            int y = inc[1] + posY;

            // Check if the position is outside the board's boundaries
            if (x < 0 || x >= cells.length || y < 0 || y >= cells[0].length) {
                return false;
            }

            // Check if the cell is in a state that equals the board's depth
            // Assuming that a cell in such a state cannot have a piece placed on it
            if (cells[x][y] == depth) {
                return false;
            }
        }
        return true; // The piece can be placed
    }

    public void placePiece(Piece piece, int posX, int posY) {
        for (int[] inc : piece.getIncrements()) {
            int x = inc[0] + posX;
            int y = inc[1] + posY;
            cells[x][y] = (cells[x][y] + 1) % depth;
        }
    }

    public void removePiece(Piece piece, int posX, int posY) {
        for (int[] inc : piece.getIncrements()) {
            int x = inc[0] + posX;
            int y = inc[1] + posY;
            cells[x][y] = (cells[x][y] - 1 + depth) % depth; // Ensure no negative values
        }
    }

    public int[][] getCells() {
        return cells;
    }

    public int getDepth() {
        return depth;
    }
}
