package exp.gibin.app.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.atmosphere.client.TrackMessageSizeInterceptor;
import org.atmosphere.cpr.AnnotationProcessor;
import org.atmosphere.cpr.AtmosphereFramework;
import org.atmosphere.cpr.AtmosphereInterceptor;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereServlet;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.BroadcasterLifeCyclePolicy.ATMOSPHERE_RESOURCE_POLICY;
import org.atmosphere.cpr.ContainerInitializer;
import org.atmosphere.spring.bean.AtmosphereSpringContext;
import org.atmosphere.util.VoidAnnotationProcessor;
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

	/*
	 * This factory is used for lookup
	 */
	public static BroadcasterFactory broadcasterFactory;
	
	/*
	 * Atmosphere resource store
	 */

	public static Map<String, AtmosphereResource> atmophsereResourceStrore = new ConcurrentHashMap<String, AtmosphereResource>();

  // @Bean
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
	
	/*
	 * This @Bean annotation is commented for activating atmosphere handler
	 */

	//@Bean
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
	


	//@Bean
	public AtmServelets getAtmServelets() {
		return new AtmServelets();
	}

	/*
	 * Handler Enpoint handled here
	 * 
     */


	@Bean
	public AtmosphereFramework getAtmopshereframework()
			throws ServletException, InstantiationException, IllegalAccessException {
		AtmosphereFramework atmosphereFramework = new AtmosphereFramework(false, false);
		atmosphereFramework.addAtmosphereHandler("/handler", getAtmopherehandler(), interceptors());
	
		return atmosphereFramework;
	}
	
	private List<AtmosphereInterceptor> interceptors() {
		List<AtmosphereInterceptor> atmosphereInterceptors = new ArrayList<>();
		// atmosphereInterceptors.add(new TrackMessageSizeInterceptor());
		return atmosphereInterceptors;
	}
	
	@Bean
	public AtmHandler getAtmopherehandler() {
		return new AtmHandler();
	}
	
	//@Bean
	public BroadcasterFactory broadcasterFactory() throws ServletException, InstantiationException, IllegalAccessException {
		return getAtmopshereframework().getAtmosphereConfig().getBroadcasterFactory();
	}
	
	@Bean
	public AtmosphereSpringContext atmosphereSpringContext() {
		AtmosphereSpringContext atmosphereSpringContext = new AtmosphereSpringContext();
		Map<String, String> map = new HashMap<>();
		map.put("org.atmosphere.cpr.broadcasterClass", org.atmosphere.cpr.DefaultBroadcaster.class.getName());
		map.put(AtmosphereInterceptor.class.getName(), TrackMessageSizeInterceptor.class.getName());
		map.put(AnnotationProcessor.class.getName(), VoidAnnotationProcessor.class.getName());
		map.put("org.atmosphere.cpr.broadcasterLifeCyclePolicy", ATMOSPHERE_RESOURCE_POLICY.IDLE_DESTROY.toString());
		atmosphereSpringContext.setConfig(map);
		
				return atmosphereSpringContext;
	}

}
