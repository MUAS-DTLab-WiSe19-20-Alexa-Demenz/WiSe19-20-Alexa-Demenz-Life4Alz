package hm.edu.life4alz.alexa.constants;

public class PhrasesAndConstants {

	private PhrasesAndConstants() {
		throw new IllegalStateException("Utility class");
	}

	public static final String CARD_TITLE = "Termin";

	public static final String WELCOME = "Hallo. Ich helfe dir deine Termine zu verwalten. Bitte sage mir zum Beispiel: Lege einen Arzttermin an. Oder sage meine Termine";
	public static final String ERROR = "Entschuldigung. Das hätte nicht passieren dürfen";
	public static final String HELP_REPROMPT = "Bitte sage mir Deinen Termin.";
	public static final String CANCEL_AND_STOP = "Auf Wiedersehen!";
	public static final String FALLBACK = "Tut mir leid, das weiss ich nicht. Sage einfach Hilfe.";
	public static final String SAY_APPOINTMENT_REPROMPT = "Das habe ich nicht verstanden. Bitte sage mir Deinen Termin.";

	public static final String GOOD_BYE = "Auf Wiedersehen.";

	public static final String STATE_KEY = "State";
	public static final String STATE_START = "_START";
	public static final String STATE_CREATE_APPOINTMENT_UNCOMPL = "_CREATE_APPOINTMENT_UNCOMPLETE";
	public static final String STATE_CREATE_APPOINTMENT_COMPL = "_CREATE_APPOINTMENT_COMPLETE";
	public static final String STATE_DEFAULT_DOCTOR_QUESTION = "_DEFAULT_DOCTOR_QUESTION";
	public static final String STATE_DEFAULT_SHOPPING_QUESTION = "_DEFAULT_SHOPPING_QUESTION";

	
	public static final String STATE_SHOW_APPOINTMENT = "_SHOW_APPOINTMENT";

	public static final String YESNO_SLOT = "YesNo";
	public static final String NO = "Nein";
	public static final String YES = "Ja";

	public static final String APPOINTMENT_LIST_KEY = "AppointmentList";

	public static final String REMIND_ME_OF_THE_FOLLOWING_ITEMS = "Ich soll dich an Folgendes erinnern:";
	public static final String HELP = "<voice name=\"Russell\">" + "Help, I need somebody\n"
			+ "Help, not just anybody\n" + "Help, you know I need someone, help\n"
			+ "When I was younger, so much younger than today\n" + "I never needed anybody's help in any way\n"
			+ "But now these days are gone, I'm not so self assured\n"
			+ "Now I find I've changed my mind and opened up the doors\n" + "Help me if you can, I'm feeling down\n"
			+ "And I do appreciate you being round\n" + "Help me get my feet back on the ground\n"
			+ "Won't you please, please help me\n" + "And now my life has changed in oh so many ways\n"
			+ "My independence seems to vanish in the haze\n" + "But every now and then I feel so insecure\n"
			+ "I know that I just need you like I've never done before\n" + "Help me if you can, I'm feeling down\n"
			+ "And I do appreciate you being round\n" + "Help me get my feet back on the ground\n"
			+ "Won't you please, please help me\n" + "When I was younger, so much younger than today\n"
			+ "I never needed anybody's help in any way\n" + "But now these days are gone, I'm not so self assured\n"
			+ "Now I find I've changed my mind and opened up the doors\n" + "Help me if you can, I'm feeling down\n"
			+ "And I do appreciate you being round\n" + "Help me get my feet back on the ground\n</amazon:effect>"
			+ "Won't you please, please help me, help me, help me, oh." + " </voice>";

}
