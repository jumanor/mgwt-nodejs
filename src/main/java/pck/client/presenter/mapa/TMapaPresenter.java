package pck.client.presenter.mapa;

import pck.client.AppController;
import pck.client.AppController.CIMapView;
import pck.client.AppController.CIServicioView;
import pck.client.event.login.TAddBackLoginEvent;
import pck.client.event.login.TAddServicioEvent;
import pck.client.event.login.TAddSolicitudEvent;
import pck.client.presenter.PresenterMGWT;
import pck.client.presenter.servicio.TServicioPresenter;
import pck.client.view.servicio.TServicioView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationEndCallback;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;

public class TMapaPresenter implements PresenterMGWT{
	
	public interface Display {
		
		 public Widget asWidget();
		 public HasTapHandlers getAddSolicitud();
		 public HasClickHandlers getAddBackLogin();
		 public HasClickHandlers getAddServicio();
		 
		 public void onAnimationEnd();
		 public String getDireccion();
		 public double getLatitud();
		 public double getLongiud();
		 
		 public void setLatitud(double latitud);
		 public void setLongitud(double longitud);
		 public void setDireccion(String direccion);
	}
	
	private Display display; 
	private AnimationHelper animationHelper;
	private HandlerManager eventBus;
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public TMapaPresenter(HandlerManager eventBus,Display view,CIMapView ciMapView){
		this.eventBus=eventBus;
		display=view;
		bind();
		
		display.setDireccion(ciMapView.direccion);
		display.setLatitud(ciMapView.latitud);
		display.setLongitud(ciMapView.longitud);
		
	}///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void bind(){
		
		display.getAddSolicitud().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				// TODO Auto-generated method stub
				//TSolicitudView view=new TSolicitudView();
				//TSolicitudPresenter presenter=new TSolicitudPresenter(view);
				//presenter.go(animationHelper, RootPanel.get());
				eventBus.fireEvent(new TAddSolicitudEvent(display.getDireccion(),display.getLatitud(),display.getLongiud()));
			}
		});
		display.getAddBackLogin().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new TAddBackLoginEvent());
			}
		});
		display.getAddServicio().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				//TServicioView view=new TServicioView();
				//TServicioPresenter presenter=new TServicioPresenter(eventBus, view);
				//presenter.go(animationHelper, RootPanel.get());
				//final AppController.CIServicioView ci=new AppController.CIServicioView();
				
				eventBus.fireEvent(new TAddServicioEvent(display.getDireccion(),display.getLatitud(),display.getLongiud()));
			}
		});
		
	}//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void go(AnimationHelper animationHelper,Animation animation, HasWidgets container) {
		// TODO Auto-generated method stub
		container.clear();
		container.add(animationHelper);
		this.animationHelper=animationHelper;
		animationHelper.goTo(display.asWidget(), animation,new AnimationEndCallback() {
			
			@Override
			public void onAnimationEnd() {
				// TODO Auto-generated method stub
				//secundario.showMapa();
				display.onAnimationEnd();
			}
		});
	}/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
