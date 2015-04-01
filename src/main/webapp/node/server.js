var io = require('socket.io').listen(9093);
var CTD=100;
io.sockets.on('connection', function (socket) {
  
  socket.on('newPedidoPhone',function(data,response){
	  
	    var respuesta={operacion:CTD};
	    CTD++;
	    var msn={};
		msn.data=respuesta;
		msn.status=1;
     	msn.message=null;
	
	    response(msn);
	  
  });
  
});
console.log('Escuchando en puerto 9093');
