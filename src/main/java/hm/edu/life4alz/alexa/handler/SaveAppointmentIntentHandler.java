package hm.edu.life4alz.alexa.handler;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import hm.edu.life4alz.alexa.constants.PhrasesAndConstants;
import hm.edu.life4alz.alexa.constants.PhrasesAndConstantsAppointment;
import hm.edu.life4alz.model.appointmenttypes.Appointment;
import hm.edu.life4alz.model.appointmenttypes.AppointmentList;
import hm.edu.life4alz.model.appointmenttypes.AppointmentTypes;
import hm.edu.life4alz.model.appointmenttypes.FamilyDoctorAppointment;
import hm.edu.life4alz.model.appointmenttypes.OculistAppointment;
import hm.edu.life4alz.model.appointmenttypes.Shopping;

public class SaveAppointmentIntentHandler implements RequestHandler {

	// change in later build
	static final String DURATION_CONST = "0";

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SaveAppointmentIntent")) && (input.getAttributesManager().getSessionAttributes().get(PhrasesAndConstants.STATE_KEY).equals(PhrasesAndConstants.STATE_CREATE_APPOINTMENT_COMPL));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
		ResponseBuilder responseBuilder = input.getResponseBuilder();
		String state = (String) sessionAttributes.get(PhrasesAndConstants.STATE_KEY);
		String serializedList = (String) sessionAttributes.get(PhrasesAndConstants.APPOINTMENT_LIST_KEY);

		if (state.equals(PhrasesAndConstants.STATE_CREATE_APPOINTMENT_COMPL)) {

			AppointmentList appointmentList = (AppointmentList) AppointmentList.fromString(serializedList);
			Appointment appointment = null;
			String appointmentType = (String) sessionAttributes
					.get(PhrasesAndConstantsAppointment.APPOINTMENTTYPE_SLOT);
			String appointmentName = (String) sessionAttributes
					.get(PhrasesAndConstantsAppointment.APPOINTMENT_NAME_KEY);
			String time = (String) sessionAttributes.get(PhrasesAndConstantsAppointment.TIME_SLOT);
			String date = (String) sessionAttributes.get(PhrasesAndConstantsAppointment.DATE_SLOT);
			String duration = DURATION_CONST;
			String location = (String) sessionAttributes.get(PhrasesAndConstantsAppointment.LOCATION_KEY);
			String name = (String) sessionAttributes.get(PhrasesAndConstantsAppointment.NAME_SLOT);

			if (appointmentType.equalsIgnoreCase(AppointmentTypes.FAMILY_DOCTOR_APPOINTMENT.getName())) {

				appointment = new FamilyDoctorAppointment(new ArrayList<String>(), appointmentType, appointmentName,
						duration, date, time, location, false, name);

			} else if (appointmentType.equalsIgnoreCase(AppointmentTypes.OCULIST_APPOINTMENT.getName())) {

				appointment = new OculistAppointment(new ArrayList<String>(), appointmentType, appointmentName,
						duration, date, time, location, false, name);

			} else if (appointmentType.equalsIgnoreCase(AppointmentTypes.SHOPPING.getName())) {

				appointment = new Shopping(new ArrayList<String>(), appointmentType, appointmentName, duration, date,
						time, location, false);

			} else {
				// TODO
				// do nothing until i know what to do
			}

			// finally save appointment
			appointmentList.getList().add(appointment);
			String serializedAppointmentList = AppointmentList.toString(appointmentList);
			sessionAttributes.put(PhrasesAndConstants.APPOINTMENT_LIST_KEY, serializedAppointmentList);
			sessionAttributes.put(PhrasesAndConstants.STATE_KEY, PhrasesAndConstants.STATE_START);
			input.getAttributesManager().setSessionAttributes(sessionAttributes);
			input.getAttributesManager().getPersistentAttributes().put(PhrasesAndConstants.APPOINTMENT_LIST_KEY,
					serializedAppointmentList);
			input.getAttributesManager().savePersistentAttributes();

		} else {
			sessionAttributes.put(PhrasesAndConstants.STATE_KEY, PhrasesAndConstants.STATE_START);
			input.getAttributesManager().setSessionAttributes(sessionAttributes);
			return responseBuilder.withSpeech("Ich bin noch nicht bereit den Termin zu speichern.")
					.withSimpleCard(PhrasesAndConstants.CARD_TITLE, "Ich bin noch nicht bereit den Termin zu speichern")
					.build();

		}

		return responseBuilder.withSpeech(PhrasesAndConstantsAppointment.CREATE_APPOINTMENT_IS_COMPLETE).withSimpleCard(
				PhrasesAndConstants.CARD_TITLE, PhrasesAndConstantsAppointment.CREATE_APPOINTMENT_IS_COMPLETE).build();

	}
}
