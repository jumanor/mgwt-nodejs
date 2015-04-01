package pck.client.event.login;
import com.google.gwt.event.shared.EventHandler;


public interface TAddBackMapaEventHandler extends EventHandler {

	void onAddBackMapa(TAddBackMapaEvent event,String direccion,double latitud,double longitud);
}
