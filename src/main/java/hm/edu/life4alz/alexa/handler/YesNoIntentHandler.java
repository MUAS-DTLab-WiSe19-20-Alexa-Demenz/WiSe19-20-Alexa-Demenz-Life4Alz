package hm.edu.life4alz.alexa.handler;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import hm.edu.life4alz.alexa.constants.PhrasesAndConstants;
import hm.edu.life4alz.alexa.constants.PhrasesAndConstantsAppointment;
import hm.edu.life4alz.model.UserInfo;

public class YesNoIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return (input.matches(intentName("YesNoIntent")));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();
		Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		String state = (String) input.getRequestEnvelope().getSession().getAttributes()
				.get(PhrasesAndConstants.STATE_KEY);
		String answer = intent.getSlots().get(PhrasesAndConstants.YESNO_SLOT).getValue();

		switch (state) {
		case PhrasesAndConstants.STATE_DEFAULT_DOCTOR_QUESTION: {

			if (answer.equalsIgnoreCase(PhrasesAndConstants.YES)) {

				String appointmentType = (String) sessionAttributes
						.get(PhrasesAndConstantsAppointment.APPOINTMENTTYPE_SLOT);
				String doctorName = UserInfo.getDoctorNameByAppointmentType(sessionAttributes, appointmentType);
				String appointmentName = appointmentType + " bei " + doctorName;
				String doctorLocation = UserInfo.getDoctorLocationByAppointmentType(sessionAttributes, appointmentType);

				sessionAttributes.put(PhrasesAndConstantsAppointment.LOCATION_KEY, doctorLocation);
				sessionAttributes.put(PhrasesAndConstantsAppointment.APPOINTMENT_NAME_KEY, appointmentName);
				sessionAttributes.put(PhrasesAndConstantsAppointment.NAME_SLOT, doctorName);
				sessionAttributes.put(PhrasesAndConstants.STATE_KEY,
						PhrasesAndConstants.STATE_CREATE_APPOINTMENT_COMPL);
				input.getAttributesManager().setSessionAttributes(sessionAttributes);

				return new SaveAppointmentIntentHandler().handle(input);

			} else {
				sessionAttributes.put(PhrasesAndConstants.STATE_KEY,
						PhrasesAndConstants.STATE_CREATE_APPOINTMENT_UNCOMPL);
				responseBuilder.withSpeech("Okay").withSimpleCard(PhrasesAndConstants.CARD_TITLE, "Okay")
						.addDelegateDirective(Intent.builder().withName("CreateNameAndLocationIntent").build());
			}
			break;
		}
		case PhrasesAndConstants.STATE_DEFAULT_SHOPPING_QUESTION: {

			if (answer.equalsIgnoreCase(PhrasesAndConstants.YES)) {

				String appointmentName = "Einkaufstermin bei Aldi";
				String location = "Beerenstraße 3, München";

				sessionAttributes.put(PhrasesAndConstantsAppointment.APPOINTMENT_NAME_KEY, appointmentName);
				sessionAttributes.put(PhrasesAndConstantsAppointment.LOCATION_KEY, location);

				sessionAttributes.put(PhrasesAndConstants.STATE_KEY,
						PhrasesAndConstants.STATE_CREATE_APPOINTMENT_COMPL);
				input.getAttributesManager().setSessionAttributes(sessionAttributes);

				return new SaveAppointmentIntentHandler().handle(input);

			} else {
				responseBuilder.withSpeech("<amazon:effect name=\"whispered\">Das kann ich noch nicht</amazon:effect>")
						.withShouldEndSession(false).build();
			}
		}
			break;
		default:
			// do nothing
		}

		return responseBuilder.build();
	}
}
