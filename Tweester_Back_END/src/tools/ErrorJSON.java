package tools;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJSON {

	public static JSONObject serviceRefused(String message, int codeError) throws JSONException {
		JSONObject responce = new JSONObject();
		responce.put("Error!", message);
		responce.put("code", codeError);
		return responce;

	}

	public static JSONObject serviceAccepted(String string, int i) throws JSONException {
		JSONObject responce = new JSONObject();
			responce.put("message", string);
			responce.put("code", i);
		
		return responce;

	}
}
