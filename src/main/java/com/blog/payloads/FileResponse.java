package com.blog.payloads;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileResponse {

	private String fileName;
	private String message;
	public FileResponse(String fileName, String message) {
		this.fileName = fileName;
		this.message = message;
	}
}
