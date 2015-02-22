package com.valhala.pizzaria.service.exception;

/**
 * Interface que encapsula as exceptions ocorridas  na camada Service.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public class ServiceException extends Exception {

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

} // fim da classe ServiceException