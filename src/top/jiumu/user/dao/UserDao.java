package top.jiumu.user.dao;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import top.jiumu.user.domain.User;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class UserDao {
	private String path = "day14_1\\users.xml";

	/**
	 * 通过用户名查询
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		/**
		 * 1.获取Document
		 * 2.Xpath查询 
		 * 	结果:null return null; 
		 * 	结果:不是null 把Element封装到User对象中 return User;
		 */

		/**
		 * 得到Document
		 */
		// 创建解析器
		SAXReader saxReader = new SAXReader();
		// 获取Document
		try {
			Document document = saxReader.read(path);
			/**
			 * 使用Xpath查询得到Element
			 */
			Element element = (Element) document
					.selectSingleNode("//user[@username='" + username + "']");
			/**
			 * 判断element是否为空
			 * 
			 */
			if (element == null)
				return null;// 等于null
			/*
			 * 不等于null
			 */
			User user = new User();
			String attributeUsername = element.attributeValue("username");
			String attributePassword = element.attributeValue("password");
			user.setUsername(attributeUsername);
			user.setPassword(attributePassword);
			return user;
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	public void add(User user) {
		/**
		 * 1.得到Document
		 * 2.通过Document得到root元素
		 * 3.把Element添加到root下面
		 * 4.保存Document
		 */

		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(path);// 得到Document
			Element root = document.getRootElement();// 获取root跟元素
			Element userElement = root.addElement("user");// 使用根元素在其下面添加新元素
			/*
			 * 设置新元素的属性
			 */
			userElement.addAttribute("username", user.getUsername());
			userElement.addAttribute("password", user.getPassword());

			/**
			 * 回写
			 */
			/*
			 * 创建输出格式化器
			 */
			OutputFormat format = new OutputFormat("\t", true);// 缩进使用\t,使用换行符
			format.setTrimText(true);// 清楚原有的换行和缩进符
			XMLWriter writer;//创建XMLWriteer
			try {
				writer = new XMLWriter(new OutputStreamWriter(
						new FileOutputStream(path), "UTF-8"), format);
				writer.write(document);
				writer.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}

	}
}
