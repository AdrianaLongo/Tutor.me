

// Per far prendere all'header l'altezza della finestra
$(document).ready(function(){
    $('.header').height($(window).height());
})


// LOGIN
$("#btnGetOffer").click(function(event) {

    //Fetch form to apply custom Bootstrap validation
    var form = $("#formGetOffer")

    if (form[0].checkValidity() === false) {
        event.preventDefault()
        event.stopPropagation()
    }

    form.addClass('was-validated');
});

// SCROLLING TRA PAGINE DA NAVBAR
$(".navbar a").click(function(){
    $("body,html").animate({
        scrollTop:$("#" + $(this).data('value')).offset().top
    },1000)

})



