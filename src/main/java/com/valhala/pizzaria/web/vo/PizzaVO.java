package com.valhala.pizzaria.web.vo;

import com.valhala.pizzaria.model.Pizza;

import java.io.Serializable;

/**
 * Classe utilizada para transportar dados de tela para as classes de integração de Back End.
 *
 * @author Bruno Luiz Viana
 * @version 1.0
 * @since 21/02/2015
 */
public class PizzaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String descricao;

    public PizzaVO() {
        super();
    }

    public PizzaVO(Integer id, String nome, String descricao) {
        super();
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public static Pizza returnAsModel(PizzaVO pizzaVO) {
        Pizza pizza = new Pizza(pizzaVO.getId(), pizzaVO.getNome(), pizzaVO.getDescricao());
        return pizza;
    }

    public static PizzaVO createFromModel(Pizza pizza) {
        PizzaVO pizzaVO = new PizzaVO(pizza.getId(), pizza.getNome(), pizza.getDescricao());
        return pizzaVO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Pizza [");
        if (id != null) {
            builder.append("id=");
            builder.append(id);
            builder.append(", ");
        } // fim do bloco if
        if (nome != null) {
            builder.append("nome=");
            builder.append(nome);
            builder.append(", ");
        } // fim do bloco if
        if (descricao != null) {
            builder.append("descricao=");
            builder.append(descricao);
        } // fim do bloco if
        builder.append("]");
        return builder.toString();
    } // fim do metodo toString

} // fim da classe PizzaVO