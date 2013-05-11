package com.hhz.ump.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.ChangeCharset;
import com.hhz.ump.entity.app.AppAttachFile;

/**
 * 邮件发送工具类
 * 
 * @author shixy
 */
public class EmailUtil {

	private static Log log = LogFactory.getLog(EmailUtil.class);

	public static void sendSimpleMail(String sender, String oriPwd, String[] toUsers, String[] ccUsers, String subject,
			String content) {

		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("mail.powerlong.com"); // 设置邮箱服务器
		javaMailSender.setUsername(sender); // 设置用户名（要与发送人相一致）
		javaMailSender.setPassword(oriPwd); // 密码

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setTo(toUsers); // 例如: zhongyb@powerlong.com
		simpleMailMessage.setCc(ccUsers); // 例如: zhongyb@powerlong.com
		simpleMailMessage.setFrom(sender + "@powerlong.com"); // 与上面要一致，不然也会出错
		simpleMailMessage.setText(content);

		Properties props = new Properties(); // 此处必须设置，
		props.put("mail.smtp.auth", "true"); // 不设置会出现 553错误，见错误1
		props.put("MAIL.IMAP.AUTH.PLAIN.DISABLE", "TRUE");
		props.setProperty("mail.imap.auth.login.disable", "true");
		javaMailSender.setJavaMailProperties(props);
		javaMailSender.send(simpleMailMessage); // 发送
	}

	public static void sendAsync(String subject, String content, String[] toUsers) throws Exception {
		String[] toUserEmails = new String[toUsers.length];
		for (int i = 0; i < toUsers.length; i++) {
			toUserEmails[i] = toUsers[i] + "@powerlong.com";
		}
		String rootPath = ServletActionContext.getServletContext().getRealPath("");
		String realPath = Struts2Utils.getRequest().getContextPath();
		send(SpringSecurityUtils.getCurrentUiid(), LoginUtil.getPwd(null), toUserEmails, null, null, subject, content,
				null, null, rootPath, realPath);
	}

	public static void sendAsyncSys(String subject, String content, String[] toUsers) throws Exception {
		if (toUsers == null || toUsers.length == 0)
			return;

		String[] toUserEmails = new String[toUsers.length];
		for (int i = 0; i < toUsers.length; i++) {
			toUserEmails[i] = toUsers[i] + "@powerlong.com";
		}
		String rootPath = ServletActionContext.getServletContext().getRealPath("");
		String realPath = Struts2Utils.getRequest().getContextPath();
		// 使用系统邮件账户发送
		String fromUser = "email_admin";
		String fromPwd = CodeNameUtil.getDictNameByCd("SYS_EMAIL_ADMIN", fromUser);
		send(fromUser, fromPwd, toUserEmails, null, null, subject, content, null, null, rootPath, realPath);
	}

