package hm.edu.life4alz.alexa.handler;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import hm.edu.life4alz.alexa.constants.PhrasesAndConstants;
import hm.edu.life4alz.alexa.constants.PhrasesAndConstantsAppointment;
import hm.edu.life4alz.model.appointmenttypes.Appointment;
import hm.edu.life4alz.model.appointmenttypes.AppointmentList;

public class ShowMyAppointmentsIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("ShowMyAppointmentsIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		AppointmentList appointmentList = new AppointmentList();
		String serializedAppointmentList;
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
		if (sessionAttributes != null) {

			serializedAppointmentList = (String) sessionAttributes.get(PhrasesAndConstants.APPOINTMENT_LIST_KEY);
			if (serializedAppointmentList != null) {

				appointmentList = (AppointmentList) AppointmentList.fromString(serializedAppointmentList);
			}
		}
		if (!appointmentList.isValid()) {

			Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
			if (persistentAttributes != null) {
				serializedAppointmentList = (String) persistentAttributes.get(PhrasesAndConstants.APPOINTMENT_LIST_KEY);
				if (serializedAppointmentList != null) {
					appointmentList = (AppointmentList) AppointmentList.fromString(serializedAppointmentList);

				}
			}
		}

		if (appointmentList.isValid()) {

			String speechText = String.format("%s ", PhrasesAndConstantsAppointment.START_SHOW_APPOINTMENTS);

			for (Appointment app : appointmentList.getList()) {
				speechText += String.format("%s ,", app.getToSpeakShort());
			}
			speechText += String.format("%s.", PhrasesAndConstantsAppointment.STOP_SHOW_APPOINTMENTS);

			responseBuilder.withSpeech(speechText).withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
					.withShouldEndSession(false);

		} else {
			responseBuilder.withSpeech(PhrasesAndConstantsAppointment.NO_APPOINTMENTS)
					.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstantsAppointment.NO_APPOINTMENTS)
					.withShouldEndSession(false);
		}
		return responseBuilder.build();
	}
}
