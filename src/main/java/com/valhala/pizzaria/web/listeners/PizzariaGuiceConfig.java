package com.valhala.pizzaria.web.listeners;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.valhala.pizzaria.web.servlet.PizzariaServletModule;

import javax.servlet.annotation.WebListener;

/**
 * Classe utilizada para iniciar o framework Guice na aplicação WEB.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
@WebListener
public class PizzariaGuiceConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new PizzariaServletModule());
    } // fim do metodo getInjector

} // fim da classe PizzariaGuiceConfig