
public class Hotdress implements Dress{
	String fail = "fail";
	public String putonfootwear(){
		return "sandals";
	}
	public String putonheadwear(){
		return "sun visor";
	}
	public String putonsocks() throws Exception{
		throw new Exception(fail);
	}
	public String putonshirt(){
		return "t-shirt";
	}
	public String putonjacket() throws Exception{
		throw new Exception(fail);
	}
	public String putonpants(){
		return "shorts";
	}
	public String leavehouse(){
		return "leaving house";
	}
	public String takeoffpajamas(){
		return "Removing PJs";
	}
}
