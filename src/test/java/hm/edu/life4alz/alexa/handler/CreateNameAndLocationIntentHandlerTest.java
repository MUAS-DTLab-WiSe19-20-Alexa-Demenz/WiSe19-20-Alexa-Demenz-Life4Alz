package hm.edu.life4alz.alexa.handler;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import hm.edu.life4alz.alexa.constants.PhrasesAndConstants;
import hm.edu.life4alz.alexa.constants.PhrasesAndConstantsAppointment;

public class CreateNameAndLocationIntentHandlerTest {

	private CreateNameAndLocationIntentHandler handler;

	@Before
	public void setup() {
		handler = new CreateNameAndLocationIntentHandler();
	}



	@Test
	public void testHandle() {
		final Response response = TestUtil.nameAndLocationTestForHandle(handler,
				PhrasesAndConstants.STATE_CREATE_APPOINTMENT_COMPL);

		String speech = response.getOutputSpeech().toString();
		assertTrue(speech.contains(PhrasesAndConstants.ERROR));
	}

}
