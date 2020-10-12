package article.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import article.dao.ArticleDao;
import article.dao.impl.WriteArticleImpl_Jdbc;
import article.model.ArticleBean;




/**
 * Servlet implementation class ArticleInsert
 */
@MultipartConfig
@WebServlet("/ArticleInsert")
public class ArticleInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ArticleInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			Map<String, String> errorMsgs = new HashMap<String, String>();
			Map<String, String> successMsgs = new HashMap<String, String>();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			HttpSession session = request.getSession();
			request.setAttribute("ErrMsg", errorMsgs);
			session.setAttribute("successMsg", successMsgs);

			try {
				String title = "";
				int activitysId = 0;
				int articleType = 0;
				int showArticle = 0;
				int memberId = 0;
				
				String activitysIdStr = "";
				String articleTypeStr = "";
				String showArticleStr = "";
				String memberIdStr = "";

//				InputStream is = null;
				// request.getParts()方法傳回一個由javax.servlet.http.Part物件所組成的Collection
				// javax.servlet.http.Part: 代表上傳到Server的資料，可以是正常的表單資料(form data)，
				// 也可以上傳的檔案。
				// Part介面可以:
				// 1. 傳回欄位的名稱(<input>的name屬性)、大小、ContentType
				// 2. 每個Part的Header
				// 3. 刪除Part
				// 4. 將Part寫入硬碟
				Collection<Part> parts = request.getParts();

				//GlobalService.exploreParts(parts, request);
				if (parts != null) { // 如果這是一個上傳資料的表單
					for (Part p : parts) {
						String fldName = p.getName();
//						System.out.println("fldName=" + fldName);
						String value = request.getParameter(fldName);
						if (p.getContentType() == null) {   // 表示 p 為一般欄位而非上傳的表單
							// 根據欄位名稱來讀取欄位的內容，然後存入適當的變數內	
							
//							if (fldName.equals("id")) {
//								 || id.trim().length() == 0
//								if (value == null) {
//									errorMsgs.put("errTitle", "必須輸入id");
//								} else {
//								idStr = value;
//								idStr = idStr.trim();
//								id = Integer.parseInt(idStr);								
//								request.setAttribute("id", id);
//							} else 
								if (fldName.equals("title")) {
								title = value;
//								if (title == null || title.trim().length() == 0) {
//									errorMsgs.put("errTitle", "必須輸入作者");
//								} 
//								else {
									request.setAttribute("title", title);
//								}								
							
							} else if (fldName.equals("activitysId")) {
//								if (value == null || activitysId.trim().length() == 0) {
//									errorMsgs.put("errActivitysId", "必須輸入書號");
//								} else {
								activitysIdStr = value;
								activitysIdStr = activitysIdStr.trim();
								activitysId = Integer.parseInt(activitysIdStr);
								request.setAttribute("activitysId", activitysId);
//								}
								
								
							} else if (fldName.equals("articleType")) {
//								articleType = value;
//								;
//								if (articleType == null || articleType.trim().length() == 0) {
//									errorMsgs.put("errArticleType", "必須輸入文章類型");
//								}
								articleTypeStr = value;
								articleTypeStr = articleTypeStr.trim();
								articleType = Integer.parseInt(articleTypeStr);
								request.setAttribute("articleType", articleType);
								
							} else if (fldName.equals("showArticle")) {
//								showArticle = value;
//								;
//								if (showArticle == null || showArticle.trim().length() == 0) {
//									errorMsgs.put("errShowArticle", "必須輸入類型");
//								}
								showArticleStr = value;
								showArticleStr = showArticleStr.trim();
								showArticle = Integer.parseInt(showArticleStr);
								request.setAttribute("showArticle", showArticle);
								
							}else if (fldName.equals("memberId")) {
//								memberId = value;
//								;
//								if (memberId == null || memberId.trim().length() == 0) {
//									errorMsgs.put("errMemberId", "必須輸入類型");
//								}
								memberIdStr = value;
								memberIdStr = memberIdStr.trim();
								memberId = Integer.parseInt(memberIdStr);
								request.setAttribute("memberId", memberId);								
							}
							}else {
								System.out.println("沒有對應的欄位");

						
				
//						else {  // 表示此份資料是上傳的檔案

//							if (fileName != null && fileName.trim().length() > 0) {
//								sizeInBytes = p.getSize();
//								is = p.getInputStream();
//							} else {
//								errorMsgs.put("errPicture", "必須挑選圖片檔");
//							}
//						}
						}											
					}				 
				}else {
					errorMsgs.put("errTitle", "此表單不是上傳檔案的表單");
				}
			
				// 如果輸入資料有錯誤
				if (!errorMsgs.isEmpty()) {
					// 轉交給原輸入資料的網頁來顯示錯誤訊息
					RequestDispatcher rd = request.getRequestDispatcher("ArticleInsert.jsp");
					rd.forward(request, response);
					return;
				}
				
				ArticleDao articleDao = new WriteArticleImpl_Jdbc();
				
				ArticleBean ab = new ArticleBean(title, activitysId, articleType, showArticle, memberId);

				articleDao.saveArticle(ab);
				
//				ArticleService articleService = new ArticleServiceImpl();
//				
//				ArticleBean ab = new ArticleBean(id, title, activitysId, articleType, showArticle, memberId);
//
//				articleService.saveArticle(ab);
				
				
				
				successMsgs.put("success", "資料新增成功");
	            // 新增成功，通知瀏覽器對新網址發出請求
				response.sendRedirect(response.encodeRedirectURL("/article/PostArticle.jsp"));
				return;
			} catch (Exception e) {
				e.printStackTrace(); 
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/article/PostArticle");
				rd.forward(request, response);
			}
		}	
	}


