// Generated code from Butter Knife. Do not modify!
package com.mantambakberas.iamantaras.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ProfileActivity$$ViewBinder<T extends com.mantambakberas.iamantaras.activity.ProfileActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493006, "field 'fotoView'");
    target.fotoView = finder.castView(view, 2131493006, "field 'fotoView'");
    view = finder.findRequiredView(source, 2131493007, "field 'nameView'");
    target.nameView = finder.castView(view, 2131493007, "field 'nameView'");
    view = finder.findRequiredView(source, 2131493018, "field 'infoView'");
    target.infoView = finder.castView(view, 2131493018, "field 'infoView'");
    view = finder.findRequiredView(source, 2131493008, "field 'emailView'");
    target.emailView = finder.castView(view, 2131493008, "field 'emailView'");
    view = finder.findRequiredView(source, 2131493026, "field 'profesiView'");
    target.profesiView = finder.castView(view, 2131493026, "field 'profesiView'");
    view = finder.findRequiredView(source, 2131493029, "field 'pelatihanView'");
    target.pelatihanView = finder.castView(view, 2131493029, "field 'pelatihanView'");
    view = finder.findRequiredView(source, 2131493032, "field 'penghargaanView'");
    target.penghargaanView = finder.castView(view, 2131493032, "field 'penghargaanView'");
    view = finder.findRequiredView(source, 2131493034, "field 'referensiView'");
    target.referensiView = finder.castView(view, 2131493034, "field 'referensiView'");
    view = finder.findRequiredView(source, 2131493037, "field 'minatView'");
    target.minatView = finder.castView(view, 2131493037, "field 'minatView'");
    view = finder.findRequiredView(source, 2131493021, "field 'act_setelan'");
    target.act_setelan = finder.castView(view, 2131493021, "field 'act_setelan'");
  }

  @Override public void unbind(T target) {
    target.fotoView = null;
    target.nameView = null;
    target.infoView = null;
    target.emailView = null;
    target.profesiView = null;
    target.pelatihanView = null;
    target.penghargaanView = null;
    target.referensiView = null;
    target.minatView = null;
    target.act_setelan = null;
  }
}
