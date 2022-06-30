package gov.epa.analyticalqc.web_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import gov.epa.analyticalqc.dto.DashboardResponse;

@Service
public class DashboardWebClient {

	@Value("${dashboard.url}")
	private String dashboardApiUrl;

	private final WebClient webClient;
	
	public DashboardWebClient() {
		this.webClient = WebClient.create();
	}

	public DashboardResponse[] get(String dtxsid) {
		try {
			DashboardResponse[] response = this.webClient.get()
				.uri(dashboardApiUrl + "/{dtxsid}", dtxsid)
				.retrieve()
				.bodyToMono(DashboardResponse[].class)
				.block();
			return response;
		} catch (WebClientResponseException e) {
			return null;
		}
	}

}
