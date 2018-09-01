(function($){
  $(function(){

    $('.sidenav').sidenav();
    $('.parallax').parallax();

  }); // end of document ready
})(jQuery); // end of jQuery name space

$(document).ready(function(){
  $('select').formSelect();
});

$(document).ready(function() {
   $('input#cnpj, textarea#textarea2').characterCounter();
});

$(document).ready(function(){
  $('.modal').modal();
});
