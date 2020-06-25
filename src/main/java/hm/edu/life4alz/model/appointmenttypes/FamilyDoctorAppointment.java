package hm.edu.life4alz.model.appointmenttypes;

import java.util.List;

public class FamilyDoctorAppointment extends DoctorAppointment {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FamilyDoctorAppointment(List<String> informationList, String appointmentType, String appointmentName,
			String durationInMin, String date, String time, String location, boolean atHome, String name) {
		super(informationList, appointmentType, appointmentName, durationInMin, date, time, location, atHome, name);
		// TODO Auto-generated constructor stub
	}

}
