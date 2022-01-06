package exception;

public class PayloadException extends RuntimeException {
    private static final long serialVersionUID = -3434049805722067674L;

    public PayloadException(String mensagem) {
        super(mensagem);
    }
}
