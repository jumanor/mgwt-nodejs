package pck.client.event.login;

import com.google.gwt.event.shared.GwtEvent;

public class TAddSolicitudEvent extends GwtEvent<TAddSolicitudEventHandler> {

	public static Type<TAddSolicitudEventHandler> TYPE = new Type<TAddSolicitudEventHandler>();
	 
	@Override
	public Type<TAddSolicitudEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(TAddSolicitudEventHandler handler) {
		// TODO Auto-generated method stub
		handler.onAddSolicitud(this,direccion,latitud,longitud);
	}
	private String direccion;
	private double latitud;
	private double longitud;
	
	public TAddSolicitudEvent(String direccion,double latitud,double longitud){
		this.direccion=direccion;
		this.latitud=latitud;
		this.longitud=longitud;
	}

}
