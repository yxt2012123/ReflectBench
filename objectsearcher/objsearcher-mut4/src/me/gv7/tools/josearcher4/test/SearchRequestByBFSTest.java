package me.gv7.tools.josearcher4.test;

import java.util.List;
import java.util.ArrayList;

import me.gv7.tools.josearcher4.entity.Blacklist;
import me.gv7.tools.josearcher4.entity.Keyword;
import me.gv7.tools.josearcher4.searcher.SearchRequstByBFS;

//original: SearchRequestByBFSTest
//Typo...
//This benchmark intercepts at the entry of searching arrays/lists/maps
//The intercept even increases the types of reflected elements, up to 30.


class SearchRequestByBFSTest {
    public static void main(String[] args) {
    	int index = 0;
    	while (index<args.length) {
	    	String is1="";String is2="";String is3="";
			try {
				is1=args[index++];
				is2=args[index++];
				is3=args[index++];
			}
			catch(Exception e) {System.out.println("3 arg needed: String, String, String");}
			main (is1,is2,is3);
			//Search_List
			//Search_Map
			//Search_Array
    	}
    }
    public static void main(String ia, String ib, String ic) {
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