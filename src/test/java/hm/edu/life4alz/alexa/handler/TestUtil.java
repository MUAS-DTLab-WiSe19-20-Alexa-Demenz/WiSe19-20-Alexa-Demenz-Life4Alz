package hm.edu.life4alz.alexa.handler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mockito.Mockito;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Context;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.User;
import com.amazon.ask.model.interfaces.system.SystemState;
import com.amazon.ask.model.slu.entityresolution.Resolution;
import com.amazon.ask.model.slu.entityresolution.Resolutions;
import com.amazon.ask.model.slu.entityresolution.Status;
import com.amazon.ask.model.slu.entityresolution.StatusCode;
import com.amazon.ask.response.ResponseBuilder;

import hm.edu.life4alz.alexa.constants.PhrasesAndConstants;
import hm.edu.life4alz.alexa.constants.PhrasesAndConstantsAppointment;
import hm.edu.life4alz.model.appointmenttypes.Appointment;
import hm.edu.life4alz.model.appointmenttypes.AppointmentList;
import hm.edu.life4alz.model.appointmenttypes.AppointmentTypes;
import hm.edu.life4alz.model.appointmenttypes.OculistAppointment;

public class TestUtil {

	public static HandlerInput mockHandlerInput(String appointmentType, String appointmentDate, String appointmentTime,
			String doctorType, String yesNo, String name, String street, String city,
			Map<String, Object> sessionAttributes, Map<String, Object> persistentAttributes,
			Map<String, Object> requestAttributes) {

		final AttributesManager attributesManagerMock = Mockito.mock(AttributesManager.class);
		// test
		attributesManagerMock.setSessionAttributes(sessionAttributes);
		attributesManagerMock.setPersistentAttributes(persistentAttributes);
		attributesManagerMock.setRequestAttributes(requestAttributes);

		when(attributesManagerMock.getSessionAttributes()).thenReturn(sessionAttributes);
		when(attributesManagerMock.getPersistentAttributes()).thenReturn(persistentAttributes);
		when(attributesManagerMock.getRequestAttributes()).thenReturn(requestAttributes);

		// Mock Slots
		Map<String, Slot> slots = new HashMap<>();

		List<Resolution> resolutionsPerAuthority = new ArrayList<>();
		resolutionsPerAuthority.add(Resolution.builder()
				.withStatus(Status.builder().withCode(StatusCode.ER_SUCCESS_MATCH).build()).build());
		Resolutions resolutions = Resolutions.builder().withResolutionsPerAuthority(resolutionsPerAuthority).build();

		if (appointmentType != null) {
			slots.put(PhrasesAndConstantsAppointment.APPOINTMENTTYPE_SLOT,
					Slot.builder().withName(PhrasesAndConstantsAppointment.APPOINTMENTTYPE_SLOT)
							.withValue(appointmentType).withResolutions(resolutions).build());
		}

		if (appointmentDate != null) {
			slots.put(PhrasesAndConstantsAppointment.DATE_SLOT,
					Slot.builder().withName(PhrasesAndConstantsAppointment.DATE_SLOT).withValue(appointmentDate)
							.withResolutions(resolutions).build());
		}
		if (appointmentTime != null) {
			slots.put(PhrasesAndConstantsAppointment.TIME_SLOT,
					Slot.builder().withName(PhrasesAndConstantsAppointment.TIME_SLOT).withValue(appointmentTime)
							.withResolutions(resolutions).build());
		}
		if (doctorType != null) {
			slots.put(PhrasesAndConstantsAppointment.DOCTOR_SLOT,
					Slot.builder().withName(PhrasesAndConstantsAppointment.DOCTOR_SLOT).withValue(doctorType)
							.withResolutions(resolutions).build());
		}
		if (yesNo != null) {
			slots.put(PhrasesAndConstants.YESNO_SLOT, Slot.builder().withName(PhrasesAndConstantsAppointment.TIME_SLOT)
					.withValue(yesNo).withResolutions(resolutions).build());
		}
		if (name != null) {
			slots.put(PhrasesAndConstantsAppointment.NAME_SLOT,
					Slot.builder().withName(PhrasesAndConstantsAppointment.NAME_SLOT).withValue(name)
							.withResolutions(resolutions).build());
		}
		if (street != null) {
			slots.put(PhrasesAndConstantsAppointment.STREET_SLOT,
					Slot.builder().withName(PhrasesAndConstantsAppointment.STREET_SLOT).withValue(street)
							.withResolutions(resolutions).build());
		}
		if (city != null) {
			slots.put(PhrasesAndConstantsAppointment.CITY_SLOT,
					Slot.builder().withName(PhrasesAndConstantsAppointment.CITY_SLOT).withValue(city)
							.withResolutions(resolutions).build());
		}

		Context context = Context.builder()
				.withSystem(SystemState.builder().withUser(User.builder().withUserId("101").build()).build()).build();

		final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
				.withRequest(IntentRequest.builder().withIntent(Intent.builder().withSlots(slots).build()).build())
				.withContext(context).build();

		// Mock Handler input attributes
		final HandlerInput input = Mockito.mock(HandlerInput.class);
		when(input.getAttributesManager()).thenReturn(attributesManagerMock);
		when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
		when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);

