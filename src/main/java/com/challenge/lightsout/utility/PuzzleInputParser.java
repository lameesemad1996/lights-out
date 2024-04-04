package com.challenge.lightsout.utility;

import com.challenge.lightsout.Piece;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzleInputParser {

    public static PuzzleInput parseInputFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        // Parse depth
        int depth = Integer.parseInt(lines.get(0));

        // Parse board state
        String[] boardRows = lines.get(1).split(",");
        int[][] board = new int[boardRows.length][boardRows[0].length()];
        for (int i = 0; i < boardRows.length; i++) {
            for (int j = 0; j < boardRows[i].length(); j++) {
                board[i][j] = Character.getNumericValue(boardRows[i].charAt(j));
            }
        }

        // Parse pieces
        String[] pieceStrings = lines.get(2).split(" ");
        List<Piece> pieces = new ArrayList<>();
        for (String pieceString : pieceStrings) {
            System.out.println(pieceString);
            pieces.add(new Piece(pieceString));
        }

        System.out.println(Arrays.deepToString(board));
        System.out.println(depth);

        return new PuzzleInput(depth, board, pieces);
    }

    public static class PuzzleInput {
        public final int depth;
        public final int[][] board;
        public final List<Piece> pieces;

        public PuzzleInput(int depth, int[][] board, List<Piece> pieces) {
            this.depth = depth;
            this.board = board;
            this.pieces = pieces;
        }
    }
}
