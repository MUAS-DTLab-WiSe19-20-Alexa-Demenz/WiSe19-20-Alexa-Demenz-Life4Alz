package hm.edu.life4alz.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.amazon.ask.model.Slot;

import hm.edu.life4alz.model.appointmenttypes.AppointmentTypes;

public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Map of DefaultDoctors

	private String userName;
	private String userAddress;
	private String oculistName;
	private String oculistAddress;
	private String familyDoctorName;
	private String familyDoctorAddress;

	public UserInfo() {

		userName = "Bert";
		userAddress = "Lothstraße 13, München";
		oculistName = "Dr. Strange";
		oculistAddress = "Waldstraße 12, Taufkirchen";
		familyDoctorName = "Dr. Frankenstein";
		familyDoctorAddress = "Rennstraße 6, München";

	}

	public UserInfo(String userName, String userAddress, String oculistName, String oculistAddress,
			String familyDoctorName, String familyDoctorAddress) {
		super();
		this.userName = userName;
		this.userAddress = userAddress;
		this.oculistName = oculistName;
		this.oculistAddress = oculistAddress;
		this.familyDoctorName = familyDoctorName;
		this.familyDoctorAddress = familyDoctorAddress;
	}

	public void addUserInfoToSessionAttributes(Map<String, Object> sessionAttributes) {
		sessionAttributes.put("UserInfo", this);
	}

	private static LinkedHashMap<String, Object> getUserInfoFromSessionAttributes(
			Map<String, Object> sessionAttributes) {
		return (LinkedHashMap<String, Object>) sessionAttributes.get("UserInfo");
	}

	public static String getDoctorNameString(Map<String, Object> sessionAttributes, Slot doctorSlot) {

		LinkedHashMap<String, Object> userInfo = getUserInfoFromSessionAttributes(sessionAttributes);
		String doctorType = doctorSlot.getResolutions().getResolutionsPerAuthority().get(0).getValues().get(0)
				.getValue().getName();

		switch (doctorType) {
		case "Oculist":
			return (String) userInfo.get("oculistName");
		case "FamilyDoctor":
			return (String) userInfo.get("familyDoctorName");
		default:
			return "";
		}

	}

	public static String getDoctorLocationByAppointmentType(Map<String, Object> sessionAttributes,
			String appointmentType) {
		LinkedHashMap<String, Object> userInfo = getUserInfoFromSessionAttributes(sessionAttributes);

		if (appointmentType.equalsIgnoreCase(AppointmentTypes.FAMILY_DOCTOR_APPOINTMENT.getName())) {
			return (String) userInfo.get("familyDoctorAddress");
		} else if (appointmentType.equalsIgnoreCase(AppointmentTypes.OCULIST_APPOINTMENT.getName())) {
			return (String) userInfo.get("oculistAddress");
		} else {
			return "";
		}
	}

	public static String getDoctorNameByAppointmentType(Map<String, Object> sessionAttributes, String appointmentType) {
		LinkedHashMap<String, Object> userInfo = getUserInfoFromSessionAttributes(sessionAttributes);

		if (appointmentType.equalsIgnoreCase(AppointmentTypes.FAMILY_DOCTOR_APPOINTMENT.getName())) {
			return (String) userInfo.get("familyDoctorName");
		} else if (appointmentType.equalsIgnoreCase(AppointmentTypes.OCULIST_APPOINTMENT.getName())) {
			return (String) userInfo.get("oculistName");
		} else {
			return "";
		}
	}

	public static String getAppointmentTypeString(Slot doctorSlot) {
		String doctorType = doctorSlot.getResolutions().getResolutionsPerAuthority().get(0).getValues().get(0)
				.getValue().getName();

		switch (doctorType) {
		case "Oculist":
			return "Augenarzttermin";
		case "FamilyDoctor":
			return "Hausarzttermin";
		default:
			return "Arzttermin";
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public String getOculistName() {
		return oculistName;
	}

	public String getOculistAddress() {
		return oculistAddress;
	}

	public String getFamilyDoctorName() {
		return familyDoctorName;
	}

	public String getFamilyDoctorAddress() {
		return familyDoctorAddress;
	}

}
