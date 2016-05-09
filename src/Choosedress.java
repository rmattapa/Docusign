
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Choosedress {
	
	static List<String> procommands = new ArrayList<String>();
	static String fail = "fail";
	static Dress dress = null;
	private static Scanner inp;
	
	public static void main(String args[]){
		
	inp = new Scanner(System.in); 
	String input = inp.nextLine();
	String[] inputs = input.split(" ");
	
	//Calling which dresser based on the input
	if(inputs[0].equals("HOT")){
		dress = new Hotdress();	
	}else if(inputs[0].equals("COLD")){
		dress = new Colddress();
	}
	
	List<String> commands = new ArrayList<String>();
	for(int i=1;i<inputs.length;i++){
		commands.add(inputs[i].substring(0, 1));
	}
	StringBuilder sb = new StringBuilder();
	
	try{
		for(int i=0;i<commands.size();i++){
			if(isValid(commands.get(i))){
				//which function to call based on input
				switch(commands.get(i)){
					case "1":
					sb.append(dress.putonfootwear()).append(", ");
					break;
					case "2":
					sb.append(dress.putonheadwear()).append(", ");
					break;
					case "3":
					sb.append(dress.putonsocks()).append(", ");
					break;
					case "4":
					sb.append(dress.putonshirt()).append(", ");
					break;
					case "5":
					sb.append(dress.putonjacket()).append(", ");
					break;
					case "6":
					sb.append(dress.putonpants()).append(", ");
					break;
					case "7":
					sb.append(dress.leavehouse()).append(", ");
					break;
					case "8":
					sb.append(dress.takeoffpajamas()).append(", ");
					break;
					default:
					throw new Exception(fail);
				}
			}
			procommands.add(commands.get(i));
		}
	}
	catch(Exception e){
		sb.append(e.getMessage()).append(", ");
	}
	
	System.out.println(sb.toString().substring(0, sb.length()-2));
	}
	
	public static boolean isValid(String command) throws Exception{
		
		//Pajamas must be taken off before anything else can be put on
		if(procommands.size() == 0){
			if(!command.equals("8")){
				throw new Exception(fail);
			}
		}
		
		//Only 1 piece of each type of clothing may be put on
		if(procommands.contains(command)){
			throw new Exception(fail);
		}
		
		//You cannot put on socks when it is hot
		//You cannot put on a jacket when it is hot
		if(command.equals("3") || command.equals("5")){
			if(dress.getClass().getName().equals("Hotdress")){
				throw new Exception(fail);
			}
		}
		
		//Socks must be put on before shoes
		//Pants must be put on before shoes
		else if(command.equals("1")){
			//For hot dress we need to check only shorts
			if(dress.getClass().getName().equals("Hotdress")){
				if(!procommands.contains("6")){
					throw new Exception(fail);
				}
			}
			else{
				if(!procommands.contains("3") || !procommands.contains("6")){
					throw new Exception(fail);
				}
			}
		}
		
		//The shirt must be put on before the headwear or jacket
		else if(command.equals("2") || command.equals("5")){
			if(!procommands.contains("4")){
				throw new Exception(fail);
			}
		}
		
		//You cannot leave the house until all items of clothing are on (except socks and a jacket when it’s hot)
		else if(command.equals("7")){
			if(!dress.getClass().getName().equals("Hotdress")){
				if(!procommands.contains("3") || !procommands.contains("5")){
					throw new Exception(fail);
				}
			}
			
			if(!procommands.contains("1") || !procommands.contains("2") || !procommands.contains("4") || !procommands.contains("6") || !procommands.contains("8")){
				throw new Exception(fail);
			}
			
		}
		
		return true;
	}
}
