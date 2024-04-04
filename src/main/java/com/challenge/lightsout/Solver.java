package com.challenge.lightsout;
import java.util.ArrayList;
import java.util.List;

public class Solver {
    private Board board;
    private List<Piece> pieces;
    private List<String> placements = new ArrayList<>();

    public Solver(Board board, List<Piece> pieces) {
        this.board = board;
        this.pieces = pieces;
    }

    public boolean solve(int pieceIndex) {
        if (board.isSolved()) {
            System.out.println("Solution: " + String.join(" ", placements));
            return true;
        }

        if (pieceIndex >= pieces.size()) {
            return false; // All pieces have been tried without finding a solution.
        }

        Piece piece = pieces.get(pieceIndex);
        for (int posX = 0; posX <= board.getCells().length - 1; posX++) {
            for (int posY = 0; posY <= board.getCells()[0].length - 1; posY++) {
                if (board.canPlacePiece(piece, posX, posY)) { // Assuming canPlacePiece checks if the piece can be placed at the given position.
                    board.placePiece(piece, posX, posY);
                    placements.add(posX + "," + posY);
                    if (solve(pieceIndex + 1)) {
                        return true;
                    } else {
                        board.removePiece(piece, posX, posY);
                        placements.remove(placements.size() - 1);
                    }
                }
            }
        }
        return false;
    }

    // Add a public method to start the solving process.
    public boolean findSolution() {
        return solve(0); // Start solving from the first piece.
    }
}

