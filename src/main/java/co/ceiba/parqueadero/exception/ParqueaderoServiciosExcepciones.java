package co.ceiba.parqueadero.exception;


public class ParqueaderoServiciosExcepciones extends Exception {
	private static final long serialVersionUID = 1L;
	
	
	public ParqueaderoServiciosExcepciones() {
		super();
	}
	
	
	public ParqueaderoServiciosExcepciones (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause, enableSuppression, writableStackTrace);
	}
	
	
	public ParqueaderoServiciosExcepciones (String message, Throwable cause) {
		super(message,cause);
	}
	
	
	public ParqueaderoServiciosExcepciones (String message) {
		super(message);
	}
	
	
	public ParqueaderoServiciosExcepciones(Throwable cause) {
		super(cause);
	}
}
