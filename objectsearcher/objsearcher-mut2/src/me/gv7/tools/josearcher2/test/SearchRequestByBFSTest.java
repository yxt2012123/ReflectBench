package me.gv7.tools.josearcher2.test;

import java.util.List;
import java.util.ArrayList;

import me.gv7.tools.josearcher2.entity.Blacklist;
import me.gv7.tools.josearcher2.entity.Keyword;
import me.gv7.tools.josearcher2.searcher.SearchRequstByBFS;

//original: SearchRequstByBFSTest
//Typo...
//This benchmark filter input at the end of for loop


class SearchRequestByBFSTest {
    public static void main(String[] args) {
  		  int a=0,b=0,c=0;
  	      try {
  	    	  a=Integer.parseInt(args[0]);
  	    	b=Integer.parseInt(args[1]);
  	    	c=Integer.parseInt(args[2]);
  	      }catch (Exception e) {
  	        System.out.println("3 args needed: int, int, int");
  	    	//15932026,-3539770,-1647019
  	      }
  	      main(a,b,c);
  	  }
    
    
    public static void main(int ia,int ib,int ic) {
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