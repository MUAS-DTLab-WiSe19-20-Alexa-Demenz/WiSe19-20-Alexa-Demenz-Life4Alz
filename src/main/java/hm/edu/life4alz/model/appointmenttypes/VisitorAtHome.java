package hm.edu.life4alz.model.appointmenttypes;

import java.util.List;

import hm.edu.life4alz.model.Information;

public class VisitorAtHome extends Appointment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String visitor;
	String visitortype;
	
	public VisitorAtHome(List<String> informationList, String appointmentType, String appointmentName, String durationInMin,
			String date, String time, String location, boolean atHome, String visitor, String visitorGroup, boolean makeCoffee) {
		super(informationList, appointmentType, appointmentName, durationInMin, date, time, location, atHome);
		this.visitor = visitor;
		this.visitortype = visitor;
		
		if( makeCoffee) {
			this.addInformation(Information.COFFEE_MACHINE.getTextToSpeak());
		}
	}

	public String getVisitor() {
		return visitor;
	}

	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}

	public String getVisitortype() {
		return visitortype;
	}

	public void setVisitortype(String visitortype) {
		this.visitortype = visitortype;
	}

}
