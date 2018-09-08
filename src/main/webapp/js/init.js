(function ($) {
    $(function () {

        $('.sidenav').sidenav();
        $('.parallax').parallax();

    }); // end of document ready
})(jQuery); // end of jQuery name space

$(document).ready(function () {
    $('select').formSelect();
});

$(document).ready(function () {
    $('input#cnpj, textarea#textarea2, \n\
        textarea#descricaoVaga, textarea#habilidades, textarea#atitudes, \n\
        textarea#idiomas, textarea#missao, textarea#visao').characterCounter();
});

$(document).ready(function () {
    $('.modal').modal();
});
