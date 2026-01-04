import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BrickBreakerGame extends JPanel implements KeyListener, ActionListener {

    // Game objects
    private static final long serialVersionUID = 1L;
    private Paddle paddle;
    private Ball ball;
    private Brick[][] bricks;
    private Timer timer;
    private boolean gameOver;
    private int score;

    // Constructor to initialize the game
    public BrickBreakerGame() {
        this.setPreferredSize(new Dimension(600, 400));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        paddle = new Paddle(250, 350);
        ball = new Ball(300, 200);
        bricks = new Brick[5][7];
        score = 0;
        gameOver = false;

        // Create bricks
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                bricks[i][j] = new Brick(j * 85 + 50, i * 25 + 50);
            }
        }

        // Timer for game loop
        timer = new Timer(5, this);
        timer.start();
    }

    // Game loop for updating and repainting the game state
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            ball.move();
            ball.checkCollision(paddle);
            checkBrickCollision();
            repaint();
        }
    }

    // Check collisions with bricks
    public void checkBrickCollision() {
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                Brick brick = bricks[i][j];
                if (brick != null && ball.getBounds().intersects(brick.getBounds())) {
                    ball.reverseDirection();
                    bricks[i][j] = null; // Remove brick
                    score += 10; // Increase score
                    if (allBricksCleared()) {
                        gameOver = true;
                        JOptionPane.showMessageDialog(this, "You Win! Score: " + score);
                    }
                }
            }
        }
    }

    // Check if all bricks have been cleared
    public boolean allBricksCleared() {
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                if (bricks[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to paint the game objects
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the paddle
        paddle.draw(g);

        // Draw the ball
        ball.draw(g);

        // Draw the bricks
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                Brick brick = bricks[i][j];
                if (brick != null) {
                    brick.draw(g);
                }
            }
        }

        // Draw the score
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);

        // Game over message
        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over", 230, 200);
        }
    }

    // Key listener methods to control the paddle
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            paddle.moveLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            paddle.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    // Main method to start the game
    public static void main(String[] args) {
        JFrame frame = new JFrame("Brick Breaker Game");
        BrickBreakerGame game = new BrickBreakerGame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
    }
}

// Paddle class to represent the player's paddle
class Paddle {
    private int x, y;
    private final int width = 100, height = 15;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        if (x > 0) x -= 15;
    }

    public void moveRight() {
        if (x < 500) x += 15;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
}

// Ball class to represent the ball in the game
class Ball {
    private int x, y, diameter = 15;
    private int xSpeed = 2, ySpeed = -2;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;

        // Ball collision with walls
        if (x <= 0 || x >= 585) xSpeed = -xSpeed; // Wall collision
        if (y <= 0) ySpeed = -ySpeed; // Top wall collision
        if (y >= 375) ySpeed = -ySpeed; // Bottom wall collision
    }

    public void checkCollision(Paddle paddle) {
        if (getBounds().intersects(paddle.getBounds())) {
            ySpeed = -ySpeed; // Reverse ball's y direction
        }
    }

    public void reverseDirection() {
        ySpeed = -ySpeed; // Reverse the ball's vertical direction
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, diameter, diameter);
    }
}

// Brick class to represent each brick
class Brick {
    private int x, y, width = 80, height = 20;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }
}

