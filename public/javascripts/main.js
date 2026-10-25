function esc(v) { return v == null ? '' : $('<div>').text(v).html(); }
function td(label, v) { return '<td data-th="' + label + '">' + esc(v) + '</td>'; }
function btn(href, icone, titulo, extra) {
    return '<a href="' + href + '" class="icon-btn ' + (extra || '') + '" title="' + titulo + '"><i class="bi bi-' + icone + '"></i></a>';
}
function tdAcoes(html) { return '<td class="text-center actions" data-th="Ações">' + html + '</td>'; }
function tabela(cabecalhos, linhas) {
    var th = cabecalhos.map(function (h) {
        return '<th' + (h === 'Ações' ? ' class="text-center"' : '') + '>' + h + '</th>';
    }).join('');
    var tr = linhas.map(function (l) { return '<tr>' + l + '</tr>'; }).join('');
    $("#dado").html('<thead><tr>' + th + '</tr></thead><tbody>' + tr + '</tbody>');
}

function chamarAcademias() {
    var termo = $("#termoAcademia").val();
    $.ajax({
        url: "/Academias/listarJson", data: "termo=" + termo, dataType: "json",
        success: function (a) {
            tabela(['Nome', 'Endereço', 'CNPJ', 'Contato', 'Ações'], a.map(function (x) {
                return td('Nome', x.nome) + td('Endereço', x.endereco) + td('CNPJ', x.CNPJ) + td('Contato', x.contato) +
                    tdAcoes(btn('/academias/remover?id=' + x.id, 'trash3', 'Remover', 'danger') +
                        btn('/academias/editar?id=' + x.id, 'pencil-square', 'Editar') +
                        btn('/academias/formPersonal?id=' + x.id, 'person-plus', 'Personais'));
            }));
        }
    });
}

function chamarClientes() {
    var termo = $("#termoCliente").val();
    $.ajax({
        url: "/Clientes/listarJson", data: "termo=" + termo, dataType: "json",
        success: function (c) {
            tabela(['Nome', 'Sobrenome', 'CPF', 'Contato', 'Idade', 'Turno', 'Acompanhamento', 'Academia', 'Personal', 'Ações'], c.map(function (x) {
                return td('Nome', x.nome) + td('Sobrenome', x.sobrenome) + td('CPF', x.cpf) + td('Contato', x.contato) +
                    td('Idade', x.idade) + td('Turno', x.turno) + td('Acompanhamento', x.acompanhamentoPersonal) +
                    td('Academia', x.academia ? x.academia.nome : '') + td('Personal', x.personal ? x.personal.nome : '') +
                    tdAcoes(btn('/clientes/remover?id=' + x.id, 'trash3', 'Remover', 'danger') +
                        btn('/clientes/editar?id=' + x.id, 'pencil-square', 'Editar'));
            }));
        }
    });
}

function chamarPersonais() {
    var termo = $("#termoPersonal").val();
    $.ajax({
        url: "/Personais/listarJson", data: "termo=" + termo, dataType: "json",
        success: function (p) {
            tabela(['Nome', 'Sobrenome', 'CPF', 'Contato', 'Idade', 'Salário', 'Ações'], p.map(function (x) {
                return td('Nome', x.nome) + td('Sobrenome', x.sobrenome) + td('CPF', x.cpf) + td('Contato', x.contato) +
                    td('Idade', x.idade) + td('Salário', x.salario) +
                    tdAcoes(btn('/personais/remover?id=' + x.id, 'trash3', 'Remover', 'danger') +
                        btn('/personais/editar?id=' + x.id, 'pencil-square', 'Editar'));
            }));
        }
    });
}

$(function () {
    $("#termoAcademia").on("input", chamarAcademias);
    $("#termoCliente").on("input", chamarClientes);
    $("#termoPersonal").on("input", chamarPersonais);
});
