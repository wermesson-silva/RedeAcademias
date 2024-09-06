/*! jQuery v1.6.4 http://jquery.com/ | http://jquery.org/license */

document.addEventListener('DOMContentLoaded', function () {
    const radioSim = document.getElementById('flexRadioDefault1'); // Radio para "Sim"
    const radioNao = document.getElementById('não'); // Radio para "Não"
    const inputPersonal = document.getElementById('floatingPersonal'); // Campo "Nome do personal"
    const mensagem = document.getElementById('mensagemPersonal');

    // Função para verificar o estado dos radios e bloquear/desbloquear o campo de personal
    function togglePersonalInput() {
        if (radioNao.checked) {
            inputPersonal.readOnly = true;  // Bloqueia a digitação no campo de personal
            mensagem.style.display = 'inline';
            inputPersonal.style.borderColor = 'red';
            inputPersonal.value = "";
        } else if (radioSim.checked) {
            inputPersonal.readOnly = false;  // Permite a digitação no campo de personal
              mensagem.style.display = 'none';
               inputPersonal.style.borderColor = '';
        }
    }

    // Adiciona os eventos para verificar o estado dos radios
    radioSim.addEventListener('change', togglePersonalInput);
    radioNao.addEventListener('change', togglePersonalInput);

    // Chama a função ao carregar a página para garantir que o estado esteja correto
    togglePersonalInput();
});
