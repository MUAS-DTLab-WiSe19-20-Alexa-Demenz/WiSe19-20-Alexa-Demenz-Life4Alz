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

public class CreateNameAndLocationIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		AttributesManager attributesManager = input.getAttributesManager();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
		String state = (String) sessionAttributes.get(PhrasesAndConstants.STATE_KEY);
		return input.matches(intentName("CreateNameAndLocationIntent"))
				&& state.equalsIgnoreCase(PhrasesAndConstants.STATE_CREATE_APPOINTMENT_UNCOMPL);

	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slots = intent.getSlots();

		Slot nameSlot = slots.get(PhrasesAndConstantsAppointment.NAME_SLOT);
		Slot streetSlot = slots.get(PhrasesAndConstantsAppointment.STREET_SLOT);
		Slot citySlot = slots.get(PhrasesAndConstantsAppointment.CITY_SLOT);
		if (nameSlot.getValue() != null && streetSlot.getValue() != null && citySlot.getValue() != null) {

			AttributesManager attributesManager = input.getAttributesManager();
			Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();

			String appointmentType = (String) sessionAttributes
					.get(PhrasesAndConstantsAppointment.APPOINTMENTTYPE_SLOT);
			String appointmentName = appointmentType + " bei " + nameSlot.getValue();

			String location = streetSlot.getValue() + ", " + citySlot.getValue();

			sessionAttributes.put(PhrasesAndConstantsAppointment.NAME_SLOT, nameSlot.getValue());
			sessionAttributes.put(PhrasesAndConstantsAppointment.LOCATION_KEY, location);
			sessionAttributes.put(PhrasesAndConstantsAppointment.APPOINTMENT_NAME_KEY, appointmentName);

			sessionAttributes.put(PhrasesAndConstants.STATE_KEY, PhrasesAndConstants.STATE_CREATE_APPOINTMENT_COMPL);

			attributesManager.setSessionAttributes(sessionAttributes);

			if (new SaveAppointmentIntentHandler().canHandle(input)) {
				return new SaveAppointmentIntentHandler().handle(input);
			} else {
				return responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.ERROR)
						.withSpeech(PhrasesAndConstants.ERROR).build();
			}

		}

		return responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.ERROR)
				.withSpeech(PhrasesAndConstants.ERROR).build();
	}
}