	public static void send(String senderName, String sender, String pwd, String[] toUsers, String[] ccUsers,
			String[] bccs, String subject, String content, List<AppAttachFile> attaFiles, List<File> files,
			String rootPath, String realPath) throws Exception {
		if (!Util.getPlasService().isDefaultSynCmailUser()) {
			log.warn("无法发送邮件，请在PLAS<参数定义表>设置GLOBAL_KEY_DEFAULT_SYN_CMAIL_USER的值!");
		} else {
			String curUser = sender;// SpringSecurityUtils.getCurrentUiid();
			String password = pwd;// LoginUtil.getLoginEmpInfo().getPassword();

			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost("mail.powerlong.com");
			mailSender.setUsername(curUser);
			mailSender.setPassword(password);

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(message, true, ChangeCharset.UTF_8);
			String senderNameTmp = senderName;
			if (StringUtils.isNotBlank(sender)) {
				if (StringUtils.isBlank(senderNameTmp)) {
					senderNameTmp = CodeNameUtil.getUserNameByCd(sender);
				}
				helper.setFrom(new InternetAddress(sender + "@powerlong.com", senderNameTmp));
			} else {
				helper.setFrom(getInternetAddress(curUser));
			}

			// 设置接收人 Start
			// 明送
			List<InternetAddress> internetAddress = new ArrayList<InternetAddress>();
			if (null != toUsers) {
				for (String user : toUsers) {
					internetAddress.add(getInternetAddress(user));
				}
				InternetAddress[] addresses = new InternetAddress[internetAddress.size()];
				helper.setTo(internetAddress.toArray(addresses));
			}
			// 抄送
			if (null != ccUsers) {
				internetAddress.clear();
				for (String user : ccUsers) {
					if (StringUtils.isNotBlank(user)) {
						internetAddress.add(getInternetAddress(user));
					}
				}
				InternetAddress[] addresses = new InternetAddress[internetAddress.size()];
				helper.setCc(internetAddress.toArray(addresses));
			}
			// 暗送
			if (bccs != null) {
				internetAddress.clear();
				for (String user : bccs) {
					internetAddress.add(getInternetAddress(user));
				}
				InternetAddress[] addresses = new InternetAddress[internetAddress.size()];
				helper.setBcc(internetAddress.toArray(addresses));
			}
			// 设置接收人End

			helper.setSubject(subject);
			replaceContent(content, helper, rootPath, realPath);
			addAttachments(attaFiles, helper);
			if (files != null) {
				for (File file : files) {
					helper.addAttachment(file.getName(), file);
				}
			}

			Properties props = new Properties(); // 此处必须设置，
			props.put("mail.smtp.auth", "true"); // 不设置会出现 553错误，见错误1
			props.put("MAIL.IMAP.AUTH.PLAIN.DISABLE", "TRUE");
			props.setProperty("mail.imap.auth.login.disable", "true");
			mailSender.setJavaMailProperties(props);
			mailSender.send(message);
			log.info("发送成功：发送人：" + sender);
		}
	}

	public static void send(String sender, String pwd, String[] toUsers, String[] ccUsers, String[] bccs,
			String subject, String content, List<AppAttachFile> attaFiles, List<File> files, String rootPath,
			String realPath) throws Exception {
		send(null, sender, pwd, toUsers, ccUsers, bccs, subject, content, attaFiles, files, rootPath, realPath);
	}

	/**
	 * 替换邮件内容中的图片文件，以便能再邮件中直接发送图片
	 * 
	 * @param content
	 * @param helper
	 */
	public static void replaceContent(String content, MimeMessageHelper helper, String rootPath, String realPath)
			throws Exception {
		// String rootPath = ServletActionContext.getServletContext().getRealPath("");
		// String realPath = Struts2Utils.getRequest().getContextPath();
		if (realPath == null || rootPath == null)
			return;
		realPath = realPath.replace("/", "");
		rootPath = StringUtils.replace(rootPath, realPath, "");
		Pattern p = Pattern.compile("(?<=(src=\"))[^\"]*(?=(\" />))");
		Matcher m = p.matcher(content);
		m.toMatchResult().toString();
		ArrayList<String> imgs = new ArrayList<String>();
		ArrayList<String> newSrc = new ArrayList<String>();
		int i = 0;
		while (m.find()) {
			imgs.add(m.group());
			newSrc.add("cid:identifier" + i);
			i++;
		}
		String newContent = StringUtils.replaceEach(content, (String[]) imgs.toArray(new String[imgs.size()]),
				(String[]) newSrc.toArray(new String[imgs.size()]));
		helper.setText(newContent, true);

		for (int k = 0; k < imgs.size(); k++) {
			FileSystemResource fsr = new FileSystemResource(new File(rootPath + imgs.get(k)));
			helper.addInline("identifier" + k, fsr);
		}
	}

	/**
	 * 根据附件列表将附件添加到邮件中
	 * 
	 * @param attaFiles
	 * @param helper
	 */
	public static void addAttachments(List<AppAttachFile> attaFiles, MimeMessageHelper helper) throws Exception {
		if (attaFiles != null) {
			for (AppAttachFile appAttachFile : attaFiles) {
				String filePath = appAttachFile.getFilePath();
				String realFileName = appAttachFile.getRealFileName();
				String fileName = appAttachFile.getFileName();
				helper.addAttachment(MimeUtility.encodeWord(realFileName), new File(filePath, fileName));
			}
		}
	}

