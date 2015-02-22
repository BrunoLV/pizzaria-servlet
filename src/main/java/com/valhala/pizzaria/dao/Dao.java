/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.pizzaria.dao;

import com.valhala.pizzaria.dao.exception.DaoException;

import java.io.Serializable;
import java.util.List;

/**
 * Interface que define o contrato de implementacao de DAO's.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public interface Dao<T> {

    Serializable inserir(T t) throws DaoException;

    void editar(T t) throws DaoException;

    void deletar(Serializable id) throws DaoException;

    T buscarPorId(Serializable id) throws DaoException;

    List<T> listar() throws DaoException;

    List<T> listarPaginacao(int indiceInicio, int numeroRegistros) throws DaoException;

    Integer obterTotalRegistros() throws DaoException;

} // fim da interface Dao