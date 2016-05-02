package com.icbc.hexm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.icbc.hexm.mail.EmailSender;
import com.icbc.hexm.message.resp.Article;
import com.icbc.hexm.message.resp.Music;
import com.icbc.hexm.message.resp.MusicMessage;
import com.icbc.hexm.message.resp.NewsMessage;
import com.icbc.hexm.message.resp.TextMessage;
import com.icbc.hexm.music.kugou.KugouMusicService;
import com.icbc.hexm.util.Log4jUtil;
import com.icbc.hexm.util.MessageUtil;
import com.icbc.hexm.util.WeiXinUtil;
import com.icbc.mysql.manager.WxpostlogManager;

/**
 * ���ķ�����
 * 
 * @author liufeng
 * @date 2013-05-20
 */
public class CoreService {
	private static final String WEIXIN_NAME = "��С��";

	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		Log4jUtil.getLogger().debug("CoreService.processRequest begin.");
		String respMessage = null;
		try {
			// Ĭ�Ϸ��ص��ı���Ϣ����
			String respContent = "�������쳣�����Ժ��ԣ�";

			// xml�������
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			WxpostlogManager.logWexinRequest(WEIXIN_NAME, requestMap);

			Log4jUtil.getLogger().debug("requestMap info:");
			Iterator<String> keyit = requestMap.keySet().iterator();
			while (keyit.hasNext()) {
				String key = keyit.next();
				Log4jUtil.getLogger().debug(key + "=" + requestMap.get(key));
			}

			// ���ͷ��ʺţ�open_id��
			String fromUserName = requestMap.get("FromUserName");
			// �����ʺ�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			System.out.println("CoreService.processRequest.fromUserName:" + fromUserName + ", toUserName:" + toUserName + ", msgType:" + msgType);

			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "�����͵����ı���Ϣ��";
				// �ı���Ϣ����
				String content = requestMap.get("Content");

				// �ж��û����͵��Ƿ��ǵ���QQ����
				if (WeiXinUtil.isQqFace(content)) {
					// �ظ��ı���Ϣ
					// �û���ʲôQQ���飬�ͷ���ʲôQQ����
					respContent = content;
				}

				// ����ԡ�������2���ֿ�ͷ
				if (content.startsWith("����")) {
					// ������2���ּ����������+���ո�-���������ȥ��
					String keyWord = content.replaceAll("^����[\\+ ~!@#%^-_=]?", "");
					// �����������Ϊ��
					if ("".equals(keyWord)) {
						respContent = "�Ѹ�ʱ������\"����+�ո�+������\"";
					} else {
						String[] kwArr = keyWord.split("@");
						// ��������
						String musicTitle = kwArr[0];
						// �ݳ���Ĭ��Ϊ��
						@SuppressWarnings("unused")
						String musicAuthor = "";
						if (2 == kwArr.length)
							musicAuthor = kwArr[1];

						// ��������
						Music music = KugouMusicService.searchMusic(musicTitle);
						// δ����������
						if (null == music) {
							respContent = "�Բ���û���ҵ��������ĸ���<" + musicTitle + ">��";
						} else {
							// ������Ϣ
							MusicMessage musicMessage = new MusicMessage();
							musicMessage.setToUserName(fromUserName);
							musicMessage.setFromUserName(toUserName);
							musicMessage.setCreateTime(new Date().getTime());
							musicMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
							musicMessage.setMusic(music);
							return MessageUtil.musicMessageToXml(musicMessage);
						}
					}
				}

				// ����Ԥ��
				if (content.startsWith("����")) {
					String[] strArray = content.split(" ");
					if (strArray.length < 2) {
						respContent = "�����أ���ظ�\"����+�ո�+������\"";
					} else {
						String cityName = strArray[1];
						respContent = WeatherService.getWeatherInfo(cityName);
						if (respContent == null || "".equals(respContent)) {
							respContent = "��ȡ����ʧ��";
						}
					}
				}

				// ����ͼ����Ϣ
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);

