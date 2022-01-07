package exception;

/**
 * Classe para Exceção personalizada de erro ao serializar o Payload para a classe CasaDTO
 */
public class PayloadException extends RuntimeException {
    private static final long serialVersionUID = -3434049805722067674L;

    public PayloadException(String mensagem) {
        super(mensagem);
    }
}
