package view;

import javax.swing.*;
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

            // Enable anti-aliasing for smooth rendering
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw background
            g2.setColor(new Color(0, 0, 0, 0)); // Transparent background
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);

            super.paintComponent(g);
            g2.dispose();
        }

        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            // Enable anti-aliasing for smoother edges
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Set border color
            g2.setColor(Color.DARK_GRAY); // Example: Blue border

            // Set border thickness
            float borderWidth = 1.0f; // Adjust border thickness here
            g2.setStroke(new BasicStroke(borderWidth));

            // Draw rounded rectangle border
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
            setContentAreaFilled(false);  // Disable the default background
            setFocusPainted(false);  // Disable the focus border
            setBorderPainted(false);  // Disable the border
            setOpaque(false);  // Make the button transparent

            // Set initial background color and text color
            currentColor = new Color(255, 255, 255);  // White background by default
            targetColor = currentColor;

            // Add a mouse listener to handle hover effects
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {

                    targetColor = new Color(89, 226, 89);// Change color on hover (dark blue)
                    setForeground(Color.WHITE);  // Change text color to white
                    startColorTransition();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    targetColor = new Color(255, 255, 255);  // Revert to original color (white)
                    setForeground(Color.BLACK);  // Change text color to black
                    startColorTransition();
                }
            });
        }

        // Method to start the color transition using a Timer
        private void startColorTransition() {
            if (colorTransitionTimer != null && colorTransitionTimer.isRunning()) {
                colorTransitionTimer.stop();  // Stop the previous timer if it was running
            }

            colorTransitionTimer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Gradually change the color
                    int r = currentColor.getRed() + (targetColor.getRed() - currentColor.getRed()) / 10;
                    int g = currentColor.getGreen() + (targetColor.getGreen() - currentColor.getGreen()) / 10;
                    int b = currentColor.getBlue() + (targetColor.getBlue() - currentColor.getBlue()) / 10;

                    currentColor = new Color(r, g, b);

                    // Update the background color
                    setBackground(currentColor);

                    // Stop the timer when the color has reached the target color
                    if (currentColor.equals(targetColor)) {
                        colorTransitionTimer.stop();
                    }
                }
            });

            colorTransitionTimer.start();
        }

        // Override the paintComponent method to draw custom button shape and color
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            // Enable anti-aliasing for smooth drawing
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Set the color based on button state
            if (getModel().isArmed()) {
                g2d.setColor(new Color(1, 93, 139));  // Pressed color
            } else if (getModel().isRollover()) {
                g2d.setColor(currentColor);  // Smooth color (hover or transition)
            } else {
                g2d.setColor(currentColor);  // Default color
            }

            // Draw a rounded rectangle with smooth edges
            int arcSize = 15;  // The degree of roundness for the corners
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcSize, arcSize);  // Smooth corners

            // Set text color and font
            g2d.setColor(new Color(0, 0, 0));  // Black text
            g2d.setFont(new Font("Arial", Font.BOLD, 15));  // Set font
            FontMetrics metrics = g2d.getFontMetrics();

            // Calculate text position
            int x = (getWidth() - metrics.stringWidth(getText())) / 2;
            int y = (getHeight() + metrics.getAscent()) / 2;


            super.paintComponent(g);
        }
    }

    static class CustomTextArea extends JTextArea {
        private Shape shape;
        private String placeholder = "";

        public CustomTextArea(int rows, int columns) {
            super(rows, columns);
            setOpaque(false);  // Make the background transparent
            setMargin(new Insets(10, 15, 10, 15));  // Add padding
            setFont(new Font("Arial", Font.PLAIN, 14));  // Set font style
            setLineWrap(true);  // Enable text wrapping
            setWrapStyleWord(true);  // Wrap at word boundaries
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            // Enable anti-aliasing for smooth rendering
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the background
            g2.setColor(new Color(240, 240, 240));  // Light gray background
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

            // Draw placeholder text if the area is empty
            if (getText().isEmpty() && !isFocusOwner() && placeholder != null) {
                g2.setColor(Color.GRAY);  // Placeholder text color
                g2.setFont(getFont());
                g2.drawString(placeholder, getInsets().left, getFontMetrics(getFont()).getHeight());
            }

            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            // Enable anti-aliasing for smooth rendering
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the border
            g2.setColor(Color.DARK_GRAY);  // Border color
            g2.setStroke(new BasicStroke(1));  // Border thickness
            g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 20, 20);

            g2.dispose();
        }

        @Override
        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
            return shape.contains(x, y);
        }

        // Method to set placeholder text
        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
            repaint();  // Redraw the component
        }

        // Method to get placeholder text
        public String getPlaceholder() {
            return this.placeholder;
        }
    }
}
