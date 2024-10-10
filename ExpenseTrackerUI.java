import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ExpenseTrackerUI {

    private Frame frame;
    private TextField descriptionField;
    private TextField amountField;
    private Choice categoryChoice;
    private TextArea expenseListArea;
    private TextArea incomeListArea;
    private Button addButton;
    private Button clearButton;
    
    private double totalExpenses = 0;
    private double totalIncome = 0;
    private Label expenseTotalLabel;
    private Label incomeTotalLabel;

    public ExpenseTrackerUI() {
        // Create frame
        frame = new Frame("Expense Tracker");
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());
        
        // Set Frame Background to Off-White (#EFE9E1)
        frame.setBackground(new Color(239, 233, 225));

        // Input Panel
        Panel inputPanel = new Panel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(new Color(209, 199, 189)); // Pale Beige (#D1C7BD)

        // Font definitions
        Font labelFont = new Font("Arial", Font.BOLD, 18);  // Larger and bold for labels
        Font buttonFont = new Font("Arial", Font.BOLD, 18);  // Larger and bold for buttons
        Font textAreaFont = new Font("Arial", Font.PLAIN,20);  // Larger text for text areas
        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);  // Larger font for TextField input
        Font headerFont = new Font("Arial", Font.BOLD, 22);  // Larger and bold for headers

        // Labels and TextFields
        Label categoryLabel = new Label("Category:");
        categoryLabel.setForeground(new Color(114, 56, 61)); // Dark Red (#72383D)
        categoryLabel.setFont(labelFont);  // Set bold font for the label
        
        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setForeground(new Color(114, 56, 61)); // Dark Red (#72383D)
        descriptionLabel.setFont(labelFont);  // Set bold font for the label
        
        Label amountLabel = new Label("Amount:");
        amountLabel.setForeground(new Color(114, 56, 61)); // Dark Red (#72383D)
        amountLabel.setFont(labelFont);  // Set bold font for the label

        // Input fields
        descriptionField = new TextField();
        descriptionField.setFont(textFieldFont);  // Set larger font size for text input
        descriptionField.setBackground(Color.WHITE); // White background for text field
        descriptionField.setForeground(Color.BLACK);
        
        amountField = new TextField();
        amountField.setFont(textFieldFont);  // Set larger font size for text input
        amountField.setBackground(Color.WHITE); // White background for text field
        amountField.setForeground(Color.BLACK);
        
        categoryChoice = new Choice();
        categoryChoice.add("Expense");
        categoryChoice.add("Income");
        categoryChoice.setFont(labelFont);  // Set larger font for choice dropdown
        categoryChoice.setBackground(Color.WHITE);  // White background for dropdown

        // Buttons
        addButton = new Button("Add Expense");
        addButton.setBackground(new Color(114, 56, 61)); // Dark Red (#72383D)
        addButton.setForeground(Color.WHITE); // White text
        addButton.setFont(buttonFont);  // Set larger bold font for button text
        
        clearButton = new Button("Clear All Entries");
        clearButton.setBackground(new Color(172, 156, 141)); // Soft Brown (#AC9C8D)
        clearButton.setForeground(Color.WHITE); // White text
        clearButton.setFont(buttonFont);  // Set larger bold font for button text

        // Add hover effect to Add Button
        addButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                addButton.setBackground(new Color(142, 70, 76)); // Lighter Dark Red on hover
            }
            public void mouseExited(MouseEvent me) {
                addButton.setBackground(new Color(114, 56, 61)); // Revert to Dark Red
            }
        });

        // Add hover effect to Clear Button
        clearButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                clearButton.setBackground(new Color(209, 199, 189)); // Pale Beige on hover
            }
            public void mouseExited(MouseEvent me) {
                clearButton.setBackground(new Color(172, 156, 141)); // Revert to Soft Brown
            }
        });

        // Add input components to the panel
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryChoice);
        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionField);
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        inputPanel.add(addButton);
        inputPanel.add(clearButton);

        // Two Text Areas for Expense and Income with totals
        Panel listPanel = new Panel(new GridLayout(1, 2, 10, 10));

        // Expense List Area
        expenseListArea = new TextArea();
        expenseListArea.setEditable(false);
        expenseListArea.setBackground(new Color(172, 156, 141)); // Soft Brown (#AC9C8D)
        expenseListArea.setForeground(Color.WHITE); // White text for entries
        expenseListArea.setFont(textAreaFont);  // Set larger font size for text area entries
        
        expenseTotalLabel = new Label("Total Expenses: $0.00");
        expenseTotalLabel.setFont(labelFont);
        expenseTotalLabel.setForeground(new Color(114, 56, 61)); // Dark Red (#72383D)

        // Income List Area
        incomeListArea = new TextArea();
        incomeListArea.setEditable(false);
        incomeListArea.setBackground(new Color(172, 156, 141)); // Soft Brown (#AC9C8D)
        incomeListArea.setForeground(Color.WHITE); // White text for entries
        incomeListArea.setFont(textAreaFont);  // Set larger font size for text area entries
        
        incomeTotalLabel = new Label("Total Income: $0.00");
        incomeTotalLabel.setFont(labelFont);
        incomeTotalLabel.setForeground(new Color(114, 56, 61)); // Dark Red (#72383D)

        // Adding components to panel
        Panel expensePanel = new Panel(new BorderLayout());
        expensePanel.setBackground(new Color(211, 211, 211));
	Label expenseHeader = new Label("Expenses");
        expenseHeader.setFont(headerFont); // Increase font size for header
        expensePanel.add(expenseHeader, BorderLayout.NORTH);
        expensePanel.add(expenseListArea, BorderLayout.CENTER);
        expensePanel.add(expenseTotalLabel, BorderLayout.SOUTH);

        Panel incomePanel = new Panel(new BorderLayout());
	incomePanel.setBackground(new Color(211, 211, 211));
        Label incomeHeader = new Label("Income");
        incomeHeader.setFont(headerFont); // Increase font size for header
        incomePanel.add(incomeHeader, BorderLayout.NORTH);
        incomePanel.add(incomeListArea, BorderLayout.CENTER);
        incomePanel.add(incomeTotalLabel, BorderLayout.SOUTH);

        listPanel.add(expensePanel);
        listPanel.add(incomePanel);

        // Add components to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(listPanel, BorderLayout.CENTER);

        // Add ActionListener for Add Button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String category = categoryChoice.getSelectedItem();
                String description = descriptionField.getText();
                String amountText = amountField.getText();
                if (!description.isEmpty() && !amountText.isEmpty()) {
                    double amount = Double.parseDouble(amountText);
                    if (category.equals("Expense")) {
                        expenseListArea.append(description + " - $" + amount + "\n");
                        totalExpenses += amount;
                        expenseTotalLabel.setText("Total Expenses: $" + totalExpenses);
                    } else if (category.equals("Income")) {
                        incomeListArea.append(description + " - $" + amount + "\n");
                        totalIncome += amount;
                        incomeTotalLabel.setText("Total Income: $" + totalIncome);
                    }
                    descriptionField.setText("");
                    amountField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid description and amount.");
                }
            }
        });

        // Add ActionListener for Clear Button
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                expenseListArea.setText("");
                incomeListArea.setText("");
                totalExpenses = 0;
                totalIncome = 0;
                expenseTotalLabel.setText("Total Expenses: $0.00");
                incomeTotalLabel.setText("Total Income: $0.00");
            }
        });

        // Add ItemListener to change button text based on category selected
        categoryChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (categoryChoice.getSelectedItem().equals("Expense")) {
                    addButton.setLabel("Add Expense");
                } else if (categoryChoice.getSelectedItem().equals("Income")) {
                    addButton.setLabel("Add Income");
                }
            }
        });

        // Close window action
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.dispose();
            }
        });

        // Show frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ExpenseTrackerUI();
    }
}
