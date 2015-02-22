/**
 *
 */
$(document).ready(function () {
    //Localization texts
    var brazilianMessages = {
        serverCommunicationError: 'Ocorreu um erro de comunicacao com o Servidor.',
        loadingMessage: 'Carregando. Aguarde...',
        noDataAvailable: 'Nao a informacao disponivel!',
        addNewRecord: 'Adicionar sabor',
        editRecord: 'Editar',
        areYouSure: 'Voce tem certeza?',
        deleteConfirmation: 'Deletando registro. Voce tem certeza?',
        save: 'Salvar',
        saving: 'Salvando',
        cancel: 'Cancelar',
        deleteText: 'Deletar',
        deleting: 'Deletando',
        error: 'Erro',
        close: 'Fechar',
        cannotLoadOptionsFor: '{0} nao pode ser enviado para o campo!',
        pagingInfo: 'Total de {2}, {0} para {1} Mostrando',
        canNotDeletedRecords: '{1} a partir do registro {0} itens nao puderam ser apagados!',
        deleteProggress: '{1} a partir do registro {0} total suprimidos, continuando...'
    };

    $('#PizzaTableContainer').jtable({
        messages: brazilianMessages,
        title: 'Cardapio de Pizzas',
        paging: true,
        pageSize: 10,
        actions: {
            listAction: 'PizzaController?action=listar',
            createAction: 'PizzaController?action=criar',
            updateAction: 'PizzaController?action=atualizar',
            deleteAction: 'PizzaController?action=deletar'
        },
        fields: {
            id: {
                title: 'Id',
                width: '5%',
                key: true,
                list: true,
                create: false,
                edit: false
            },
            nome: {
                title: 'Nome',
                width: '20%',
                edit: true
            },
            descricao: {
                title: 'Descricao',
                width: '60%',
                edit: true,
                type: 'textarea'
            }
        }
    });
    $('#PizzaTableContainer').jtable('load');
});