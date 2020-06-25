package hm.edu.life4alz.alexa.handler;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import hm.edu.life4alz.alexa.constants.PhrasesAndConstants;
import hm.edu.life4alz.alexa.constants.PhrasesAndConstantsAppointment;
import hm.edu.life4alz.model.appointmenttypes.Appointment;
import hm.edu.life4alz.model.appointmenttypes.AppointmentList;

public class ShowAppointmentDetailIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {

		return input.matches(intentName("ShowAppointmentDetailIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		AppointmentList appointmentList = new AppointmentList();
		AppointmentList validAppList;
		String serializedAppointmentList;
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();

		// get slots
		Map<String, Slot> slots = intent.getSlots();
		Slot appointmentTypeSlot = slots.get(PhrasesAndConstantsAppointment.APPOINTMENTTYPE_SLOT);
		Slot dateSlot = slots.get(PhrasesAndConstantsAppointment.DATE_SLOT);

		// get list from sessionAttributes
		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
		if (sessionAttributes != null) {

			serializedAppointmentList = (String) sessionAttributes.get(PhrasesAndConstants.APPOINTMENT_LIST_KEY);
			if (serializedAppointmentList != null) {
				appointmentList = (AppointmentList) AppointmentList.fromString(serializedAppointmentList);

			}
		}

		// get list from persistentAttributes
		if (!appointmentList.isValid()) {
			Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
			if (persistentAttributes != null) {
				serializedAppointmentList = (String) persistentAttributes.get(PhrasesAndConstants.APPOINTMENT_LIST_KEY);
				if (serializedAppointmentList != null) {
					appointmentList = (AppointmentList) AppointmentList.fromString(serializedAppointmentList);
				}
			}
		}
		// filter list		
		validAppList = appointmentList.getFilteredList(appointmentTypeSlot.getValue(), dateSlot.getValue());

		// get speechText
		if (validAppList.isValid()) {
			String speechText;

			if (validAppList.getList().size() > 1) {
				speechText = String.format("%s %s %s", PhrasesAndConstantsAppointment.START_SHOW_APPOINTMENTS_NUMBER,
						validAppList.getList().size(), PhrasesAndConstantsAppointment.APPOINTMENTS);
				for (Appointment app : validAppList.getList()) {
					speechText += String.format("%s ", app.getToSpeakLong());
				}
				speechText += String.format("%s", PhrasesAndConstantsAppointment.STOP_SHOW_APPOINTMENTS);
			} else {
				speechText = String.format("%s ", PhrasesAndConstantsAppointment.START_SHOW_APPOINTMENT);
				for (Appointment app : validAppList.getList()) {
					speechText += String.format("%s ", app.getToSpeakLong());
					speechText += ".";
				}
			}
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
