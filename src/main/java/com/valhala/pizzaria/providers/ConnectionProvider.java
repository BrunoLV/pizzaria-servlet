package com.valhala.pizzaria.providers;

import com.google.inject.Provider;
import com.valhala.pizzaria.providers.exceptions.ProviderException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Classe utilizada para prover conexões com o banco de dados.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public class ConnectionProvider implements Provider<Connection> {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("bd");

    @Override
    public Connection get() {
        Connection connection = null;
        try {
            connection = montarConnection();
        } catch (ProviderException e) {
        }
        return connection;
    } // fim do metodo get

    /*
     * Metodo utilizado para gerar uma conexao com o banco de dados.
     */
    private Connection montarConnection() throws ProviderException {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(RESOURCE_BUNDLE.getString("bd.url"),
                    RESOURCE_BUNDLE.getString("bd.user"),
                    RESOURCE_BUNDLE.getString("bd.password"));
        } catch (SQLException e) {
            throw new ProviderException(e);
        } // fim do bloco try/catch
        return connection;
    } // fim do montarConnection

} // fim da classe ConnectionProvider