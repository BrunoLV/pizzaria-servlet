package com.valhala.pizzaria.web.servlet;

import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;
import com.valhala.pizzaria.annotations.Logavel;
import com.valhala.pizzaria.annotations.Transacional;
import com.valhala.pizzaria.dao.PizzaDao;
import com.valhala.pizzaria.dao.impl.jdbc.PizzaDaoJdbcImpl;
import com.valhala.pizzaria.facade.PizzaServiceFacade;
import com.valhala.pizzaria.facade.impl.PizzaServiceFacadeImpl;
import com.valhala.pizzaria.interceptors.LogInterceptor;
import com.valhala.pizzaria.interceptors.TransacaoInterceptor;
import com.valhala.pizzaria.providers.ConnectionProvider;
import com.valhala.pizzaria.service.PizzaService;
import com.valhala.pizzaria.service.impl.PizzaServiceImpl;
import com.valhala.pizzaria.web.servlet.controller.PizzaController;

import java.sql.Connection;

/**
 * Classe utilizada configurar o modulo Guice que efetua a configuração da injeção de dependencias.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public class PizzariaServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        TransacaoInterceptor transacaoInterceptor = new TransacaoInterceptor();
        requestInjection(transacaoInterceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transacional.class), transacaoInterceptor);

        LogInterceptor logInterceptor = new LogInterceptor();
        requestInjection(logInterceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Logavel.class), logInterceptor);

        bind(Connection.class).toProvider(ConnectionProvider.class).in(RequestScoped.class);
        bind(PizzaDao.class).to(PizzaDaoJdbcImpl.class);
        bind(PizzaService.class).to(PizzaServiceImpl.class);
        bind(PizzaServiceFacade.class).to(PizzaServiceFacadeImpl.class);

        serve("/PizzaController", "PizzaController").with(PizzaController.class);
    } // fim do metodo configureServlets

} // fim da classe PizzariaServletModule