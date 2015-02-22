/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.pizzaria.dao.impl.jdbc;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.valhala.pizzaria.annotations.Logavel;
import com.valhala.pizzaria.dao.PizzaDao;
import com.valhala.pizzaria.dao.exception.DaoException;
import com.valhala.pizzaria.model.Pizza;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacao de DAO que lida com dados de PIZZA utilizando a abordagem JDBC.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public class PizzaDaoJdbcImpl extends BaseDaoJdbc implements PizzaDao {

    private static final String SQL_INSERT = "INSERT INTO tb_pizza (nome, descricao) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE tb_pizza SET nome = ?, descricao = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM tb_pizza WHERE id = ?";
    private static final String SQL_SELECT_POR_ID = "SELECT * FROM tb_pizza WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM tb_pizza";
    private static final String SQL_SELECT_COUNT = "SELECT COUNT(*) FROM tb_pizza";
    private static final String SQL_SELECT_PAGINADO = "SELECT * FROM (SELECT p.*, ROW_NUMBER() OVER (ORDER BY id) r FROM tb_pizza p) AS consulta_paginada WHERE r > ? AND r <= ?";

    @Inject
    private Provider<Connection> provider;

    public PizzaDaoJdbcImpl() {
        super();
    }

    @Override
    @Logavel
    public Serializable inserir(Pizza pizza) throws DaoException {
        Integer idGerado = null;
        try (PreparedStatement stmt = montarStatementQueRetornaChaveGerada(provider.get(), SQL_INSERT, pizza.getNome(), pizza.getDescricao())) {
            idGerado = executaUpdateRetornandoChaveGerada(stmt);
        } catch (SQLException e) {
            throw new DaoException(e);
        } // fim do bloco try/catch
        return idGerado;
    } // fim do metodo inserir

    @Override
    @Logavel
    public void editar(Pizza pizza) throws DaoException {
        try (PreparedStatement stmt = montarStatement(provider.get(), SQL_UPDATE, pizza.getNome(), pizza.getDescricao(), pizza.getId())) {
            executaUpdate(stmt);
        } catch (SQLException e) {
            throw new DaoException(e);
        } // fim do bloco try/catch
    } // fim do metodo editar

    @Override
    @Logavel
    public void deletar(Serializable id) throws DaoException {
        try (PreparedStatement stmt = montarStatement(provider.get(), SQL_DELETE, id)) {
            int linhasAfetadas = executaUpdate(stmt);
        } catch (SQLException e) {
            throw new DaoException(e);
        } // fim do bloco try/catch
    } // fim do metodo deletar

    @Override
    @Logavel
    public Pizza buscarPorId(Serializable id) throws DaoException {
        Pizza pizza = null;
        try (PreparedStatement stmt = montarStatement(provider.get(), SQL_SELECT_POR_ID, id)) {
            try (ResultSet rs = executarQuery(stmt)) {
                if (rs.next()) {
                    pizza = new Pizza(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"));
                } // fim do bloco if
            } // fim do bloco try
        } catch (SQLException e) {
            throw new DaoException(e);
        } // fim do bloco try/catch
        return pizza;
    } // fim do metodo buscarPorId

    @Override
    @Logavel
    public List<Pizza> listar() throws DaoException {
        List<Pizza> pizzas = new ArrayList<>();
        try (PreparedStatement stmt = montarStatement(provider.get(), SQL_SELECT_ALL)) {
            try (ResultSet rs = executarQuery(stmt)) {
                while (rs.next()) {
                    Pizza pizza = new Pizza(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"));
                    pizzas.add(pizza);
                } // fim do bloco if
            } // fim do bloco try
        } catch (SQLException e) {
            throw new DaoException(e);
        } // fim do bloco try/catch
        return pizzas;
    } // fim do metodo listar

    @Override
    @Logavel
    public List<Pizza> listarPaginacao(int indiceInicio, int numeroRegistros) throws DaoException {
        List<Pizza> pizzas = new ArrayList<>();
        try (PreparedStatement stmt = montarStatement(provider.get(), SQL_SELECT_PAGINADO, indiceInicio, numeroRegistros)) {
            try (ResultSet rs = executarQuery(stmt)) {
                while (rs.next()) {
                    Pizza pizza = new Pizza(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"));
                    pizzas.add(pizza);
                } // fim do bloco if
            } // fim do bloco try
        } catch (SQLException e) {
            throw new DaoException(e);
        } // fim do bloco try/catch
        return pizzas;
    } // fim do metodo listarPaginacao

    @Override
    @Logavel
    public Integer obterTotalRegistros() throws DaoException {
        Integer total = null;
        try (PreparedStatement stmt = montarStatement(provider.get(), SQL_SELECT_COUNT)) {
            try (ResultSet rs = executarQuery(stmt)) {
                if (rs.next()) {
                    total = rs.getInt("count");
                } // fim do bloco if
            } // fim do bloco try
        } catch (SQLException e) {
            throw new DaoException(e);
        } // fim do bloco try/catch
        return total;
    } // fim do metodo obterTotalRegistros

} // fim da classe PizzaDaoJdbcImpl