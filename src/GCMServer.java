

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class GCMServer
 */
@WebServlet("/GCMServer")
public class GCMServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Put your Google API Server Key here
		private static final String GOOGLE_SERVER_KEY = "AIzaSyDZ60w-JN-RzBHk1litPqzKtzqThmZnpaY";

		// Put your Google Project number here
		final String GOOGLE_USERNAME = "512212818580" + "@gcm.googleapis.com";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GCMServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.append("Hi");
		out.close();
			try {
				String regKey = request.getParameter("RegNo");
				String mobile = request.getParameter("MobileNo");
				String userMessage = mobile + "," + regKey;
				Set<String> regIdSet = RegIdManager.readFromFile();
				String toDeviceRegId = (String) (regIdSet.toArray())[0];
				SmackClient.sendMessage(toDeviceRegId, GOOGLE_SERVER_KEY, userMessage);
				request.setAttribute("pushStatus", "Message Sent.");
			} catch (IOException ioe) {
				ioe.printStackTrace();
				request.setAttribute("pushStatus",
						"RegId required: " + ioe.toString());
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("pushStatus", e.toString());
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
