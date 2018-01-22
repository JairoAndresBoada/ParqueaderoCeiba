package co.ceiba.parqueadero.exception;


public class ParqueaderoLogicaExcepciones extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	
	public ParqueaderoLogicaExcepciones() {
		super();
	}
	
	
	public ParqueaderoLogicaExcepciones (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause, enableSuppression, writableStackTrace);
	}
	
	public ParqueaderoLogicaExcepciones (String message, Throwable cause) {
		super(message,cause);
	}
	
	
	public ParqueaderoLogicaExcepciones (String message) {
		super(message);
	}
	
	
	public ParqueaderoLogicaExcepciones(Throwable cause) {
		super(cause);
	}
}
