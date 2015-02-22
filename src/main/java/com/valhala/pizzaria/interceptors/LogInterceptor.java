package com.valhala.pizzaria.interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.pmw.tinylog.Logger;

/**
 * Classe utilizada para implementar o aspecto de log na aplicação.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public class LogInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object retorno = null;
        invocation.getMethod().getName();
        Logger.info("Executando classe: " + invocation.getMethod().getDeclaringClass().getSimpleName()
                + " - metodo: " + invocation.getMethod().getName()
                + " - argumentos: " + invocation.getArguments());
        try {
            retorno = invocation.proceed();
            Logger.info("Retorno classe: " + invocation.getMethod().getDeclaringClass().getSimpleName()
                    + " - metodo: " + invocation.getMethod().getName()
                    + " - retorno: " + retorno);
        } catch (Exception e) {
            Logger.error("Ocorreu erro no classe: " + invocation.getMethod().getDeclaringClass().getSimpleName()
                    + " - metodo: " + invocation.getMethod().getName());
            Logger.error(e);
            throw e;
        } // fim do bloco try/catch
        return retorno;
    } // fim do metodo invoke

} // fim da classe LogInterceptor