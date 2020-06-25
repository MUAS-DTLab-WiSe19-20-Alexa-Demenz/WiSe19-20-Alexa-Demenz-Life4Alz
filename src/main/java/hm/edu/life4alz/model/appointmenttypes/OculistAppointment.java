package hm.edu.life4alz.model.appointmenttypes;

import java.util.List;

import hm.edu.life4alz.model.Information;

public class OculistAppointment extends DoctorAppointment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OculistAppointment(List<String> informationList, final String appointmentType, final String appointmentName, final String durationInMin,
			final String date, final String time, final String location, final boolean atHome, final String name) {
		super(informationList, appointmentType, appointmentName, durationInMin, date, time, location, atHome, name);

		this.addInformation(Information.GLASSES.getTextToSpeak());
	
	}

	
}
