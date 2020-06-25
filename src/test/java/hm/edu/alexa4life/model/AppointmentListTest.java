package hm.edu.alexa4life.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;

import hm.edu.life4alz.model.appointmenttypes.Appointment;
import hm.edu.life4alz.model.appointmenttypes.AppointmentList;
import hm.edu.life4alz.model.appointmenttypes.AppointmentTypes;
import hm.edu.life4alz.model.appointmenttypes.FamilyDoctorAppointment;
import hm.edu.life4alz.model.appointmenttypes.Shopping;



public class AppointmentListTest {
	    
	AppointmentList appointmentList;
		@Before
		public void setup() {
			appointmentList = new AppointmentList();
	        appointmentList.getList().add(new Shopping(new ArrayList<String>(), AppointmentTypes.SHOPPING.getName(), "testName","0", "03.02.2020","10:00", "Baumstraße 3, München", false));
	        appointmentList.getList().add(new Shopping(new ArrayList<String>(), AppointmentTypes.SHOPPING.getName(), "testName","0", "03.02.2020","10:00", "Baumstraße 3, München", false));
	        appointmentList.getList().add(new Shopping(new ArrayList<String>(), AppointmentTypes.SHOPPING.getName(), "testName","0", "04.02.2020","10:00", "Baumstraße 3, München", false));
	        appointmentList.getList().add(new FamilyDoctorAppointment(new ArrayList<String>(), AppointmentTypes.FAMILY_DOCTOR_APPOINTMENT.getName(), "testName","0", "03.02.2020","10:00", "Baumstraße 3, München", false, "Dr.Frankenstein"));
	        System.out.println(appointmentList.getList().size());
		}

	
		@Test
	    public void validateSettersAndGetters() throws Exception{

	        PojoClass activityPojo = PojoClassFactory.getPojoClass(AppointmentList.class);
	        Validator validator = ValidatorBuilder.create()

	        		// Lets make sure that we have a getter for every field defined.
	                .with(new GetterMustExistRule())

	                // Lets also validate that they are behaving as expected
	                .with(new GetterTester()).build();

	        // Start the Test
	        validator.validate(activityPojo);
	    }
	    
	    @Test
	    public void validateIsValid() throws Exception {
	    	
	        AppointmentList emptyAppointmentList = new AppointmentList();
	        assertEquals("isNotValid_empty", false, emptyAppointmentList.isValid());

	        appointmentList= new AppointmentList();
	        appointmentList.getList().add(new Appointment());
	        assertEquals("isValid", true, appointmentList.isValid());
	    }
	    
	    @Test
	    public void testGetFilteredList() {
	    	assertEquals(3,appointmentList.getFilteredList(AppointmentTypes.SHOPPING.getName(), null).getList().size());
	    	assertEquals(2,appointmentList.getFilteredList(AppointmentTypes.SHOPPING.getName(), "03.02.2020").getList().size());
	    	assertEquals(3,appointmentList.getFilteredList(null, "03.02.2020").getList().size());
	    	
	    }
	}
