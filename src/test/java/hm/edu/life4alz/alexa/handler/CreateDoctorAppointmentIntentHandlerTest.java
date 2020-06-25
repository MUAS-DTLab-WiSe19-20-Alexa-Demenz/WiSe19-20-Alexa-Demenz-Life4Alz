//package hm.edu.life4alz.alexa.handler;
//
//import static org.junit.Assert.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//import com.amazon.ask.dispatcher.request.handler.HandlerInput;
//import com.amazon.ask.model.Response;
//
//import hm.edu.life4alz.alexa.constants.PhrasesAndConstantsAppointment;
//
//public class CreateDoctorAppointmentIntentHandlerTest {
//    private CreateDoctorAppointmentIntentHandler handler;
//
//    @Before
//    public void setup() {
//        handler = new CreateDoctorAppointmentIntentHandler();
//    }
//
//    @Test
//    public void testCanHandle() {
//        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
//        when(inputMock.matches(any())).thenReturn(true);
//        assertTrue(handler.canHandle(inputMock));
//    }
//
//    @Test
//    public void testHandle() {
//        final Response response = TestUtil.standardTestForHandle(handler);
//        System.out.println(response.getReprompt());
//        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstantsAppointment.APPOINTMENT_IS));
//    }
//}
