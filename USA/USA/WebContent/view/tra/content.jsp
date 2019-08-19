<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title></title>
	<style>
	.replypaging { display: block; margin: 0; }
	.replypaging li { display: inline; font-size: 20px }
	.replypaging li a { font-family: 'Trebuchet MS', sans-serif; font-size: 25px; }
	
	</style>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>

<body onload="sessioncheck()">
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/money.jsp" />
<jsp:include page="/view/main/weather.jsp" />
<br><br>
<center>



<!-- 게시판 수정 -->
<table cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="5">게시판</td>
	</tr>
	<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">작성자&nbsp;&nbsp;</div>
			</td>
			<td>${vo.tra_writer }</td>
			
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">제 목&nbsp;&nbsp;</div>
		</td>
		
		<td style="font-family:돋음; font-size:12">
		${vo.tra_subject }
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td style="font-family:돋음; font-size:12">
			<table border=0 width=490 height=250 style="table-layout:fixed">
				<tr>
					<td valign=top style="font-family:돋음; font-size:12">
					<c:if test="${vo.tra_filename!=null}">
					<img src="${pageContext.request.contextPath}/upload/${vo.tra_filename}" width="355" height="650" />
					
					</c:if>
					${vo.tra_contents}
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12px">
			<div align="center">첨부파일</div>
		</td>
		<td style="font-family:돋음; font-size:12px">
		<c:if test="${vo.tra_filename!=null }">
	
		<a href="FileDownload.do?file_name=${vo.tra_filename}">
			${vo.tra_filename }
		</a>
		</c:if>
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;"></td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<
	<tr align="right" valign="middle">
		<td colspan="5">
			<font size=2>
			
			 <c:if test="${sessionScope.member ==vo.tra_writer }"> 
			<a href="./TradeBoardModifyFormAction.do?num=${vo.tra_num }">
			[수정]
			</a>&nbsp;&nbsp;
			<a href="./TradeBoardDeleteAction.do?num=${vo.tra_num}">
			[삭제]
			</a>&nbsp;&nbsp;
			 </c:if>
			
			<a href="./list.do">[목록]</a>&nbsp;&nbsp;
			</font>
		</td>
	</tr>
	</table>
<br><br><br>
				<!-- 본문 작성-->
				
				<input type="hidden" id="tradeboardnum" value="${vo.tra_num}">
				<input type="hidden" id="tradecontentcomment_id" value="${sessionScope.member}">


		
		<br>
		
</div>

	
	


<!-- 아래부터 -->



<!-- 지울것 -->

<li id='sessiontotalreplylist' style="list-style: none"></li>

<div id='trareplypaging'>

</div>


<c:if test="${not empty sessionScope.member }">
<input type="text" id="ajaxjung">
<input type="button" onclick="subajax('0','${sessionScope.member}')"value="댓글등록" onclick="replycheck()">
</c:if>


<ul class="replypaging">


<li id='sessionjavalist' style="list-style: none"></li>
</ul>


<script>

//구현해야할것.
function replycheck(){
console.log(session+"찍혀?");

	if(session=="없음"){
		if(confirm('로그인하시겠습니까?')){
			
			
		}else{
			return ;
		}
		
	}

}

//페이징 처리 참고.
function alink(currentpagemove){
    event.preventDefault();
	console.log(currentpagemove.innerText);
	currentpage=currentpagemove.innerText;
	if(currentpagemove.innerText=="이후"){
	currentpage=String( Number(currentpagemove.parentNode.previousSibling.innerText)+1);
		
	}else if(currentpagemove.innerText=="이전"){
		//현재페이지를 this로 현재 html요소를 가져온후 a link 상위에있는 li요소를 가져온후 li안에 있는 숫자를 가져온후 +1해준다.
		//자바단에서 String으로 받기떄문에 text를 숫자형으로 바꿔준후 다시 string형태로 변환
		currentpage=String( Number(currentpagemove.parentNode.nextSibling.innerText)-1);
		
	}
	
	pagestatus="move";
	sessioncheck();
	
	
}
//페이징처리 참고

	/* function insert(insert,ref){
		var i =document.getElementById(insert).value;
		console.log(i);
		}
	function modify(){
	location.href='dddd.do';
} */


