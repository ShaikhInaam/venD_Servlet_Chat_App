package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import hashing.MD5Coverter;
import pojo.Account;

public class MapToPojo {
	
	public static Account convertToPojo(Map params,boolean convert)
	{
		 
		 Account pj=new Account();
		 pj.setFullname(((String[])params.get("fullname"))[0]);
		 pj.setUsername(((String[]) params.get("username"))[0]);
		 pj.setEmail(((String[]) params.get("email"))[0]);
		 pj.setMobile(((String[]) params.get("mobile"))[0]);
		 
		 if(convert)
			 pj.setPassword(MD5Coverter.convert(((String[])params.get("pass"))[0]));
		 
		 else
			 pj.setPassword(((String[])params.get("pass"))[0]);
		 
		 return pj;
	}

}
