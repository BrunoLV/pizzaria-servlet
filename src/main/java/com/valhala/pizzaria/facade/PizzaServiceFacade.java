/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.pizzaria.facade;

import com.valhala.pizzaria.service.exception.ServiceException;
import com.valhala.pizzaria.web.vo.PizzaVO;

import java.io.Serializable;
import java.util.List;

/**
 * Interface que define o contrato de implementação Facade para ações que envolvem a Entidade Pizza.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public interface PizzaServiceFacade {

    PizzaVO criar(PizzaVO pizzaVO) throws ServiceException;

    PizzaVO editar(PizzaVO pizzaVO) throws ServiceException;

    void deletar(Serializable id) throws ServiceException;

    List<PizzaVO> listar() throws ServiceException;

    List<PizzaVO> listarPaginado(Integer indiceInicial, Integer registrosPorPagina) throws ServiceException;

    Integer obterTotalRegistros() throws ServiceException;

} // fim da interface PizzaServiceFacade