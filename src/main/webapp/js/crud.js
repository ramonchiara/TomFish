function createForm() {
    $.ajax({
        url: 'webresources/pessoas',
        method: 'POST',
        data: {
            nome: $('#nome').val(),
            peso: $('#peso').val(),
            altura: $('#altura').val()
        },
        statusCode: {
            201: function () {
                alert("Pessoa criada com sucesso!");
            },
            409: function () {
                alert("Já existe uma pessoa cadastrada com esse nome!");
            }
        }
    });
}

function createJson() {
    $.ajax({
        url: 'webresources/pessoas',
        method: 'POST',
        data: JSON.stringify({
            nome: $('#nome').val(),
            peso: $('#peso').val(),
            altura: $('#altura').val()
        }),
        contentType: 'application/json',
        statusCode: {
            201: function () {
                alert("Pessoa criada com sucesso!");
            },
            409: function () {
                alert("Já existe uma pessoa cadastrada com esse nome!");
            }
        }
    });
}

function findAll() {
    $.ajax({
        url: 'webresources/pessoas',
        method: 'GET',
        success: function (data) {
            var out = '';
            for (var i = 0; i < data.length; i++) {
                var pessoa = data[i];
                out = out + pessoa.nome + " está " + pessoa.imc.categoria + "\n";
            }
            $('#resultado').val(out);
        }
    });
}

function findByName() {
    $.ajax({
        url: 'webresources/pessoas/' + $('#nome2').val(),
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            var pessoa = data;
            $('#resultado').val(pessoa.nome + " está " + pessoa.imc.categoria);
        },
        statusCode: {
            404: function () {
                alert("Pessoa não encontrada!");
            }
        }
    });
}

function update() {
    $.ajax({
        url: 'webresources/pessoas/' + $('#nome3').val(),
        method: 'PUT',
        data: JSON.stringify({
            nome: $('#nome3').val(),
            peso: $('#peso3').val(),
            altura: $('#altura3').val()
        }),
        contentType: 'application/json',
        success: function (data) {
            alert("Pessoa atualizada!");
        },
        statusCode: {
            404: function () {
                alert("Pessoa não encontrada!");
            }
        }
    });
}

function remove() {
    $.ajax({
        url: 'webresources/pessoas/' + $('#nome4').val(),
        method: 'DELETE',
        success: function (data) {
            alert("Pessoa removida!");
        },
        statusCode: {
            404: function () {
                alert("Pessoa não encontrada!");
            }
        }
    });
}
