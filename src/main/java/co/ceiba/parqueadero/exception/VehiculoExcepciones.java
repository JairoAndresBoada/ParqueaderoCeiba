package co.ceiba.parqueadero.exception;


public class VehiculoExcepciones extends Exception {

	private static final long serialVersionUID = 1L;

	
	public VehiculoExcepciones() {
		super();
	}
	
	
	public VehiculoExcepciones (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause, enableSuppression, writableStackTrace);
	}
	
	
	public VehiculoExcepciones (String message, Throwable cause) {
		super(message,cause);
	}
	
	
	public VehiculoExcepciones (String message) {
		super(message);
	}
	
	
	public VehiculoExcepciones(Throwable cause) {
		super(cause);
	}

}
