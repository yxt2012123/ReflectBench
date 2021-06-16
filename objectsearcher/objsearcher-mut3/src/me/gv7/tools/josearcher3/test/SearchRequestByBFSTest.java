package me.gv7.tools.josearcher3.test;

import java.util.List;
import java.util.ArrayList;

import me.gv7.tools.josearcher3.entity.Blacklist;
import me.gv7.tools.josearcher3.entity.Keyword;
import me.gv7.tools.josearcher3.searcher.SearchRequstByBFS;

//original: SearchRequestByBFSTest
//This benchmark filter inputs through max-search-depth

class SearchRequestByBFSTest {
    public static void main(String[] args) {
		  int a=0;
  	      try {
  	    	  a=Integer.parseInt(args[0]);

  	      }catch (Exception e) {
  	        System.out.println("1 arg needed: int");
  	    	//99999999
  	      }
  	      main(a);
  	  }
    
    public static void main(int a) {
        //Thread thread = Thread.currentThread();
        TestClass target =  new TestClass();
        List<Keyword> keys = new ArrayList<>();
        keys.add(new Keyword.Builder().setField_type("entity").build());
        List<Blacklist> blacklists = new ArrayList<>();
        blacklists.add(new Blacklist.Builder().setField_name("parallelLockMap").build());
        SearchRequstByBFS searcher = new SearchRequstByBFS(target,keys);
        //
        searcher.setMax_search_depth(a%100000000-99999994);
        searcher.setIs_debug(true);
        //searcher.setBlacklists(blacklists);
        searcher.searchObject();
    }
}