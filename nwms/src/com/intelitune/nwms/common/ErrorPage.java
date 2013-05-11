package com.intelitune.nwms.common;

import java.rmi.RemoteException;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.intelitune.ccos.client.WMSServicePortType;
import com.intelitune.ccos.client.WMSServicePortTypeProxy;

public class ErrorPage extends net.sf.click.util.ErrorPage {

	public String error = null;
	 private static Log log = LogFactory.getLog(ErrorPage.class);
	    private WMSServicePortType  wmsService=new WMSServicePortTypeProxy();
	    private SessionFactory sf;
//	    static{
//	    	new Configuration().configure().buildSessionFactory();
//	    }
	    
	    public String getTemplate(){
	    	return "/templates/wms/wms_template.htm";
	    }
	    public ErrorPage(){
	    	String a = (String)this.getContext().getSessionAttribute("eee");
	    	if(a==null){
	    		error= this.getMessage("error20");
	    	}
	    	else{
	    		error = a ;
	    	}
	    	sf = getSessionFactory();
	    	try {
                if (sf.getCurrentSession().getTransaction().isActive()) {
                    log.debug("Trying to rollback database transaction after exception");
                    sf.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
                log.error("Could not rollback transaction after exception!", rbEx);
            }
            sf.getCurrentSession().getTransaction().begin();
//	    	try {
//				wmsService.closeSession("");
//			} catch (RemoteException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
	    }
	    
	    protected SessionFactory getSessionFactory() {
			try {
				return (SessionFactory) new InitialContext()
						.lookup("nWMSSessionFactory");
			} catch (Exception e) {
				log.error("Could not locate SessionFactory in JNDI", e);
				throw new IllegalStateException(
						"Could not locate SessionFactory in JNDI");
			}
		}
	
}
