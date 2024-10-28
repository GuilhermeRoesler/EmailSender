package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import utils.EmailSender;

public class PrincipalJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel1;
	private JLabel lblEmailSender, lblTo, lblSubject, lblBody;
	private JTextField tfdTo, tfdSubject;
	private JTextArea taBody;
	private JButton btnSubmit, btnExit;

	private int x = 40;
	private int tfdWidth = 484 - 80;
	private int tfdHeight = 30;
	private int separadorY = 25;
	private Font font = new Font("Open Sans", Font.BOLD, 17);
	private Font font2 = new Font("Open Sans", Font.BOLD, 14);

	public PrincipalJFrame() {
		setResizable(false);
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// panel1
		panel1 = new JPanel();
		panel1.setBounds(10, 11, 464, 49);
		contentPane.add(panel1);

		// lblEmailSender
		lblEmailSender = new JLabel("Email Sender");
		lblEmailSender.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailSender.setFont(new Font("Nimbus Sans", Font.BOLD, 30));
		panel1.add(lblEmailSender);

		// lblTo
		lblTo = new JLabel("To:");
		lblTo.setBounds(x, 107, 80, 21);
		lblTo.setFont(font);
		contentPane.add(lblTo);

		// tfdTo
		tfdTo = new JTextField();
		tfdTo.setBounds(x, lblTo.getY() + separadorY, tfdWidth, tfdHeight);
		tfdTo.setFont(font2);
		contentPane.add(tfdTo);

		// lblSubject
		lblSubject = new JLabel("Subject:");
		lblSubject.setFont(font);
		lblSubject.setBounds(x, 188, 80, 21);
		contentPane.add(lblSubject);

		// tfdSubject
		tfdSubject = new JTextField();
		tfdSubject.setBounds(x, lblSubject.getY() + separadorY, tfdWidth, tfdHeight);
		tfdSubject.setFont(font2);
		contentPane.add(tfdSubject);

		// lblBody
		lblBody = new JLabel("Body:");
		lblBody.setFont(font);
		lblBody.setBounds(40, 270, 80, 21);
		contentPane.add(lblBody);

		// taBody
		taBody = new JTextArea();
		taBody.setBounds(x, lblBody.getY() + separadorY, tfdWidth, 254);
		taBody.setFont(font2);
		taBody.setLineWrap(true);
		taBody.setWrapStyleWord(true);
		contentPane.add(taBody);

		// btnSubmit
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(385, 577, 89, 23);
		contentPane.add(btnSubmit);

		// btnExit
		btnExit = new JButton("Exit");
		btnExit.setBounds(10, 577, 89, 23);
		contentPane.add(btnExit);

		// btnExitActionListener
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});

		// btnSubmitActionListener
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmailSender.sendEmail(tfdTo.getText(), tfdSubject.getText(), taBody.getText());
				tfdTo.setText(null);
				tfdSubject.setText(null);
				taBody.setText(null);
			}
		});
	}
}
