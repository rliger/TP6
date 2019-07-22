package fr.tp4.dao;

public class DAOConfigurationException extends RuntimeException {
	/*
	 * Constructeurs
	 */
	public DAOConfigurationException(String message) {
		super(message);
	}

	public DAOConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOConfigurationException(Throwable cause) {
		super(cause);
	}
}