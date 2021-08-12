package app.core.session;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SessionContaxt {

	@Autowired
	private ApplicationContext ctx;
	private Map<String, Session> sessionsMap = new ConcurrentHashMap<String, Session>();
	@Value("${session.remove.expired.period:20}")
	private int removeExpiredPeriod;
	
	private boolean isSessionExpired(Session session) {
		return System.currentTimeMillis() - session.getLastAccessed() > session.getMaxInactiveInterval();
	}
	
	public void invalidate(Session session) {
		sessionsMap.remove(session.token);
	}
	
	public Session createSession() {
		Session s = ctx.getBean(Session.class);
		sessionsMap.put(s.token, s);
		return s;
	}
	
	public Session getSession(String sessionToken) {
		Session s = sessionsMap.get(sessionToken);
		if(s != null) {
			if(!isSessionExpired(s)) {
				s.resetLastAccessed();
				return s;
		}else {
			invalidate(s);
			return null;
		}
			
		}else {
			return null;
			
		}
	}

	@Scheduled(initialDelay = 10000L, fixedDelayString = "${session.context.activate.task.time}")
	public void init() {
		System.out.println(new Date());
		for (String sessionToken : sessionsMap.keySet()) {
			Session s = sessionsMap.get(sessionToken);
			if(isSessionExpired(s)) {
				invalidate(s);
			}
		}
	}
	
}
