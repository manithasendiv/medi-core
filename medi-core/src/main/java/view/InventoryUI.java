package view;

import controller.InventoryController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class InventoryUI {
    private JPanel panel1;
    private JPanel Header;
    private JPanel HeaderContent;
    private JPanel InventoryView;
    private JPanel AddData;
    private JTable Inventory;
    private JTextField textMedicine;
    private JTextField textQty;
    private JTextField textPrice;
    private JButton addItemButton;
    private JTextField textThreshold;

    InventoryController inventoryController;

    public InventoryUI() {
        int id = 0;
        updateInventoryTable();
        textMedicine.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textMedicine.getText().equals("Medicine Name"))
                {
                    textMedicine.setText("");
                }
            }
        });
        textMedicine.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(textMedicine.getText().isEmpty())
                {
                    textMedicine.setText("Medicine Name");
                }
            }
        });

        textQty.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textQty.getText().equals("Quantity"))
                {
                    textQty.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(textQty.getText().isEmpty())
                {
                    textQty.setText("Quantity");
                }
            }
        });

        textThreshold.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textThreshold.getText().equals("Threshold"))
                {
                    textThreshold.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(textThreshold.getText().isEmpty())
                {
                    textThreshold.setText("Threshold");
                }
            }
        });

        textPrice.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textPrice.getText().equals("Price"))
                {
                    textPrice.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(textPrice.getText().isEmpty())
                {
                    textPrice.setText("Price");
                }
            }
        });
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mid = id + 1;
                String name = textMedicine.getText();
                String qty = textQty.getText();
                String threshold = textThreshold.getText();
                String price = textPrice.getText();
                inventoryController = new InventoryController();
                model.Inventory inventory = inventoryController.addInventoryToDB(mid, name, Integer.parseInt(qty), Integer.parseInt(threshold), Double.parseDouble(price));
                if(inventoryController.addInventoryToDB()){
                    JOptionPane.showMessageDialog(panel1, "Data added successfully");
                    updateInventoryTable();
                } else {
                    JOptionPane.showMessageDialog(panel1, "Error in adding data");
                }
                textMedicine.setText("Medicine Name");
                textQty.setText("Quantity");
                textThreshold.setText("Threshold");
                textPrice.setText("Price");
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("InventoryUI");
        frame.setContentPane(new InventoryUI().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }

    public void updateInventoryTable() {
        inventoryController = new InventoryController();
        try{
            ResultSet resultSet = inventoryController.getInventoryDetails();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();
            System.out.println(columns);

            DefaultTableModel model = (DefaultTableModel) Inventory.getModel();
            model.setRowCount(0);

            model.addRow(new Object[]{"ID", "Medicine", "Quantity", "Threshold" ,"Price"});
            model.addRow(new Object[]{resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)});

            while (resultSet.next()) {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(resultSet.getObject(i));
                }
                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(panel1, "Error in fetching data");
            throw new RuntimeException(e);
        }
    }

    private void createUIComponents() {
        Border emptyBorder = BorderFactory.createLineBorder(Color.WHITE);
        addItemButton = new JButton();
        addItemButton.setBorder(emptyBorder);
        addItemButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        HeaderContent = new JPanel();
        HeaderContent.setBackground(new Color(0, 0, 0, 0));
        HeaderContent.setOpaque(false);
        Header = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {

                    Paint p = new GradientPaint(0.0f, 0.0f, new Color(0, 147, 255, 255), getWidth(), getHeight(), new Color(9, 64, 121,255 ), true);

                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    super.paintComponent(g);
                }
            }
        };

        DefaultTableModel model = new DefaultTableModel();
        Inventory = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Quantity");
        model.addColumn("Threshold");
        model.addColumn("Price");

        class RoundedJTextField extends JTextField {
            private Shape shape;
            public RoundedJTextField(int size) {
                super(size);
                setBounds(520, 284, 190, 25);
                setBackground(new Color(0, 0, 0, 0));
                setOpaque(false);
                setMargin(new Insets(5, 20, 5, 20));
            }
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 0));
                g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                super.paintComponent(g);
            }
            protected void paintBorder(Graphics g) {
                g.setColor(new Color(165, 165, 165, 255));
                g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            }
            public boolean contains(int x, int y) {
                if (shape == null || !shape.getBounds().equals(getBounds())) {
                    shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                }
                return shape.contains(x, y);
            }
        }

        textMedicine = new RoundedJTextField(20);
        textMedicine.setText("Medicine Name");
        textPrice = new RoundedJTextField(20);
        textPrice.setText("Price");
        textQty = new RoundedJTextField(20);
        textQty.setText("Quantity");
        textThreshold = new RoundedJTextField(20);
        textThreshold.setText("Threshold");
    }
}
