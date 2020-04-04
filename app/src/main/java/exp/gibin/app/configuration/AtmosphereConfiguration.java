package exp.gibin.app.configuration;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.atmosphere.cpr.AtmosphereFramework;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereServlet;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.ContainerInitializer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import exp.gibin.app.controller.AtmHandler;
import exp.gibin.app.controller.AtmServelets;

/*
 * For sample 01
 *
 */

@Configuration
@EnableAutoConfiguration
public class AtmosphereConfiguration {

	public static BroadcasterFactory broadcasterFactory;

	public static Map<String, AtmosphereResource> atmophsereResourceStrore = new ConcurrentHashMap<String, AtmosphereResource>();

	@Bean
	public EmbeddedAtmosphereInitializer atmosphereInitializer() {
		return new EmbeddedAtmosphereInitializer();
	}

	private static class EmbeddedAtmosphereInitializer extends ContainerInitializer
			implements ServletContextInitializer {

		@Override
		public void onStartup(ServletContext servletContext) throws ServletException {
			onStartup(Collections.<Class<?>>emptySet(), servletContext);
		}

	}
	// first websocket end point is configured here

	@Bean
	public ServletRegistrationBean atmosphereServlet() {
		// Dispatcher servlet is mapped to '/home' to allow the AtmosphereServlet
		// to be mapped to '/chat'
		ServletRegistrationBean registration = new ServletRegistrationBean(new AtmosphereServlet(), "/chathome");

		registration.addInitParameter("org.atmosphere.cpr.packages", "sample");
		registration.addInitParameter(
				"org.atmosphere.interceptor.HeartbeatInterceptor" + ".clientHeartbeatFrequencyInSeconds", "100");

		registration.addInitParameter("org.atmosphere.cpr.broadcaster.shareableThreadPool", "true");
		registration.addInitParameter("org.atmosphere.cpr.broadcaster.maxProcessingThreads", "10");
		registration.addInitParameter("org.atmosphere.cpr.broadcaster.maxAsyncWriteThreads", "10");

		registration.setLoadOnStartup(0);
		//registration.setLoadOnStartup(3);
		registration.setAsyncSupported(true);
		// Need to occur before the EmbeddedAtmosphereInitializer
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);

		return registration;
	}

	@Bean
	public AtmServelets getAtmServelets() {
		return new AtmServelets();
	}

	/*
	 * Handler Enpoint handled here
	 * 
     */

	@Bean
	public AtmHandler getAtmopherehandler() {
		return new AtmHandler();
	}

	@Bean
	public AtmosphereFramework getAtmopshereframework()
			throws ServletException, InstantiationException, IllegalAccessException {
		AtmosphereFramework atmosphereFramework = new AtmosphereFramework(false, false);
		atmosphereFramework.addAtmosphereHandler("/handler", getAtmopherehandler());
		return atmosphereFramework;
	}

}
