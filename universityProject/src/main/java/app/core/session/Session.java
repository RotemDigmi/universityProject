package app.core.session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Session {

	private Map<String, Object> parameters = new HashMap<>();
	public final String token;
	private long lastAccessed;
	@Value("${session.max.inactive.interval:5}")
	private long maxInactiveInterval;
	private static final int TOKEN_MAX_LENGTH = 12;
	
	{
		this.token = UUID.randomUUID().toString().replace("-", "").substring(0, TOKEN_MAX_LENGTH);
		resetLastAccessed();
	}
	
	public void resetLastAccessed() {
		this.lastAccessed = System.currentTimeMillis();
	}
	
	@PostConstruct
	private void init() {
		maxInactiveInterval = TimeUnit.MINUTES.toMillis(maxInactiveInterval);
	}

	public Object getParameters(String paramName) {
		return parameters.get(paramName);
	}

	public void setParameters(String paramName, Object paramVal) {
		parameters.put(paramName, paramVal);
	}

	public long getLastAccessed() {
		return lastAccessed;
	}

	public long getMaxInactiveInterval() {
		return maxInactiveInterval;
	}
	
	
	
}
