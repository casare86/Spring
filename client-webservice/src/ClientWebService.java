import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

public class ClientWebService {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String content = Request
				.Post("http://localhost:8080/regulator/companys")
				.addHeader("Accept", "application/json") //the type expected as response
				.execute()
				.returnContent()
				.asString();
		
		System.out.println("Content:" + content);
	}
}
