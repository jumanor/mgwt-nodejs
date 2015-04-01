package pck.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;

public abstract interface PresenterMGWT {
  public abstract void go(AnimationHelper animationHelper,Animation animation,final HasWidgets container);
  //public abstract void bind();
}
