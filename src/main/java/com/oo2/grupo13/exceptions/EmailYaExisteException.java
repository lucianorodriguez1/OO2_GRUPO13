package com.oo2.grupo13.exceptions;

public class EmailYaExisteException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -988147235993178960L;

	public EmailYaExisteException(String mensaje) {
        super(mensaje);
    }
}


