/**
 * @author Gregor Smith - 2018
 */
package com.webfront.service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link SaltService}
 *
 * @param <T>
 *            Type to generate salt from.
 */
@Service
public class SaltServiceHandler<T> implements SaltService<T> {

	/** Logger instance. */
	private static final Logger LOG = LogManager.getLogger(SaltService.class);

	/** Salt length. */
	private static final int SALT_VALUE_LENGTH = 32;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String createSalt(final T object, final Class<T> type) {

		LOG.debug("Starting to generate salt for object of type: {}", type);

		final Field[] typeFields = type.getDeclaredFields();

		final StringBuilder fieldValueConcatonation = new StringBuilder();

		LOG.debug("Iterating through object fields to obtain salt values.");

		for (final Field field : typeFields) {

			if (null == object) {
				LOG.debug(
						"Object was null, cannot generate salt from fields. Reverting to random character generation.");
				break;
			}

			if (field.getName().toLowerCase().contains("pass")) {
				continue;
			}

			String fieldValue = "";
			try {
				final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(
						field.getName(), type);
				final Method getter = propertyDescriptor.getReadMethod();

				if (null != getter) {

					final Object fieldObject = getter.invoke(object);

					if (null != fieldObject) {
						fieldValue = fieldObject.toString();
					}
				}
			} catch (final IllegalArgumentException e) {
				LOG.warn("Could not obtain salt value for field: {}. Exception was {}",
						field.getName(), e);
			} catch (final IllegalAccessException e) {
				LOG.warn("Could not obtain salt value for field: {}. Exception was {}",
						field.getName(), e);
			} catch (final IntrospectionException e) {
				LOG.warn("Could not obtain salt value for field: {}. Exception was {}",
						field.getName(), e);
			} catch (final InvocationTargetException e) {
				LOG.warn("Could not obtain salt value for field: {}. Exception was {}",
						field.getName(), e);
			}

			LOG.debug("Successfully obtained value for field: {}. Adding to salt.", field.getName());

			fieldValueConcatonation.append(fieldValue);
		}

		if (fieldValueConcatonation.length() < SALT_VALUE_LENGTH) {

			LOG.debug("Salt value below required length, generating characters to append.");

			final int requiredChars = SALT_VALUE_LENGTH
					- fieldValueConcatonation.length();

			final Random random = new Random();

			final byte[] saltBytes = new byte[requiredChars];

			random.nextBytes(saltBytes);

			final String randomChars = new String(saltBytes);
			fieldValueConcatonation.append(randomChars);
		}

		LOG.debug("Salt successfully generated.");

		return fieldValueConcatonation.toString();
	}
}
