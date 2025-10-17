package Cinema.Food_ordering_system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Cinema.UserInputMethods;

public class PaymentSystem {

	private List<Double> knownMoneyValues = List.of(1.0, 2.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0, 500.0, 1000.0);

	public void initializePayment(Scanner scanner) {
		List<String> options = new ArrayList<>();
		Map<String, PaymentMethods> keyToMethod = new HashMap<>();
		for (int i = 0; i < PaymentMethods.values().length; i++) {
			PaymentMethods method = PaymentMethods.values()[i];
			options.add(method.inputKey);
			System.out.printf("For %s press %s\n", method, method.inputKey);
			keyToMethod.put(method.inputKey, method);
		}
		String choice = UserInputMethods.getStringChoice(scanner, "Type your choice:", options);
		
		PaymentMethods method = keyToMethod.get(choice);
		/*
		for (int i = 0; i < PaymentMethods.values().length; i++) {
			if (PaymentMethods.values()[i].inputKey.equals(choice)) {
				method = PaymentMethods.values()[i];
			}
		}
		*/
		switch(method) {
		case CASH:
			break;
			
		case CARD:
			break;
		}
	}

}