//그냥 등록전송 눌렀을떄
	function sub(TradeBoardreply_Content,ref,writer){
// 써야할것 작성자  ,  게시판 글 번호 ,글내용,답글인지 아닌지, writer는 답글일경우 답글
// 
   var tradeboardnum=document.getElementById('tradeboardnum').value;   //글번호 또는 = writer
   
   console.log(tradeboardnum);
    var tradeBoardreply_Content =document.getElementById(TradeBoardreply_Content).value; //글내용  TradeBoardreply_Content
   var tradecontentcomment_id=document.getElementById('tradecontentcomment_id').value; //작성자
    //ref 그냥인지 대댓글인지.
    
    console.log(tradeBoardreply_Content,ref,tradecontentcomment_id,tradeboardnum); 
     location.href="TradeBoardreplyWriteAction.do?tradeboardnum=" +tradeboardnum+ "&TradeBoardreply_Content="+tradeBoardreply_Content+"&tradecontentcomment_id="+tradecontentcomment_id+"&ref="+ref+"&tradeBoardreplyWriterNum=0&tradeBoarReplyNumlv=0";
	console.log("TradeBoardreplyWriteAction.do?tradeboardnum=" +tradeboardnum+ "&TradeBoardreply_Content="+tradeBoardreply_Content+"&tradecontentcomment_id="+tradecontentcomment_id+"&ref="+ref+"&tradeBoardreplyWriterNum=0"
	 );
}
 
 //ajax 글 등록
 
 function subajax(ref,writer){
// 써야할것 작성자  ,  게시판 글 번호 ,글내용,답글인지 아닌지, writer는 답글일경우 답글
// 
   var tradeboardnum=document.getElementById('tradeboardnum').value;   //글번호 또는 = writer
   	obj=new Object();
	obj.content=document.getElementById('ajaxjung').value;//글 내용 변수로 담아옴.
	obj.writer=document.getElementById('tradecontentcomment_id').value;//글 작성자
	obj.ref=ref;    //0 
	obj.writernum="0";  //누구의 댓글인지 근데 원댓글이기떄문에 0으로 셋팅 action에서 ref값이 1이여야 값을 요청함.원댓글이기떄문에 고유번호가  작성자참고번호.
	obj.numlv="0";
	obj.tranum= ${vo.tra_num};
	
	
	
	$.ajax({
         type:"post",
        async:false, 
        url:"./TradeBoardReplyWriteAction.do",
        data:{data : JSON.stringify(obj)},
        success:function(data2){
       // var json=	JSON.parse(data2);
           if(data2[0].result!=null){
            alert(data2);
           }
           pagestatus="insert";
           
           sessioncheck();		
        }
	});
 }
 
 //글 등록 로직.
 
 
 
 //답글 전송 눌렀을떄
	function subrereply(TradeBoardreply_Content,ref,writer,rewriterrep,tradeBoarReplyNumlv,tradeboardreplywriterreplywriter){
	// 써야할것 작성자  ,  게시판 글 번호 ,글내용,답글인지 아닌지, writer는 답글일경우 답글
	// 
	   var tradeboardnum=document.getElementById('tradeboardnum').value;   //글번호 또는 = writer
	    var tradeBoardreply_Content =document.getElementById(TradeBoardreply_Content).value; //글내용 
	   var tradecontentcomment_id=document.getElementById('tradecontentcomment_id').value; //작성자
	   var tradeBoardreplyWriterNum=rewriterrep //답글다는사람의 번호
	    var tradeBoarReplyNumlv=tradeBoarReplyNumlv+1; 											//답글 레벨
	   
	   //ref 그냥인지 대댓글인지.
	    console.log("TradeBoardreplyWriteAction.do?tradeboardnum=" +tradeboardnum+ "&TradeBoardreply_Content="+tradeBoardreply_Content+"&tradecontentcomment_id="+tradecontentcomment_id+"&ref="+ref+"&tradeBoardreplyWriterNum="+tradeBoardreplyWriterNum); 
	     location.href="TradeBoardreplyWriteAction.do?tradeboardnum=" +tradeboardnum+ "&TradeBoardreply_Content="+tradeBoardreply_Content+"&tradecontentcomment_id="+tradecontentcomment_id+"&ref="+ref+"&tradeBoardreplyWriterNum="+tradeBoardreplyWriterNum+"&tradeBoarReplyNumlv="+tradeBoarReplyNumlv+"&tradeboardreplywriterreplywriter="+tradeboardreplywriterreplywriter;
	 }
 
 
 	function subajaxrep(replynum,Tradeboardreplyref,tradeboardwriter,Tradeboardrewriterrep,tradeBoarReplyNumlv,tradeboardreplywriterreplywriter){
 		console.log(342)
 		 var tranum=document.getElementById('tradeboardnum').value;
 		console.log(document.getElementById(replynum+"WRITE").value +"답글컨텐츠내용");
 		obj =new Object();
 		obj.tranum=Number(document.getElementById('tradeboardnum').value);
 		obj.content=document.getElementById(replynum+"WRITE").value;
 		obj.ref="1";
 		obj.writer=tradeboardwriter;
 		obj.wrtierrepwriter=tradeboardreplywriterreplywriter;
 		obj.numlv=tradeBoarReplyNumlv;
 		obj.writernum=Tradeboardrewriterrep;
 		//답글달면  
 		
 		
 		$.ajax({
 	         type:"post",
 	        async:false, 
 	        url:"./TradeBoardReplyWriteAction.do",
 	        data:{data : JSON.stringify(obj)},
 	        success:function(data2){
 	       // var json=	JSON.parse(data2);
 	           if(data2[0].result!=null){
 	            alert(data2);
 	           }
 	          pagestatus="move";//답글다는사람이 있는페이지로 지정하기위해서.
 	           
 	           sessioncheck();		
 	        }
 	}); 
 	}
 	function subajaxdelete(replynum){
 		
 		 
 			  if(confirm("정말 삭제하시겠습니까?")){

 				  
 				 obj=new Object();
 		 		obj.trarepnum=replynum;
 		 		
 		 		
 		 		$.ajax({
 		 	         type:"post",
 		 	        async:false, 
 		 	        url:"./TradeBoardReplyDeleteAction.do",
 		 	        data:{data : JSON.stringify(obj)},
 		 	        success:function(data2){
 		 	       // var json=	JSON.parse(data2);
 		 	           if(data2[0].result!=null){
 		 	            alert(data2);
 		 	           }
 		 	          pagestatus="move";//삭제하는페이지로 가게하기위해서.
 		 	           
 		 	           sessioncheck();		
 		 	        }
 		 	}); 
 		 		
 				  
 			  
 			  }else{
 				return false;
 			   }
 		  
 		
 	}
 	
 
 //수정눌렀을떄 고유번호,게시판번호,컨텐츠
 	function submodi(tra_num,tra_tranum){
	 
	 var tradeBoardreply_Content =document.getElementById(tra_num+'MODI_VAL').value;
	 console.log(tradeBoardreply_Content);
	 
	 
	 obj=new Object();
		obj.trarepnum=tra_num;
		obj.content      =tradeBoardreply_Content;
		
		
		$.ajax({
	         type:"post",
	        async:false, 
	        url:"./TradeBoardReplyModifyAction.do",
	        data:{data : JSON.stringify(obj)},
	        success:function(data2){
	       // var json=	JSON.parse(data2);
	           if(data2[0].result!=null){
	            alert(data2);
	           }
	          pagestatus="move";//삭제하는페이지로 가게하기위해서.
	           
	           sessioncheck();		
	        }
	}); 
	 
 }
	 
  function replydelete(replynum,tradeboardnum){
	  if(confirm("정말 삭제하시겠습니까?")){
		location.href="TradeBoardReplyDelete.do?trarep_num="+replynum+"&tradeboardnum="+tradeboardnum;
	   }else{
		return false;
	   }
  }
 
  
  
 
 
 
 //글 css정리
 
 //수정 MODI_VA
