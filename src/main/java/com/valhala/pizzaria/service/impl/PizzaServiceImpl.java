package com.valhala.pizzaria.service.impl;

import com.google.inject.Inject;
import com.valhala.pizzaria.annotations.Logavel;
import com.valhala.pizzaria.dao.PizzaDao;
import com.valhala.pizzaria.dao.exception.DaoException;
import com.valhala.pizzaria.model.Pizza;
import com.valhala.pizzaria.service.PizzaService;
import com.valhala.pizzaria.service.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

/**
 * Implementação de PizzaService
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public class PizzaServiceImpl implements PizzaService {

    @Inject
    private PizzaDao dao;

    @Override
    @Logavel
    public Pizza create(Pizza pizza) throws ServiceException {
        Pizza retorno = null;
        try {
            Serializable idGerado = this.dao.inserir(pizza);
            retorno = this.dao.buscarPorId(idGerado);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } // fim do bloco try/catch
        return retorno;
    } // fim do metodo create

    @Override
    @Logavel
    public Pizza update(Pizza pizza) throws ServiceException {
        Pizza retorno = null;
        try {
            this.dao.editar(pizza);
            retorno = this.dao.buscarPorId(pizza.getId());
        } catch (DaoException e) {
            throw new ServiceException(e);
        } // fim do bloco try/catch
        return retorno;
    } // fim do metodo update

    @Override
    @Logavel
    public void delete(Serializable id) throws ServiceException {
        try {
            this.dao.deletar(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } // fim do bloco try/catch
    } // fim do metodo delete

    @Override
    @Logavel
    public Pizza readById(Serializable id) throws ServiceException {
        Pizza pizza = null;
        try {
            pizza = this.dao.buscarPorId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } // fim do bloco try/catch
        return pizza;
    } // fim do metodo readById

    @Override
    @Logavel
    public List<Pizza> readAll() throws ServiceException {
        List<Pizza> pizzas = null;
        try {
            pizzas = this.dao.listar();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } // fim do bloco try/catch
        return pizzas;
    } // fim do metodo readAll

    @Override
    @Logavel
    public List<Pizza> readAllPaginated(Integer initialIndex, Integer recordPerPage) throws ServiceException {
        Integer range = initialIndex + recordPerPage;
        List<Pizza> pizzas = null;
        try {
            pizzas = this.dao.listarPaginacao(initialIndex, range);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } // fim do bloco try/catch
        return pizzas;
    } // fim do metodo readAllPaginated

    @Override
    @Logavel
    public Integer getCountRecord() throws ServiceException {
        Integer total = null;
        try {
            total = this.dao.obterTotalRegistros();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } // fim do bloco try/catch
        return total;
    } // fim do metodo getCountRecord

} // fim da classe PizzaServiceImpl