/**
 * @author Gregor Smith - 2018
 */
package com.webfront.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link HashingService}
 */
@Service
public class HashingServiceHandler implements HashingService {

	/** Logger instance. */
	private static final Logger LOG = LogManager.getLogger(HashingServiceHandler.class);

	/** Salt service. */
	private final SaltService<Object> saltService;

	/**
	 * Constructor.
	 *
	 * @param saltService
	 *            Salt service.
	 */
	@Autowired
	public HashingServiceHandler(final SaltService<Object> saltService) {
		this.saltService = saltService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String buildPasswordHash(final String password, final String salt) {

		LOG.debug("Building password hash.");

		final String internalSalt;

		if (StringUtils.isBlank(salt)) {
			LOG.debug("No salt provided, generating salt.");
			internalSalt = saltService.createSalt(null, Object.class);
		} else {
			internalSalt = salt;
		}

		final String combinedKey = new StringBuilder(password).append(internalSalt)
				.toString();
		final byte[] combinedBytes = combinedKey.getBytes();
		final String hashedPassword = DigestUtils.sha256Hex(combinedBytes);

		LOG.debug("Password hash built.");

		return hashedPassword;
	}

}