				List<Article> articleList = new ArrayList<Article>();
				// ��ͼ����Ϣ
				if ("1".equals(content)) {
					Article article = new Article();
					article.setTitle("΢�Ź����ʺſ����̳�Java��");
					article.setDescription("���壬80��΢�Ź����ʺſ�������4���¡�Ϊ������ѧ�����ţ����Ƴ���ϵ�н̳̣�Ҳϣ����˻�����ʶ����ͬ�У�");
					article.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
					article.setUrl("http://blog.csdn.net/lyq8479");
					articleList.add(article);
					// ����ͼ����Ϣ����
					newsMessage.setArticleCount(articleList.size());
					// ����ͼ����Ϣ������ͼ�ļ���
					newsMessage.setArticles(articleList);
					// ��ͼ����Ϣ����ת����xml�ַ���
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// ��ͼ����Ϣ---����ͼƬ
				else if ("2".equals(content)) {
					Article article = new Article();
					article.setTitle("΢�Ź����ʺſ����̳�Java��");
					// ͼ����Ϣ�п���ʹ��QQ���顢���ű���
					article
							.setDescription("���壬80��"
									+ WeiXinUtil.emoji(0x1F6B9)
									+ "��΢�Ź����ʺſ�������4���¡�Ϊ������ѧ�����ţ����Ƴ���ϵ�����ؽ̳̣�Ҳϣ����˻�����ʶ����ͬ�У�\n\nĿǰ���Ƴ��̳̹�12ƪ�������ӿ����á���Ϣ��װ����ܴ��QQ���鷢�͡����ű��鷢�͵ȡ�\n\n���ڻ��ƻ��Ƴ�һЩʵ�ù��ܵĿ������⣬���磺����Ԥ�����ܱ����������칦�ܵȡ�");
					// ��ͼƬ��Ϊ��
					article.setPicUrl("");
					article.setUrl("http://blog.csdn.net/lyq8479");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// ��ͼ����Ϣ
				else if ("3".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("΢�Ź����ʺſ����̳�\n����");
					article1.setDescription("");
					article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
					article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");

					Article article2 = new Article();
					article2.setTitle("��2ƪ\n΢�Ź����ʺŵ�����");
					article2.setDescription("");
					article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");

					Article article3 = new Article();
					article3.setTitle("��3ƪ\n����ģʽ���ü��ӿ�����");
					article3.setDescription("");
					article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// ��ͼ����Ϣ---������Ϣ����ͼƬ
				else if ("4".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("΢�Ź����ʺſ����̳�Java��");
					article1.setDescription("");
					// ��ͼƬ��Ϊ��
					article1.setPicUrl("");
					article1.setUrl("http://blog.csdn.net/lyq8479");

					Article article2 = new Article();
					article2.setTitle("��4ƪ\n��Ϣ����Ϣ�����ߵķ�װ");
					article2.setDescription("");
					article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");

					Article article3 = new Article();
					article3.setTitle("��5ƪ\n������Ϣ�Ľ�������Ӧ");
					article3.setDescription("");
					article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");

					Article article4 = new Article();
					article4.setTitle("��6ƪ\n�ı���Ϣ�����ݳ������ƽ���");
					article4.setDescription("");
					article4.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					articleList.add(article4);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// ��ͼ����Ϣ---���һ����Ϣ����ͼƬ
				else if ("5".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("��7ƪ\n�ı���Ϣ�л��з���ʹ��");
					article1.setDescription("");
					article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
					article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");

					Article article2 = new Article();
					article2.setTitle("��8ƪ\n�ı���Ϣ��ʹ����ҳ������");
					article2.setDescription("");
					article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");

					Article article3 = new Article();
					article3.setTitle("����������¶���������������ͨ���������Ի��ע΢�Ź����ʺ�xiaoqrobot��֧�����壡");
					article3.setDescription("");
					// ��ͼƬ��Ϊ��
					article3.setPicUrl("");
					article3.setUrl("http://blog.csdn.net/lyq8479");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}

				if (respMessage != null)
					return respMessage;

			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵�ͼƬ���յ���";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵ĵ���λ�����յ���";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵��������յ���";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵���Ƶ��Ϣ���յ���";

			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					Log4jUtil.getLogger().debug("���µĹ�ע������");
					respContent = "лл���Ĺ�ע��\n " + "����Ԥ����ظ�\"����+�ո�+������\"\n" + "�Ѹ���ظ�\"����+�ո�+������\"";
					Log4jUtil.getLogger().debug(respContent);
					EmailSender.sendEmail("���µĹ�ע������", "��һ�����û���ע���΢�Ź��ں�");
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					Log4jUtil.getLogger().debug("����ȡ����ע����");
					EmailSender.sendEmail("����ȡ����ע����", "��һ���û�ȡ����ע���΢�Ź��ں�");
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ
				}
			}

			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

}
