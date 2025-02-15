package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

public class CreateUIComponent {
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

            g2.setColor(new Color(0, 0, 0, 0));
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
                    0,
                    0,
                    getWidth() - (int) borderWidth,
                    getHeight() - (int) borderWidth,
                    40, 40
            );

            g2.dispose();
        }
    }

    static class RoundedJPasswordField extends JPasswordField {
        private Shape shape;

        public RoundedJPasswordField(int size) {
            super(size);
            setBounds(520, 284, 190, 25);
            setBackground(new Color(0, 0, 0, 0));
            setOpaque(false);
            setMargin(new Insets(5, 20, 5, 20));
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw background
            g2.setColor(new Color(0, 0, 0, 0));
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);

            super.paintComponent(g);
            g2.dispose();
        }

        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Set border color
            g2.setColor(Color.DARK_GRAY);

            float borderWidth = 1.0f;
            g2.setStroke(new BasicStroke(borderWidth));

            g2.drawRoundRect(
                    0,
                    0,
                    getWidth() - (int) borderWidth,
                    getHeight() - (int) borderWidth,
                    40, 40
            );

            g2.dispose();
        }
    }

    static class CustomButton extends JButton {

        private Color targetColor;
        private Color currentColor;
        private Timer colorTransitionTimer;

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

                    targetColor = new Color(0, 119, 182);
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
                g2d.setColor(new Color(3, 4, 94));
            } else if (getModel().isRollover()) {
                g2d.setColor(currentColor);
            } else {
                g2d.setColor(currentColor);
            }

            int arcSize = 30;
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcSize, arcSize);

            // Set text color and font
            g2d.setColor(new Color(0, 0, 0));
            g2d.setFont(new Font("Arial", Font.BOLD, 15));
            super.paintComponent(g);
        }
    }
}
