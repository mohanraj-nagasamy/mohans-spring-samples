package com.jsf2.test.app.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Dimitar Makariev
 */
@ManagedBean
@Scope("session")
public class PreferencesBean implements Serializable {

    public PreferencesBean() {
        this.currentLocale = FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
        this.menuPosition = "left";
        this.currentColor = "gray";
    }
    private static final long serialVersionUID = 5715138209989353221L;
    private Locale currentLocale;

    public Locale getCurrentLocale() {
        return this.currentLocale;
    }

    public List<Locale> getSupportedLocales() {

        List<Locale> locales = new ArrayList<Locale>();
        for (Iterator<Locale> it = FacesContext.getCurrentInstance().getApplication().getSupportedLocales(); it.hasNext();) {
            Locale loc = it.next();
            locales.add(loc);
        }
        return locales;
    }

    public void setCurrentLocaleLanguage(final String language) {
        this.currentLocale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(this.currentLocale);
    }

    public boolean getIsUserLoggedIn() {
        return isUserInRole("ROLE_USER") || isUserInRole("ROLE_ADMIN");
    }

    protected boolean isUserInRole(final String role) {
        final Collection<GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (Iterator it = grantedAuthorities.iterator(); it.hasNext();) {
            final GrantedAuthority ga = (GrantedAuthority) it.next();
            if (role.equals(ga.getAuthority())) {
                return true;
            }
        }
        return false;
    }
    private String menuPosition;

    public String getMenuPosition() {
        return menuPosition;
    }

    public void rightMenu() {
        menuPosition = "right";
    }

    public void leftMenu() {
        menuPosition = "left";
    }
    private String currentColor;

    public String getCurrentColor() {
        return currentColor;
    }

    public void grayTheme() {
        currentColor = "gray";
    }

    public void blueTheme() {
        currentColor = "blue";
    }
}
