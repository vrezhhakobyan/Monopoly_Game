package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintStream;

public class MonopolyGUI extends JFrame {
    private static final int BOARD_SIZE = 800;
    private static final int SQUARE_SIZE = 80;
    private static final int IMAGE_SIZE = 800;
    private static final int PLAYER_SIZE = 20;

    private Image middleImage;
    private JPanel controlPanel;
    private int numPlayers;
    private int[] playerPositionsX;
    private int[] playerPositionsY;
    private JTextArea consoleTextArea;

    public MonopolyGUI() {
        setTitle("Monopoly Board");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        middleImage = Toolkit.getDefaultToolkit().getImage("src/images/monopoly-board.png");

        controlPanel = new JPanel();
        addControlButtons(controlPanel);
        controlPanel.setPreferredSize(new Dimension(200, getHeight())); // Reduce width of control panel
        add(controlPanel, BorderLayout.WEST); // Move control panel to the left

        numPlayers = 0;
        playerPositionsX = new int[6];
        playerPositionsY = new int[6];

        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
                drawPlayers(g);
            }
        };
        boardPanel.setPreferredSize(new Dimension(BOARD_SIZE + 200, BOARD_SIZE)); // Increase width of board panel
        add(boardPanel, BorderLayout.CENTER);

        consoleTextArea = new JTextArea();
        consoleTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 100)); // Adjust console size
        add(scrollPane, BorderLayout.EAST);

        redirectSystemOutToConsole();

        setVisible(true);
    }

    private void addControlButtons(JPanel controlPanel) {
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        String[] playerOptions = {"2 Players", "3 Players", "4 Players", "5 Players", "6 Players"};

        for (String option : playerOptions) {
            JButton button = new JButton(option);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String buttonText = button.getText();
                    numPlayers = Integer.parseInt(buttonText.split(" ")[0]);
                    setupPlayerPositions();
                    controlPanel.setVisible(false);
                    repaint();
                    displayPlayerInfo();
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
                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                    int x = j * SQUARE_SIZE;
                    int y = i * SQUARE_SIZE;
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                } else {
                    int x = j * SQUARE_SIZE;
                    int y = i * SQUARE_SIZE;
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

    private void redirectSystemOutToConsole() {
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                consoleTextArea.append(String.valueOf((char) b));
                consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength());
            }
        });

        System.setOut(printStream);
    }

    private void displayPlayerInfo() {
        System.out.println("Player 1 Info:");
        System.out.println("Name: Player 1");
        System.out.println("Money: $1500");
    }

}
