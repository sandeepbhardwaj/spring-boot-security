package com.koko.oaut2;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@Component
public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("======================Session Created========================");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("======================Session Destroyed========================");
	}
}