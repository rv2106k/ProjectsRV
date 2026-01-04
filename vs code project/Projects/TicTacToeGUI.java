import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A simple Tic-Tac-Toe game with a graphical user interface.
 * * This class sets up the main game window, the 3x3 grid of buttons,
 * and handles all the game logic, including checking for wins,
 * draws, and resetting the game.
 */
public class TicTacToeGUI extends JFrame implements ActionListener {

    // --- GUI Components ---
    
    /** Main window of the game. */
    private JFrame frame;
    
    /** Panel to hold the 3x3 grid of buttons. */
    private JPanel boardPanel;
    
    /** Array of 9 buttons for the game grid. */
    private JButton[] buttons = new JButton[9];
    
    /** Button to reset the game. */
    private JButton resetButton;
    
    /** Label to show game status (e.g., whose turn it is). */
    private JLabel statusLabel;

    // --- Game Logic State ---
    
    /** Tracks the current player. 'X' starts the game. */
    private char currentPlayer = 'X';
    
    /** Flag to indicate if the game is over (won or drawn). */
    private boolean gameEnded = false;
    
    /** Total number of moves made, used to check for a draw. */
    private int movesCount = 0;

    /**
     * Main constructor to set up and launch the game.
     */
    public TicTacToeGUI() {
        // --- 1. Set up the Main Window (JFrame) ---
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500); // Width, Height
        frame.setLayout(new BorderLayout()); // Main layout for the window
        frame.setLocationRelativeTo(null); // Center the window on the screen

        // --- 2. Set up the Status Label (Top) ---
        statusLabel = new JLabel("Player " + currentPlayer + "'s turn");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        frame.add(statusLabel, BorderLayout.NORTH); // Add label to the top

        // --- 3. Set up the Game Board (Center) ---
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3)); // 3x3 grid
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Border around the grid

        // Initialize all 9 buttons
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(""); // Create button with no text
            buttons[i].setFont(new Font("Arial", Font.BOLD, 100)); // Large font for 'X' and 'O'
            buttons[i].setFocusable(false); // Remove dotted focus outline on click
            buttons[i].addActionListener(this); // Add this class as the event listener
            boardPanel.add(buttons[i]); // Add button to the grid panel
        }
        
        frame.add(boardPanel, BorderLayout.CENTER); // Add grid panel to the center

        // --- 4. Set up the Reset Button (Bottom) ---
        resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> resetGame()); // Use a lambda expression for the reset button
        
        JPanel bottomPanel = new JPanel(); // Panel to hold the reset button
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(resetButton);
        
        frame.add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel to the frame

        // --- 5. Make the window visible ---
        frame.setVisible(true);
    }

    /**
     * This method is called whenever a button on the grid is clicked.
     * It's the main entry point for the game logic.
     *
     * @param e The ActionEvent object containing details about the click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Find which button was clicked
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                // Check if the button is already played or if the game is over
                if (buttons[i].getText().equals("") && !gameEnded) {
                    // --- Make the move ---
                    buttons[i].setText(String.valueOf(currentPlayer)); // Set 'X' or 'O'
                    buttons[i].setForeground(currentPlayer == 'X' ? Color.RED : Color.BLUE); // Set color
                    movesCount++;

                    // --- Check for Win or Draw ---
                    if (checkWin()) {
                        endGame(currentPlayer + " wins!");
                    } else if (movesCount == 9) {
                        endGame("It's a draw!");
                    } else {
                        // --- Switch Player ---
                        switchPlayer();
                    }
                }
            }
        }
    }

    /**
     * Checks all 8 possible winning conditions (3 rows, 3 cols, 2 diagonals).
     *
     * @return true if the current player has won, false otherwise.
     */
    private boolean checkWin() {
        // Define all 8 winning lines
        int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6}             // Diagonals
        };

        String player = String.valueOf(currentPlayer);
        
        // Loop through all winning conditions
        for (int[] line : winConditions) {
            if (buttons[line[0]].getText().equals(player) &&
                buttons[line[1]].getText().equals(player) &&
                buttons[line[2]].getText().equals(player)) {
                return true; // Found a winning line
            }
        }
        return false; // No win yet
    }

    /**
     * Switches the current player from 'X' to 'O' or 'O' to 'X'
     * and updates the status label.
     */
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        statusLabel.setText("Player " + currentPlayer + "'s turn");
    }

    /**
     * Called when the game ends (either by a win or a draw).
     *
     * @param message The message to display (e.g., "X wins!" or "It's a draw!").
     */
    private void endGame(String message) {
        gameEnded = true;
        statusLabel.setText(message);
        
        // Show a popup dialog
        JOptionPane.showMessageDialog(frame, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Resets the game board and all state variables to their initial values.
     * This is called when the "Reset Game" button is clicked.
     */
    private void resetGame() {
        // Clear all buttons
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setBackground(null); // Reset background color (if any)
        }
        
        // Reset game state
        currentPlayer = 'X';
        gameEnded = false;
        movesCount = 0;
        statusLabel.setText("Player " + currentPlayer + "'s turn");
    }

    /**
     * The main method. It ensures the GUI is created and run on the
     * Event Dispatch Thread (EDT) for thread safety, which is the
     * standard practice for Swing applications.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure GUI updates are thread-safe
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToeGUI(); // Create an instance of our game
            }
        });
    }
}