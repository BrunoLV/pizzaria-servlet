package com.valhala.pizzaria.web.util;

/**
 * Classe utilizada para implementar a acao de montagem de respostas.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public class MontadorResponse {

    public static String montarMensagemSucessoListagem(String itens, Integer numeroRegistros) {
        return "{\"Result\":\"OK\",\"Records\":" + itens + ", \"TotalRecordCount\":" + numeroRegistros + "}";
    } // fim do metodo montarMensagemSucessoListagem

    public static String montarMensagemSucessoObjeto(String objeto) {
        return "{\"Result\":\"OK\",\"Record\":" + objeto + "}";
    } // fim do metodo montarMensagemSucessoObjeto

    public static String montarMensagemErro(String mensagem) {
        return "{\"Result\":\"ERROR\",\"Message\":\"" + mensagem + "\"}";
    } // fim do metodo montarMensagemErro

    public static String montarMensagemSucesso() {
        return "{\"Result\":\"OK\"}";
    } // fim do metodo montarMensagemSucesso

} // fim da classe MontadorResponse