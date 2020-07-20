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

public class HelpIntentHandlerTest {
    private HelpIntentHandler handler;

    @Before
    public void setup() {
        handler = new HelpIntentHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandle() {
        final Response response = TestUtil.standardTestForHandle(handler);
        assertTrue(!response.getOutputSpeech().toString().contains(PhrasesAndConstants.HELP));
    }
}
