package org.smartsupply.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RmsPropertyPlaceHolderConfigurer extends
		PropertyPlaceholderConfigurer {
	private String classPathPropertiesFilename = "RMS_SETTINGS.properties";
	private String environmentVariable = "RMS_SETTINGS";
	private Properties applicationSettings;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void loadProperties(Properties props) throws IOException {
		super.loadProperties(props);
		loadContextBasedProperties(props);
		this.applicationSettings = props;
	}

	private void loadContextBasedProperties(Properties props)
			throws IOException {
		// only attempt to load new properties if they were not found in the
		// default location
		if (props.isEmpty()) {
			loadFromClassPath(props);
			loadFromEnvironmentVariable(props);
		}
	}

	private void loadFromClassPath(Properties props) throws IOException {
		InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(getClassPathPropertiesFilename());
		if (stream != null) {
			props.load(stream);
			super.setProperties(props);
		}
	}

	private void loadFromEnvironmentVariable(Properties props)
			throws IOException {
		File settingsFile = getSettingsFile(environmentVariable);
		if (settingsFile != null) {
			log.info("Attempting to load properties from: "
					+ settingsFile.getAbsolutePath());
			setFileSystemLocation(settingsFile);
			super.loadProperties(props);
		}
	}

	private void setFileSystemLocation(File fileLocation) {
		if (fileLocation != null) {
			FileSystemResource resource = new FileSystemResource(fileLocation);
			setLocation(resource);
		}
	}

	private File getSettingsFile(String environVariable) {
		String systemVariable = System.getenv(environVariable);
		if (systemVariable == null) {
			systemVariable = System.getProperty(environVariable);
		}

		File settingsFile = null;
		if (systemVariable != null) {
			settingsFile = new File(systemVariable);
		} else {
			log.warn("Unable to find environment or system variable: " + environVariable);
		}

		return settingsFile;
	}

	public Properties getApplicationProperties() {
		return applicationSettings;
	}

	public void setApplicationProperties(Properties appProps) {
		this.applicationSettings = appProps;
	}

	public String getPropertiesFilename() {
		return classPathPropertiesFilename;
	}

	public void setPropertiesFilename(String propertiesFilename) {
		this.classPathPropertiesFilename = propertiesFilename;
	}

	public String getClassPathPropertiesFilename() {
		return classPathPropertiesFilename;
	}

	public void setClassPathPropertiesFilename(String classPathPropertiesFilename) {
		this.classPathPropertiesFilename = classPathPropertiesFilename;
	}

	public String getEnvironmentVariable() {
		return environmentVariable;
	}

	public void setEnvironmentVariable(String environmentVariable) {
		this.environmentVariable = environmentVariable;
	}
}
