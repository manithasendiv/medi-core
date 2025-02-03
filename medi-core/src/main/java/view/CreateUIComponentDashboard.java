package view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class CreateUIComponentDashboard {
    static class RoundedJTextField extends JTextField {
        private Shape shape;

        public RoundedJTextField(int size) {
            super(size);
            setBounds(520, 284, 190, 25);
            setBackground(new Color(0, 0, 0, 0));
            setOpaque(false);
            setMargin(new Insets(5, 20, 5, 20));
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0, 0, 0, 0)); // Transparent background
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);

            super.paintComponent(g);
            g2.dispose();
        }

        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.DARK_GRAY);
            float borderWidth = 1.0f;
            g2.setStroke(new BasicStroke(borderWidth));
            g2.drawRoundRect(
                    (int) (borderWidth / 2),
                    (int) (borderWidth / 2),
                    getWidth() - (int) borderWidth,
                    getHeight() - (int) borderWidth,
                    20, 20
            );

            g2.dispose();
        }

        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);
            }
            return shape.contains(x, y);
        }
    }

    static class CustomButton extends JButton {

        private Color targetColor;
        private Color currentColor;
        private Timer colorTransitionTimer;

        // Constructor
        public CustomButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);

            currentColor = new Color(255, 255, 255);
            targetColor = currentColor;

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {

                    targetColor = new Color(89, 226, 89);
                    setForeground(Color.WHITE);
                    startColorTransition();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    targetColor = new Color(255, 255, 255);
                    setForeground(Color.BLACK);
                    startColorTransition();
                }
            });
        }

        private void startColorTransition() {
            if (colorTransitionTimer != null && colorTransitionTimer.isRunning()) {
                colorTransitionTimer.stop();
            }

            colorTransitionTimer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int r = currentColor.getRed() + (targetColor.getRed() - currentColor.getRed()) / 10;
                    int g = currentColor.getGreen() + (targetColor.getGreen() - currentColor.getGreen()) / 10;
                    int b = currentColor.getBlue() + (targetColor.getBlue() - currentColor.getBlue()) / 10;

                    currentColor = new Color(r, g, b);
                    setBackground(currentColor);

                    if (currentColor.equals(targetColor)) {
                        colorTransitionTimer.stop();
                    }
                }
            });
            colorTransitionTimer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isArmed()) {
                g2d.setColor(new Color(1, 93, 139));
            } else if (getModel().isRollover()) {
                g2d.setColor(currentColor);
            } else {
                g2d.setColor(currentColor);
            }

            int arcSize = 15;
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcSize, arcSize);

            g2d.setColor(new Color(0, 0, 0));
            g2d.setFont(new Font("Arial", Font.BOLD, 15));
            FontMetrics metrics = g2d.getFontMetrics();

            int x = (getWidth() - metrics.stringWidth(getText())) / 2;
            int y = (getHeight() + metrics.getAscent()) / 2;


            super.paintComponent(g);
        }
    }

    static class CustomTableCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (row % 2 == 0) {
            cellComponent.setBackground(new Color(220, 240, 255));
        } else {
            cellComponent.setBackground(Color.WHITE);
        }

        if (isSelected) {
            cellComponent.setBackground(new Color(142, 210, 119));
            cellComponent.setForeground(Color.WHITE);
        } else {
            cellComponent.setForeground(Color.BLACK);
        }

        return cellComponent;
    }
}

    static class CustomTextArea extends JTextArea {
        public CustomTextArea(int rows, int columns) {
            super(rows, columns);

            setPreferredSize(new Dimension(150, 100));
            setMinimumSize(new Dimension(150, 100));
            setMaximumSize(new Dimension(150, 100));

            setOpaque(false);
            setFont(new Font("Arial", Font.PLAIN, 14));
            setLineWrap(true);
            setWrapStyleWord(true);
            setMargin(new Insets(10, 10, 10, 10));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(240, 240, 240));
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(Color.GRAY);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

            g2.dispose();
        }
    }

    static class CustomRadioButton extends JRadioButton {
        private Color baseColor = new Color(236, 236, 236, 255);
        private Color hoverColor = new Color(157, 198, 232);
        private Color selectedColor = Color.BLACK;
        private Color borderColor = Color.DARK_GRAY;

        public CustomRadioButton(String text) {
            super(text);
            setOpaque(false);
            setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    baseColor = hoverColor; // Change to hover color
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    baseColor = new Color(236, 236, 236, 255);
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int diameter = 20;
            int padding = 0;

            g2.setColor(borderColor);
            g2.fillOval(padding, (getHeight() - diameter) / 2, diameter, diameter);
            g2.setColor(baseColor);
            g2.fillOval(padding + 2, (getHeight() - diameter) / 2 + 2, diameter - 4, diameter - 4);


            if (isSelected()) {
                g2.setColor(selectedColor);
                int innerDiameter = diameter - 8;
                g2.fillOval(padding + 4, (getHeight() - innerDiameter) / 2, innerDiameter, innerDiameter);
            }

            g2.setColor(getForeground());
            g2.setFont(getFont());
            FontMetrics metrics = g2.getFontMetrics();
            int textX = padding + diameter + 5;
            int textY = (getHeight() + metrics.getAscent() - metrics.getDescent()) / 2;
            g2.drawString(getText(), textX, textY);
        }
    }

    static class CustomComboBox extends JComboBox<String> {

        public CustomComboBox() {
            super();
            setUI(new CustomComboBoxUI());
            setRenderer(new CustomComboBoxRenderer());
            setPreferredSize(new Dimension(200, 30));
        }

        private static class CustomComboBoxUI extends BasicComboBoxUI {
            @Override
            protected JButton createArrowButton() {
                // Create a custom arrow button
                JButton button = new JButton("▼");
                button.setFont(new Font("Arial", Font.BOLD, 12));
                button.setFocusPainted(false);
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setForeground(new Color(100, 100, 100));
                return button;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(240, 240, 240)); // Light gray for the background
                g2d.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 20, 20);
            }

            @Override
            protected void installDefaults() {
                super.installDefaults();
                comboBox.setOpaque(false);
                comboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            }
        }

        private static class CustomComboBoxRenderer extends BasicComboBoxRenderer {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setOpaque(true);

                if (isSelected) {
                    label.setBackground(new Color(0, 119, 182));
                    label.setForeground(Color.WHITE);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(new Color(50, 50, 50));
                }
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return label;
            }
        }
    }
}
