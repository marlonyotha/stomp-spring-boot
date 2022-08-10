package br.com.yotha.stompwebchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.yotha.stompwebchat.model.Message;
import br.com.yotha.stompwebchat.service.WebSocketService;

@RestController
public class WebSocketController {

	@Autowired
	private WebSocketService webSocketService;

	@PostMapping("/send-message")
	public void sendMessage(@RequestBody final Message message) {
		webSocketService.notifyAll(message.getMessageContent());
	}

	@PostMapping("/send-private-message/{id}")
	public void sendPrivateMessage(@PathVariable final String id, @RequestBody final Message message) {
		webSocketService.notifyUser(id, message.getMessageContent());
	}
}
