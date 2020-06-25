package hm.edu.life4alz.alexa.handler;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import hm.edu.life4alz.alexa.constants.PhrasesAndConstants;
import hm.edu.life4alz.alexa.constants.PhrasesAndConstantsAppointment;

public class CreateAppointmentIntentHandlerTest {
	private CreateAppointmentIntentHandler handler;

	@Before
	public void setup() {
		handler = new CreateAppointmentIntentHandler();
	}

	@Test
	public void testCanHandle() {
		final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
		when(inputMock.matches(any())).thenReturn(true);
		assertTrue(handler.canHandle(inputMock));
	}

	@Test
	public void testHandle() {
		final Response response = TestUtil.createAppointmentIntentTestForHandle(handler,
				PhrasesAndConstants.STATE_START);
//		System.out.println(response.getReprompt());
		String speech = response.getOutputSpeech().toString();
		assertTrue(speech.contains(PhrasesAndConstantsAppointment.SPECIFY_DOCTOR_APPOINTMENTTYPE));
	}
}