function fncModi(moditranum,dd)
{
	 

	
	 console.log('MODI'+moditranum);
	 
    var i=document.getElementsByClassName('modi');
    for (let index = 0; index < i.length; index++) {
        // i[index].style.display=' ';
        document.getElementsByClassName('read')[index].style.display = 'block';
        
        document.getElementsByClassName('modi')[index].style.display = 'none';
        document.getElementsByClassName('write')[index].style.display = 'none';
        
        
    }
    //jstl로 변수명 지정해줄것.
   
 document.getElementById('MODI'+moditranum).style.display='block ';
 
    //이것을 누른 폼(리드)을 안보이게하고.
  dd.parentNode.parentNode.style.display="none";
    
    
 console.log(dd.parentNode.parentNode.childNodes[2].childNodes[0])
 //수정폼을 보이게함.
 dd.parentNode.parentNode.nextSibling.style.display="block";
 
 console.log(dd.parentNode.parentNode.nextSibling.nextSibling); 
 
 
 console.log(dd.parentNode.parentNode.nextSibling.childNodes[1].childNodes[0])
 
 //수정폼에다가 read안에있는 text값을 불러옴. css가 적용되지 않아서 위와같은 방법을 사용했음. 정적이면 무조건 아래에있는방법으로 사용할것.
   dd.parentNode.parentNode.nextSibling.childNodes[1].childNodes[0].value=dd.parentNode.parentNode.childNodes[2].childNodes[0].innerText;
 //document.getElementById(moditranum+'MODI_VAL').value=document.getElementById(moditranum+'READ_VAL').innerText;
 
 //console.log(dd.parentNode.parentNode.nextSibling.childNode)
 
 //document.getElementById('READ'+moditranum).style.display='none';
 //document.getElementById('writeForm2').style.display='none';





}



