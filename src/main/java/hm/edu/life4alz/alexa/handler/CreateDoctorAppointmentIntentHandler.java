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
import hm.edu.life4alz.model.UserInfo;

public class CreateDoctorAppointmentIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.getRequestEnvelope().getRequest().getType().equals("IntentRequest")
				&& input.matches(intentName("CreateDoctorAppointmentIntent"));

	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		Slot doctorSlot = slots.get(PhrasesAndConstantsAppointment.DOCTOR_SLOT);

		// override appointmentType
		if (doctorSlot != null && doctorSlot.getResolutions().toString().contains("ER_SUCCESS_MATCH")) {
			AttributesManager attributesManager = input.getAttributesManager();
			Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();

			// override appointment type
			String doctorName = UserInfo.getDoctorNameString(sessionAttributes, doctorSlot);
			String appointmentType = UserInfo.getAppointmentTypeString(doctorSlot);

			sessionAttributes.put(PhrasesAndConstantsAppointment.APPOINTMENTTYPE_SLOT, appointmentType);
			sessionAttributes.put(PhrasesAndConstants.STATE_KEY, PhrasesAndConstants.STATE_DEFAULT_DOCTOR_QUESTION);
			attributesManager.setSessionAttributes(sessionAttributes);

			String speechText = String.format("%s %s %s %s", PhrasesAndConstantsAppointment.APPOINTMENT_IS,
					doctorSlot.getValue(), PhrasesAndConstantsAppointment.DEFAULT_DOCTOR_QUESTION, doctorName);

			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText).withSpeech(speechText)
					.withShouldEndSession(false);

		} else {
			responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SAY_APPOINTMENT_REPROMPT)
					.withSpeech(PhrasesAndConstants.SAY_APPOINTMENT_REPROMPT).withShouldEndSession(false);
		}
		return responseBuilder.build();
	}
}
