package br.com.yotha.stompwebchat.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import br.com.yotha.stompwebchat.dto.ResponseMessage;

@Service
public class WebSocketService {

	private final SimpMessagingTemplate messagingTemplate;
	private final NotificationService notificationService;

	public WebSocketService(SimpMessagingTemplate messagingTemplate, NotificationService notificationService) {
		this.messagingTemplate = messagingTemplate;
		this.notificationService = notificationService;
	}

	public void notifyFrontend(final String message) {
		ResponseMessage response = new ResponseMessage(message);
		notificationService.sendGlobalNotification();
		messagingTemplate.convertAndSend("/topic/messages", response);
	}

	public void notifyUser(final String id, final String message) {
		ResponseMessage response = new ResponseMessage(message);
		notificationService.sendPrivateNotification(id);
		messagingTemplate.convertAndSendToUser(id, "/queue/private-messages", response);
	}
}