//답글
function fncWriteform(writenum,dd){
    var i=document.getElementsByClassName('read');
    for (let index = 0; index < i.length; index++) {
        // i[index].style.display=' ';
        document.getElementsByClassName('read')[index].style.display = 'block';
        
        document.getElementsByClassName('modi')[index].style.display = 'none';
        document.getElementsByClassName('write')[index].style.display = 'none';
        
    
    }
   

/*  document.getElementById('WRITE').style.display='';
 
 document.getElementById('writeForm2').style.display='none';
   */
  
   dd.parentNode.parentNode.style.display="block";
   dd.parentNode.parentNode.nextSibling.style.display="none";
   dd.parentNode.parentNode.nextSibling.nextSibling.style.display="block";
 //document.getElementById('MODI'+writenum).style.display='none';
 //document.getElementById('WRITE'+writenum).style.display='block';
 //document.getElementById('writeForm2').style.display='none';

  
  
    // document.getElementById('READ').style.display = '';
    // document.getElementById('write_form').innerText='';
    // document.getElementById('MODI').style.display = 'none';
    // document.getElementById('write').style.display='';
    // document.getElementById('writeForm2').style.display='none';

}
function fncmodicancle()
{
    var i=document.getElementsByClassName('read');

    for (let index = 0; index < i.length; index++) {
        // i[index].style.display=' ';
        document.getElementsByClassName('read')[index].style.display = '';
        
        document.getElementsByClassName('modi')[index].style.display = 'none';
        document.getElementsByClassName('write')[index].style.display = 'none';
        
    
    }

    
    document.getElementById('writeForm2').style.display='';
}


/////////////////////아래부터는 테스트
 var currentpage="1";
 
 //시작일때는 가장 처음에 달린 댓글부터.'start' 페이지 이동일떄는 
 var pagestatus="start";
 var session="없음";
 function currentpageclick(){
	 var i=2;
	 currentpage=String(i);
	 sessioncheck();
 }
 function replyinsertclick(){
	 
 }
function sessioncheck(){
	console.log(currentpage);
	var obj=new Object();
	obj.tranum=${vo.tra_num};
	obj.pagestatus=pagestatus;
	obj.currentpage=currentpage;
	
	
	$.ajax({
         type:"post",
        async:false, 
        url:"./TradeBoardReplyListAction.do",
        data:{data:JSON.stringify(obj)},
        success:function(data2){
        var json=	JSON.parse(data2);
        	currentpage=String(json.page[0].currentpage);
           // sessioncompare(json);//댓글화면에 찍어주기
            sessionpage(json); //페이징처리뿌리기
            
            if(json.session[0].session!=null){
            session=json.session[0].session;
            }

            document.getElementById('sessiontotalreplylist').innerText='총 댓글 '+json.totalreply;
            tableinsert(json);
        }
	
})
}



//댓글 찍어주는곳 아래에서 나머지 개발중.
function sessioncompare(data){
	var i;
	console.log(data.session[0].session);

	
	
	if(data.session[0].session!=null){
		for(j in data.replyinfo){
		//if(data.replyinfo[j].)
		i+="<div>";
		i+="<table border='1'>";
		i+="<tr id='writeForm2'  height='25' style='display:'>";
		i+="<td>"+data.replyinfo[j].content+ "<td>";
		i+="</tr></table>+${sessionScope.member}";
		}
		document.getElementById('sessionjava').innerHTML=i;
		console.log(111);
		}


		
}




//여기까지 테스트
//페이징처리
function sessionpage(data){
	var j="";  //임시 태그저장소.
	var startpage=data.page[0].startpage;
	var endpage=data.page[0].endpage;
	var pagecount=data.page[0].pagecount;
	
	if(pagestatus=="insert"){
		
		//(currentpage-1)/pageBlock) * pageBlock + 1
		startpage=( Math.floor((data.page[0].currentpage-1) /data.page[0].pageblock ) )*data.page[0].pageblock  +1;
		endpage=data.page[0].pagecount;
	}
	
	var tempnum=startpage;
		if(data.page[0].pageblock<startpage){
			j+="<li><a href='#' onclick='alink(this)'>";
	 		j+="이전"+"</a></li>";
		}
	for(var i=startpage;i<=endpage;i++){
		j+="<li><a href='#' onclick='alink(this)'>";
		j+=tempnum+"</a></li>"
		tempnum++;
	}
	if(pagecount>endpage){
		j+="<li><a href='#' onclick='alink(this)'>";
		j+="이후"+"</a></li>";
	}
	
	document.getElementById('sessionjavalist').innerHTML=j;
	}
	
	
	
	
