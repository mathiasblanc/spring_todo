package io.cdtilde.todo.util;

public class CustomError {
	private final String errorMessage;

	public CustomError(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
