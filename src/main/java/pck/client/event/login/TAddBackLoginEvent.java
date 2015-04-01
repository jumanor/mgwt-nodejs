package pck.client.event.login;

import com.google.gwt.event.shared.GwtEvent;

public class TAddBackLoginEvent extends GwtEvent<TAddBackLoginEventHandler> {

	public static Type<TAddBackLoginEventHandler> TYPE = new Type<TAddBackLoginEventHandler>();
	 
	@Override
	public Type<TAddBackLoginEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}
	@Override
	protected void dispatch(TAddBackLoginEventHandler handler) {
		// TODO Auto-generated method stub
		handler.onAddBackLogin(this);
	}
	
	
}
