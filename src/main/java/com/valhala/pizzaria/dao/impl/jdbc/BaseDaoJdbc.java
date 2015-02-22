package com.valhala.pizzaria.dao.impl.jdbc;

import com.valhala.pizzaria.dao.exception.DaoException;

import java.sql.*;

/**
 * Classe utilitaria de com operações base para DAO's JDBC;
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public class BaseDaoJdbc<T> {

    /**
     * Metodo utilizado para montar Statements para execucao de queries.
     *
     * @param connection
     * @param query
     * @param parameters
     * @return
     * @throws DaoException
     */
    public PreparedStatement montarStatement(Connection connection, String query, Object... parameters) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            if (parameters != null && parameters.length > 0) {
                int numParam = 1;
                for (Object param : parameters) {
                    statement.setObject(numParam, param);
                    numParam++;
                } // fim do bloco for
            } // fim do bloco if
        } catch (SQLException e) {
            throw new DaoException(e);
        } // fim do bloco try/catch
        return statement;
    } // fim do metodo montarStatement

    /**
     * Metodo utilizado para montar Statements para execucao de queries que retornam Keys geradas nas operações.
     *
     * @param connection
     * @param query
     * @param parameters
     * @return
     * @throws DaoException
     */
    public PreparedStatement montarStatementQueRetornaChaveGerada(Connection connection, String query, Object... parameters) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if (parameters != null && parameters.length > 0) {
                int numParam = 1;
                for (Object param : parameters) {
                    statement.setObject(numParam, param);
                    numParam++;
                } // fim do bloco for
            } // fim do bloco if
        } catch (SQLException e) {
            throw new DaoException(e);
        } // fim do bloco try/catch
        return statement;
    } // fim do metodo montarStatementQueRetornaChaveGerada

    /**
     * Metodo utilizado para execução de queries definidas no statement.
     *
     * @param statement
     * @return
     * @throws DaoException
     */
    public ResultSet executarQuery(PreparedStatement statement) throws DaoException {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new DaoException(e);
        } // fim do bloco try/catch
        return resultSet;
    } // fim do metodo executarQuery

    /**
     * Metodo utilizado para execução de queries retornando key gerada.
     *
     * @param statement
     * @return
     * @throws DaoException
     */
    public int executaUpdate(PreparedStatement statement) throws DaoException {
        int numeroLinhas = 0;
        try {
            numeroLinhas = statement.executeUpdate();
            if (numeroLinhas <= 0) {
                throw new DaoException("O comando de INSERT nao funcionou. VERIFICAR...");
            } // fim do bloco if
        } catch (SQLException e) {
            throw new DaoException(e);
        } // fim do bloco try/catch
        return numeroLinhas;
    } // fim do metodo executaUpdate

    public int executaUpdateRetornandoChaveGerada(PreparedStatement statement) throws DaoException {
        int numeroLinhas = 0;
        int idGerado = 0;
        try {
            numeroLinhas = statement.executeUpdate();
            if (numeroLinhas <= 0) {
                throw new DaoException("O comando de INSERT nao funcionou. VERIFICAR...");
            } // fim do bloco if
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                idGerado = resultSet.getInt(1);
            } // fim do bloco if
        } catch (SQLException e) {
            throw new DaoException(e);
        } // fim do bloco try/catch
        return idGerado;
    } // fim do metodo executaUpdateRetornandoChaveGerada

} // fim da classe BaseDaoJdbc