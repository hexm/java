package com.icbc.hexm.weixin;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.icbc.hexm.util.Log4jUtil;
import com.icbc.hexm.util.WeiXinUtil;



/**
 * �˵���������
 * 
 * @author hexm
 * @date 2013-08-08
 */
public class MenuManager {
	private static Logger log = Log4jUtil.getLogger();

	private static final String queryMenuUrl="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	public static void main(String[] args) {
		// hexm
//		String appId = "wx1679968cc900c5a4";
//		String appSecret = "0bec86680f2624d16dbc9008c2dd1d93";
		//wzq
//		String appId = "wxdf92072564bf449f";
//		String appSecret = "ba9a3ecf8c7b5ea4cd3292c42a42309b";
		//���Ժ���Ϣ
//		String appId = "wx0365ee06eb58d61a";
//		String appSecret = "e30f98a236656d2af71b93cc99b6f5a0";
		// ���ýӿڻ�ȡaccess_token
		//�к��Ļ���Ϣ
		String appId = "wxea31d19bee0e0bdb";
		String appSecret = "9326fe39cf677669efcf584272b70edb";
		// ���ýӿڻ�ȡaccess_token
		AccessToken at = WeiXinUtil.getAccessToken(appId, appSecret);
		System.out.println("ACCESS_TOKEN:" + at.getToken());
		System.out.println("expires_in:" + at.getExpiresIn());
		if (null != at) {
			//��ѯ�˵� 
			String url = queryMenuUrl.replace("ACCESS_TOKEN", at.getToken());
			System.out.println(url);
			JSONObject jsonObject = WeiXinUtil.httpRequest(url, "POST", null);
			System.out.println(jsonObject.toString());
			// ���ýӿ�ɾ���˵�
			int deleteResult = WeiXinUtil.deleteMenu(at.getToken());
			if (0 == deleteResult)
				log.info("ɾ���˵��ɹ���");
			else
				log.info("ɾ���˵�ʧ�ܣ������룺" + deleteResult);
			
			// ���ýӿڴ����˵�
			int result = WeiXinUtil.createMenu(getMenu(), at.getToken());
			if (0 == result)
				log.info("�˵������ɹ���");
			else
				log.info("�˵�����ʧ�ܣ������룺" + result);
		}
	}

	/**
	 * ��װ�˵�����
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("����Ԥ��");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("������ѯ");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("�ܱ�����");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("��ʷ�ϵĽ���");
		btn14.setType("click");
		btn14.setKey("14");

		CommonButton btn21 = new CommonButton();
		btn21.setName("�����㲥");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("������Ϸ");
		btn22.setType("click");
		btn22.setKey("22");

		CommonButton btn23 = new CommonButton();
		btn23.setName("��Ů��̨");
		btn23.setType("click");
		btn23.setKey("23");

		CommonButton btn24 = new CommonButton();
		btn24.setName("����ʶ��");
		btn24.setType("click");
		btn24.setKey("24");

		CommonButton btn25 = new CommonButton();
		btn25.setName("�������");
		btn25.setType("click");
		btn25.setKey("25");

		CommonButton btn31 = new CommonButton();
		btn31.setName("Q��Ȧ");
		btn31.setType("click");
		btn31.setKey("31");

		CommonButton btn32 = new CommonButton();
		btn32.setName("��Ӱ���а�");
		btn32.setType("click");
		btn32.setKey("32");

		CommonButton btn33 = new CommonButton();
		btn33.setName("��ĬЦ��");
		btn33.setType("click");
		btn33.setKey("33");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("��������");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("������վ");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("��������");
		mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });

		/**
		 * ���ǹ��ں�xiaoqrobotĿǰ�Ĳ˵��ṹ��ÿ��һ���˵����ж����˵���<br>
		 * 
		 * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br>
		 * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}