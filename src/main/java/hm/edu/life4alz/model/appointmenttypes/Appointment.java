package hm.edu.life4alz.model.appointmenttypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hm.edu.life4alz.alexa.constants.PhrasesAndConstantsAppointment;

public class Appointment implements Serializable {

	/**
	 * Version 1
	 */
	private static final long serialVersionUID = -3518779460283070054L;
	private List<String> informationList;

	private String appointmentType;
	private String appointmentName;
	private String durationInMin;
	private String date;
	private String time;
	private String location;
	private boolean atHome;

	public Appointment() {
		informationList = new ArrayList<>();
	}

	public Appointment(List<String> informationList, String appointmentType, String appointmentName,
			String durationInMin, String date, String time, String location, boolean atHome) {
		super();
		this.informationList = informationList;
		this.appointmentType = appointmentType;
		this.appointmentName = appointmentName;
		this.durationInMin = durationInMin;
		this.date = date;
		this.time = time;
		this.location = location;
		this.atHome = atHome;
	}

	public boolean isValid() {
		return (date != null && appointmentName != null);
	}

	public void addInformation(String information) {
		this.informationList.add(information);
	}

	public List<String> getInformationList() {
		return informationList;
	}

	public void setInformationList(List<String> informationList) {
		this.informationList = informationList;
	}

	public String getAppointmentName() {
		return appointmentName;
	}

	public void setAppointmentName(String appointmentName) {
		this.appointmentName = appointmentName;
	}

	public String getDurationInMin() {
		return durationInMin;
	}

	public void setDurationInMin(String durationInMin) {
		this.durationInMin = durationInMin;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isAtHome() {
		return atHome;
	}

	public void setAtHome(boolean atHome) {
		this.atHome = atHome;
	}

	public String getToSpeakShort() {
		return String.format(PhrasesAndConstantsAppointment.APPOINTMENT_TO_SPEAK_SHORT +" <break time=\"0.5s\" /> ", appointmentName, date, getTime());
	}

	public String getToSpeakLong() {
		String s = String.format(PhrasesAndConstantsAppointment.APPOINTMENT_TO_SPEAK_SHORT +" <break time=\"0.5s\" /> ", appointmentName, date, getTime()) +". ";
		s += String.format("%s ", PhrasesAndConstantsAppointment.REMINDER);
		for (String info : informationList) {
			s += info + ", <break time=\"0.5s\" /> ";
		}
		return s;
	}
	
	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
