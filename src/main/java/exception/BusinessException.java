package exception;

public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 2201800548165425255L;

    public BusinessException(String mensagem) {
        super(mensagem);
    }
}
