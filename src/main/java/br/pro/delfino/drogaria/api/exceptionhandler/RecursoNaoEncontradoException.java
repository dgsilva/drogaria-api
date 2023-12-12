package br.pro.delfino.drogaria.api.exceptionhandler;

public class RecursoNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public RecursoNaoEncontradoException(String message) {
		super(message);
	}

}
