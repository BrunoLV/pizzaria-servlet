package com.valhala.pizzaria.providers.exceptions;

/**
 * Exception que encapsula erros ocorridos nos Providers.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public class ProviderException extends Exception {

    public ProviderException() {
        super();
    }

    public ProviderException(String message) {
        super(message);
    }

    public ProviderException(Throwable cause) {
        super(cause);
    }

    public ProviderException(String message, Throwable cause) {
        super(message, cause);
    }

} // fim da classe ProviderException