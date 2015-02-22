package com.valhala.pizzaria.service;

import com.valhala.pizzaria.service.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

/**
 * Interface que define o contrato de implementacao de classes Service de CRUD.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public interface CrudService<T> {

    T create(T t) throws ServiceException;

    T update(T t) throws ServiceException;

    void delete(Serializable id) throws ServiceException;

    T readById(Serializable id) throws ServiceException;

    List<T> readAll() throws ServiceException;

    List<T> readAllPaginated(Integer inicialIndex, Integer recordPerPage) throws ServiceException;

    Integer getCountRecord() throws ServiceException;

} // fim da interface CrudService