	/**
	 * 获取外部邮件未读邮件数量
	 * 
	 * @return
	 */
	public static int getNewEmailNum() {
		String pop3Host = "192.168.180.100";
		// String curUser = "huangbj";
		// String oriPassword = "123123";//LoginUtil.getPwd(null);

		String curUser = SpringSecurityUtils.getCurrentUiid();
		String oriPassword = LoginUtil.getPwd(null);

		int num = 0;
		// 协议: pop3
		// try {
		// Properties props = new Properties();
		// Session session = Session.getDefaultInstance(props, null);
		// Store store = session.getStore("imap");
		//
		// store.connect(pop3Host, curUser, oriPassword);
		// Folder inbox = store.getFolder("INBOX");
		// inbox.open(Folder.READ_ONLY);
		// num = inbox.getUnreadMessageCount();
		// inbox.close(true);
		// store.close();
		// } catch (Exception e) {
		// System.out.println("获取coremail pop3未读邮件数异常!账号["+curUser+"]"+e.getMessage());
		// }

		// 协议: imap
		Properties props = new Properties();
		// props.put("mail.transport.protocol", "imap");
		// props.put("mail.store.protocol", "imap");
		// props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");
		// props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");

		props.put("mail.smtp.auth ", "true");
		props.put("MAIL.IMAP.AUTH.PLAIN.DISABLE", "TRUE");
		props.setProperty("mail.imap.auth.login.disable", "true");
		Session sess = Session.getDefaultInstance(props, null);

		try {
			Store store = sess.getStore("imap");
			// store.connect("mail.nbtl.com.cn", "zhying@tenglonggroup.com", "*****");
			// store.connect(pop3Host, "huangbj"+"@"+pop3Host, "123123");
			store.connect(pop3Host, curUser + "@" + pop3Host, oriPassword);
			// store.connect("mail.163.com", "skybat@mail.163.com", "sadedff.uks");
			// Folder folder = store.getFolder("INBOX");
			// IMAPFolder defaultFolder = (IMAPFolder) store.getDefaultFolder(); // 得到默认的
			// // Folder
			// Folder[] allFolder = defaultFolder.list();
			// allFolder[0].open(Folder.READ_ONLY);

			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			num = inbox.getUnreadMessageCount();
			inbox.close(true);
			store.close();

			// Message message[] = allFolder[0].getMessages();
			// for (int i = 0; i < message.length; i++) {
			// System.out.print("from:" + message[i].getFrom().toString());
			// System.out.print(";subject:" + message[i].getSubject());
			// System.out.print("sendDate:"
			// + message[i].getSentDate().toString());
			// System.out.println("content:"+message[i].getContent());
			// System.out.println();
			// }
		} catch (Exception e) {
			log.error("获取coremail imap未读邮件数异常!账号[" + curUser + "]" + e.getMessage(), e);
		}

		return num;
	}

	public static void main(String[] args) {
		System.out.println(EmailUtil.getNewEmailNum());
	}

	/**
	 * 根据 UUID 或者 Email 地址 获取 InternetAddress
	 * 
	 * @param emailOrUuid
	 * @return
	 * @throws Exception
	 */
	public static InternetAddress getInternetAddress(String emailOrUuid) throws Exception {
		if (StringUtils.isBlank(emailOrUuid))
			throw new Exception("参数不能为空：getInternetAddress->" + emailOrUuid);
		String uuid = emailOrUuid;
		if (emailOrUuid.indexOf("@") != -1) {
			uuid = emailOrUuid.substring(0, emailOrUuid.indexOf("@"));
		} else {
			emailOrUuid = emailOrUuid + "@powerlong.com";
		}
		return new InternetAddress(emailOrUuid, CodeNameUtil.getUserNameByCd(uuid));
	}
}
