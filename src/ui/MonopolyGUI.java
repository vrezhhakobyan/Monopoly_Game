package ui;

import javax.swing.*;
import java.awt.*;

/**
 * The MonopolyGUI class represents the graphical user interface for the Monopoly game.
 * It includes the game board, control buttons, player information display, and interaction buttons.
 */
public class MonopolyGUI extends JFrame {
    protected static final int BOARD_SIZE = 660;
    protected static final int SQUARE_SIZE = 60;
    private static final int IMAGE_SIZE = 660;
    protected static final int PLAYER_SIZE = 15;

    private final Image middleImage;
    private static int numPlayers;
    protected static int[] playerPositionsX;
    protected static int[] playerPositionsY;
    public static JTextArea consoleTextArea;
    private static JPanel boardPanel;

    /**
     * Constructs a MonopolyGUI object.
     * Initializes the graphical user interface components.
     */
    public MonopolyGUI() {
        setTitle("Monopoly Board");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        middleImage = Toolkit.getDefaultToolkit().getImage("src/images/monopoly-board.png");

        JPanel controlPanel = new JPanel();
        addControlButtons(controlPanel);
        controlPanel.setPreferredSize(new Dimension(100, getHeight()));
        add(controlPanel, BorderLayout.CENTER);

        playerPositionsX = new int[6];
        playerPositionsY = new int[6];

        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
                drawPlayers(g);
            }
        };
        boardPanel.setPreferredSize(new Dimension(BOARD_SIZE + 200, BOARD_SIZE));
        add(boardPanel, BorderLayout.WEST);

        consoleTextArea = new JTextArea();
        consoleTextArea.setEditable(false);
        consoleTextArea.setFont(new Font("Arial", Font.ITALIC, 20));
        JScrollPane scrollPane = new JScrollPane(consoleTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        add(scrollPane, BorderLayout.EAST);
        setVisible(true);
    }

    /**
     * Retrieves the board panel.
     *
     * @return The board panel
     */
    public static JPanel getBoardPanel() {
        return boardPanel;
    }

    private void addControlButtons(JPanel controlPanel) {
        controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        String[] playerOptions = {"2 Players", "3 Players", "4 Players", "5 Players", "6 Players"};

        for (String option : playerOptions) {
            JButton button = new JButton(option);
            button.addActionListener(e -> {
                String buttonText = button.getText();
                numPlayers = Integer.parseInt(buttonText.split(" ")[0]);

                for (Component comp : controlPanel.getComponents()) {
                    if (comp instanceof JButton btn) {
                        if (btn.getText().contains("Player")) {
                            btn.setVisible(false);
                        }
                    }
                }
            });
            controlPanel.add(button);
        }
    }

    private void drawBoard(Graphics g) {
        int rows = BOARD_SIZE / SQUARE_SIZE;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                int x = j * SQUARE_SIZE;
                int y = i * SQUARE_SIZE;

                if (i == 0 || i == rows - 1 || j == 0 || j == rows - 1) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }

        if (middleImage != null) {
            int imageX = (BOARD_SIZE - IMAGE_SIZE) / 2;
            int imageY = (BOARD_SIZE - IMAGE_SIZE) / 2;
            g.drawImage(middleImage, imageX, imageY, IMAGE_SIZE, IMAGE_SIZE, this);
        }
    }

    private void drawPlayers(Graphics g) {
        if (numPlayers > 0) {
            Color[] playerColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA};
            int startX = 11;
            int startY = 11;
            int playerOffsetX = 0;
            int playerOffsetY = 0;

            for (int i = 0; i < numPlayers; i++) {
                g.setColor(playerColors[i]);
                int x = startX + (i % 2) * playerOffsetX;
                int y = startY + (i / 2) * playerOffsetY;
                x += playerPositionsX[i];
                y += playerPositionsY[i];
                g.fillOval(x, y, PLAYER_SIZE, PLAYER_SIZE);
            }
        }
    }

    /**
     * Retrieves the number of players.
     *
     * @return The number of players
     */
    public static int getNumPlayers() {
        return numPlayers;
    }
}
