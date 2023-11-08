package br.com.pedrociarlini.xairedirect.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemInformation {

	@Value("${system.version:temp}")
	private String appVersion;

	public String getAppVersion() {
		return appVersion;
	}
}
