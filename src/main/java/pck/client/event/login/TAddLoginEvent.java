package pck.client.event.login;

import com.google.gwt.event.shared.GwtEvent;

public class TAddLoginEvent extends GwtEvent<TAddLoginEventHandler> {

	public static Type<TAddLoginEventHandler> TYPE = new Type<TAddLoginEventHandler>();
	 
	@Override
	public Type<TAddLoginEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(TAddLoginEventHandler handler) {
		// TODO Auto-generated method stub
		handler.onAddLogin(this);
	}

}
