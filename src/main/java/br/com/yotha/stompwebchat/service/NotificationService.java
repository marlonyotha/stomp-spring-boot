package br.com.yotha.stompwebchat.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import br.com.yotha.stompwebchat.model.ResponseMessage;


@Service
public class NotificationService {
	private final SimpMessagingTemplate messagingTemplate;

	public NotificationService(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	public void sendGlobalNotification() {
		ResponseMessage message = new ResponseMessage("Global Notification");
		messagingTemplate.convertAndSend("/topic/global-notifications", message);
	}

	public void sendPrivateNotification(final String userId) {
		ResponseMessage message = new ResponseMessage("Private Notification");
		messagingTemplate.convertAndSendToUser(userId, "/topic/private-notifications", message);
	}
}
