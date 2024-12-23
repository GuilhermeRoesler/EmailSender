package view;

public class Main {

	public static final PrincipalJFrame2 framePrincipal = new PrincipalJFrame2();
	public static final EnviandoJFrame frameEnviando = new EnviandoJFrame();

	public static void main(String[] args) {

		try {
			framePrincipal.setLocationRelativeTo(null);
			frameEnviando.setLocationRelativeTo(null);
			framePrincipal.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
