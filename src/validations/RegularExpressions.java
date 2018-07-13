package validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utilities.StringUtilities;

public class RegularExpressions {
	
	private static Pattern p=null;
	private static Matcher m=null;
	
	public static boolean validate(String name,String pattern)
	{
		if(!StringUtilities.isEmpty(name)) {
			p=Pattern.compile(pattern);
			m=p.matcher(name);
			return m.matches();
		} else {
			return false;
		}

	}
	
	
	public static void main(String arg[])
	{
		//System.out.println(RegularExpressions.validate("Asdf258=000", "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$"));
		System.out.println(RegularExpressions.validate("safihassan", "[a-zA-Z- ]{10,50}"));
	}

}
