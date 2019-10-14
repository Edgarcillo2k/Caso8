var lista = ['#svg0', '#svg1', '#svg2', '#svg3', '#svg4','#svg5','#svg6','#svg7', '#svg8', '#svg9', '#svg10', '#svg11'];

$('#nextGen').click(function(){
    $(lista[0]).toggle();
    lista.shift();
});




