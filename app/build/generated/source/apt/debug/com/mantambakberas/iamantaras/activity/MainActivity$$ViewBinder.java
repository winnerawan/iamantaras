// Generated code from Butter Knife. Do not modify!
package com.mantambakberas.iamantaras.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.mantambakberas.iamantaras.activity.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493000, "field 'act_share'");
    target.act_share = finder.castView(view, 2131493000, "field 'act_share'");
    view = finder.findRequiredView(source, 2131492999, "field 'act_profile'");
    target.act_profile = finder.castView(view, 2131492999, "field 'act_profile'");
    view = finder.findRequiredView(source, 2131493007, "field 'nameView'");
    target.nameView = finder.castView(view, 2131493007, "field 'nameView'");
    view = finder.findRequiredView(source, 2131493008, "field 'emailView'");
    target.emailView = finder.castView(view, 2131493008, "field 'emailView'");
    view = finder.findRequiredView(source, 2131493006, "field 'fotoView'");
    target.fotoView = finder.castView(view, 2131493006, "field 'fotoView'");
    view = finder.findRequiredView(source, 2131493017, "field 'act_logout'");
    target.act_logout = finder.castView(view, 2131493017, "field 'act_logout'");
  }

  @Override public void unbind(T target) {
    target.act_share = null;
    target.act_profile = null;
    target.nameView = null;
    target.emailView = null;
    target.fotoView = null;
    target.act_logout = null;
  }
}
