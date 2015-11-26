package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_shiro_principal_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_shiro_principal_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_shiro_principal_nobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("<title>用户登录</title>\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/static/css/bootstrap.min.css\" />\n");
      out.write("<style type=\"text/css\">\n");
      out.write("html, body {\n");
      out.write("\theight: 100%;\n");
      out.write("}\n");
      out.write(".error {\n");
      out.write("\tcolor: red;\n");
      out.write("}\n");
      out.write(".box {\n");
      out.write("\tfilter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#6699FF',\n");
      out.write("\t\tendColorstr='#6699FF'); /*  IE */\n");
      out.write("\tbackground-image: linear-gradient(bottom, #6699FF 0%, #6699FF 100%);\n");
      out.write("\tbackground-image: -o-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);\n");
      out.write("\tbackground-image: -moz-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);\n");
      out.write("\tbackground-image: -webkit-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);\n");
      out.write("\tbackground-image: -ms-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);\n");
      out.write("\tmargin: 0 auto;\n");
      out.write("\tposition: relative;\n");
      out.write("\twidth: 100%;\n");
      out.write("\theight: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".login-box {\n");
      out.write("\twidth: 100%;\n");
      out.write("\tmax-width: 500px;\n");
      out.write("\theight: 400px;\n");
      out.write("\tposition: absolute;\n");
      out.write("\ttop: 50%;\n");
      out.write("\tmargin-top: -200px;\n");
      out.write("\t/*设置负值，为要定位子盒子的一半高度*/\n");
      out.write("}\n");
      out.write("\n");
      out.write("@media screen and (min-width:500px) {\n");
      out.write("\t.login-box {\n");
      out.write("\t\tleft: 50%;\n");
      out.write("\t\t/*设置负值，为要定位子盒子的一半宽度*/\n");
      out.write("\t\tmargin-left: -250px;\n");
      out.write("\t}\n");
      out.write("}\n");
      out.write("\n");
      out.write(".form {\n");
      out.write("\twidth: 100%;\n");
      out.write("\tmax-width: 500px;\n");
      out.write("\theight: 275px;\n");
      out.write("\tmargin: 25px auto 0px auto;\n");
      out.write("\tpadding-top: 25px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".login-content {\n");
      out.write("\theight: 300px;\n");
      out.write("\twidth: 100%;\n");
      out.write("\tmax-width: 500px;\n");
      out.write("\tbackground-color: rgba(255, 250, 2550, .6);\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".input-group {\n");
      out.write("\tmargin: 0px 0px 30px 0px !important;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".form-control, .input-group {\n");
      out.write("\theight: 40px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".form-group {\n");
      out.write("\tmargin-bottom: 0px !important;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".login-title {\n");
      out.write("\tpadding: 20px 10px;\n");
      out.write("\tbackground-color: rgba(0, 0, 0, .6);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".login-title h1 {\n");
      out.write("\tmargin-top: 10px !important;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".login-title small {\n");
      out.write("\tcolor: #fff;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".link p {\n");
      out.write("\tline-height: 20px;\n");
      out.write("\tmargin-top: 30px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".btn-sm {\n");
      out.write("\tpadding: 8px 24px !important;\n");
      out.write("\tfont-size: 16px !important;\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("\t<div class=\"box\">\n");
      out.write("\t\t<div class=\"login-box\">\n");
      out.write("\t\t\t<div class=\"login-title text-center\">\n");
      out.write("\t\t\t\t<h1>\n");
      out.write("\t\t\t\t\t<small>登录</small>\n");
      out.write("\t\t\t\t</h1>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"login-content \">\n");
      out.write("\t\t\t\t<div class=\"form\">\n");
      out.write("\t\t\t\t\t<form action=\"#\" method=\"post\">\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-xs-12  \">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"input-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<span class=\"input-group-addon\"><span\n");
      out.write("\t\t\t\t\t\t\t\t\t\tclass=\"glyphicon glyphicon-user\"></span></span> \n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input type=\"text\" id=\"username\" name=\"username\" class=\"form-control\" placeholder=\"用户名\" value=\"");
      if (_jspx_meth_shiro_principal_0(_jspx_page_context))
        return;
      out.write("\"/>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-xs-12  \">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"input-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<span class=\"input-group-addon\"><span\n");
      out.write("\t\t\t\t\t\t\t\t\t\tclass=\"glyphicon glyphicon-lock\"></span></span>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input type=\"password\" id=\"password\" name=\"password\" class=\"form-control\" placeholder=\"密码\"/>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-xs-12  \">\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"input-group\">\n");
      out.write("\t\t\t\t\t\t\t\t\t 自动登录：<input\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttype=\"checkbox\" name=\"rememberMe\" value=\"true\"/>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group form-actions\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-xs-4 col-xs-offset-4 \">\n");
      out.write("\t\t\t\t\t\t\t\t<button type=\"submit\" class=\"btn btn-sm btn-info\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<span class=\"glyphicon glyphicon-off\"></span> 登录\n");
      out.write("\t\t\t\t\t\t\t\t</button>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"error\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${error}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</div>\n");
      out.write("\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_shiro_principal_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  shiro:principal
    org.apache.shiro.web.tags.PrincipalTag _jspx_th_shiro_principal_0 = (org.apache.shiro.web.tags.PrincipalTag) _jspx_tagPool_shiro_principal_nobody.get(org.apache.shiro.web.tags.PrincipalTag.class);
    _jspx_th_shiro_principal_0.setPageContext(_jspx_page_context);
    _jspx_th_shiro_principal_0.setParent(null);
    int _jspx_eval_shiro_principal_0 = _jspx_th_shiro_principal_0.doStartTag();
    if (_jspx_th_shiro_principal_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_shiro_principal_nobody.reuse(_jspx_th_shiro_principal_0);
      return true;
    }
    _jspx_tagPool_shiro_principal_nobody.reuse(_jspx_th_shiro_principal_0);
    return false;
  }
}
