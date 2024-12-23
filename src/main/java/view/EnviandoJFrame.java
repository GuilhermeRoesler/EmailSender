package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class EnviandoJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEnviando;

	public EnviandoJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		setResizable(false);

		lblEnviando = new JLabel("Enviando...");
		lblEnviando.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnviando.setBounds(0, 0, 314, 141);
		lblEnviando.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 20));
		contentPane.add(lblEnviando);
	}

}
