package view;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import utils.Constants;
import utils.EmailSender;

public class PrincipalJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblBackground;
	private JTextField tfdSubject, tfdTo;
	private JTextArea taBody;
	private JButton btnSubmit;

	private int x = 70;
	private int y = 129;
	private int tfdWidth = 350;
	private int tfdHeight = 30;
	private int separadorY = 89;

	private Font font1 = new Font("Open Sans", Font.BOLD, 14);

	public PrincipalJFrame() {
		setTitle("Email Sender");
		setResizable(false);
		setIconImage(Constants.LOGO);

		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// tfdTo
		tfdTo = new JTextField();
		tfdTo.setBounds(x, y + separadorY * 0, tfdWidth, tfdHeight);
		tfdTo.setFont(font1);
		tfdTo.setOpaque(false);
		tfdTo.setBorder(null);
		contentPane.add(tfdTo);

		// tfdSubject
		tfdSubject = new JTextField();
		tfdSubject.setBounds(x, y + separadorY * 1, tfdWidth, tfdHeight);
		tfdSubject.setFont(font1);
		tfdSubject.setOpaque(false);
		tfdSubject.setBorder(null);
		contentPane.add(tfdSubject);

		// taBody
		taBody = new JTextArea();
		taBody.setBounds(x, y + separadorY * 2, tfdWidth, tfdHeight + 147);
		taBody.setFont(font1);
		taBody.setLineWrap(true);
		taBody.setWrapStyleWord(true);
		taBody.setBorder(tfdSubject.getBorder());
		taBody.setOpaque(false);
		taBody.setBorder(null);
		contentPane.add(taBody);

		// btnSubmit
		btnSubmit = new JButton();
		btnSubmit.setBounds(175, 518, 135, 42);
		btnSubmit.setBorder(null);
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(btnSubmit);

		// lblBackground
		lblBackground = new JLabel(Constants.TELA_PRINCIPAL);
		lblBackground.setBounds(0, 0, 484, 611);
		contentPane.add(lblBackground);

		// btnSubmitActionListener
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Main.frameEnviando.setVisible(true);
				try {
					EmailSender.sendEmail(tfdTo.getText(), tfdSubject.getText(), taBody.getText());
					JOptionPane.showMessageDialog(null, "Email enviado com sucesso!!!", "Sucesso",
							JOptionPane.PLAIN_MESSAGE);
					tfdSubject.setText(null);
					tfdTo.setText(null);
					taBody.setText(null);
					Main.frameEnviando.setVisible(false);
				} catch (AddressException e1) {
					JOptionPane.showMessageDialog(null,
							"Endereço de Email inválido\nPor favor, verifique e tente novamente!");
					Main.frameEnviando.setVisible(false);
				} catch (SendFailedException e1) {
					JOptionPane.showMessageDialog(null,
							"Endereço de Email inválido\nPor favor, verifique e tente novamente!");
					Main.frameEnviando.setVisible(false);
				} catch (MessagingException e1) {
					JOptionPane.showMessageDialog(null,
							"Houve algum erro durante o processo, por favor, troque as credenciais e tente novamente!");
					Main.frameEnviando.setVisible(false);
					e1.printStackTrace();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"Houve algum erro durante o processo, por favor, troque as credenciais e tente novamente!");
					Main.frameEnviando.setVisible(false);
				}
			}
		});

		Component[] fields = { tfdTo, tfdSubject, taBody };
		for (Component field : fields) {
			field.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						btnSubmit.doClick();
					}
				}
			});
		}
	}
}
