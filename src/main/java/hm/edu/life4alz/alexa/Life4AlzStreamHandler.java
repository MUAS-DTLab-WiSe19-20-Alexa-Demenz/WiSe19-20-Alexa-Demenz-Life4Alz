package hm.edu.life4alz.alexa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import hm.edu.life4alz.alexa.handler.CancelandStopIntentHandler;
import hm.edu.life4alz.alexa.handler.CreateAppointmentIntentHandler;
import hm.edu.life4alz.alexa.handler.CreateDoctorAppointmentIntentHandler;
import hm.edu.life4alz.alexa.handler.CreateNameAndLocationIntentHandler;
import hm.edu.life4alz.alexa.handler.FallbackIntentHandler;
import hm.edu.life4alz.alexa.handler.HelpIntentHandler;
import hm.edu.life4alz.alexa.handler.LaunchRequestHandler;
import hm.edu.life4alz.alexa.handler.SaveAppointmentIntentHandler;
import hm.edu.life4alz.alexa.handler.SessionEndedRequestHandler;
import hm.edu.life4alz.alexa.handler.ShowAppointmentDetailIntentHandler;
import hm.edu.life4alz.alexa.handler.ShowMyAppointmentsIntentHandler;
import hm.edu.life4alz.alexa.handler.YesNoIntentHandler;

public class Life4AlzStreamHandler extends SkillStreamHandler {

	static { System.setProperty("log4j.configurationFile", "src/main/resources/log4j2.xml"); }

	static Logger logger = LogManager.getLogger();
	
	
    private static Skill getSkill() {
    	
    	logger.error("in Life4AlzStreamHandler");
        return Skills.standard()
                .addRequestHandlers(
//                        new WhatsMyAppointmentIntentHandler(),
                		new CreateAppointmentIntentHandler(),
                		new CreateDoctorAppointmentIntentHandler(),
                		new SaveAppointmentIntentHandler(),
                		new CreateNameAndLocationIntentHandler(),
                		new YesNoIntentHandler(),
                        new ShowMyAppointmentsIntentHandler(),
                        new ShowAppointmentDetailIntentHandler(),
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new SessionEndedRequestHandler(),
                        new HelpIntentHandler(),
                        new FallbackIntentHandler())
                .withTableName("do_13_appointment")
                
                .withAutoCreateTable(true)
                // Add your skill id below
                .withSkillId("amzn1.ask.skill.537158fa-85d7-4e3f-9752-49853ba6b43c")
                .build();
    }

    public Life4AlzStreamHandler() {
        super(getSkill());
    }

}
