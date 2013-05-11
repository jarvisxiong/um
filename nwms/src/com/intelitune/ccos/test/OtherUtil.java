package com.intelitune.ccos.test;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OtherUtil {

	/**
	 * 对象深度克隆
	 * 
	 * @param originObj
	 * @return
	 */
	public final static Object objectClone(Object originObj) {
		ByteArrayOutputStream bao = null;
		ByteArrayInputStream bai = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			bao = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bao);
			oos.writeObject(originObj);
			oos.flush();
			oos.close();
			bai = new ByteArrayInputStream(bao.toByteArray());
			ois = new ObjectInputStream(bai);
			Object obj = ois.readObject();
			ois.close();
			oos = null;
			ois = null;
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (bao != null) {
					bao.close();
					bao = null;
				}
				if (bai != null) {
					bai.close();
					bai = null;
				}
			} catch (IOException e) {
			}
		}
	}
}

