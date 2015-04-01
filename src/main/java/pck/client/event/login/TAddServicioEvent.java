package pck.client.event.login;

import pck.client.AppController.CIServicioView;

import com.google.gwt.event.shared.GwtEvent;

import pck.client.AppController.CIServicioView;

public class TAddServicioEvent extends GwtEvent<TAddServicioEventHandler> {

	public static Type<TAddServicioEventHandler> TYPE = new Type<TAddServicioEventHandler>();
	 
	@Override
	public Type<TAddServicioEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}
	private String direccion;
	private double latitud;
	private double longitud;
	
	@Override
	protected void dispatch(TAddServicioEventHandler handler) {
		// TODO Auto-generated method stub
		handler.onAddServicio(this,direccion,latitud,longitud);
	}
	public TAddServicioEvent(String direccion,double latitud,double longitud){
		this.direccion=direccion;
		this.latitud=latitud;
		this.longitud=longitud;
	}
}
