/**
 * 
 */
package org.smartsupply.api.service;

import org.smartsupply.model.admin.User;
import org.smartsupply.model.exception.ValidationException;

import java.awt.*;
import java.util.List;

public interface EventService {
	
	void saveEventHandler(Event event) throws ValidationException;

	void deleteEvent(String[] eventIdArray);

	Event getEventById(String id);

	void validate(Event event);

	List<Event> getCommentsByStudent(User senderStudent);

	List<Event> getUserEvents(User user);
}
