package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonopolyGUI extends JFrame {
    private static final int BOARD_SIZE = 660;
    private static final int SQUARE_SIZE = 60;
    private static final int IMAGE_SIZE = 660;
    private static final int PLAYER_SIZE = 10;

    private Image middleImage;
    private JPanel controlPanel;
    private static int numPlayers;
    private int[] playerPositionsX;
    private int[] playerPositionsY;
    public static JTextArea consoleTextArea;

    public MonopolyGUI() {
        // This is the main board
        setTitle("Monopoly Board");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        middleImage = Toolkit.getDefaultToolkit().getImage("src/images/monopoly-board.png");

        // These are interaction buttons
        controlPanel = new JPanel();
        addControlButtons(controlPanel);
        controlPanel.setPreferredSize(new Dimension(100, getHeight()));
        add(controlPanel, BorderLayout.CENTER);

        playerPositionsX = new int[6];
        playerPositionsY = new int[6];

        // This draws the board
        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
                drawPlayers(g);
            }
        };
        boardPanel.setPreferredSize(new Dimension(BOARD_SIZE + 200, BOARD_SIZE));
        add(boardPanel, BorderLayout.WEST);

        // Information display
        consoleTextArea = new JTextArea();
        consoleTextArea.setEditable(false);
        consoleTextArea.setFont(new Font("Arial", Font.ITALIC, 25));
        JScrollPane scrollPane = new JScrollPane(consoleTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        add(scrollPane, BorderLayout.EAST);
        setVisible(true);
    }

    private void addControlButtons(JPanel controlPanel) {
        controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        String[] playerOptions = {"2 Players", "3 Players", "4 Players", "5 Players", "6 Players"};

        for (String option : playerOptions) {
            JButton button = new JButton(option);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String buttonText = button.getText();
                    numPlayers = Integer.parseInt(buttonText.split(" ")[0]);

                    for (Component comp : controlPanel.getComponents()) {
                        if (comp instanceof JButton btn) {
                            if (btn.getText().contains("Player")) {
                                btn.setVisible(false);
                            }
                        }
                    }
                }
            });
            controlPanel.add(button);
        }
    }

    private void setupPlayerPositions() {
        int startX = SQUARE_SIZE / 2 - PLAYER_SIZE / 2;
        int startY = SQUARE_SIZE / 2 - PLAYER_SIZE / 2;

        for (int i = 0; i < numPlayers; i++) {
            playerPositionsX[i] = startX;
            playerPositionsY[i] = startY + i * (BOARD_SIZE - SQUARE_SIZE);
        }
    }

    private void drawBoard(Graphics g) {
        int rows = BOARD_SIZE / SQUARE_SIZE;
        int cols = rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = j * SQUARE_SIZE;
                int y = i * SQUARE_SIZE;

                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                    // Draw border squares
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                } else {
                    // Draw inner squares
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }

        // Adjust the image size
        if (middleImage != null) {
            int imageX = (BOARD_SIZE - IMAGE_SIZE) / 2;
            int imageY = (BOARD_SIZE - IMAGE_SIZE) / 2;
            g.drawImage(middleImage, imageX, imageY, IMAGE_SIZE, IMAGE_SIZE, this);
        }
    }



    private void drawPlayers(Graphics g) {
        if (numPlayers > 0) {
            Color[] playerColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA};
            int startX = BOARD_SIZE - SQUARE_SIZE + 30;
            int startY = BOARD_SIZE - SQUARE_SIZE + 30;
            int playerOffsetX = SQUARE_SIZE / 3;
            int playerOffsetY = SQUARE_SIZE / 3;

            for (int i = 0; i < numPlayers; i++) {
                g.setColor(playerColors[i]);
                int x = startX - (i % 2) * playerOffsetX;
                int y = startY - (i / 2) * playerOffsetY;
                g.fillOval(x, y, PLAYER_SIZE, PLAYER_SIZE);
            }
        }
    }

    public static int getNumPlayers() {
        return numPlayers;
    }
}