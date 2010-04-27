package com.jsf2.test.app.web;

import com.jsf2.test.common.web.FacesUtils;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Dimitar Makariev
 */
@ManagedBean("loginBean")
@Scope("request")
public class LoginBean {

    private final AuthenticationManager am;

    @Inject
    public LoginBean(@Named("authenticationManager") AuthenticationManager am) {
        this.am = am;
    }

    public String login() {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(this.getUserName(), getPassword());
            Authentication result = am.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);

        } catch (AuthenticationException e) {
            String loginFailedMessage = FacesUtils.getBundleKey("msg", "login.failed");
            FacesUtils.addErrorMessage(loginFailedMessage);
            return null;
        }
        return "/secured/person/list?faces-redirect=true";
    }
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
