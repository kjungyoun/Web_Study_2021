package com.ssafy.util;

import javax.print.attribute.standard.PageRanges;

/**
 * JSP에서 데이터를 page navigation 형식으로 구성하기 위해 사용되는 클래스이다.
 */
public class PageUtility {
    int firstpagecount=0;
    int lastpagecount=0;

    int nextpagecount=0;				//다음 페이지
    int beforepagecount=0;			//이전 페이지
    int currentpagecount=0;			//현재 페이지
	
    int beforetenpage=0;				//이전 페이지
    int nexttenpage=0;					//다음 페이지

    int totalrowcount=0;				//총 row 개수
    int totalpagecount=0;				//총 페이지 수
    int displayrowcount=0;				//한 페이지당 보여줄 개수
    int pagePercount = 5;
    String imagepath;
    String search;
    /**
     * 현재페이지와 경로 한번에 보여줄 열의 갯수를 세팅하는 생성자
     * @param page int 현재 보여질 페이지
     * @param count java.lang.String 조회해온 데이터의 전체 row수
     * @param interval int 한 페이지당 보여질 row수
     * @exception java.lang.Exception
     */
    public PageUtility(int displayrowcount, int totalrowcount, int currentpagecount) throws Exception
    {
        super();
        this.displayrowcount = displayrowcount;
        this.totalrowcount = totalrowcount;
        this.currentpagecount = currentpagecount;
        if(totalrowcount%displayrowcount==0) this.totalpagecount=totalrowcount/displayrowcount;
        else this.totalpagecount=totalrowcount/displayrowcount+1;
    }
    /**
     * 현재페이지와 경로 한번에 보여줄 열의 갯수를 세팅하는 생성자
     * @param page int 현재 보여질 페이지
     * @param count java.lang.String 조회해온 데이터의 전체 row수
     * @param interval int 한 페이지당 보여질 row수
     * @param String search 검색 조건 
     * @exception java.lang.Exception
     */
   
    /**
     * 메소드 설명을 삽입하십시오.
     * 작성 날짜: (2002-11-15 오전 11:31:16)
     * @param args java.lang.String[]
     */
    public String getCurrentPageCount()
    {
        return String.valueOf(currentpagecount);
    }
    /**
     * 목록에 출력할 page bar를 구성한다.
     * @return java.lang.String
     */
    public String getPageBar()    {
  		
        StringBuffer sb=new StringBuffer();
        firstpagecount=((currentpagecount-1)/pagePercount*pagePercount)+1;
        lastpagecount=firstpagecount+pagePercount;

        beforetenpage=firstpagecount-pagePercount;
        beforetenpage= beforetenpage<1?1:beforetenpage;
        nexttenpage=lastpagecount;
        
//        System.out.println("firstpagecount:"+firstpagecount);
//        System.out.println("lastpagecount:"+lastpagecount);
//        System.out.println("beforetenpage:"+beforetenpage);
//        System.out.println("nexttenpage:"+nexttenpage);
        
        
        if(beforetenpage<1)     beforetenpage=1;
        if(nexttenpage>totalpagecount)
            nexttenpage=(((totalpagecount-1)/pagePercount)+1)*pagePercount;
        if(lastpagecount>totalpagecount)
            lastpagecount = totalpagecount+1;
		
        sb.append("<li class='page-item'>");   
        if(((currentpagecount-1)/pagePercount*pagePercount)+1>beforetenpage) {
            sb.append("<a class='page-link' href='javascript:pagelist("+beforetenpage+")' aria-label='Previous'>");
        } else {
            sb.append("<a class='page-link' href='#' aria-label='Previous'>");
        }
        sb.append("<i aria-hidden='true' class='fa fa-angle-left'></i>" + 
        		"<span class='sr-only'>Previous</span></a></li>");
        
        for (int i = firstpagecount; i < lastpagecount; i++)
        {
            if(i<=totalpagecount)
            {
                if (i == currentpagecount)
                	 sb.append("<li class='page-item active'><a class='page-link' href='#'>"+i+"</a></li>");
                else
                	sb.append("<li class='page-item'><a class='page-link' href='javascript:pagelist("+i+")'>"+i+"</a></li>");
            }
        }
        sb.append("<li class='page-item'>");
      
        if((((currentpagecount-1)/5)+1)*5 < nexttenpage)
            sb.append("<a class='page-link' href='javascript:pagelist("+nexttenpage+")' aria-label='Next' >");
        else
            sb.append("<a class='page-link' href='#' aria-label='Next'>");
        sb.append("<i aria-hidden='true' class='fa fa-angle-right'></i><span class='sr-only'>Next</span></a></li>");
        return sb.toString();
    }
    /**
     * 메소드 설명을 삽입하십시오.
     * 작성 날짜: (2002-11-15 오전 11:31:16)
     * @param args java.lang.String[]
     */
    public String getTotalPageCount() {       return String.valueOf(totalpagecount);
    }
}