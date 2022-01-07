package exception;

/**
 * Classe para Exceção personalizada de casa sem cômodos
 */
public class ComodosVazioException extends RuntimeException{

    private static final long serialVersionUID = -6540638069928776067L;

    public ComodosVazioException(String mensagem) {
        super(mensagem);
    }
}
