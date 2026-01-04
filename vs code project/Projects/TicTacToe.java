import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * TicTacToe.java
 * Simple Tic Tac Toe with Swing UI.
 * Two modes: Two-player (local) and Single-player vs Computer (minimax AI).
 *
 * Compile: javac TicTacToe.java
 * Run:     java TicTacToe
 */
public class TicTacToe extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private char[][] board = new char[3][3]; // 'X', 'O', or '\0'
    private boolean xTurn = true; // X always starts
    private JLabel statusLabel = new JLabel("Welcome to Tic Tac Toe!");
    private JButton resetBtn = new JButton("Reset");
    private JToggleButton modeToggle = new JToggleButton("Mode: 2-Player");
    private boolean vsComputer = false;

    public TicTacToe() {
        super("Tic Tac Toe");
        initUI();
        resetBoard();
        setSize(380, 460);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initUI() {
        // Top panel: status + mode + reset
        JPanel topPanel = new JPanel(new BorderLayout(8, 8));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        topPanel.add(statusLabel, BorderLayout.CENTER);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        modeToggle.addActionListener(e -> toggleMode());
        controls.add(modeToggle);
        resetBtn.addActionListener(e -> {
            resetBoard();
            updateStatus();
        });
        controls.add(resetBtn);
        topPanel.add(controls, BorderLayout.EAST);

        // Board grid
        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font btnFont = new Font("SansSerif", Font.BOLD, 48);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton b = new JButton("");
                b.setFont(btnFont);
                final int rr = r, cc = c;
                b.addActionListener(e -> onCellClicked(rr, cc));
                buttons[r][c] = b;
                boardPanel.add(b);
            }
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(boardPanel, BorderLayout.CENTER);
        updateStatus();
    }

    private void toggleMode() {
        vsComputer = !vsComputer;
        modeToggle.setText(vsComputer ? "Mode: vs Computer" : "Mode: 2-Player");
        resetBoard();
        updateStatus();
    }

    private void resetBoard() {
        xTurn = true;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = '\0';
                buttons[r][c].setText("");
                buttons[r][c].setEnabled(true);
            }
        }
        updateStatus();
    }

    private void onCellClicked(int r, int c) {
        if (board[r][c] != '\0') return; // already filled
        board[r][c] = xTurn ? 'X' : 'O';
        buttons[r][c].setText(String.valueOf(board[r][c]));
        buttons[r][c].setEnabled(false);

        if (checkForWin(board, xTurn ? 'X' : 'O')) {
            statusLabel.setText((xTurn ? "X" : "O") + " wins!");
            disableAll();
            return;
        } else if (isBoardFull(board)) {
            statusLabel.setText("It's a draw!");
            return;
        }

        xTurn = !xTurn;
        updateStatus();

        // If mode is vsComputer and it's O's turn (computer plays 'O'):
        if (vsComputer && !xTurn) {
            computerMove();
        }
    }

    private void computerMove() {
        // Use minimax to pick the best move for 'O'
        Move best = minimax(board, 'O');
        if (best.r >= 0) {
            // apply move
            board[best.r][best.c] = 'O';
            buttons[best.r][best.c].setText("O");
            buttons[best.r][best.c].setEnabled(false);

            if (checkForWin(board, 'O')) {
                statusLabel.setText("O wins!");
                disableAll();
                return;
            } else if (isBoardFull(board)) {
                statusLabel.setText("It's a draw!");
                return;
            }
            xTurn = true;
            updateStatus();
        }
    }

    private void disableAll() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                buttons[r][c].setEnabled(false);
    }

    private void updateStatus() {
        if (checkForWin(board, 'X')) {
            statusLabel.setText("X wins!");
            disableAll();
        } else if (checkForWin(board, 'O')) {
            statusLabel.setText("O wins!");
            disableAll();
        } else if (isBoardFull(board)) {
            statusLabel.setText("It's a draw!");
        } else {
            String player = xTurn ? "X" : "O";
            if (vsComputer && !xTurn) {
                // computer about to play
                statusLabel.setText("Computer (O) thinking...");
            } else {
                statusLabel.setText("Turn: " + player + (vsComputer ? (xTurn ? " (You)" : " (Computer)") : ""));
            }
        }
    }

    // ---------- Game logic helpers ----------

    private boolean checkForWin(char[][] b, char p) {
        // rows & cols
        for (int i = 0; i < 3; i++) {
            if (b[i][0] == p && b[i][1] == p && b[i][2] == p) return true;
            if (b[0][i] == p && b[1][i] == p && b[2][i] == p) return true;
        }
        // diagonals
        if (b[0][0] == p && b[1][1] == p && b[2][2] == p) return true;
        if (b[0][2] == p && b[1][1] == p && b[2][0] == p) return true;
        return false;
    }

    private boolean isBoardFull(char[][] b) {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (b[r][c] == '\0') return false;
        return true;
    }

    // Minimax implementation for optimal play by 'O' (computer).
    // Score: +10 if 'O' wins, -10 if 'X' wins, 0 draw. Depth considered to choose quicker wins.
    private Move minimax(char[][] current, char player) {
        // Check terminal states
        if (checkForWin(current, 'O')) return new Move(+10);
        if (checkForWin(current, 'X')) return new Move(-10);
        if (isBoardFull(current)) return new Move(0);

        Move best = new Move();
        if (player == 'O') {
            best.score = Integer.MIN_VALUE;
        } else {
            best.score = Integer.MAX_VALUE;
        }

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (current[r][c] == '\0') {
                    current[r][c] = player;
                    Move m = minimax(current, (player == 'O') ? 'X' : 'O');
                    // small depth penalty to prefer faster wins
                    if (m.score == 10 || m.score == -10) {
                        // propagate with slight adjustment
                    }
                    current[r][c] = '\0'; // undo

                    m.r = r; m.c = c;
                    if (player == 'O') {
                        if (m.score > best.score) {
                            best = m;
                        }
                    } else {
                        if (m.score < best.score) {
                            best = m;
                        }
                    }
                }
            }
        }
        return best;
    }

    // Minimax base-case wrapper that adds depth-awareness
    private Move minimaxWithDepth(char[][] boardState, char player, int depth) {
        if (checkForWin(boardState, 'O')) return new Move(10 - depth);
        if (checkForWin(boardState, 'X')) return new Move(depth - 10);
        if (isBoardFull(boardState)) return new Move(0);

        Move best = new Move();
        if (player == 'O') best.score = Integer.MIN_VALUE;
        else best.score = Integer.MAX_VALUE;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (boardState[r][c] == '\0') {
                    boardState[r][c] = player;
                    Move m = minimaxWithDepth(boardState, (player == 'O') ? 'X' : 'O', depth + 1);
                    boardState[r][c] = '\0';
                    m.r = r; m.c = c;
                    if (player == 'O') {
                        if (m.score > best.score) best = m;
                    } else {
                        if (m.score < best.score) best = m;
                    }
                }
            }
        }
        return best;
    }

    // Replace minimax usage above with depth-aware version for better play:
    private Move minimax(char[][] current, char playerToMove) {
        return minimaxWithDepth(copyBoard(current), playerToMove, 0);
    }

    private char[][] copyBoard(char[][] src) {
        char[][] dst = new char[3][3];
        for (int i = 0; i < 3; i++)
            System.arraycopy(src[i], 0, dst[i], 0, 3);
        return dst;
    }

    private static class Move {
        int r = -1, c = -1;
        int score = 0;
        Move() {}
        Move(int score) { this.score = score; }
    }

    public static void main(String[] args) {
        // Ensure UI uses system look & feel for better native appearance
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored){}
        SwingUtilities.invokeLater(() -> {
            TicTacToe t = new TicTacToe();
            t.setVisible(true);
        });
    }
}
