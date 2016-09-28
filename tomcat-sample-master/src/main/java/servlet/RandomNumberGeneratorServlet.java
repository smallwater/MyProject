package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.william.demo.RandomNumberGenerator;

@SuppressWarnings("serial")
@WebServlet(
	name = "RandomNumberGeneratorServlet",
	urlPatterns = {"/RandomNumberGeneratorServlet"}
)
public class RandomNumberGeneratorServlet extends HttpServlet{

	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	System.out.println(RandomNumberGeneratorServlet.class + " doGet(...) was invoked");
    	printParameters(req);

        String servletOutput = generateRandomNumbersJson(req);
        System.out.println(RandomNumberGeneratorServlet.class + ".doGet(...) output is: " + servletOutput);
    	
        ServletOutputStream out = resp.getOutputStream();
        out.write(servletOutput.getBytes());
        out.flush();
        out.close();
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	System.out.println(RandomNumberGeneratorServlet.class + " doPost(...) was invoked");
    	printParameters(req);
    	
        String servletOutput = generateRandomNumbersJson(req);
        System.out.println(RandomNumberGeneratorServlet.class + ".doPost(...) output is: " + servletOutput);
    	
        ServletOutputStream out = resp.getOutputStream();
        out.write(servletOutput.getBytes());
        out.flush();
        out.close();
    }
    
    /**
     * 
     * @param req Contains the "quantity" and "bound" parameters.
     * @return A JSON string of random numbers.
     */
    private static String generateRandomNumbersJson(HttpServletRequest req){
    	String quantityString = req.getParameter("quantity");
    	String boundString = req.getParameter("bound");

    	List<Integer> candidate = RandomNumberGenerator.generate(quantityString, boundString);
    	return RandomNumberGenerator.derivePrettyString(candidate);
    }
    
    public static void printParameters(HttpServletRequest req){
    	String quantityString = req.getParameter("quantity");
    	String boundString = req.getParameter("bound");
    	System.out.println("Parameters are: quantity = [" + quantityString + "] and bound = [" + boundString + "]");
    }

}
