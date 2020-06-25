package hm.edu.life4alz.model;

public enum Information {

	HEALTH_INSURANCE_CARD("Nimm deine Gesundheitskarte mit"), WALLET("Vergiss deinen Geldbeutel nicht"), KEYS("Du brauchst Schlüssel"), SHOPPING_BAG("Vergiss deine Einkaufstasche nicht"),
	GLASSES("Nimm deine Brille mit "), COFFEE_MACHINE("Du musst noch Kaffee machen"), MOBILEPHONE("Vergiss dein Mobiltelefon nicht");

	Information(final String textToSpeak) {
		this.textToSpeak = textToSpeak;
	}

	private String textToSpeak;

	public String getTextToSpeak() {
		return textToSpeak;
	}
}
