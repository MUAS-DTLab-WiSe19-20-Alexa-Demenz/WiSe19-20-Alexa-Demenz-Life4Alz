package hm.edu.life4alz.model.appointmenttypes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AppointmentList implements Serializable {

	private static final long serialVersionUID = -6532645128280334376L;
	private List<Appointment> list;

	public AppointmentList() {
		list = new ArrayList<>();
	}

	public boolean isValid() {
		if (list.isEmpty()) {
			return false;
		}
		return list.size() > 0;
	}

	public List<Appointment> getList() {
		return list;
	}

	/*
	 * Read the object from Base64 string. source:
	 * https://stackoverflow.com/questions/134492/how-to-serialize-an-object-into-a-
	 * string
	 */
	public static Object fromString(String s) {
		byte[] data = Base64.getDecoder().decode(s);
		ObjectInputStream ois;
		Object o = null;
		try {
			ois = new ObjectInputStream(new ByteArrayInputStream(data));
			o = ois.readObject();
			ois.close();
		} catch (ClassNotFoundException | IOException e) {
			Logger logger = Logger.getLogger(AppointmentList.class.getName());
		    logger.log(Level.INFO, e.toString());
		}
		return o;
	}

	/*
	 * Write the object to a Base64 string.
	 */
	public static String toString(Serializable o) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			oos.close();
		} catch (IOException e) {
			Logger logger = Logger.getLogger(AppointmentList.class.getName());
		    logger.log(Level.INFO, e.toString());
		}
		return Base64.getEncoder().encodeToString(baos.toByteArray());
	}

	public AppointmentList getFilteredList(String appointmentType, String date) {
		AppointmentList filteredList = new AppointmentList();

		for (Appointment app : list) {
			if (appointmentType != null && !appointmentType.equals("")) {
				if (appointmentType.equals(AppointmentTypes.DOCTOR_APPOINTMENT.getName())) {
					
					if (app.getAppointmentType().equals(AppointmentTypes.DOCTOR_APPOINTMENT.getName())
							|| app.getAppointmentType().equals(AppointmentTypes.FAMILY_DOCTOR_APPOINTMENT.getName())
							|| app.getAppointmentType().equals(AppointmentTypes.OCULIST_APPOINTMENT.getName())) {
						if (date != null && !date.equals("")) {
							if (app.getDate().equals(date)) {
								filteredList.getList().add(app);
							}
						} else {
							filteredList.getList().add(app);
						}
					}
				} else if (app.getAppointmentType().equals(appointmentType)) {
					if (date != null && !date.equals("")) {
						if (app.getDate().equals(date)) {
							filteredList.getList().add(app);
						}
					} else {
						filteredList.getList().add(app);
					}

				}
			} else if (date != null && !date.equals("")) {
				if (app.getDate().equals(date)) {
					filteredList.getList().add(app);
				}
			}
		}
		return filteredList;
	}
}
