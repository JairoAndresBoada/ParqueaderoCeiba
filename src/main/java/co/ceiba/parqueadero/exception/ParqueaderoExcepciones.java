package co.ceiba.parqueadero.exception;

public class ParqueaderoExcepciones extends Exception{

	private static final long serialVersionUID = 1L;
	
	
	public ParqueaderoExcepciones() {
		super();
	}
	
	
	public ParqueaderoExcepciones (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause, enableSuppression, writableStackTrace);
	}
	
	
	public ParqueaderoExcepciones (String message, Throwable cause) {
		super(message,cause);
	}
	
	
	public ParqueaderoExcepciones (String message) {
		super(message);
	}
	
	
	public ParqueaderoExcepciones(Throwable cause) {
		super(cause);
	}
}
