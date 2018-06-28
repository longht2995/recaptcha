package greenglobal.recaptcha;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.zkoss.json.JSONObject;
import org.zkoss.json.JSONValue;

public class RecaptchaVerifier {
	final static String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
	public static JSONObject verifyResponse(String secret, String captchaResponse) throws Exception {

		URL obj = new URL(VERIFY_URL);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add header
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "response="+captchaResponse+"&secret="+secret;

		// Send a request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		InputStreamReader in = new InputStreamReader(con.getInputStream());

		JSONObject result = (JSONObject)JSONValue.parse(in);
		in.close();

		return result;
	}
}
