package hm.edu.alexa4life.model;



import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

import hm.edu.life4alz.model.appointmenttypes.Appointment;



public class AppointmentTest {
    
	@Test
    public void validateSettersAndGetters() throws Exception{

        PojoClass activityPojo = PojoClassFactory.getPojoClass(Appointment.class);
        Validator validator = ValidatorBuilder.create()

        		// Lets make sure that we have a getter and a setter for every field defined.
                .with(new SetterMustExistRule()).with(new GetterMustExistRule())

                // Lets also validate that they are behaving as expected
                .with(new SetterTester()).with(new GetterTester()).build();

        // Start the Test
        validator.validate(activityPojo);
    }
    
    @Test
    public void validateIsValid() throws Exception {
        Appointment appointmentNull= new Appointment();
        assertEquals("isNotValid_null", false, appointmentNull.isValid());

        Appointment appointment= new Appointment();
        appointment.setAppointmentName("Arzttermin");
        appointment.setDate("2019-12-17 15:00:00");
        assertEquals("isValid", true, appointment.isValid());
    }
}
