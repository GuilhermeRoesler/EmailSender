package view;

public class Main {
	
	static final PrincipalJFrame framePrincipal = new PrincipalJFrame();
	
	public static void main(String[] args) {
		
		try {
			framePrincipal.setLocationRelativeTo(null);
			framePrincipal.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
