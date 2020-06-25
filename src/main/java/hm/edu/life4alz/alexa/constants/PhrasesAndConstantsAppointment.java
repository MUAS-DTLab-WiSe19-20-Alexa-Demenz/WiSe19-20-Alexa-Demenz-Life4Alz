package hm.edu.life4alz.alexa.constants;

public class PhrasesAndConstantsAppointment {

	public static final String TIME_SLOT = "Time";

	public static final String APPOINTMENTTYPE_SLOT = "AppointmentType";

	public static final String DATE_SLOT = "Date";

	public static final String APPOINTMENT_NAME_KEY = "AppointmentName";
	
	public static final String DOCTOR_SLOT = "DoctorAppointmentType";
	
	public static final String NAME_SLOT = "Name";
	public static final String STREET_SLOT = "Street";
	public static final String CITY_SLOT = "City";
	
	public static final String DOCTOR_NAME_KEY = "DoctorName";
	public static final String LOCATION_KEY = "Location";
	public static final String FAMILY_DOCTOR = "Hausarzt";
	public static final String FAMILY_DOCTOR_KEY = "FamilyDoctor";
	public static final String OCULIST = "Augenarzt";
	public static final String OCULIST_KEY = "Oculist";
	
	public static final String UNKNOWN_DOCTOR = "Diesen Arzt kenne ich nicht.";
	public static final String UNKNOWN_APPOINTMENT = "Diesen Termin kenne ich nicht!";
	
	
	public static final String APPOINTMENT_IS = "Dein Termin ist ein ";
	public static final String APPOINTMENT_WHEN = "Er ist am ";
	public static final String WHAT_IS_DOCTOR_NAME = "Wie lautet der Name des Arztes?";
	public static final String WHAT_IS_APPOINTMENT = "Du kannst mich nach deinem Termin fragen. Frage einfach: Was habe ich für einen Termin?";
	public static final String APPOINTMENT_UNKNOWN = "Ich kenne diese Art von Termin nicht. Sage zum Beispiel: Lege mir einen Arzttermin an.";

	public static final String SPECIFY_DOCTOR_APPOINTMENTTYPE = "Um was für eine Art von Arzt handelt es sich? Sage zum Beispiel Augenarzt.";

	public static final String CREATE_APPOINTMENT_IS_COMPLETE = "Dein Termin wurde angelegt.";
	
	public static final String APPOINTMENT_TO_SPEAK_SHORT = "%s am %s um %s";
	public static final String START_SHOW_APPOINTMENTS = "Du hast folgende Termine:";
	public static final String STOP_SHOW_APPOINTMENTS = "Das waren alle deine Termine.";
	public static final String NO_APPOINTMENTS = "Du hast keine Termine.";

	public static final String START_SHOW_APPOINTMENT = "Du hast einen ";
	public static final String REMINDER = "Ich soll dich an folgendes erinnern: ";

	public static final String START_SHOW_APPOINTMENTS_NUMBER = "Du hast";

	public static final String APPOINTMENTS = "Termine: <break time=\\\"0.5s\\\" /> ";

	public static final String DEFAULT_DOCTOR_QUESTION = "Handelt es sich dabei um einen Termin bei deinen Stammarzt";
	public static final String DEFAULT_SHOPPING_QUESTION = "Handelt es sich um deinen normalen Supermarkt?";
	
	private PhrasesAndConstantsAppointment() {
		throw new IllegalStateException("Utility class");
	}
}