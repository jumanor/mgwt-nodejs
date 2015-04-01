package pck.client.presenter.servicio;

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

import pck.client.AppController.CIServicioView;
import pck.client.event.login.TAddBackMapaEvent;
import pck.client.presenter.PresenterMGWT;

public class TServicioPresenter implements PresenterMGWT {

	public interface Display {
		
		 public Widget asWidget();
		 public void onAnimationEnd();
		 public HasClickHandlers getAddBackMapa();
	}
	
	private Display display;
	private AnimationHelper animationHelper;
	private HandlerManager eventBus;
	private CIServicioView ciServicioView;
	
	public TServicioPresenter(HandlerManager eventBus,Display display,CIServicioView ciServicioView){
		this.eventBus=eventBus;
		this.display=display;
		this.ciServicioView=ciServicioView;
		bind();
	}
	public void bind(){
		display.getAddBackMapa().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new TAddBackMapaEvent(ciServicioView.direccion,ciServicioView.latitud,ciServicioView.longitud));
			}
		});
	}
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
	}

}
