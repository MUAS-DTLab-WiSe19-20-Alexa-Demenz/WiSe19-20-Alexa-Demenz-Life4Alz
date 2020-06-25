/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package hm.edu.life4alz.alexa.handler;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import hm.edu.life4alz.alexa.constants.PhrasesAndConstants;
import hm.edu.life4alz.model.UserInfo;
import hm.edu.life4alz.model.appointmenttypes.AppointmentList;

public class LaunchRequestHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {

		return input.matches(requestType(LaunchRequest.class));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		// test reminder
		UserInfo userInfo = new UserInfo();
		AttributesManager attributesManager = input.getAttributesManager();
		ResponseBuilder responseBuilder = input.getResponseBuilder();

		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
		sessionAttributes.put(PhrasesAndConstants.STATE_KEY, PhrasesAndConstants.STATE_START);
		userInfo.addUserInfoToSessionAttributes(sessionAttributes);

		Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
		String serializedList = (String) persistentAttributes.get(PhrasesAndConstants.APPOINTMENT_LIST_KEY);
		if (serializedList != null) {
			sessionAttributes.put(PhrasesAndConstants.APPOINTMENT_LIST_KEY, serializedList);
		} else {
			sessionAttributes.put(PhrasesAndConstants.APPOINTMENT_LIST_KEY,
					(String) AppointmentList.toString(new AppointmentList()));
		}
		attributesManager.setSessionAttributes(sessionAttributes);

		responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.WELCOME)
				.withShouldEndSession(false).withSpeech(PhrasesAndConstants.WELCOME)
				.withReprompt(PhrasesAndConstants.HELP_REPROMPT);
		return responseBuilder.build();
	}

}
