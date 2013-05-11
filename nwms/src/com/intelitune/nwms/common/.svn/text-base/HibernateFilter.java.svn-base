package com.intelitune.nwms.common;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.cfg.Configuration;

import com.intelitune.ccos.client.WMSServicePortType;
import com.intelitune.ccos.client.WMSServicePortTypeProxy;

public class HibernateFilter implements Filter {
	
    private static Log log = LogFactory.getLog(HibernateFilter.class);
    private WMSServicePortType  wmsService=new WMSServicePortTypeProxy();
    private SessionFactory filterSf;
    private long remoteThreadId=0;
    static{
    	new Configuration().configure().buildSessionFactory();
    }
    
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
        try {
        	
            log.debug("Starting a database transaction");
            filterSf.getCurrentSession().beginTransaction();

            // Call the next filter (continue request processing)
            arg2.doFilter(arg0, arg1);

            // Commit and cleanup
            log.debug("Committing the database transaction");
            filterSf.getCurrentSession().getTransaction().commit();
   

        } catch (Exception ex) {
            log.error("This interceptor does not implement optimistic concurrency control!");
            log.error("Your application will not work until you add compensation actions!");
            // Rollback, close everything, possibly compensate for any permanent changes
            // during the conversation, and finally restart business conversation. Maybe
            // give the user of the application a chance to merge some of his work with
            // fresh data... what you do here depends on your applications design.
            filterSf.getCurrentSession().getTransaction().rollback();
            ex.printStackTrace();
        } finally{
        	filterSf.getCurrentSession().close();
        }
        
	}

	
	public void init(FilterConfig arg0) throws ServletException {
		
        log.debug("Initializing filter...");
        log.debug("Obtaining SessionFactory from static HibernateUtil singleton");
		try {
			filterSf = getSessionFactory();
		} catch (Exception e) {
			new Configuration().configure().buildSessionFactory();
		}
	}
	
	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("nWMSSessionFactory");
		} catch (Exception e) {
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

}
