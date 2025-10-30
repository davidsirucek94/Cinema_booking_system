package Cinema.Food_ordering_system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Cinema.UserInputMethods;

public class PaymentSystem {

	private List<Integer> knownMoneyValues = List.of(1, 2, 5, 10, 20, 50, 100, 200, 500, 1000);

	public void initializePayment(Scanner scanner, double finalPrice) {
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

		switch (method) {
		case CASH:
			List<String> stringMoneyValues = new ArrayList<>();
			for (int value : knownMoneyValues) {
				String stringValue = String.valueOf(value);
				stringMoneyValues.add(stringValue);
			}
			int enteredMoney = Integer
					.parseInt(UserInputMethods.getStringChoice(scanner, "Please enter money.", stringMoneyValues));
			boolean isSufficientAmount = false;
			double moneyToReturn = 0;
			Map<Integer, Integer> notesToReturn = new HashMap<>();
			do {
				if (enteredMoney < finalPrice) {
					double diff = finalPrice - enteredMoney;
					enteredMoney += Integer.parseInt(UserInputMethods.getStringChoice(scanner,
							String.format("Insufficient ammount. Please enter %.2f Czk more.", diff),
							stringMoneyValues));
					isSufficientAmount = false;
				} else {
					isSufficientAmount = true;
					double diff = enteredMoney - finalPrice;
					moneyToReturn = diff;
					for (int i = knownMoneyValues.size() - 1; i >= 0; i--) {
						if (diff < knownMoneyValues.get(i)) {
							continue;
						}

						int div = (int) (diff / knownMoneyValues.get(i));
						notesToReturn.put(knownMoneyValues.get(i), div);
						diff = diff - (div * knownMoneyValues.get(i));
					}
				}
			} while (isSufficientAmount == false);
			System.out.printf("Money to return %.2f Czk.\n", moneyToReturn);
			String returnMoney = "";
			for (Map.Entry<Integer, Integer> entry : notesToReturn.entrySet()) {
				returnMoney = returnMoney + String.format("%d x %d Czk, ", entry.getValue(), entry.getKey());
			}
			/*for (int i = 0; i < knownMoneyValues.size(); i++) {
				if (notesToReturn.containsKey(knownMoneyValues.get(i))) {
					returnMoney = returnMoney + String.format("%d x %d Czk, ",
							notesToReturn.get(knownMoneyValues.get(i)), knownMoneyValues.get(i));
				}
			}*/
			System.out.println("Notes to return: " + returnMoney);
			break;

		case CARD:
			throw new RuntimeException("not implemented");
		}

	}

}
