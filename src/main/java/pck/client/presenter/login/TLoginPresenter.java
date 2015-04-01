package pck.client.presenter.login;

import pck.client.event.login.TAddLoginEvent;
import pck.client.presenter.PresenterMGWT;
import pck.client.utis.TGlobal;
import pck.client.utis.TGlobalUsuario;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationEndCallback;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;


public class TLoginPresenter implements PresenterMGWT{
	
	public interface Display {
		
		 public Widget asWidget();
		 public HasTapHandlers getAddLogin();
		 public void verMenu();
	}
	private Display display; 
	private AnimationHelper animationHelper;
	private HandlerManager eventBus;
	
	public TLoginPresenter(HandlerManager eventBus,Display display){
		this.display=display;
		this.eventBus=eventBus;
		bind();
		
	}/////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void bind(){
		display.getAddLogin().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				// TODO Auto-generated method stub
				TGlobalUsuario.setTelefono("876563412");
				TGlobalUsuario.setCodPhone("SP40633367");
				
				eventBus.fireEvent(new TAddLoginEvent());
				
				
			}
		});

	}/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void go(AnimationHelper animationHelper,Animation animation,HasWidgets container) {
		// TODO Auto-generated method stub
		
		TGlobal.setAltoPantalla(Window.getClientHeight());
		
		container.clear();
		container.add(animationHelper);
		this.animationHelper=animationHelper;
		animationHelper.goTo(display.asWidget(), animation,new AnimationEndCallback() {
			
			@Override
			public void onAnimationEnd() {
				// TODO Auto-generated method stub
				//secundario.showMapa();
				display.verMenu();
			}
		});
	}/////////////////////////////////////////////////////////////////////////////////////////////////////////

}
