package com.service.pet.data;

import java.util.Date;

/**
 * @author Maitrik PANCHAL
 * Response data to generate response in below format
 */
public class ResponseData {

	private String message;
	private Object data;
	private Date timeStamp;

	public ResponseData() {
	}

	public ResponseData(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public Date getTimeStamp() {
		return new Date();
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "ResponseData [message=" + message + ", data=" + data + ", timeStamp=" + timeStamp + "]";
	}

}
