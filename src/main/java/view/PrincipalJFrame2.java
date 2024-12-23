package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import utils.Constants;
import utils.EmailSender;

public class PrincipalJFrame2 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private JTextField emailField, subjectField;
    private JTextArea messageArea;
    private JButton sendButton;
    private Color primaryColor = new Color(41, 128, 185);
    private Color backgroundColor = new Color(236, 240, 241);
    private Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font headerFont = new Font("Segoe UI", Font.BOLD, 24);

    public PrincipalJFrame2() {
        setupFrame();
        initializeComponents();
        setupLayout();
        addActionListeners();
    }

    private void setupFrame() {
        setTitle("Modern Email Sender");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(Constants.LOGO);
    }

    private void initializeComponents() {
        mainPanel = new JPanel();
        mainPanel.setBackground(backgroundColor);
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(backgroundColor);
        JLabel titleLabel = new JLabel("Compose Email");
        titleLabel.setFont(headerFont);
        titleLabel.setForeground(primaryColor);
        headerPanel.add(titleLabel);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Email Field
        emailField = createStyledTextField("Recipient Email");
        addFormComponent(formPanel, new JLabel("To:"), emailField, gbc, 0);

        // Subject Field
        subjectField = createStyledTextField("Subject");
        addFormComponent(formPanel, new JLabel("Subject:"), subjectField, gbc, 1);

        // Message Area
        messageArea = createStyledTextArea();
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setPreferredSize(new Dimension(0, 250));
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(scrollPane, gbc);

        // Send Button
        sendButton = createStyledButton("Send Email");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.add(sendButton);

        // Add all panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField(20);
        field.setFont(mainFont);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return field;
    }

    private JTextArea createStyledTextArea() {
        JTextArea area = new JTextArea();
        area.setFont(mainFont);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        return area;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(mainFont);
        button.setForeground(Color.WHITE);
        button.setBackground(primaryColor);
        button.setPreferredSize(new Dimension(150, 40));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void addFormComponent(JPanel panel, JLabel label, JComponent component, 
                                GridBagConstraints gbc, int row) {
        label.setFont(mainFont);
        label.setForeground(primaryColor);
        
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.9;
        panel.add(component, gbc);
    }

    private void addActionListeners() {
        sendButton.addActionListener(e -> sendEmail());

        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendEmail();
                }
            }
        };

        emailField.addKeyListener(enterKeyListener);
        subjectField.addKeyListener(enterKeyListener);
        messageArea.addKeyListener(enterKeyListener);
    }

    private void sendEmail() {
        try {
            EmailSender.sendEmail(emailField.getText(), subjectField.getText(), messageArea.getText());
            showSuccessMessage();
            clearFields();
        } catch (AddressException | SendFailedException e) {
            showErrorMessage("Invalid email address. Please verify and try again!");
        } catch (MessagingException e) {
            showErrorMessage("An error occurred. Please check your credentials and try again!");
            e.printStackTrace();
        } catch (Exception e) {
            showErrorMessage("An unexpected error occurred. Please try again!");
        }
    }

    private void showSuccessMessage() {
        JOptionPane.showMessageDialog(this,
            "Email sent successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        emailField.setText("");
        subjectField.setText("");
        messageArea.setText("");
    }

    private void setupLayout() {
        SwingUtilities.updateComponentTreeUI(this);
    }
}
