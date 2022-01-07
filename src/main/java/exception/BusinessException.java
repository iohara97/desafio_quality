package exception;

/**
 * Classe para Exceção personalizada de bairro não existente
 */
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 2201800548165425255L;

    public BusinessException(String mensagem) {
        super(mensagem);
    }
}
