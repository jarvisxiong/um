/**
 * 
 */
package com.hhz.uums.utils;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springside.modules.spring.SpringContextHolder;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocaleProvider;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.TextProviderFactory;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author huangj 2009-12-4
 */
public class MessageUtils {
	protected static Logger LOG = LoggerFactory.getLogger(MessageUtils.class);

	/**
	 * Spring国际化
	 */
	private static MessageSource messageSource = (MessageSource) SpringContextHolder.getBean("messageSource");

	/**
	 *Struts2 国际化
	 */
	private static final transient TextProvider textProvider = new TextProviderFactory().createInstance(MessageUtils.class, new LocaleProvider() {

		public Locale getLocale() {
			ActionContext ctx = ActionContext.getContext();
			if (ctx != null)
				return ctx.getLocale();
			else {
				LOG.debug("Action context not initialized");
				return null;
			}
		}
	});

	public static String getText(String aTextName) {
		return getText(aTextName, new String[0]);
	}

	public static String getText(String key, String... args) {
		String val;
		try {
			val = textProvider.getText(key, args);
		} catch (Exception e) {
			val = key;
		}
		return val;
	}

	public static String getMessage(Locale locale, String key, String... arg) {
		String val;
		try {
			val = messageSource.getMessage(key, arg, locale);
		} catch (Exception e) {
			val = key;
		}
		return val;
	}

	public static String getMessage(String key, String... arg) {
		Locale local = Locale.getDefault();
		Locale lSession = (Locale) ActionContext.getContext().getSession().get("WW_TRANS_I18N_LOCALE");
		if (lSession != null) {
			local = lSession;
		}
		return getMessage(local, key, arg);
	}
}
