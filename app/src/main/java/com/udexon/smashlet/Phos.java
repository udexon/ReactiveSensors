package com.udexon.smashlet;

import com.roxstudio.utils.*;
// import java.io.FileWriter;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.lang.reflect.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

/*
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
*/

import java.util.Arrays;

import org.jsoup.nodes.*;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.net.*;
import java.io.IOException; import java.nio.file.Files; import java.nio.file.Paths;

import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Phos {

    static HashMap<String, List<String>> CDW = new HashMap<String, List<String>>();
    // static HashMap<String, List<String>> SHV = new HashMap<String, List<String>>();

    static HashMap<String, String> BV = new HashMap<String, String>();
    
    // static HashMap<String, String> SHV = new HashMap<String, String>();

    // branch label
    static HashMap<String, Integer> SL = new HashMap<String, Integer>();
    
    static HashMap<String, Map> SHV = new HashMap<String, Map>();
    // static TreeMap<String, Map> T   = new TreeMap<String, Map>();

    static TreeMap<String, String> T   = new TreeMap<String, String>();

    // static Map<String, Object> SHV; // = new Map<String, Object>();
    public static Stack<String> S=new Stack<String>();
    static int sm_tmp_i; // global int
    
    static List<String> WL = new ArrayList<String>();
    
    static String F_OTHER = "";

    public static void main(String[] args) {
    
		String[] T = args ;
		
		int L=T.length;
		int i;
		int $;

		String s_h = ""; // history

        for(i=0; i<args.length; i++) {
            WL.add(args[i]);
            s_h = s_h +" "+ args[i];
        }

        S.push( s_h );
        S.push( "/storage/emulated/0/Phos/p_history" );
        sm_wa();
	    
	    String s = ": dltag a bv: ig/${a}.html https://www.instagram.com/explore/tags/${a}/ copy: ;";
	    String[] s_args = s.split(" ");
	    FGLA(s_args);
	    
	    F(": dlsc ig/${a}.html fgc: commanl: nlc: explode: /shortcode/ grep: dl_shortcode: ;");
	    
	    // F(": commanl:
	    F(": alike_user_trb cx: dup:    dup: dup: esp: esp: XXX esp:  A l:  nl:  dup:   dup: dup: XXX esp: esp: esp:   2over:   swap: -   dup: esp: COUNTER .   4 ixn:   dup: dup_before_new_ops_remove_esp_because_it_pops_the_dup .      fgc: commanl: nlc: explode: /preview_like/ gx: akx: 0 i: i: colon: explode: 2 i:   dup: A pshv:   esp: /username/ grep: akx: cx: 1 - i: i: colon: explode: 1 i: dup: B pshv: esp:  SCSCSC esp:   dup: jpg: ig/ swap: 2 mssx: dq: src eq: img swap: 300 height eq: 3 mss: trb:     C pshv:         esp:   space: ec:  1 -    A bnz: ;");
	    
	    F(": ncol cmx: dup:   A l: "+
    " <tr> esp:  dup: 2over:   swap: -  "+
    " dup:_ . esp:_ . COUNTER . "+
    " dup: COUNTER_for_Col_B .  "+
    " 5 ixn: td: esp:  ixn_gets_COLUMN_A_from_outside_loop . "+
    " dup: "+
    " C0 B0 2 coln . ."+
    " . "+ // pop the last counter dup (left over)
    " </tr> esp: nl:     1 -    A bnz: ;"); 

        F(": coln A l:    3 pick: 2 pick: rshv: swap:  i: td: esp:     swap:     . "+
            "       1 -  A bnz: ;");

        F( "/storage/emulated/0/Phos/p_cdw fgc: ucdw:"); // load CDW at start

	    FGLA(args);
					
    }
    
    public static void F(String s)
    {
       String before = s;
       String after = before.trim().replaceAll(" +", " ");

    // $a = explode(' ', trim($a)); // remove front and trailing spaces
    // String[] T = after.split(" ");  // java must use double quotes, not single quotes!!

        FGLA( after.split(" ") );
    }

    // since time lapse between sensor data ready is random, find max value push to stack
    public static void sm_now()
    {
        LocalDateTime myObj = LocalDateTime.now();
        // System.out.println(myObj);
        S.push( myObj.toString() );
    }

    public static void sm_fsub() // floating point subtraction
    {
        S.push(Float.toString(Float.parseFloat(S.pop())-Float.parseFloat(S.pop())));
    }
    
    public static void sm_sleep2s() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        System.out.println("Sleep time in ms = "+(System.currentTimeMillis()-start));
        
    }
    
    public static void sm_sleep() throws InterruptedException {
        // long start = System.currentTimeMillis();
        Thread.sleep( Integer.parseInt( S.pop() ) );
        // System.out.println("Sleep time in ms = "+(System.currentTimeMillis()-start));
        
    }

    public static void sm_i() // json_HashMap n i: n-th element
    {
        // int $a = Integer.parseInt( S.pop() );
        String $a = S.pop();
        
        Gson gson = new Gson(); 
     
        String $b = S.pop();
        
        // System.out.print( "  179 sm_i "+ $b +" "  );
        
        HashMap<String, String> HMA = new HashMap<String, String>();
        HMA = gson.fromJson( $b , HashMap.class);        
        
        // String[] userArray = gson.fromJson( $b , String[].class);  

        // System.out.print( "  183 sm_i "+ userArray[ $a ]  );
        
        // System.out.println( "  188 sm_i "+ HMA.get( $a )  );
        
        S.push( HMA.get( $a ) );
        
//        S.push( userArray[ $a ] );
        
  //      System.out.print( "  185 sm_i "+ userArray[ $a ]  );
    }




    public static void sm_ia() // jsonArray i ia: 
    {
        int $a = Integer.parseInt( S.pop() );
        // String $a = S.pop();
        
        Gson gson = new Gson(); 
     
        String $b = S.pop();
        
        // System.out.print( "  179 sm_i "+ $b +" "  );
        
        HashMap<String, String> HMA = new HashMap<String, String>();
        // HMA = gson.fromJson( $b , HashMap.class);        
        
        String[] userArray = gson.fromJson( $b , String[].class);  

        // System.out.print( "  183 sm_i "+ userArray[ $a ]  );
        
        // System.out.println( "  188 sm_i "+ HMA.get( $a )  );
        
        // S.push( HMA.get( $a ) );
        
        S.push( userArray[ $a ] );
        
        // System.out.println( "  185 sm_i "+ userArray[ $a ]  );
    }



    public static void sm_akx() // PHP array_keys no pop
    {
        // HashMap<String, String> HMA = new HashMap<String, String>();
        TreeMap<String, String> HMA = new TreeMap<String, String>();
        TreeMap<String, String> HMO = new TreeMap<String, String>();
        
        Gson gson = new Gson(); 
     
        // String $b = S.pop();
        String $b = S.peek();
        
        // HMA = gson.fromJson( $b , HashMap.class);
        HMA = gson.fromJson( $b , TreeMap.class);
        
        // System.out.print( "  "+ HMA.keySet() +" "  );  
        
        int i=0;
        
        String ko= new String();
        
        Set<String> KS = HMA.keySet();
        
        // Collections.sort( KS );
        
        // for( i=0; i<KS.size(); i++  )
        for( String k : KS )
        {
            // System.out.print( "  "+ k +" "  ); 
            HMO.put( Integer.toString(i), k );
            ko=k; 
            i++;
        }
        
        // HMO.put( Integer.toString(i-1), ko );
        String jsonArray=gson.toJson( HMO );

        S.push( jsonArray );
    }


        
    public static void sm_fgc()
    {
        // System.out.println("  147 In sm_fgc" );
    
        try {
        InputStream is = new FileInputStream( S.pop() );
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
                
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
                
        while(line != null){
           sb.append(line).append("\n");
           line = buf.readLine();
        }
                
        String fileAsString = sb.toString();
        // System.out.println("Contents : " + fileAsString);
        
        S.push( fileAsString );
        }
        catch (Exception e) {}
        
    }
    
    public static void sm_cx() // cx: count / array size, no pop
    {
        Gson gson = new Gson(); 
        // List<String> f_path = new ArrayList<String>();
    
        String vv = S.peek();

        System.out.println( "  195 cx: "  );
        
        // Map map = gson.fromJson( vv, Map.class); 
        List<String> f_path = gson.fromJson( vv, List.class); 
        
        System.out.println( "  199 cx: "  );
        
        // Map VM = SHV.get( vn ); // .push( vv );
        // VM.put("a", map);
        // VM.put("2", map);
        // VM.put("0", map);
        
        
        System.out.println( f_path );
        System.out.println( f_path.size() );
        
        S.push( Integer.toString( f_path.size() ) );
        
    }

    public static void sm_cmx() // cmx: count map as array size, no pop
    {
        Gson gson = new Gson(); 
        // List<String> f_path = new ArrayList<String>();
    
        String vv = S.peek();

        // System.out.println( "  323 cmx: "  );
        
        Map f_path = gson.fromJson( vv, Map.class); 
        // List<String> f_path = gson.fromJson( vv, List.class); 
        
        // System.out.println( "  328 cmx: "  );
        // System.out.println( f_path );
        // System.out.println( f_path.size() );
        
        S.push( Integer.toString( f_path.size() ) );
    }

    
    public static void sm_glob()
    {
       // String glob = "glob:**/*.java";
       
String glob = "glob:**/"+S.pop();
		String path = ".";
		try {
    		match(glob, path);
		}
		catch (Exception e) {}
		
   //  HashMap<String, List<String>> HMA = new HashMap<String, List<String>>();
        
     List<String> f_path = new ArrayList<String>();
        
        Gson gson=new GsonBuilder().create();
        
        S.pop();
        for(int i=0; i<sm_tmp_i; i++)
        f_path.add(S.pop());
        
        Collections.sort( f_path );
        
        String jsonArray=gson.toJson(f_path);
        
        S.push( jsonArray );
	}
    
    
    
    public static void match(String glob, String location) throws IOException {
		
		final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(
				glob);
				
		// final Int count = new Int();		
		// final int count=0;
		sm_tmp_i = 0;
		
		Files.walkFileTree(Paths.get(location), new SimpleFileVisitor<Path>() {
			
			@Override
			public FileVisitResult visitFile(Path path,
					BasicFileAttributes attrs) throws IOException {
				if (pathMatcher.matches(path)) {
					System.out.println(path);
					S.push( path.toString() );
					sm_tmp_i++;
					// if ( !attrs.isDirectory() && hasRepExt( location ) )
					// count.value++;
					// count++;
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc)
					throws IOException {
				return FileVisitResult.CONTINUE;
			}
		});
		
		S.push( Integer.toString(sm_tmp_i) );
	}
        
    // function fgl_dl_shortcode()
    public static void sm_dl_shortcode()
    {

        // $a=array_pop($S);
        String $a = S.pop();
        
        Gson gson = new Gson(); 
 
        String[] userArray = gson.fromJson($a, String[].class);  

        /*
        foreach($a as $k => $b)
        {
        $c=explode(":", $b);
        echo "\n\n In fgl_dl_shortcode ";
        echo " $k ".$c[1]." ";
        $l=strlen($c[1]);
        $d=substr($c[1],1,$l-3);
        echo $d;

        //    flush();
        //    ob_flush();

        copy("https://www.instagram.com/p/".$d, "ig/".$d.".html");
        if (file_exists("ig/".$d.".html")) get_meta("ig/".$d.".html");

        }
        */

        File file;

        for(String b : userArray) {

            String[] $c=b.split(":");
                
            System.out.println(b);
            
            int $l=$c[1].length();
            String $d=$c[1].substring(1,$l-2);

            // copy("https://www.instagram.com/p/".$d, "ig/".$d.".html");
            // S.push("ig/"+$d+".html");
            // S.push("https://www.instagram.com/p/"+$d);
            // F("ig/"+$d+".html https://www.instagram.com/p/"+$d+" copy:");
            
            // URL must have '/' at end for path!!
            // curl_copy( "https://www.instagram.com/p/"+$d+"/", "ig/"+$d+".html");
            
            S.push( "ig/"+$d+".html" );
            S.push( "https://www.instagram.com/p/"+$d+"/" );
            sm_copy();

            // if (file_exists("ig/".$d.".html")) get_meta("ig/".$d.".html");            
            file = new File("ig/"+$d+".html");
            if (file.exists() && file.isFile())
            {
              System.out.println("file exists, and it is a file: "+ "ig/"+$d+".html");
              
              S.push( "ig/"+$d+".html" );
              sm_meta();
            }            

        }
    }
    

    public static void sm_meta()
    {
        String fn = S.pop();

        System.out.println( "  247 sm_meta "+ fn);
                
        // Document doc = Jsoup.parse("test.html");
        try {

            System.out.println( "  252 sm_meta "+ fn);

            File input = new File( fn );        
            
            System.out.println( "  256 sm_meta "+ fn);
            
            // dies WITHOUT warning if jar is not included!!
            Document doc = Jsoup.parse( input, "UTF-8", "" );
            
            System.out.println( "  260 sm_meta "+ fn);
            
            System.out.println( "  262 doc body test "+ doc.body().text() );

            Map<String, String> metas = new HashMap<>();
            Elements metaTags = doc.getElementsByTag("meta");

            int i=0;
            // String[] f_jpath;
            List<String> f_jpath = new ArrayList<String>();
            String f_jurl="";
            
            for (Element metaTag : metaTags) {
              String content = metaTag.attr("content");
              String name = metaTag.attr("name");
              metas.put(name, content);
              if (i==10) {
                System.out.println( metas.get(name) );
                f_jpath = Arrays.asList( metas.get(name).split("/") );
                f_jurl = metas.get(name);
              }
              i++;
            }
            System.out.println( "  260 jpg url "+ f_jpath.get(6) );
            
            String[] f_jfn = f_jpath.get(8).split("\\?");
            
            System.out.println( "  265 jpg url "+ f_jpath.get(8) );
            System.out.println( f_jfn[0] );
            
            // curl_copy( f_jurl+"/", "ig/"+f_jfn[0] );
            // curl_copy( f_jurl+"/", "ig/"+f_jfn[0] );
            // curl_copy( f_jurl, "ig/"+f_jfn[0] );
            
            S.push( "ig/"+f_jfn[0] ); S.push( f_jurl );
            sm_copy();
        }
        
        catch (Exception e) {
            System.out.println( "  528 Exception "+ e );
        }
    }    


    public static void sm_mssx()
    // function fgl_mssx() // n -- merge string on stack (n items), no space
    {
        // $l=array_pop($S);
        int $l = Integer.parseInt( S.pop() );
        
        // $L=count($S);
        int $L = S.size();
        
        // $s=$S[$L-$l];
        
        int $i=0; 
        
        String $s=S.elementAt($L-$l); 
        S.removeElementAt($L-$l);
        
        // System.out.println( "  549 sm_mssx "+ $s );
        
        for($i=$L-$l+1; $i<$L; $i++) {
            $s=$s + S.elementAt($L-$l); 
            S.removeElementAt($L-$l);
        }
            // $s=$s.$S[$i]; 
        
        // array_splice($S, $L-$l);
        // $S[]=$s;
        
        // System.out.println( "  558 sm_mssx "+ $s );
        
        S.push( $s );
    }
    
    
    public static void sm_mss()
    // function fgl_mssx() // n -- merge string on stack (n items), no space
    {
        // $l=array_pop($S);
        int $l = Integer.parseInt( S.pop() );
        
        // $L=count($S);
        int $L = S.size();
        
        // $s=$S[$L-$l];
        
        int $i=0; 
        
        String $s=S.elementAt($L-$l); 
        S.removeElementAt($L-$l);
        
        // System.out.println( "  549 sm_mssx "+ $s );
        
        for($i=$L-$l+1; $i<$L; $i++) {
            $s=$s +" "+ S.elementAt($L-$l); 
            S.removeElementAt($L-$l);
        }
            // $s=$s.$S[$i]; 
        
        // array_splice($S, $L-$l);
        // $S[]=$s;
        
        // System.out.println( "  558 sm_mssx "+ $s );
        
        S.push( $s );
    }


    public static void sm_jpg()
    {
        String fn = S.pop();

        // System.out.println( "  247 sm_meta "+ fn);
                
        // Document doc = Jsoup.parse("test.html");
        try {

            // System.out.println( "  252 sm_meta "+ fn);

            File input = new File( fn );        
            
            // System.out.println( "  256 sm_meta "+ fn);
            
            // dies WITHOUT warning if jar is not included!!
            Document doc = Jsoup.parse( input, "UTF-8", "" );
            
            // System.out.println( "  260 sm_meta "+ fn);
            
            // System.out.println( "  262 doc body test "+ doc.body().text() );

            Map<String, String> metas = new HashMap<>();
            Elements metaTags = doc.getElementsByTag("meta");

            int i=0;
            // String[] f_jpath;
            List<String> f_jpath = new ArrayList<String>();
            String f_jurl="";
            
            for (Element metaTag : metaTags) {
              String content = metaTag.attr("content");
              String name = metaTag.attr("name");
              metas.put(name, content);
              if (i==10) {
                // System.out.println( metas.get(name) );
                f_jpath = Arrays.asList( metas.get(name).split("/") );
                f_jurl = metas.get(name);
              }
              i++;
            }
            
            // System.out.println( "  260 jpg url "+ f_jpath.get(6) );
            
            String[] f_jfn = f_jpath.get(8).split("\\?");
            
            // System.out.println( "  265 jpg url "+ f_jpath.get(8) );
            // System.out.println( f_jfn[0] );
            
            // curl_copy( f_jurl+"/", "ig/"+f_jfn[0] );
            // curl_copy( f_jurl+"/", "ig/"+f_jfn[0] );
            // curl_copy( f_jurl, "ig/"+f_jfn[0] );
            
            // S.push( "ig/"+f_jfn[0] ); 
            S.push( f_jfn[0] ); 
            
            // S.push( f_jurl );
            // sm_copy();
        }
        
        catch (Exception e) {
            System.out.println( "  528 Exception "+ e );
        }
    }    

    public static void sm_3sort()
    // function fgl_3sort() // A B C 3sort: sort array A, B+C follows A
    {
        // $c=array_pop($S);    
        String $c = S.pop();
        
        // $b=array_pop($S);   
        String $b = S.pop();
         
        // $a=array_pop($S);
        String $a = S.pop();
        
        // array_multisort($SHV[$a], SORT_NUMERIC , $SHV[$b], $SHV[$c]);
        System.out.println( "  676 3sort: "+ SHV.get( $a ) );
        System.out.println( "  676 3sort: "+ SHV.get( $b ) );   
        System.out.println( "  676 3sort: "+ SHV.get( $c ) );  
        
        String[] t_args = { $a, $b, $c };
        Col3_i( t_args );
    }

    public static void Col3(String[] args) {
        List<Report> reportList = new ArrayList<>();
        
        /*
        reportList.add(new Report("reportKey2", "studentNumber2", "school1"));
        reportList.add(new Report("reportKey4", "studentNumber4", "school6"));
        reportList.add(new Report("reportKey1", "studentNumber1", "school1"));
        reportList.add(new Report("reportKey3", "studentNumber2", "school4"));
        reportList.add(new Report("reportKey2", "studentNumber2", "school3"));
        */

        int L = SHV.get( args[0] ).size() ;
        int i=0;
        
        for( i=0; i<L; i++)
            reportList.add(new Report( 
                SHV.get( args[0] ).get( Integer.toString(i) ).toString(),
                SHV.get( args[1] ).get( Integer.toString(i) ).toString(),
                SHV.get( args[2] ).get( Integer.toString(i) ).toString() ));

        System.out.println("pre-sorting");
        System.out.println(reportList);
        System.out.println();

        Collections.sort(reportList, Comparator.comparing(Report::getReportKey)
            .thenComparing(Report::getStudentNumber)
            .thenComparing(Report::getSchool));

        System.out.println("post-sorting");
        System.out.println(reportList);
        
        // System.out.println( "  703 Col3 "+ args );
        System.out.println( "  703 Col3 "+ SHV.get( args[0] ) );  
        System.out.println( "  703 Col3 "+ SHV.get( args[1] ) );  
        System.out.println( "  703 Col3 "+ SHV.get( args[2] ) );
        System.out.println( SHV.get( args[2] ).size() );  
    }


    public static void Col3_i(String[] args) {
        List<Report_i> reportList = new ArrayList<>();
        
        /*
        reportList.add(new Report("reportKey2", "studentNumber2", "school1"));
        reportList.add(new Report("reportKey4", "studentNumber4", "school6"));
        reportList.add(new Report("reportKey1", "studentNumber1", "school1"));
        reportList.add(new Report("reportKey3", "studentNumber2", "school4"));
        reportList.add(new Report("reportKey2", "studentNumber2", "school3"));
        */

        System.out.println( "  734 Col3_i "+ SHV.get( args[2] ) );


        int L = SHV.get( args[0] ).size() ;
        int i=0;
        
        for( i=0; i<L; i++)
            reportList.add(new Report_i( 
                SHV.get( args[0] ).get( Integer.toString(i) ).toString(),
                SHV.get( args[1] ).get( Integer.toString(i) ).toString(),
                SHV.get( args[2] ).get( Integer.toString(i) ).toString() ));

        System.out.println("pre-sorting");
        System.out.println(reportList);
        System.out.println();

        Collections.sort(reportList, Comparator.comparing(Report_i::getReportKey)
            .thenComparing(Report_i::getStudentNumber)
            .thenComparing(Report_i::getSchool));

        System.out.println("post-sorting");
        System.out.println(reportList);
        
        
        L = reportList.size() ;
        
        sm_array(); S.push( "A0" ); sm_shv();
        sm_array(); S.push( "B0" ); sm_shv();
        sm_array(); S.push( "C0" ); sm_shv();
        
        for( i=0; i<L; i++) {
            System.out.println( reportList.get( i ) );
            System.out.println( reportList.get( i ).reportKey );
            
            S.push( Integer.toString( reportList.get( i ).reportKey ) );
            S.push( "A0" );
            sm_pshv();
            
            S.push( reportList.get( i ).studentNumber );
            S.push( "B0" );
            sm_pshv();
            
            S.push( reportList.get( i ).school );
            S.push( "C0" );
            sm_pshv();
        }
        /*
            reportList.add(new Report_i( 
                SHV.get( args[0] ).get( Integer.toString(i) ).toString(),
                SHV.get( args[1] ).get( Integer.toString(i) ).toString(),
                SHV.get( args[2] ).get( Integer.toString(i) ).toString() ));
        */
        
        // System.out.println( "  703 Col3 "+ args );
        System.out.println( "  787 Col3_i "+ SHV );  
        
        /*
        System.out.println( "  703 Col3 "+ SHV.get( args[1] ) );  
        System.out.println( "  703 Col3 "+ SHV.get( args[2] ) );
        System.out.println( SHV.get( args[2] ).size() ); 
        */ 
    }

    public static void sm_rshv()     
    // function fgl_rshv() // read shell var
    {
        // $S[]=$SHV[array_pop($S)];
        System.out.println( "  800 rshv: "+ SHV );  
        
        Gson gson = new Gson(); 
        // SHV.put( S.pop(), gson.fromJson( S.pop(), HashMap<String, List<String>>.class) ); 
        
        String vn = S.pop();
        // String vv = S.pop();

        System.out.println( "  805 rshv: "+ vn );
        
        // Map map = gson.fromJson( vv, Map.class); 
        
        // System.out.println( vv );
        
        Map VM = SHV.get( vn ); // .push( vv ); 
        
        System.out.print( "  816 rshv: " );
        System.out.println( VM );  
        
        Gson gson0=new GsonBuilder().create();
        String jsonArray=gson0.toJson( VM );
        
        S.push( jsonArray );
    }


    public static void Col_Report(String[] args) {
        List<Report> reportList = new ArrayList<>();
        reportList.add(new Report("reportKey2", "studentNumber2", "school1"));
        reportList.add(new Report("reportKey4", "studentNumber4", "school6"));
        reportList.add(new Report("reportKey1", "studentNumber1", "school1"));
        reportList.add(new Report("reportKey3", "studentNumber2", "school4"));
        reportList.add(new Report("reportKey2", "studentNumber2", "school3"));

        System.out.println("pre-sorting");
        System.out.println(reportList);
        System.out.println();

        Collections.sort(reportList, Comparator.comparing(Report::getReportKey)
            .thenComparing(Report::getStudentNumber)
            .thenComparing(Report::getSchool));

        System.out.println("post-sorting");
        System.out.println(reportList);
    }

    private static class Report_i {

        // private String reportKey;
        private int reportKey;
        private String studentNumber;
        private String school;

        public Report_i(String reportKey, String studentNumber, String school) {
            System.out.println( "  793 Report_i "+ reportKey.substring(0, reportKey.length()-1 ) );
            this.reportKey = Integer.parseInt( reportKey.substring(0, reportKey.length()-1 ) );
            System.out.println( "  795 Report_i "+ reportKey );
            this.studentNumber = studentNumber;
            this.school = school;
        }

        public int getReportKey() {
            return reportKey;
        }

        public void setReportKey(String reportKey) {
            this.reportKey = Integer.parseInt(  reportKey );
        }

        public String getStudentNumber() {
            return studentNumber;
        }

        public void setStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        @Override
        public String toString() {
            return "Report{" +
                   "reportKey='" + reportKey + '\'' +
                   ", studentNumber='" + studentNumber + '\'' +
                   ", school='" + school + '\'' +
                   '}';
        }
    }

    private static class Report {

        private String reportKey;
        private String studentNumber;
        private String school;

        public Report(String reportKey, String studentNumber, String school) {
            this.reportKey = reportKey;
            this.studentNumber = studentNumber;
            this.school = school;
        }

        public String getReportKey() {
            return reportKey;
        }

        public void setReportKey(String reportKey) {
            this.reportKey = reportKey;
        }

        public String getStudentNumber() {
            return studentNumber;
        }

        public void setStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        @Override
        public String toString() {
            return "Report{" +
                   "reportKey='" + reportKey + '\'' +
                   ", studentNumber='" + studentNumber + '\'' +
                   ", school='" + school + '\'' +
                   '}';
        }
    }


    public static void sm_meta_0()
    {
      System.out.println( "  230 sm_meta");
    
        try {
        File input = new File( S.pop() );
        Document doc = Jsoup.parse(input, "UTF-8", "");

// Element content = doc.getElementById("content");
Element content = doc.getElementById("body");
// Elements links = content.getElementsByTag("a");
Elements links = doc.getElementsByTag("meta");
for (Element link : links) {
  String linkHref = link.attr("href");
  String linkText = link.text();
  
      System.out.println(linkHref);
}            
        }
        catch (Exception e) {
              System.out.println( "  246 sm_meta "+e);
        }
    
    }

    public static void sm_commanl()
    {
        String new_str = ",\n" ;

        String patternString = ",";        
        
        String text = S.pop() ;

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        /*
        int count = 0;
        while(matcher.find()) {
            count++;
            text = text.replace( text.substring(matcher.start(), matcher.end()),  
                    new_str );
        }
        */
        
        String replaceAll = matcher.replaceAll( new_str );
        
        S.push( replaceAll );
    }
    
    public static void sm_nlc() // nlc: push newline character to stack
    {
        S.push( "\n" );
    }
    
    public static void sm_array() // array: PHP style empty array
    {
        HashMap<String, List<String>> HMA = new HashMap<String, List<String>>();
        
        Gson gson=new GsonBuilder().create();
        String jsonArray=gson.toJson(HMA);
        
        S.push( jsonArray );
    }
    public static void sm_pick() // n pick: duplicate n-th item below TOS
    // function fgl_pick()
    {
        // $l = count($S);
        int $l = S.size();
        
        // $S[] = $S[$l - 2 - array_pop($S)];
        S.push( S.elementAt( $l - 2 - Integer.parseInt( S.pop() ) ) );
    }

    public static void sm_pshv() // vv vn shv: push vv to vn in SHV
    {
        // PHP array autoincrement index from 0, need to add code for Java Map
        
        Gson gson = new Gson(); 
        // SHV.put( S.pop(), gson.fromJson( S.pop(), HashMap<String, List<String>>.class) ); 
        
        String vn = S.pop();
        String vv = S.pop();

        System.out.println( "  758 pshv: "+ vn +" "+ vv  );
        
        // Map map = gson.fromJson( vv, Map.class); 
        
        System.out.print( "  762 pshv: " );
        System.out.println( vv );
        
        Map VM = SHV.get( vn ); // .push( vv ); 
        
        /*
        System.out.println( SHV );
        System.out.println( VM );
        System.out.println( VM.size() );
        System.out.println( VM.keySet() );
        */
        
        // String[] ks = VM.keySet().toArray(new String[map.size()]);
        Set<String> ks = VM.keySet();
        
        List L = new ArrayList();
        
        for( String k : ks ) {
            // System.out.println( k );
            if (isStringInt(k))
            {
               // Number is integer
               // System.out.print( "  Is integer: " );
               // System.out.println( Integer.parseInt(k) );
               L.add( Integer.parseInt(k) );
            }
        }
        
       // System.out.println( L );
       Collections.sort( L );
       // System.out.println( L );
       
       // int Lx = 0;
       int i=0;
       String nx="0";
        
         if (L.size()>0) {
            for( i=0; i<L.size(); i++ ) {
                if ( i > 0) {
            // System.out.println( Integer.parseInt( L.get( i ).toString() ) - Integer.parseInt( L.get( i - 1 ).toString() )  );
        
                    if ( ( Integer.parseInt( L.get( i ).toString() ) - Integer.parseInt( L.get( i - 1 ).toString() )  ) > 1 ) {
                    break;
                }
            }
        }

        // System.out.print( "  Next available index: " );
        // System.out.println( Integer.parseInt( L.get( i - 1 ).toString() ) + 1 );      
       
        nx = Integer.toString( Integer.parseInt( L.get( i - 1 ).toString() ) + 1 );
        }
       
        // VM.put( nx, map);
        VM.put( nx, vv );
        
        System.out.println( VM );        
        System.out.println( SHV );
    }
    
    public static boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
    
    public static void sm_shv() // val varname shv: create variable in SHV and initialize it
    {
        Gson gson = new Gson(); 
        // SHV.put( S.pop(), gson.fromJson( S.pop(), HashMap<String, List<String>>.class) ); 
        
        String varname = S.pop();
        String jsonString = S.pop();
        
        Map map = gson.fromJson(jsonString, Map.class); 
        
        System.out.println( "  383 "  );
        
        // SHV.put( varname, jsonString ); // store jsonString as is, convert when there is a need to modify it?
        SHV.put( varname, map );
        
        System.out.println( SHV );
    }

    public static void sm_t() // val varname t: create variable in T and initialize it
    {
        Gson gson = new Gson();

        String varname = S.pop();
        String jsonString = S.pop();

        // Map map = gson.fromJson(jsonString, Map.class);
        // T.put( varname, map );

        T.put( varname, jsonString );

        // System.out.print( "  1097 t: " );        
        // System.out.println( T );
    }

    public static void sm_rt() // varname rt: read variable in T, push on to stack
    {
        // S.push( T.get( S.pop() ).toString() );
        S.push( T.get( S.pop() ) );
    }

    public static void sm_tr() // varname tr: read variable in T, push on to stack, t? convention for tree related functions
    {
        S.push( T.get( S.pop() ).toString() );
    }

    
    public static void sm_explode() // explode: PHP explode
    {
        // How to store array in Java Stack<String>? Use JSON? Need type prefix?
        // Use json format string as prefix?
        
        String dlm = S.pop();
        String s_in = S.pop();
        
        String[] sa = s_in.split(dlm);
        
        // JSONArray arr_strJson = new JSONArray(Arrays.asList(sa));
        
        Gson gson=new GsonBuilder().create();
        String jsonArray=gson.toJson(sa);
        
        S.push( jsonArray );
        
        // System.out.println("  In explode: " + jsonArray +" "+ sa.length +" "+ s_in +" "+ dlm );
    }


    public static void sm_grep()
    {
        // String new_str = S.pop() ;
        Stack<String> S0=new Stack<String>();
        // HashMap<String, String> HMA = new HashMap<String, String>();
        TreeMap<String, String> HMA = new TreeMap<String, String>();

        String patternString = S.pop();        
        
        int L = patternString.length();
        patternString = patternString.substring(1, L-1);
        
        String text = S.pop() ;

        Pattern pattern = Pattern.compile(patternString);

        Gson gson = new Gson(); 
     
        String[] userArray = gson.fromJson(text, String[].class);  
     
        int j=0;
        for(String user : userArray) {
            Matcher matcher = pattern.matcher(user);
                
                int count = 0;
                while(matcher.find()) {
                    count++;
                    // System.out.println(user);
                    // S0.push(user);
                    HMA.put( Integer.toString(j), user );
                    
                }
                
            j++;
        }
        
        // Collections.sort( HMA );

        Object[] arr = S0.toArray();
        // System.out.println(arr.length);
        // Gson gson=new GsonBuilder().create();
        // String jsonArray=gson.toJson(arr);
        String jsonArray=gson.toJson(HMA);
        
        S.push( jsonArray );        
     }


   public static void sm_gx() // gx: grep, no pop.
    {
        // String new_str = S.pop() ;
        Stack<String> S0=new Stack<String>();
        
HashMap<String, String> HMA = new HashMap<String, String>();
        

        String patternString = S.pop();        
        
        int L = patternString.length();
        patternString = patternString.substring(1, L-1);
        
        String text = S.peek() ;

        Pattern pattern = Pattern.compile(patternString);

        Gson gson = new Gson(); 
     
        String[] userArray = gson.fromJson(text, String[].class);  
     
     int j=0;
        for(String user : userArray) {
            Matcher matcher = pattern.matcher(user);
                
                int count = 0;
                while(matcher.find()) {
                    count++;
                    // System.out.println(Integer.toString(j) +" "+ user);
                    // S0.push(user);
                    HMA.put( Integer.toString(j), user );
                    
                }
                
                j++;
        }

        Object[] arr = S0.toArray();
        // System.out.println(arr.length);
        // for (String sa: arr.toArray() ) System.out.println(sa);
        // Gson gson=new GsonBuilder().create();
        // String jsonArray=gson.toJson(arr);
        String jsonArray=gson.toJson( HMA );
        
        S.push( jsonArray );        
     }
    


    
    public static void curl_copy( String url, String fn )
    {
			
        // System.out.println( S.pop() + " " + S.pop() );
        
        /*
        String[] s_args = new String[2];
        s_args[0] = ( url );
        s_args[1] = "-o";
        */
        
        String[] s_args = { url, "--output", "test.jpg" };

        System.out.println( "  403 curl_copy "+ s_args[0] );
	
	    // if (false) {
	    if (true) {
	            String s = new CUrl().opt(s_args).exec(null);
    
                // System.out.println(s);
                
                try {
   
                    // BufferedWriter writer = new BufferedWriter( new FileWriter( "o_java.html" ));
   
                    BufferedWriter writer = new BufferedWriter( new FileWriter( fn ));
                
                    writer.write( s );
                    // do stuff 
                    writer.close();
                }
                catch ( Exception e ) {}
                
        }
        
        System.out.println( "  338 sm_copy "+ s_args[0] );
    }
    
    // public static void sm_copy_nio()
    public static void sm_copy()
    {
    
        String url = S.pop(); 
        
        String fn = S.pop();
        
        System.out.println( "  446 sm_copy "+ fn +" "+ url );
 
        try(InputStream in = new URL( url ).openStream()){
            Files.copy(in, Paths.get( fn ));
        }
        
        catch ( Exception e ) {}
    }

    
    
    public static void sm_copy_curl()
    {
			
        // System.out.println( S.pop() + " " + S.pop() );
        
        String[] s_args = new String[1];
        s_args[0] = ( S.pop() );

        System.out.println( "  317 "+ s_args[0] );
	
	    // if (false) {
	    if (true) {
	            String s = new CUrl().opt(s_args).exec(null);
    
                System.out.println(s);
                
                try {
   
                    // BufferedWriter writer = new BufferedWriter( new FileWriter( "o_java.html" ));
   
                    BufferedWriter writer = new BufferedWriter( new FileWriter( S.pop() ));
                
                    writer.write( s );
                    // do stuff 
                    writer.close();
                }
                catch ( Exception e ) {}
                
        }
        
        System.out.println( "  338 sm_copy "+ s_args[0] );
    }

    public static void sm_cdw() // cdw: push all CDW to stack
    {
        Gson gson=new GsonBuilder().create();
        String jsonArray=gson.toJson( CDW );
        System.out.println( "  1284 cdw: "+ jsonArray ); 
        S.push( jsonArray );
    }
    
    public static void sm_ucdw() // tocdw: update CDW from json
    {
       Gson gson = new Gson(); 
       CDW = gson.fromJson( S.pop() , HashMap.class);     
    }

    public static void sm_s() // s: print stack
    {
        for(int i=0; i<S.size(); i++)
            System.out.print( "  "+ S.elementAt(i) +" "  );
    
    }

    public static void sm_ixn()
    // function fgl_ixn() // extract i-th element from array, no pop, array at ($l - $n) location on stack
    {
        // $l = count($S);
        int $l = S.size();

        // $n = array_pop($S);
        int $n = Integer.parseInt( S.pop() );
        
        // $a = array_pop($S);
        int $a = Integer.parseInt( S.pop() );
        
        Gson gson = new Gson(); 
     
        // String[] userArray = gson.fromJson(text, String[].class);  

        // $b = $S[ $l - $n - 1 ];
        String $b = S.elementAt( $l - $n - 1 );
        
        // System.out.print( "  "+ $b +" "  );
        
        String[] userArray = gson.fromJson( $b , String[].class);  
        
        // $S[] = $b[$a];
        S.push( userArray[ $a ] );

    }


    public static void sm_preg_replace()
    {
        String new_str = S.pop() ;

        String patternString = S.pop();        
        
        String text = S.pop() ;

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        /*
        int count = 0;
        while(matcher.find()) {
            count++;
            text = text.replace( text.substring(matcher.start(), matcher.end()),  
                    new_str );
        }
        */
        
        // S.push( text );
        
        String replaceAll = matcher.replaceAll( new_str );
        
        S.push( replaceAll );
    }
    
    
    // public static String sm_other(String $v)
    public static void sm_other(String $v)
    {    
        // System.out.println("  1396 sm_other "+ $v );
    
        if ($v.equals("bv:")) {
            BV.put( S.pop(), S.pop() );
            System.out.println("  bv: sm_bv " );
            System.out.println( BV ); // + S.pop() +" "+ S.pop() );
        }                  
		else if ($v.equals("dup:") || $v.equals("dup")) {
         //   System.out.print(" stack size: " ); System.out.println( S.size() );
            
        //    System.out.println( " 776 dup: "+ S.peek() ); // + S.pop() +" "+ S.pop() );
		    S.push( S.peek() );
		    
		 //   System.out.print(" stack size: " ); System.out.println( S.size() );
		}
		else if ($v.equals("colon:")) {
		    S.push( ":" );
		}
		else if ($v.equals("nl:")) {
    		System.out.println();
		}
		else if ($v.equals("dq:")) {
		    S.push( "\""+ S.pop() +"\"" ); 
		}
		else if ($v.equals("trb:")) {
		    S.push( "<"+ S.pop() +">" ); 
		}
		else if ($v.equals("eq:")) {
		    S.push( S.pop() +"="+ S.pop() ); 
		}
		else if ($v.equals("2over:")) {
		    S.push( S.elementAt( S.size()-3 ) );
		}
		else if ($v.equals("swap:")) {
		    String tmp_a = S.pop();
		    String tmp_b = S.pop();
		    S.push( tmp_a );
		    S.push( tmp_b );
		}		
		else if ($v.equals("npeek:")) {   // n peek: peek element n on stack
		    System.out.println( "  npeek: "+ S.elementAt( Integer.parseInt( S.pop() ) ) );
		}
		else if ($v.equals("esp:")) {
            System.out.print( S.pop() + " " );
		}
		else if ($v.equals("copy:")) {
			
            // System.out.println( S.pop() + " " + S.pop() );
            
            String[] s_args = new String[1];
            s_args[0] = ( S.pop() );
		
		    // if (false) {
		    if (true) {
		            String s = new CUrl().opt(s_args).exec(null);
        
                    System.out.println(s);
                    
                    try {
       
                        // BufferedWriter writer = new BufferedWriter( new FileWriter( "o_java.html" ));
       
                        BufferedWriter writer = new BufferedWriter( new FileWriter( S.pop() ));
                    
                        writer.write( s );
                        // do stuff 
                        writer.close();
                    }
                    catch ( Exception e ) {}
                    
            }
        }
		else if ($v.equals("fgc:")) sm_fgc();
		else if ($v.equals("preg_replace:")) sm_preg_replace();
		else if ($v.equals("commanl:")) sm_commanl();
		else if ($v.equals("peek:"))                             
		    System.out.println( "peek: "+ S.peek() );
	    else F_OTHER = "NOT_FOUND"; 			    
    }
    
    public static void sm_wa() // wa: write append 
    {

        String fileName = S.pop();
        String data = S.pop();
        OutputStream os = null;

        try {
            // below true flag tells OutputStream to append
            os = new FileOutputStream(new File(fileName), true);
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sm_w() // w: write, no append 
    {

        String fileName = S.pop();
        String data = S.pop();
        OutputStream os = null;

        try {
            // below true flag tells OutputStream to append
            os = new FileOutputStream(new File(fileName), false);
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    
    public static void sm_wl() // wl: word list
    {

        // List<String> f_path = new ArrayList<String>();

        Gson gson=new GsonBuilder().create();

        String jsonArray=gson.toJson( WL );

        S.push( jsonArray );
    }

    public static void sm_tk() // key t: check if key exist in T
    {
        // System.out.print( "  1103 t: " );        

        String varname = S.pop();
        
        if ( T.containsKey( varname ) )
            S.push( "1" );
        else
            S.push( "0" );

        // System.out.println( T );
    }

    /*
    public static void sm_ucdw() // tocdw: update CDW from json
    {
       Gson gson = new Gson(); 
       CDW = gson.fromJson( S.pop() , HashMap.class);     
    }
    */


    
    public static void sm_l_dbg() // breaks l: disabled first
    {
    // public static void FGLA(String[] $a)
/*    
function fgl_l() // X l: label X, set index to $SL['X'] for bz: branch if zero and bnz:
    // 2019_11_10 this is for multi layer loops?
    // $SS[] = array(0, $a); // at start of FGLA
    // $SS = { 0, $a }
    
    $xc = count($SS); 
    $xk =& $SS[$xc - 1][0]; // token counter of current FGLA call
    $xs =& $SS[$xc - 1][1]; // token list
    $xl = count($SS[$xc - 1][1]); // token list length
    $vk = $xk;
    
    $Z=$xl;

    $SL[array_pop($S)]=$xk;  // override same alias?
}
*/
    }
    
    // function FGL($a) // 5gl to php bootstrap code, use function argument as input string; add colon definition;
    public static void FGLA(String[] $a)
    {
        // 2019-11-03
        // global $argv, $S, $SS, $xk, $xs, $SC, $SL, $CDW; // $CDW: colon defined words
        
        // $a = preg_replace('/\s+/', ' ', $a);
        // String before = $a;
        // String after = before.trim().replaceAll(" +", " ");
        
        // $a = explode(' ', trim($a)); // remove front and trailing spaces
        // String[] T = after.split(" ");  // java must use double quotes, not single quotes!!
        
        String[] T = $a ;
    
        // System.out.print( "  1496 FGLA T: " ); 
        // System.out.println( Arrays.toString( T ) );    

        /*                
        $SS[] = array(0, $a); // 2018 08 02 new items pushed to $SS, caused problem?
        $xc = count($SS);
        $xk =& $SS[$xc - 1][0];
        */
        
        // $xs =& $SS[$xc - 1][1]; // the difference between this and next line? ($xc-1) points to current alias layer; should be no difference here, but may be different in sm_l?
        String[] $xs = T;
        String $v;

        // $xl = count($SS[$xc - 1][1]);
        int $xl = $xs.length;
        int $xk = 0;
        int $vk = $xk;
        int $Z=$xl;                

        /*
        HashMap<String, List<String>> CDW = new HashMap<String, List<String>>();
        HashMap<String, String> BV = new HashMap<String, String>();
        Stack<String> S=new Stack<String>();
        */

        // 2018 07 10
        do {
            // while ($vk < $xl) {
            
            F_OTHER = "";
        
            $vk = $xk;
            
            // $v = trim($xs[$xk]);
            $v = $xs[$xk].trim();
        
            // System.out.println( $v );
            
//            F(': dltag a bv: ig/${a}.html https://www.instagram.com/explore/tags/${a}/ copy: ;');
          
            String text    =
                "This is the text which is to be searched " +
                "for occurrences of the word 'is'.";

            // text = "ig/${a}.html";
            text = $v ;

        String patternString = "is";
        
        patternString = "/\\${+(.*?)}/";
        
        patternString = "\\$\\{+(.*?)\\}";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while(matcher.find()) {
            count++;
            
            if (BV.get( text.substring(matcher.start()+2, matcher.end()-1) )!=null)
                System.out.println("found: " + count + " : "
                    + matcher.start() + " - " + matcher.end() + " " + text.substring(matcher.start(), matcher.end()) + " " + text.substring(matcher.start()+2, matcher.end()-1) +" "+ BV.get( text.substring(matcher.start()+2, matcher.end()-1) ));
                $v = $v.replace( "${"+text.substring(matcher.start()+2, matcher.end()-1)+"}",  
                BV.get( text.substring(matcher.start()+2, matcher.end()-1) )
                );
        }
            
            /*
            // Bash style variable substitutions
            if(preg_match_all('/\${+(.*?)}/', $v, $m)) {
            
                foreach($m[1] as $mk => $me) { // $m[1] is inner match, $m[0] is outer match
                               
                    if (isset($BV[$me])) $v=preg_replace('/\${'.$me.'}/',  $BV[$me], $v);         
                    $S[]=$v;
                }
            
            }

//        if (in_array($v, array_keys($CDW))) { // $CDW colon defined words, unify Forth (no colon) and Unicode
            else 
            */
            
            // System.out.println("  913 " + $v );
            
            if ( CDW.keySet().contains( $v ) ) {
                
                String[] $WA = CDW.get($v).toArray( new String[0] ); // array_pop($WA); // remove semicolon;
                // System.out.println( "  157 " + $v );
                // System.out.println( $WA.length );
                
                FGLA($WA);  // need to implement multi layer loop
                
            }    
        
        else if ($v == ">:" || $v == "<:") {
            // $S[] = $v;
            S.push( $v );
        } else {
            // $l = strlen($v);
            int $l = $v.length();
            
            // System.out.println( $v +" "+ $xk +" "+ $xs.length +" "+ $v.charAt($l-1)   );
            // System.out.println( $v.charAt($l-1)==':' );
            
            if ($v.equals(":") && $l==1) { // colon definition CDW 20190119
            
                // echo "is colon; ";  
                
                $xk++; $vk = $xk; 

                // System.out.println( $v + " " + $xk + " " + $xs.length );

                
                // $v = trim($xs[$xk]);
                $v = $xs[$xk].trim();
            
                // $CDW[$v]=array();
                CDW.put( $v, new ArrayList<String>() );

                String $w0 = $v;
                $xk++;
                
                do {
                
                    // System.out.println( "  189 " + $v + " " + $xk + " " + $xs.length );
                    $vk = $xk;
                    $v = $xs[$xk].trim();
                    
                    // $l = strlen($v);
                    $l = $v.length();
                    
                    if ($v.equals(";") && $l==1) {
                        break;
                    }

                    CDW.get( $w0 ).add( $v );

                    $xk++;
                } while (true);
            }
            else if ($v.equals("see:")) { // special words
                String w = $xs[++$xk].trim();
                System.out.println( "  1597 see: "+ w +" "+ CDW.get( w ) ); 
               
                Gson gson=new GsonBuilder().create();
                String jsonArray=gson.toJson( CDW.get( w ) );
                
                S.push( jsonArray );
                
            }
            else if ($v.charAt($l-1)==':') {  // } at 1195

                // System.out.println( "  375 " + $v +" "+ $xk +" "+ $xs.length +" "+ $v.charAt($l-1)   );
                
                // $l = strlen($v);
                $l = $v.length();
                
                // $fn = substr($v, 0, $l - 1);
                String $fn = "sm_"+ $v.substring(0, $l - 1);

                // System.out.println("  983 "+ $fn);
                
                // Class clazz = Test.class;
                Class clazz = Phos.class;

                /*
                for (Method method : clazz.getDeclaredMethods()) {
                    System.out.println(method.getName());
                    if (method.getName().equals($fn)) {
                        System.out.println("Method exists: "+$fn);
                    }
                }
                */

                try {
                    if (clazz.getDeclaredMethod($fn, null) != null) {
                        // System.out.println("  397 Method exists: "+$fn);
                        
                        //Step1 - Using string funClass to convert to class
                        // String funClass = "package.myclass";
                        // Class c = Class.forName(funClass);
                        Class c = Phos.class;

                        //Step2 - instantiate an object of the class abov
                        Object o = c.newInstance();
                        //Prepare array of the arguments that your function accepts, lets say only one string here
                        Class[] paramTypes = new Class[1];
                        paramTypes[0]=String.class;
                        
                        // String methodName = "mymethod";
                        String methodName = $fn;
                        
                        //Instantiate an object of type method that returns you method name
                        // Method m = c.getDeclaredMethod(methodName, paramTypes);
                        Method m = c.getDeclaredMethod(methodName, null);
                        
                        //invoke method with actual params
                        // m.invoke(o, "testparam");
                        m.invoke(o);
                    } 
                }
                catch (Exception e) {
                    // System.out.println("  1793 Exception Method NOT exists: "+$fn);
                    
                    sm_other( $v );
                    
                    // 2019-11-08
                    // need to put all definitions of *: which has no sm_*() functions here?
                    // put definitions of words without : at end outside catch?
                    
                    if ( F_OTHER.equals("NOT_FOUND") ) 
                    if ($v.equals("l:")) {
                        System.out.print("  1037 l: label vk=");
                        System.out.println( $vk );
                        // SL.put( S.pop(), $vk - 2 );
                        SL.put( S.pop(), $vk - 1 );
                        System.out.println( SL );
                    }
                    else if ($v.equals("bnz:")) {
                        /*
                        $bx = $SL[ array_pop($S) ]; // array_pop($S);
                        fgl_dup();
                        if (array_pop($S)!=0)  { $xk = $bx + 1; continue; }
                        */
                        String L = S.pop();
              //          System.out.print( "   1046 bnz: "+ L + " ");
                        
                        int $bx = SL.get( L );
                        System.out.println( $bx ); 
                        
                        if ( Integer.parseInt( S.peek() ) != 0 ) $xk = $bx + 1;
                    }
                        // k bzk: jump forward k token if TOS==0
                        // jump backward if k<0
                        // simulate if then, jump to end-if part, no else
                        // if TOS!=0 continue execution
                        // for if-then-else, add bk: at end-if, jump out of else part
                    
                    else if ($v.equals("bzk:")) { // k bzk:
                        
                        String L = S.pop();
                        
                        // int $bx = SL.get( L );
                        int $bx = Integer.parseInt( L );
                        
                        // System.out.print( "  1809 bzk: "+ L +"  "); 
                        // System.out.println( $bx ); 
                        
                        if ( Integer.parseInt( S.peek() ) == 0 ) $xk = $xk + $bx;
                        // do not pop, can be used to jump over else part
                    }
                    else if ($v.equals("bk:")) { // k bk:
                        String L = S.pop();
                        
                        int $bx = Integer.parseInt( L );
                        System.out.println( "  1812 bk: "+ L ); 
                        
                        // if ( Integer.parseInt( S.peek() ) == 0 ) $xk = $bx + 2;
                        
                        $xk += $bx;
                        // jump over else part
                    }
                    
                    else if ($v.equals("ssz:")) { // stack size
                        System.out.print( "1054 ssz: stack size " ); 
                        System.out.println( S.size() ); 
                    }
                    else if ($v.equals("pssz:")) { // push stack size to stack, do not change ssz:, may affect other scripts
                        S.push( Integer.toString( S.size() ) );
                    }
                    else if ($v.equals("rae:")) {
                        S.removeAllElements();
                    }
                    else {
                       System.out.println( "  1825 "+ $v +" not found " );
                       // System.exit(); 
                       return;
                    
                    }

                }  

/* from libfgl.php                  
function fgl_l() // X l: label X, set index to $SL['X'] for bz: branch if zero and bnz:
{
    global $SL;
    global $argv, $S, $SS, $xk, $xs, $SC;

    $xc = count($SS);
    $xk =& $SS[$xc - 1][0];
    $xs =& $SS[$xc - 1][1];
    $xl = count($SS[$xc - 1][1]);
    $vk = $xk;
    
    $Z=$xl;

    $SL[array_pop($S)]=$xk;
}   

               else if ($fn=="bnz")
               {

               $bx = $SL[ array_pop($S) ]; // array_pop($S);
               
               fgl_dup();
               if (array_pop($S)!=0)  { $xk = $bx + 1; continue; }
               
               }    
*/            
                /*        
                if (function_exists("fgl_" . $fn)) {
                
                    call_user_func("fgl_" . $fn);
					
					if (is_array(end($S))) { // xif: executes TRUE or FALSE part, push prg_ctr to stack
						$va = end($S);
						
						// if (in_array("prg_ctr", end($S))) { 
						if (isset($va[0])) if ($va[0]=="prg_ctr") {
							$va = array_pop($S);
							$vk = $va[1]; $xk=$vk;
						}
					}					
                }
                */
/*                
                else if (in_array($fn.":", array_keys($CDW))) { // $CDW colon defined words
                
                    echo __LINE__." in CDW ".var_src($CDW[$fn.":"]);
                    
                    // $S[]=
                    $WA = $CDW[$fn.":"]; array_pop($WA); // remove semicolon;
                    // FGLA($CDW[$fn.":"]);
                    FGLA($WA);                
                }

                else if ($fn=="r") {
                
                    echo __LINE__." r: ";
                    // fgl_s();
                    
                    // push remainder of command string to stack?
                    $s=array_pop($S);
                    // $S[]=$xk; // $S[]=$xs; 
                    $S[]=implode(' ', array_slice($xs, $xk+1));
                    
                    // $S[]='$S[]=$'.array_pop($S).'; ';
                    $S[]='$S[]=$'.$s.'; '; $S[]=':r:'; // flag, swap after eval()
                    fgl_s(); return;
                }
                
                else if ($fn=="v") {
                
                    echo "\n".__LINE__." v: ";
                    // fgl_s();
                    
                    // push remainder of command string to stack?
                    $sa=array_pop($S);
                    $sb=array_pop($S);

                    // $S[]=$xk; // $S[]=$xs; 
                    $S[]=implode(' ', array_slice($xs, $xk+1));
                    
                    // $S[]='$S[]=$'.array_pop($S).'; ';
                    // $S[]='$S[]=$'.$s.'; ';
                    $S[]='$'.$sa.'='.$sb.'; '; $S[]=':v:'; // flag, no swap after eval()
                    fgl_s(); return;
                }
                
                else if ($fn=="a") {
                
                    echo "\n".__LINE__." v: ";
                    // fgl_s();
                    
                    // push remainder of command string to stack?
                    $sa=array_pop($S);
                    $sc=count($S);
                    $se=$S[ $sc - $sa ];
                    
                    for ($si=0; $si<$sa; $si++) {
                    
                    }
                    
                    $sb=array_pop($S);

                    // $S[]=$xk; // $S[]=$xs; 
                    $S[]=implode(' ', array_slice($xs, $xk+1));
                    
                    // $S[]='$S[]=$'.array_pop($S).'; ';
                    // $S[]='$S[]=$'.$s.'; ';
                    $S[]='$'.$sa.'='.$sb.'; '; $S[]=':v:'; // flag, no swap after eval()
                    fgl_s(); return;
                }
                
                else if ($fn=="count") {
                    echo __LINE__." r: ";
                    fgl_s();
                    $S[]='$S[]=count('.array_pop($S).'); ';
                }
                
               else if ($fn=="bz")
               {
               
               fgl_s();
               
               $bx = array_pop($S);
               
              // if (array_pop($S)==0) $xk=$SL[ array_pop($S) ];
              if (array_pop($S)==0) $xk = $bx;
              continue;
               
               }    
           
               else if ($fn=="bnz")
               {

               $bx = $SL[ array_pop($S) ]; // array_pop($S);
               
               fgl_dup();
               if (array_pop($S)!=0)  { $xk = $bx + 1; continue; }
               
               }    
                
                else {
                    // echo " line ".__LINE__ . " fgl_" . $fn . " error.\n";
                }
 */               
            }  //  else if ($v.charAt($l-1)==':') {  // 977
            else if ($v.equals("-")) {
              //  System.out.println( " stack size "+ S.size() ); 
                int b = Integer.parseInt(S.pop());
                int a = Integer.parseInt(S.pop());
             //   System.out.println( " stack size "+ S.size() ); 
                S.push(Integer.toString( a - b ));            
            //    System.out.println( " stack size "+ S.size() ); 
            }
            else if ($v.equals("+")) { 
                S.push(Integer.toString(Integer.parseInt(S.pop())+Integer.parseInt(S.pop())));
            }
            else if ($v.equals(".")) { 
                S.pop();
            }

            else {
           //     System.out.println("  1220 push " + $v );
                S.push( $v );
            }
/*            
            else {

                if (ord($v)==0) echo " null char ";
                else
                if ($v[0] == '_') {
		
					if ($v=='_') $S[]=$v;
					
                } else {
                    if ($v == '.s') {
                        echo "\nline " . __LINE__ . " {$v} ";
                        fgl_s();
                    } else {
                        if ($v == '-') {
                            // $S[] = array_pop($S) - array_pop($S);
                            $sa = array_pop($S);
                            $sb = array_pop($S);
                            // echo gettype($sb) ." ". $sb ." ". gettype($sa) ." ". $sa ." ";
                            $S[] = (int) $sb - (int) $sa; 
                        } 
                        else if ($v == '+') {
                            // $S[] = array_pop($S) - array_pop($S);
                            $sa = array_pop($S);
                            $sb = array_pop($S);
                            $S[] = $sb + $sa; 
                        }
                        else if ($v == '.') {

//                        fgl_stv(); 

                            array_pop($S);
                        }
                        else {
                            if ($v == '===') {
                                $S[] = array_pop($S) === array_pop($S);
                            } else {
                                $S[] = $v;
                            }
                        }
                    }
                }
            }
            */
        }

        $xk++;
        if ($xk >= $xl) {
            break;
        } 


    } while ($vk < $xl);
    
                // System.out.println( "  1939 FGLA end: "+ CDW.keySet() );

    }


}
