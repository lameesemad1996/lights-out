package com.challenge.lightsout;
import java.util.ArrayList;
import java.util.List;

public class Piece {
    private List<int[]> increments = new ArrayList<>();

    public Piece(String shape) {
        String[] rows = shape.split(",");
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length(); j++) {
                if (rows[i].charAt(j) == 'X') {
                    increments.add(new int[]{i, j});
                }
            }
        }
    }

    public List<int[]> getIncrements() {
        return increments;
    }
}
