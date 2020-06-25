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
import hm.edu.life4alz.model.appointmenttypes.AppointmentTypes;

public class CreateAppointmentIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("CreateAppointmentIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();

		Map<String, Slot> slots = intent.getSlots();

		// get slots
		Slot appointmentTypeSlot = slots.get(PhrasesAndConstantsAppointment.APPOINTMENTTYPE_SLOT);
		Slot timeSlot = slots.get(PhrasesAndConstantsAppointment.TIME_SLOT);
		Slot dateSlot = slots.get(PhrasesAndConstantsAppointment.DATE_SLOT);

		ResponseBuilder responseBuilder = input.getResponseBuilder();

		if (appointmentTypeSlot.getValue() != null && timeSlot.getValue() != null && dateSlot.getValue() != null
				&& appointmentTypeSlot.getResolutions().toString().contains("ER_SUCCESS_MATCH")) {

			// store appointmentType and date in session attributes
			AttributesManager attributesManager = input.getAttributesManager();
			Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();

			sessionAttributes.put(PhrasesAndConstantsAppointment.APPOINTMENTTYPE_SLOT, appointmentTypeSlot.getValue());
			sessionAttributes.put(PhrasesAndConstantsAppointment.TIME_SLOT, timeSlot.getValue());
			sessionAttributes.put(PhrasesAndConstantsAppointment.DATE_SLOT, dateSlot.getValue());
			attributesManager.setSessionAttributes(sessionAttributes);

			// store persistent
			if (appointmentTypeSlot.getValue().equalsIgnoreCase(AppointmentTypes.DOCTOR_APPOINTMENT.getName())) {

				sessionAttributes.put(PhrasesAndConstants.STATE_KEY,
						PhrasesAndConstants.STATE_CREATE_APPOINTMENT_UNCOMPL);

				String speechText = PhrasesAndConstantsAppointment.SPECIFY_DOCTOR_APPOINTMENTTYPE;
				responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText)
						.withShouldEndSession(false);

			} else if (appointmentTypeSlot.getValue().equalsIgnoreCase(AppointmentTypes.SHOPPING.getName())) {
				String speechText = PhrasesAndConstantsAppointment.DEFAULT_SHOPPING_QUESTION;

				sessionAttributes.put(PhrasesAndConstants.STATE_KEY,
						PhrasesAndConstants.STATE_DEFAULT_SHOPPING_QUESTION);
				responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText)
						.withShouldEndSession(false);
			} else {
				String speechText = String.format("%s %s. %s", PhrasesAndConstantsAppointment.APPOINTMENT_IS,
						appointmentTypeSlot.getValue(), PhrasesAndConstantsAppointment.WHAT_IS_APPOINTMENT);
				responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText)
						.withShouldEndSession(false);
			}
		} else {
			// unknown appointment
			responseBuilder
					.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstantsAppointment.UNKNOWN_APPOINTMENT)
					.withSpeech(PhrasesAndConstantsAppointment.UNKNOWN_APPOINTMENT)
					.withReprompt(PhrasesAndConstantsAppointment.UNKNOWN_APPOINTMENT).withShouldEndSession(false);
		}
		return responseBuilder.build();
	}
}
