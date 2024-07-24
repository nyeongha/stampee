import repository.CafeRepository;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");

		CafeRepository cafeRepository = new CafeRepository();

		// Sample data for testing
		long cafeId = 101L;
		String name = "Cafe Latte";
		String address = "123 Coffee Street";
		String password = "securepassword";
		String email = "contact@cafelatte.com";
		String contact = "010-1234-9998";

		// Call the userSignUp method
		cafeRepository.userSignUp(cafeId, name, address, password, email, contact);

		System.out.println("User signed up successfully!");
	}
}