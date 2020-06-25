package hm.edu.life4alz.model.appointmenttypes;

import java.util.List;

import hm.edu.life4alz.model.Information;

public class Shopping extends Appointment {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Shopping(List<String> informationList, String appointmentType, String appointmentName, String durationInMin, String date,
			String time, String location, boolean atHome) {
		super(informationList, appointmentType, appointmentName, durationInMin, date, time, location, atHome);
		
		this.addInformation(Information.SHOPPING_BAG.getTextToSpeak());
		this.addInformation(Information.WALLET.getTextToSpeak());
		this.addInformation(Information.KEYS.getTextToSpeak());
		this.addInformation(Information.MOBILEPHONE.getTextToSpeak());
	}

	
}
