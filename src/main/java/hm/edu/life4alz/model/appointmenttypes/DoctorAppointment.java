package hm.edu.life4alz.model.appointmenttypes;

import java.util.List;

import hm.edu.life4alz.model.Information;

public class DoctorAppointment extends Appointment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String doctorName;

	public DoctorAppointment() {
		
	}
	
	public DoctorAppointment(final List<String> informationList, final String appointmentType, final String appointmentName,
			final String durationInMin, final String date, final String time, final String location, final boolean atHome, final String doctorName) {
		super(informationList, appointmentType, appointmentName, durationInMin, date, time, location, atHome);		
		
		this.doctorName = doctorName;
		this.addInformation(Information.KEYS.getTextToSpeak());
		this.addInformation(Information.WALLET.getTextToSpeak());
		this.addInformation(Information.MOBILEPHONE.getTextToSpeak());
		this.addInformation(Information.HEALTH_INSURANCE_CARD.getTextToSpeak());
	}



}
