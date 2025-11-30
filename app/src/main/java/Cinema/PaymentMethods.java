package Cinema;

import java.util.HashSet;
import java.util.Set;

public enum PaymentMethods {

	CASH("C"), CARD("K"); //tímto volám v enumu ten spodní konstruktor = vytvářím instance - to co je v závorce je ten inputKey
	
	public final String inputKey;
	private Set<String> takenKeys = new HashSet<>();
	
	private PaymentMethods(String inputKey) {
		if (takenKeys.contains(inputKey)) {
			throw new IllegalArgumentException(String.format("The key '%s' is already taken!", inputKey));
		}
		
		this.inputKey = inputKey;
		
		takenKeys.add(inputKey);
	}
	// NEFUNGUJE UNIKÁTNOST SYMBOLŮ, DODĚLAT
}
