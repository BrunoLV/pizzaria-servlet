/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.pizzaria.facade.impl;

import com.google.inject.Inject;
import com.valhala.pizzaria.annotations.Logavel;
import com.valhala.pizzaria.annotations.Transacional;
import com.valhala.pizzaria.facade.PizzaServiceFacade;
import com.valhala.pizzaria.model.Pizza;
import com.valhala.pizzaria.service.PizzaService;
import com.valhala.pizzaria.service.exception.ServiceException;
import com.valhala.pizzaria.web.vo.PizzaVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação de PizzaServiceFacade.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public class PizzaServiceFacadeImpl implements PizzaServiceFacade {

    @Inject
    private PizzaService pizzaService;

    @Override
    @Transacional
    @Logavel
    public PizzaVO criar(PizzaVO pizzaVO) throws ServiceException {
        Pizza pizza = PizzaVO.returnAsModel(pizzaVO);
        PizzaVO retorno = PizzaVO.createFromModel(this.pizzaService.create(pizza));
        return retorno;
    } // fim do metodo criar

    @Override
    @Transacional
    @Logavel
    public PizzaVO editar(PizzaVO pizzaVO) throws ServiceException {
        Pizza pizza = PizzaVO.returnAsModel(pizzaVO);
        PizzaVO retorno = PizzaVO.createFromModel(this.pizzaService.update(pizza));
        return retorno;
    } // fim do metodo editar

    @Override
    @Transacional
    public void deletar(Serializable id) throws ServiceException {
        this.pizzaService.delete(id);
    } // fim do metodo deletar

    @Override
    @Logavel
    public List<PizzaVO> listar() throws ServiceException {
        List<PizzaVO> pizzaVOs = new ArrayList<>();
        List<Pizza> pizzas = null;
        pizzas = this.pizzaService.readAll();
        for (Pizza pizza : pizzas) {
            pizzaVOs.add(PizzaVO.createFromModel(pizza));
        } // fim do bloco for
        return pizzaVOs;
    } // fim do metodo listar

    @Override
    @Logavel
    public List<PizzaVO> listarPaginado(Integer indiceInicial, Integer registrosPorPagina) throws ServiceException {
        List<PizzaVO> pizzaVOs = new ArrayList<>();
        List<Pizza> pizzas = null;
        pizzas = this.pizzaService.readAllPaginated(indiceInicial, registrosPorPagina);
        for (Pizza pizza : pizzas) {
            pizzaVOs.add(PizzaVO.createFromModel(pizza));
        } // fim do bloco for
        return pizzaVOs;
    } // fim do metodo listarPaginado

    @Override
    @Logavel
    public Integer obterTotalRegistros() throws ServiceException {
        return this.pizzaService.getCountRecord();
    } // fim do metodo obterTotalRegistros

} // fim da classe PizzaServiceFacadeImpl