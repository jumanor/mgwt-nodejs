package pck.client.event.login;

import com.google.gwt.event.shared.GwtEvent;

public class TAddBackMapaEvent extends GwtEvent<TAddBackMapaEventHandler> {

	public static Type<TAddBackMapaEventHandler> TYPE = new Type<TAddBackMapaEventHandler>();
	 
	@Override
	public Type<TAddBackMapaEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(TAddBackMapaEventHandler handler) {
		// TODO Auto-generated method stub
		handler.onAddBackMapa(this,direccion,latitud,longitud);
	}
	private String direccion;
	private double latitud;
	private double longitud;
	
	public TAddBackMapaEvent(String direccion,double latitud,double longitud){
		this.direccion=direccion;
		this.latitud=latitud;
		this.longitud=longitud;
	}
}
