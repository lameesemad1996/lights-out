package com.challenge.lightsout;
import java.util.ArrayList;
import java.util.List;

public class Solver {
    private final Board board;
    private final List<Piece> pieces;
    public final List<String> placements = new ArrayList<>();
    public final boolean[] usedPieces;

    public Solver(Board board, List<Piece> pieces) {
        this.board = board;
        this.pieces = pieces;
        // Initialize the piece usage tracking array.
        this.usedPieces = new boolean[pieces.size()];
    }

    public boolean solve(int pieceIndex) {
        if (pieceIndex == pieces.size()) {
            // Check if all pieces are processed and if the board is solved.
            if (board.isSolved() && allPiecesUsed()) {
                System.out.println("Solution: " + String.join(" ", placements));
                return true;
            }
            return false;
        }

        Piece piece = pieces.get(pieceIndex);
        for (int posX = 0; posX < board.getCells().length; posX++) {
            for (int posY = 0; posY < board.getCells()[0].length; posY++) {
                if (board.canPlacePiece(piece, posX, posY)) {
                    // Uncomment the following line to follow the placement logic
                    // System.out.println("Placing piece " + pieceIndex + " at " + posX + "," + posY);
                    board.placePiece(piece, posX, posY);
                    placements.add(posY + "," + posX);
                    // Mark the placed piece as used.
                    usedPieces[pieceIndex] = true;

                    // Recursive call to place the next piece.
                    if (solve(pieceIndex + 1)) {
                        return true;
                    } else {
                        // Uncomment the following line to follow the placement logic
                        // System.out.println("Removing piece " + pieceIndex + " from " + posX + "," + posY);
                        board.removePiece(piece, posX, posY);
                        placements.remove(placements.size() - 1);
                        // Mark the removed piece as unused.
                        usedPieces[pieceIndex] = false;
                    }
                }
            }
        }
        // If no valid placement found for this piece, return false.
        return false;
    }

    // Check if all pieces have been used in the solution.
    private boolean allPiecesUsed() {
        for (boolean used : usedPieces) {
            if (!used) return false;
        }
        return true;
    }

    // Public method to initiate solving.
    public boolean findSolution() {
        // Start solving from the first piece.
        return solve(0);
    }
}
