package hm.edu.life4alz.alexa.handler;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import hm.edu.life4alz.alexa.constants.PhrasesAndConstantsAppointment;

public class ShowMyAppointmentsIntentHandlerTest {
	private ShowMyAppointmentsIntentHandler handler;

	@Before
	public void setup() {
		handler = new ShowMyAppointmentsIntentHandler();
	}

	@Test
	public void testCanHandle() {
		final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
		when(inputMock.matches(any())).thenReturn(true);
		assertTrue(handler.canHandle(inputMock));
	}

	@Test
	public void testHandle1() {
		final Response response = TestUtil.ShowMyAppointmentsIntentForHandle(handler, false);
		
		String speech = response.getOutputSpeech().toString();
		assertTrue(speech.contains(PhrasesAndConstantsAppointment.NO_APPOINTMENTS));
	}

	@Test
	public void testHandle2() {
		final Response response = TestUtil.ShowMyAppointmentsIntentForHandle(handler, true);
		
		String speech = response.getOutputSpeech().toString();
		assertTrue(speech.contains("Du hast folgende Termine: Augenarzttermin bei Dr. X am 15.01.2020 um 15:00"));
	}
}
