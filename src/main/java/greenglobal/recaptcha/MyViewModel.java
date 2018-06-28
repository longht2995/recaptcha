package greenglobal.recaptcha;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.ToServerCommand;
import org.zkoss.json.JSONObject;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;

@ToServerCommand("verify")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MyViewModel {

	@Init
	public void init() {
	
	}
private boolean disabled = true;
	
	final String SECRET = "6Lcj5AYTAAAAANcaQYWvFkHVSkqR6FsBaCXXw54r"; //from reCaptcha
	
	@Command @NotifyChange("disabled")
	public void verify(@BindingParam("response")String response) throws Exception{
		JSONObject result = RecaptchaVerifier.verifyResponse(SECRET, response);
		if (Boolean.parseBoolean(result.get("success").toString())){
			disabled = false;
		}else{
			String errorCode = result.get("error-codes").toString();
			//log or show error
			Clients.showNotification(errorCode);
		}
	}
	
	@Command
	public void submit(){
		Clients.showNotification("submitted");
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}
