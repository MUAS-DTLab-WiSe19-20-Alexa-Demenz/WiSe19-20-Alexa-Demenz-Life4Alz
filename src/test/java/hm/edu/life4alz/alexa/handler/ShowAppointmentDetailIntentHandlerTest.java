package hm.edu.life4alz.alexa.handler;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

public class ShowAppointmentDetailIntentHandlerTest {
	private ShowAppointmentDetailIntentHandler handler;

	@Before
	public void setup() {
		handler = new ShowAppointmentDetailIntentHandler();
	}

	@Test
	public void testCanHandle() {
		final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
		when(inputMock.matches(any())).thenReturn(true);
		assertTrue(handler.canHandle(inputMock));
	}

	@Test
	public void testHandle1() {
		final Response response = TestUtil.ShowAppointmentDetailIntentForHandle(handler, "DATE");
		
		String speech = response.getOutputSpeech().toString();
		assertTrue(speech.contains("Du hast einen  Hausarzttermin bei Dr. Z am 20.01.2020 um 09:00"));
	}

	@Test
	public void testHandle2() {
		final Response response = TestUtil.ShowAppointmentDetailIntentForHandle(handler, "DOC");
		
		String speech = response.getOutputSpeech().toString();
		assertTrue(speech.contains("Du hast 2 Termine:"));
		assertTrue(speech.contains("Augenarzttermin bei Dr. X am 15.01.2020"));
		assertTrue(speech.contains("Hausarzttermin bei Dr. Z am 20.01.2020 um 09:00"));
	}

	@Test
	public void testHandle3() {
		final Response response = TestUtil.ShowAppointmentDetailIntentForHandle(handler, "BOTH");
		
		String speech = response.getOutputSpeech().toString();
		assertTrue(speech.contains("Du hast einen  Hausarzttermin bei Dr. Z am 20.01.2020 um 09:00"));
	}
	@Test
	public void testHandle4() {
		final Response response = TestUtil.ShowAppointmentDetailIntentForHandle(handler, "Na");
		
		String speech = response.getOutputSpeech().toString();
		assertTrue(speech.contains("Du hast keine Termine"));
	}
}
