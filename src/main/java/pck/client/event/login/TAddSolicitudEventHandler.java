package pck.client.event.login;
import com.google.gwt.event.shared.EventHandler;


public interface TAddSolicitudEventHandler extends EventHandler {

	void onAddSolicitud(TAddSolicitudEvent event,String direccion,double latitud,double longitud);
}
