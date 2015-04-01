package pck.client.event.login;
import pck.client.AppController.CIServicioView;

import com.google.gwt.event.shared.EventHandler;


public interface TAddServicioEventHandler extends EventHandler {

	void onAddServicio(TAddServicioEvent event,String direccion,double latitud,double longitud);
}