		return input;
	}

	public static Response createAppointmentIntentTestForHandle(RequestHandler handler, String status) {
		final Map<String, Object> sessionAttributes = new HashMap<>();
		final Map<String, Object> persistentAttributes = new HashMap<>();

		sessionAttributes.put(PhrasesAndConstants.STATE_KEY, status);
		final HandlerInput inputMock = TestUtil.mockHandlerInput("Arzttermin", "06.06.20", "05:00:00", null, null, null,
				null, null, sessionAttributes, persistentAttributes, null);
		final Optional<Response> res = handler.handle(inputMock);

		assertTrue(res.isPresent());
		final Response response = res.get();

		// assertFalse(response.getShouldEndSession());
		assertNotEquals("Test", response.getReprompt());
		assertNotNull(response.getOutputSpeech());
		return response;
	}

	public static Response nameAndLocationTestForHandle(RequestHandler handler, String status) {
		final Map<String, Object> sessionAttributes = new HashMap<>();
		sessionAttributes.put(PhrasesAndConstants.STATE_KEY, status);

		final HandlerInput inputMock = TestUtil.mockHandlerInput(null, null, null, null, null, "Leo", "Leostraße",
				"München", sessionAttributes, null, null);
		final Optional<Response> res = handler.handle(inputMock);

		assertTrue(res.isPresent());
		final Response response = res.get();

		assertNotEquals("Test", response.getReprompt());
		assertNotNull(response.getOutputSpeech());
		return response;
	}

	public static Response standardTestForHandle(RequestHandler handler) {
		final Map<String, Object> sessionAttributes = new HashMap<>();
		final Map<String, Object> persistentAttributes = new HashMap<>();

		final HandlerInput inputMock = TestUtil.mockHandlerInput("Arzttermin", "06.06.20", "05:00:00", null, null, null,
				null, null, sessionAttributes, persistentAttributes, null);
		final Optional<Response> res = handler.handle(inputMock);

		assertTrue(res.isPresent());
		final Response response = res.get();

		// assertFalse(response.getShouldEndSession());
		assertNotEquals("Test", response.getReprompt());
		assertNotNull(response.getOutputSpeech());
		return response;
	}

	public static Response sessionAttributesTestForHandle(RequestHandler handler) {
		final Map<String, Object> sessionAttributes = new HashMap<>();
		sessionAttributes.put(PhrasesAndConstantsAppointment.APPOINTMENTTYPE_SLOT, "Arzttermin");
		sessionAttributes.put(PhrasesAndConstantsAppointment.TIME_SLOT, "12.02.20");
		sessionAttributes.put(PhrasesAndConstantsAppointment.DATE_SLOT, "15:00:00");
		final HandlerInput inputMock = TestUtil.mockHandlerInput(null, null, null, null, null, null, null, null,
				sessionAttributes, null, null);
		final Optional<Response> res = handler.handle(inputMock);

		assertTrue(res.isPresent());
		final Response response = res.get();

		// assertTrue(response.getShouldEndSession());
		assertNotNull(response.getOutputSpeech());
		return response;
	}

	public static Response persistentAttributesTestForHandle(RequestHandler handler) {
		final Map<String, Object> persistentAttributes = new HashMap<>();
		persistentAttributes.put(PhrasesAndConstantsAppointment.APPOINTMENTTYPE_SLOT, "Arzttermin");
		persistentAttributes.put(PhrasesAndConstantsAppointment.TIME_SLOT, "12.02.22");
		final HandlerInput inputMock = TestUtil.mockHandlerInput(null, null, null, null, null, null, null, null, null,
				persistentAttributes, null);
		final Optional<Response> res = handler.handle(inputMock);

		assertTrue(res.isPresent());
		final Response response = res.get();

		assertNotNull(response.getOutputSpeech());
		return response;
	}

	public static Response noAttributesTestForHandle(RequestHandler handler) {
		final HandlerInput inputMock = TestUtil.mockHandlerInput(null, null, null, null, null, null, null, null, null,
				null, null);
		final Optional<Response> res = handler.handle(inputMock);

		assertTrue(res.isPresent());
		final Response response = res.get();

		assertFalse(response.getShouldEndSession());
		assertNotNull(response.getOutputSpeech());
		return response;
	}

	public static Response ShowMyAppointmentsIntentForHandle(RequestHandler handler, boolean appointments) {
		final Map<String, Object> sessionAttributes = new HashMap<>();

		AppointmentList appList = new AppointmentList();
		if (appointments) {
			Appointment app = new OculistAppointment(new ArrayList<>(), AppointmentTypes.OCULIST_APPOINTMENT.getName(),
					"Augenarzttermin bei Dr. X", null, "15.01.2020", "15:00", "dort", false, "Dr. X");
			appList.getList().add(app);
		} else {

		}
		sessionAttributes.put(PhrasesAndConstants.APPOINTMENT_LIST_KEY, AppointmentList.toString(appList));

		final HandlerInput inputMock = TestUtil.mockHandlerInput(null, null, null, null, null, null, null, null,
				sessionAttributes, null, null);
		final Optional<Response> res = handler.handle(inputMock);

		assertTrue(res.isPresent());
		final Response response = res.get();

		assertNotEquals("Test", response.getReprompt());
		assertNotNull(response.getOutputSpeech());
		return response;
	}

	public static Response ShowAppointmentDetailIntentForHandle(RequestHandler handler, String param) {
		final Map<String, Object> sessionAttributes = new HashMap<>();

		AppointmentList appList = new AppointmentList();
		Appointment app0 = new OculistAppointment(new ArrayList<>(), AppointmentTypes.OCULIST_APPOINTMENT.getName(),
				"Augenarzttermin bei Dr. X", null, "15.01.2020", "15:00", "dort", false, "Dr. X");

		Appointment app1 = new OculistAppointment(new ArrayList<>(),
				AppointmentTypes.FAMILY_DOCTOR_APPOINTMENT.getName(), "Hausarzttermin bei Dr. Z", null, "20.01.2020",
				"09:00", "dort", false, "Dr. Z");

		Appointment app2 = new OculistAppointment(new ArrayList<>(), AppointmentTypes.SHOPPING.getName(),
				"Shopping bei Aldi", null, "15.01.2020", "03:00", "aldi West", false, "Aldi");

		appList.getList().add(app0);
		appList.getList().add(app1);
		appList.getList().add(app2);

		sessionAttributes.put(PhrasesAndConstants.APPOINTMENT_LIST_KEY, AppointmentList.toString(appList));
		HandlerInput inputMock = null;

		if (param.equals("DATE")) {
			inputMock = TestUtil.mockHandlerInput("", "20.01.2020", null, null, null, null, null, null,
					sessionAttributes, null, null);
		} else if (param.equals("DOC")) {
			inputMock = TestUtil.mockHandlerInput(AppointmentTypes.DOCTOR_APPOINTMENT.getName(), "", null, null, null,
					null, null, null, sessionAttributes, null, null);
		} else if (param.equals("BOTH")) {
			inputMock = TestUtil.mockHandlerInput(AppointmentTypes.DOCTOR_APPOINTMENT.getName(), "20.01.2020", null,
					null, null, null, null, null, sessionAttributes, null, null);
		} else {
			inputMock = TestUtil.mockHandlerInput("", "", null, null, null, null, null, null, sessionAttributes, null,
					null);
		}
		Optional<Response> res = handler.handle(inputMock);

		assertTrue(res.isPresent());
		Response response = res.get();
		assertNotEquals("Test", response.getReprompt());
		assertNotNull(response.getOutputSpeech());
		return response;
	}

	public static Response sessionEndedTestForHandle(RequestHandler handler) {
		final HandlerInput inputMock = TestUtil.mockHandlerInput(null, null, null, null, null, null, null, null, null,
				null, null);
		final Optional<Response> res = handler.handle(inputMock);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getShouldEndSession());
		return response;
	}

}
