package br.com.yotha.stompwebchat.dto;

import br.com.yotha.stompwebchat.StringUtils;
import lombok.Data;

@Data
public class ChatMessage {

	private String from;
	private String text;
	private String recipient;
	private String time;

	public ChatMessage() {

	}

	public ChatMessage(String from, String text, String recipient) {
		this.from = from;
		this.text = text;
		this.recipient = recipient;
		this.time = StringUtils.getCurrentTimeStamp();
	}
}