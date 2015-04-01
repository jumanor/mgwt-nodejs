package pck.client.presenter.solicitud;
import pck.client.event.login.TAddBackMapaEvent;
import pck.client.presenter.PresenterMGWT;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationEndCallback;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
public class TSolicitudPresenter implements PresenterMGWT {

	public interface Display {
		
		 public Widget asWidget();
		 public void setDireccion(String direccion);
		 public HasClickHandlers getAddBackMapa();
		 public void setLatitud(double latitud);
		 public void setLongitud(double longitud);
		 
		 public double getLatitud();
		 public double getLongitud();
		 public String getDireccion();
		 
	}
	
	private Display display; 
	private HandlerManager eventBus;
	private String direccion;
	
	private double latitud;
	private double longitud;
	
	public TSolicitudPresenter(HandlerManager eventBus,Display view){
		this.eventBus=eventBus;
		display=view;
		bind();
	}/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void bind(){
		display.getAddBackMapa().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new TAddBackMapaEvent(display.getDireccion(),display.getLatitud(),display.getLongitud()));
			}
		});
		
	}/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setDireccion(String direccion){
		//display.setDireccion(direccion);
		this.direccion=direccion;
	}
	public void setLatitud(double latitud){
		//display.setDireccion(direccion);
		this.latitud=latitud;
	}
	public void setLongitud(double longitud){
		//display.setDireccion(direccion);
		this.longitud=longitud;
	}
	@Override
	public void go(AnimationHelper animationHelper,Animation animation, HasWidgets container) {
		// TODO Auto-generated method stub
		
		//RootPanel.get().clear();
		//RootPanel.get().add(animationHelper);
		
		
		container.clear();
		container.add(animationHelper);
	
		animationHelper.goTo(display.asWidget(),animation,new AnimationEndCallback() {
			
			@Override
			public void onAnimationEnd() {
				// TODO Auto-generated method stub
				//secundario.showMapa();
				display.setDireccion(direccion);
				display.setLatitud(latitud);
				display.setLongitud(longitud);
			}
		});
		
		
		/*
		container.clear();
		container.add(display.asWidget());
		*/
	}/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
