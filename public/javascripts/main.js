function chamarAcademias() {
    var termo = $("#termoAcademia").val();

    $.ajax({
        url: "/Academias/listarJson",
        data: "termo=" + termo,
        dataType: "json",
        success: function (a) {
            var titulo = `
                <thead class="table-light">
                    <tr>
                        <th>Nome</th>
                        <th>Endereço</th>
                        <th>CNPJ</th>
                        <th>Contato</th>
                        <th class="text-center">Ações</th>
                    </tr>
                </thead>
            `;

            $("#dado").html(titulo);

            for (var i = 0; i < a.length; i++) {
                var nome = `<td>${a[i].nome}</td>`;
                var endereco = `<td>${a[i].endereco}</td>`;
                var cnpj = `<td>${a[i].CNPJ}</td>`;
                var contato = `<td>${a[i].contato}</td>`;
                var acoes = `
                    <td class="text-center" data-th="Ações">
                        <a href="/academias/remover?id=${a[i].id}" class="text-dark text-decoration-none">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                                <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06m6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528M8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
                            </svg>
                        </a>
                        <a href="/academias/editar?id=${a[i].id}" class="mx-2 text-decoration-none">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-highlighter" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M11.096.644a2 2 0 0 1 2.791.036l1.433 1.433a2 2 0 0 1 .035 2.791l-.413.435-8.07 8.995a.5.5 0 0 1-.372.166h-3a.5.5 0 0 1-.234-.058l-.412.412A.5.5 0 0 1 2.5 15h-2a.5.5 0 0 1-.354-.854l1.412-1.412A.5.5 0 0 1 1.5 12.5v-3a.5.5 0 0 1 .166-.372l8.995-8.07zm-.115 1.47L2.727 9.52l3.753 3.753 7.406-8.254zm3.585 2.17.064-.068a1 1 0 0 0-.017-1.396L13.18 1.387a1 1 0 0 0-1.396-.018l-.068.065zM5.293 13.5 2.5 10.707v1.586L3.707 13.5z"/>
                            </svg>
                        </a>
                        <a href="/academias/formPersonal?id=${a[i].id}" class="text-decoration-none">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
                                <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                                <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
                            </svg>
                        </a>
                    </td>
                `;
                $("#dado").append(`<tbody><tr>${nome}${endereco}${cnpj}${contato}${acoes}</tr></tbody>`);
            }
        },
    });
}

function chamarClientes() {
    var termo = $("#termoCliente").val();

    $.ajax({
        url: "/Clientes/listarJson",
        data: "termo=" + termo,
        dataType: "json",
        success: function (c) {
            var titulo = `
                <thead class="table-light">
					<tr>
						<th>Nome</th>
						<th>Sobrenome</th>
						<th>CPF</th>
						<th>Contato</th>
						<th>idade</th>
						<th>Turno</th>
						<th>Acompanhamento</th>
						<th>Academia</th>
						<th>Personal</th>
						<th class="text-center">Ações</th>
					</tr>
				</thead>
            `;

            $("#dado").html(titulo);

            for (var i = 0; i < c.length; i++) {
                var nome = `<td>${c[i].nome}</td>`;
                var sobrenome = `<td>${c[i].sobrenome}</td>`;
                var cpf = `<td>${c[i].cpf}</td>`;
                var contato = `<td>${c[i].contato}</td>`;
                var idade = `<td>${c[i].idade}</td>`;
                var turno = `<td>${c[i].turno}</td>`;
                var acompanhamento = `<td>${c[i].acompanhamentoPersonal}</td>`;
                var academia = `<td>${c[i].academia.nome}</td>`;
                var personal = `<td>${c[i].personal.nome}</td>`;
                var acoes = `
                    <td class="text-center" data-th="Ações">
                        <a href="/clientes/remover?id=${c[i].id}" class="text-dark text-decoration-none">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                                <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06m6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528M8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
                            </svg>
                        </a>
                        <a href="/clientes/editar?id=${c[i].id}" class="text-decoration-none">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-highlighter" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M11.096.644a2 2 0 0 1 2.791.036l1.433 1.433a2 2 0 0 1 .035 2.791l-.413.435-8.07 8.995a.5.5 0 0 1-.372.166h-3a.5.5 0 0 1-.234-.058l-.412.412A.5.5 0 0 1 2.5 15h-2a.5.5 0 0 1-.354-.854l1.412-1.412A.5.5 0 0 1 1.5 12.5v-3a.5.5 0 0 1 .166-.372l8.995-8.07zm-.115 1.47L2.727 9.52l3.753 3.753 7.406-8.254zm3.585 2.17.064-.068a1 1 0 0 0-.017-1.396L13.18 1.387a1 1 0 0 0-1.396-.018l-.068.065zM5.293 13.5 2.5 10.707v1.586L3.707 13.5z"/>
                            </svg>
                        </a>
                    </td>
                `;
                $("#dado").append(`<tbody><tr>${nome}${sobrenome}${cpf}${contato}${idade}${turno}${acompanhamento}${academia}${personal}${acoes}</tr></tbody>`);
            }
        },
    });
}

