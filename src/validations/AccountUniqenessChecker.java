package validations;

import java.util.ArrayList;
import java.util.List;

import dao.DatabaseOperations;
import pojo.Account;

public class AccountUniqenessChecker {
	
	public static String checkUnique(Account acc)
	{
		
		String st="";
		List<Account> list=new ArrayList();
		
		list= (List<Account>) DatabaseOperations.checkDuplicate(acc.getUsername(), "username", "Account");
		if(list.size()!=0)
			return "Username already taken\\nchoose another one!";
		
		list= (List<Account>) DatabaseOperations.checkDuplicate(acc.getEmail(), "email", "Account");
		if(list.size()!=0)
			return "Email already taken\\nchoose another one!";
		
		list= (List<Account>) DatabaseOperations.checkDuplicate(acc.getMobile(), "mobile", "Account");
		if(list.size()!=0)
			return "Cell# already taken\\nchoose another one!";
		
		
		return st;
	}

}