///아래부터는 테이블 처리
function tableinsert(data){
	var i="";
	for(j in data.replyinfo){
	
	i+='<div>';
	i+='<table border="1" style="border-collapse: collapse">';
	
	if(data.replyinfo[j].alive==0){
	
		//고칠것
	i+='<tr class="read" height="40" style="display:  ;" id="READ'+data.replyinfo[j].num+'" >';

	i+='<td width="90px;" >';
		if( data.replyinfo[j].numref ==1){
	i+='<font size="1" color="gray">ㄴ'+data.replyinfo[j].trarep_writerrepwriter+'</font> ';	
		}
	i+=data.replyinfo[j].writer+'</td>';
	i+='<td width="10px;"">:</td>';	
    i+='<td width="330px;"><span id="'+data.replyinfo[j].num+'">'+data.replyinfo[j].content   +'</span> <font style="font-size:5px;" >'+data.replyinfo[j].trarep_date+'</font>&nbsp;&nbsp;</td>'
    	if(data.replyinfo[j].writer==data.session[0].session)	{
    i+='<td width="40px;"><span id="MODBTN" style="cursor:hand;" onclick="fncModi('+data.replyinfo[j].num+',this)">수정</span></td>'
    	if(data.session[0].session!=null){
    i+='<td width="40px;"><span id="write_form" onclick="javascript:fncWriteform('+ data.replyinfo[j].num +',this)">답글</span></td>';
    	}
    i+='<td width="40px;"><span  onclick="subajaxdelete('+data.replyinfo[j].num+')">삭제</span></td>';
      	} //세션값과 글쓴이가 같은지.
	i+="</tr>";		
	}	//alve==0세션끝나는곳
	//글쓰기 끝.
	
	if(data.replyinfo[j].alive==1){
	i+='<tr class="read" height="25" style="display:  ;"  >   <td width="465px">삭제된 댓글입니다.</td>';
	i+='</tr><tr class="modi" style="display: none "><td></td></tr>';
	i+='<tr class="write" style="display: none "><td></td></tr>';
	i+='</table>';
	
	
	}
	//none으로 바꿀것.
	if(data.replyinfo[j].alive==0){
		
		
	
	i+='<tr class="modi" style="display: none " height="25" id="MODI'+data.replyinfo[j].num+'">';
	i+='<td width="50px">'+data.session[0].session+'</td>';
	i+='<td width="350px;""><input type="text" id="'+data.replyinfo[j].num+'MODI_VAL" name="MODI_VAL" style="width:300px; height:25px;"></td>';
	i+='<td width="30px;"><input type="button" onclick="javascript:submodi('+data.replyinfo[j].num +','+data.replyinfo[j].tranum +');" value="입력"></td>';
	//i+='<td><span id="write_form" onclick="javascript:fncWriteform('+data.replyinfo[j].num +')">답글 </span></td>';
	i+='<td onclick="javascript:fncmodicancle()">입력취소</td></tr>';
	

	var tempi="'";
	i+='<tr class="write" style="display: none " height="25" id="WRITE'+data.replyinfo[j].num +'">';
	i+='<td>답글</td> <td>:</td>';
	i+='<td width="350px"><input type="text" id="'+data.replyinfo[j].num+'WRITE" style="width: 360px; height:20px;"></td>';
	i+='<td><input type="button" value="입력" onclick="subajaxrep( ' +data.replyinfo[j].num+',1,'+tempi+data.session[0].session+tempi+','+data.replyinfo[j].trarep_writerrep+','+data.replyinfo[j].trarep_numref_lv+','+tempi+data.replyinfo[j].writer+tempi+') "></td>';
	
	
	i+='<td onclick="javascript:fncmodicancle()">취소</td>';
	i+='</tr>'
	i+='</table>';
	i+='</div>';
	}
	}
	document.getElementById('trareplypaging').innerHTML=i;
	console.log("되고있나");
	
}



</script>




</center>
<jsp:include page="/view/main/footer.html" />
</body>
</html>
