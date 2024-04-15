import spock.lang.Specification
import com.challenge.lightsout.Board
import com.challenge.lightsout.Piece
import com.challenge.lightsout.Solver

class LightsOutSolverSpec extends Specification {

    def "ensure solver solves the board with all pieces used correctly"() {
        given: "a board and a set of pieces"
        int depth = 2
        int[][] initialCells = [[0, 0, 1], [0, 1, 1], [0, 1, 1]]
        Board board = new Board(initialCells, depth)

        List<Piece> pieces = [
                new Piece(".X,XX"),
                new Piece("XX"),
                new Piece(".X,.X,XX")
        ]

        Solver solver = new Solver(board, pieces)

        when: "the solver tries to solve the puzzle"
        boolean result = solver.findSolution()

        then: "the result should be true and the board should be solved with all pieces used"
        result
        board.isSolved()
        // Check if all pieces were used
        solver.usedPieces.every { it == true }
    }

    def "ensure solver outputs correct placements"() {
        given: "a specific board and set of pieces"
        int depth = 2
        int[][] initialCells = [[0, 0, 1], [0, 1, 1], [0, 1, 1]]
        Board board = new Board(initialCells, depth)

        List<Piece> pieces = [
                new Piece(".X,XX"),
                new Piece("XX"),
                new Piece(".X,.X,XX")
        ]

        Solver solver = new Solver(board, pieces)

        when: "the solver tries to solve the puzzle"
        solver.findSolution()

        then: "the output should match the expected placements"
        solver.placements == ["0,1", "0,2", "1,0"]
    }
}
