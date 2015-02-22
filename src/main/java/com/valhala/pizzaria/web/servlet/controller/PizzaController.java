package com.valhala.pizzaria.web.servlet.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.valhala.pizzaria.facade.PizzaServiceFacade;
import com.valhala.pizzaria.service.exception.ServiceException;
import com.valhala.pizzaria.web.util.MontadorResponse;
import com.valhala.pizzaria.web.vo.PizzaVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Classe Controller responsavel pela interação de tela.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
@Singleton
public class PizzaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String ACTION_LIST = "listar";
    private static final String ACTION_CREATE = "criar";
    private static final String ACTION_UPDATE = "atualizar";
    private static final String ACTION_DELETE = "deletar";

    @Inject
    private PizzaServiceFacade pizzaService;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null) {

            response.setContentType("application/json");
            List<PizzaVO> pizzaVOs = null;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            switch (action) {
                case ACTION_LIST:
                    try {
                        // Fetch Data from User Table
                        int indiceInicial = Integer.parseInt(request.getParameter("jtStartIndex"));
                        int registrosPorPagina = Integer.parseInt(request.getParameter("jtPageSize"));

                        pizzaVOs = this.pizzaService.listarPaginado(indiceInicial, registrosPorPagina);
                        response.getWriter().print(MontadorResponse.montarMensagemSucessoListagem(gson.toJson(pizzaVOs), this.pizzaService.obterTotalRegistros()));
                    } catch (ServiceException e) {
                        response.getWriter().print(MontadorResponse.montarMensagemErro(e.getMessage()));
                    } // fim do bloco try/catch
                    break;
                case ACTION_CREATE:
                    try {
                        String nome = request.getParameter("nome");
                        String descricao = request.getParameter("descricao");
                        PizzaVO pizzaVO = new PizzaVO(null, nome, descricao);
                        response.getWriter().print(MontadorResponse.montarMensagemSucessoObjeto(gson.toJson(this.pizzaService.criar(pizzaVO))));
                    } catch (ServiceException e) {
                        response.getWriter().print(MontadorResponse.montarMensagemErro(e.getMessage()));
                    } // fim do bloco try/catch
                    break;
                case ACTION_UPDATE:
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String nome = request.getParameter("nome");
                        String descricao = request.getParameter("descricao");
                        PizzaVO pizzaVO = new PizzaVO(id, nome, descricao);
                        response.getWriter().print(MontadorResponse.montarMensagemSucessoObjeto(gson.toJson(this.pizzaService.editar(pizzaVO))));
                    } catch (ServiceException e) {
                        response.getWriter().print(MontadorResponse.montarMensagemErro(e.getMessage()));
                    } // fim do bloco try/catch
                    break;
                case ACTION_DELETE:
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        this.pizzaService.deletar(id);
                        response.getWriter().print(MontadorResponse.montarMensagemSucesso());
                    } catch (ServiceException e) {
                        response.getWriter().print(MontadorResponse.montarMensagemErro(e.getMessage()));
                    } // fim do bloco try/catch
                    break;
                default:
                    response.getWriter().print(MontadorResponse.montarMensagemErro("Acao Inexistente!"));
                    break;
            } // fim do bloco switch
        } // fim do bloco if
    } // fim do metodo service

} // fim da classe PizzaController