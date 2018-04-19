package org.smartsupply.api.utils;

import liquibase.FileOpener;
import liquibase.Liquibase;
import liquibase.exception.LiquibaseException;
import org.smartsupply.model.exception.RmsUnexpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;

// This class handles the execution of the application liquibase changesets
public class SpringLiquibaseUpdater implements ResourceLoaderAware, FileOpener {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private DataSource dataSource;
	private String changeLog;
	private String contexts;
	private ResourceLoader resourceLoader;

	/**
	 * initialization method used to setup the liquibase subsystem and also
	 * execution of the changesets
	 * 
	 * @throws Exception
	 *             throws this exception when their is an exception in the
	 *             initialization or execution of the liquibase subsystem or
	 *             execution of the liquibase changesets
	 */
	public void init() throws Exception {
		try {
			log.info("Change-log is " + changeLog);
			this.getResourceAsStream(getChangeLog());
		} catch (Exception ex) {
			log.warn("LiquibaseChangeLog doesnot exist: " + getChangeLog());
			return;
		}

		try {

			Liquibase liquibase = new Liquibase(getChangeLog(), this, getDataSource().getConnection());
			liquibase.update(contexts);
		} catch (LiquibaseException ex) {
			throw new RmsUnexpectedException("Liquibase Database Update failure", ex);
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getChangeLog() {
		return changeLog;
	}

	public void setChangeLog(String changeLog) {
		this.changeLog = changeLog;
	}

	private ResourceLoader getResourceLoader() {
		return this.resourceLoader;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public InputStream getResourceAsStream(String file) throws IOException {
		return getResourceLoader().getResource(appendClassPathPrefixIfNecessary(file)).getInputStream();
	}

	private String appendClassPathPrefixIfNecessary(String file) {
		if (isClassPathPrefixPresent(changeLog) && !isClassPathPrefixPresent(file)) {
			return ResourceLoader.CLASSPATH_URL_PREFIX + file;
		} else {
			return file;
		}
	}

	private boolean isClassPathPrefixPresent(String file) {
		return file.startsWith(ResourceLoader.CLASSPATH_URL_PREFIX);
	}

	@Override
	public Enumeration<URL> getResources(String resource) throws IOException {
		Vector<URL> urls = new Vector<>();
		urls.add(getResourceLoader().getResource(resource).getURL());
		return urls.elements();
	}

	@Override
	public ClassLoader toClassLoader() {
		return getResourceLoader().getClassLoader();
	}
}
