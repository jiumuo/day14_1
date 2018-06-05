package top.jiumu.user.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.vcode.utils.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.创建验证码类
		 */
		VerifyCode verifyCode = new VerifyCode();
		/*
		 * 2.获取验证码图片
		 */
		BufferedImage image = verifyCode.getImage();
		/*
		 * 3.把文本保存到session域中
		 */
		request.getSession().setAttribute("session_VerifyCode", verifyCode.getText());
		/*
		 * 4.把图片发送到客户端
		 */
		verifyCode.output(image, response.getOutputStream());
	}

}
