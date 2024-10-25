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
		
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("Para quem você gostaria de mandar o e-mail?");
//		String to = sc.next();
//		
//		System.out.println("Qual o assunto do e-mail?");
//		String subject = sc.next();
//		
//		System.out.println("Digite aqui a mensagem:");
//		String messageBody = sc.nextLine();
//		
//		EmailSender.sendEmail(to, subject, messageBody);
//
//		sc.close();
	}
}
