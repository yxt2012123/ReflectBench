package me.gv7.tools.josearcher5.test;

import java.util.List;
import java.util.ArrayList;

import me.gv7.tools.josearcher5.entity.Blacklist;
import me.gv7.tools.josearcher5.entity.Keyword;
import me.gv7.tools.josearcher5.searcher.SearchRequstByBFS;

//original: SearchRequestByBFSTest
//Typo...
//This benchmark take advantage of all the searching features.


class SearchRequestByBFSTest {
    public static void main(String[] args) {
    	int index = 0;
    	while (index<args.length) {
	    	boolean[] b=new boolean[100];
			try {
				for (int i=0;i<100;i++) {
					b[i]=Boolean.parseBoolean(args[index++]);
				}
			}
			catch(Exception e) {System.out.println("100 args needed: Boolean*100");}
			main (b);
			//- - - - - - - - - -
			//f f true f f true true f true true
			//true f true f true f true f true f
			//true f true f true true true f true f
			//f true f f f true true f true true
			//true f true f true f true f true f (*5)
    	}
    }
    
    public static int getDepth(boolean[] b) {
    	int count=0;
    	for (int i=50;i<99;i++) {
    		if (!(b[i]^b[i+1])) count--;
    		else count++;
    	}
    	return count;
    }
    
    public static boolean getSearchMap(boolean[] b) {
    	return b[0] || b[1] || !b[2] || b[3] || b[4] ||
    			!b[5] || !b[6] || b[7] || !b[8] || !b[9] || !b[0];
    	//Always true.
    }
    
    public static boolean getSearchList(boolean[] b) {
    	return b[10] || b[11] || !b[12] || b[13] || b[14] ||
    			!b[15] || !b[16] || b[17] || !b[18] || !b[19];
    }
    
    public static boolean getSearchArray(boolean[] b) {
    	for (int i=20;i<34;i++) {
    		if (b[i]==b[i+1]) return true;
    	}
    	return false;
    }
    
    public static boolean getSearchParents(boolean[] b) {
    	if (b[35]&&b[36]&&!b[37]&&b[38]&&!b[39]&&!b[40]&&
    			b[41]&&!b[42]&&!b[43]&&!b[44]&&b[45]&&b[46]&&
    			!b[47]&&b[48]&&b[49]) return true;
    	return false;
    }
    
    public static void main(boolean[] b) {

        //Thread thread = Thread.currentThread();
        TestClass target =  new TestClass();
        List<Keyword> keys = new ArrayList<>();
        keys.add(new Keyword.Builder().setField_type("entity").build());
        List<Blacklist> blacklists = new ArrayList<>();
        blacklists.add(new Blacklist.Builder().setField_name("parallelLockMap").build());
        SearchRequstByBFS searcher = new SearchRequstByBFS(target,keys);
        searcher.setMax_search_depth(getDepth(b));
        SearchRequstByBFS.searchArray=getSearchArray(b);
        SearchRequstByBFS.searchList=getSearchList(b);
        SearchRequstByBFS.searchMap=getSearchMap(b);
        SearchRequstByBFS.searchParents=getSearchParents(b);
        
        searcher.setIs_debug(true);
        //searcher.setBlacklists(blacklists);
        searcher.searchObject();
    }
}