function chamarPersonais() {
    var termo = $("#termoPersonal").val();

    $.ajax({
        url: "/Personais/listarJson",
        data: "termo=" + termo,
        dataType: "json",
        success: function (p) {
            var titulo = `
                <thead class="table-light">
                    <tr>
                        <th>Nome</th>
						<th>Sobrenome</th>
						<th>CPF</th>
						<th>Contato</th>
						<th>Idade</th>
						<th>Salário</th>
						<th class="text-center">Ações</th>
                    </tr>
                </thead>
            `;

            $("#dado").html(titulo);

            for (var i = 0; i < p.length; i++) {
                var nome = `<td>${p[i].nome}</td>`;
                var sobrenome = `<td>${p[i].sobrenome}</td>`;
                var cpf = `<td>${p[i].cpf}</td>`;
                var contato = `<td>${p[i].contato}</td>`;
                var idade = `<td>${p[i].idade}</td>`;
                var salario = `<td>${p[i].salario}</td>`;
                var acoes = `
                    <td class="text-center" data-th="Ações">
                        <a href="/personais/remover?id=${p[i].id}" class="text-dark text-decoration-none">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                                <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06m6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528M8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
                            </svg>
                        </a>
                        <a href="/personais/editar?id=${p[i].id}" class="text-decoration-none">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-highlighter" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M11.096.644a2 2 0 0 1 2.791.036l1.433 1.433a2 2 0 0 1 .035 2.791l-.413.435-8.07 8.995a.5.5 0 0 1-.372.166h-3a.5.5 0 0 1-.234-.058l-.412.412A.5.5 0 0 1 2.5 15h-2a.5.5 0 0 1-.354-.854l1.412-1.412A.5.5 0 0 1 1.5 12.5v-3a.5.5 0 0 1 .166-.372l8.995-8.07zm-.115 1.47L2.727 9.52l3.753 3.753 7.406-8.254zm3.585 2.17.064-.068a1 1 0 0 0-.017-1.396L13.18 1.387a1 1 0 0 0-1.396-.018l-.068.065zM5.293 13.5 2.5 10.707v1.586L3.707 13.5z"/>
                            </svg>
                        </a>
                    </td>
                `;
                $("#dado").append(`<tbody><tr>${nome}${sobrenome}${cpf}${contato}${idade}${salario}${acoes}</tr></tbody>`);
            }
        },
    });
}

$(function () {
    $("#termoAcademia").on("input", function () {
        chamarAcademias();
    });
    
    $("#termoCliente").on("input", function () {
        chamarClientes();
    });
    
    $("#termoPersonal").on("input", function () {
        chamarPersonais();
    });
});
