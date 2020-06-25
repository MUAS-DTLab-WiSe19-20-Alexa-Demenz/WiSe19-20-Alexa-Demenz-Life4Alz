package hm.edu.life4alz.model.appointmenttypes;

public enum AppointmentTypes {
	CUSTOM_APPOINTMETNT("Termin"), DOCTOR_APPOINTMENT("Arzttermin"), FAMILY_DOCTOR_APPOINTMENT("Hausarzttermin"),
	OCULIST_APPOINTMENT("Augenarzttermin"), VISITOR_AT_HOME("Besuch"), SHOPPING("Einkaufstermin");

	private String name;

	AppointmentTypes(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
