package Voicezone;

public class Result_bean {

	public  String result ;//= new String[20000];
	public  String result_scenario;// = new String[20000];
	public  String result_actual;// = new String[20000];
	public  String result_expected ;//= new String[20000];
	
	Result_bean()
	{
		 this.result=result;
		 this.result_scenario=result_scenario;
		 this.result_actual=result_actual;
		 this.result_expected=result_expected;
		
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult_scenario() {
		return result_scenario;
	}

	public void setResult_scenario(String result_scenario) {
		this.result_scenario = result_scenario;
	}

	public String getResult_actual() {
		return result_actual;
	}

	public void setResult_actual(String result_actual) {
		this.result_actual = result_actual;
	}

	public String getResult_expected() {
		return result_expected;
	}

	public void setResult_expected(String result_expected) {
		this.result_expected = result_expected;
	}
		
	
	
}
