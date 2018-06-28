package greenglobal.recaptcha.services;

import greenglobal.recaptcha.entity.Log;
import java.util.List;

public interface MyService {

	Log addLog(Log log);

	List<Log> getLogs();

	void deleteLog(Log log);
}
