package me.gv7.tools.josearcher1.test;

import java.util.List;
import java.util.ArrayList;

import me.gv7.tools.josearcher1.entity.Blacklist;
import me.gv7.tools.josearcher1.entity.Keyword;
import me.gv7.tools.josearcher1.searcher.SearchRequstByBFS;

//original: SearchRequstByBFSTest
//Typo...
//This benchmark directly filter input before the reflection point


class SearchRequestByBFSTest {
    public static void main(String[] args) {
    	int index = 0;
    	while (index<args.length) {
		  double a=0;
		  double b=0;
		  double c=0;
	      try {
	    	  a=Double.parseDouble(args[index++]);
	    	  b=Double.parseDouble(args[index++]);
	    	  c=Double.parseDouble(args[index++]);
	      }catch (Exception e) {
	        System.out.println("3 args needed: double, double, double");
	    	//1
	      }
	      main(a,b,c);
	      //-1.83956105399861422145596627064E53
	      //1e300
	      //1.58740106
    	}
	  }
    
    public static void main(double ia,double ib,double ic) {
    	SearchRequstByBFS.a=ia;
    	SearchRequstByBFS.b=ib;
    	SearchRequstByBFS.c=ic;
        //Thread thread = Thread.currentThread();
        TestClass target =  new TestClass();
        List<Keyword> keys = new ArrayList<>();
        keys.add(new Keyword.Builder().setField_type("entity").build());
        List<Blacklist> blacklists = new ArrayList<>();
        blacklists.add(new Blacklist.Builder().setField_name("parallelLockMap").build());
        SearchRequstByBFS searcher = new SearchRequstByBFS(target,keys);
        //searcher.setMax_search_depth(1000);
        searcher.setIs_debug(true);
        //searcher.setBlacklists(blacklists);
        searcher.searchObject();
    }
}