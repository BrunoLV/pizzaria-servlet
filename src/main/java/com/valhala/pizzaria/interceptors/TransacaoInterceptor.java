/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.pizzaria.interceptors;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.sql.Connection;

/**
 * Classe utilizada para implementar o aspecto de gerenciamento de transação na aplicação.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public class TransacaoInterceptor implements MethodInterceptor {

    @Inject
    private Provider<Connection> provider;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object objetoRetornado = null;
        Connection connection = provider.get();
        connection.setAutoCommit(false);
        try {
            objetoRetornado = invocation.proceed();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } // fim do bloco try/catch
        return objetoRetornado;
    } // fim do metodo invoke

} // fim da classe TransacaoInterceptor