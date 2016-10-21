// Generated code from Butter Knife. Do not modify!
package com.mantambakberas.iamantaras.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegisterActivity$$ViewBinder<T extends com.mantambakberas.iamantaras.activity.RegisterActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493039, "field 'register'");
    target.register = finder.castView(view, 2131493039, "field 'register'");
    view = finder.findRequiredView(source, 2131493038, "field 'txtName'");
    target.txtName = finder.castView(view, 2131493038, "field 'txtName'");
    view = finder.findRequiredView(source, 2131492991, "field 'txtEmail'");
    target.txtEmail = finder.castView(view, 2131492991, "field 'txtEmail'");
    view = finder.findRequiredView(source, 2131492992, "field 'txtPassword'");
    target.txtPassword = finder.castView(view, 2131492992, "field 'txtPassword'");
  }

  @Override public void unbind(T target) {
    target.register = null;
    target.txtName = null;
    target.txtEmail = null;
    target.txtPassword = null;
  }
}
