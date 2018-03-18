/**
 * @author Gregor Smith - 2018
 */
package com.webfront.integration;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles http requests to the backing API.
 */
@Component
public interface ClientRequest {

	/**
	 * Performs a request.
	 *
	 * @param path
	 *            The path to make the request to. Context path is set in
	 *            WebFront.config properties file.
	 * @param requestMethod
	 *            Http request method.
	 * @param queryParams
	 *            Map of query parameters.
	 * @param content
	 *            Body of the request.
	 * 
	 * @return Response.
	 */
	Response performClientRequest(String path, RequestMethod requestMethod,
			Map<String, String> queryParams,
			Object content);
}
