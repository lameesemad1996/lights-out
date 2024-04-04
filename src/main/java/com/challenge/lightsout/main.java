package com.challenge.lightsout;

import com.challenge.lightsout.utility.PuzzleInputParser;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        try {
            PuzzleInputParser.PuzzleInput input = PuzzleInputParser.parseInputFile("./src/main/resources/input.txt");

            Board board = new Board(input.board, input.depth);
            Solver solver = new Solver(board, input.pieces);

            if (solver.findSolution()) {
                System.out.println("Solved!");
            } else {
                System.out.println("No solution found.");
            }
        } catch (IOException e) {
            System.out.println("Failed to read the input file: " + e.getMessage());
        }
    }
}
