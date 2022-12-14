package br.com.yotha.stompwebchat.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import br.com.yotha.stompwebchat.model.Message;
import br.com.yotha.stompwebchat.model.ResponseMessage;
import br.com.yotha.stompwebchat.service.NotificationService;

@Controller
public class MessageController {
	@Autowired
	private NotificationService notificationService;

	@MessageMapping("/message")
	@SendTo("/topic/messages")
	public ResponseMessage getMessage(final Message message) {
		notificationService.sendGlobalNotification();
		return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
	}

	@MessageMapping("/private-message")
	@SendToUser("/topic/private-messages")
	public ResponseMessage getPrivateMessage(final Message message, final Principal principal) {
		String userId = principal == null ? "" : principal.getName();
		notificationService.sendPrivateNotification(userId);
		return new ResponseMessage(HtmlUtils.htmlEscape(
				"Sending private message to user " + userId + ": " + message.getMessageContent()));
	}

	@MessageExceptionHandler
	@SendToUser("/topic/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}
}
