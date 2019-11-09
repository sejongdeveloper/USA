<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title></title>
	<style>
	.replypaging { display: block; margin: 0; margin-bottom: 50; margin-top: 30;}
	.replypaging li { display: inline; font-size: 20px; }
	.replypaging li a { font-family: 'Trebuchet MS', sans-serif; font-size: 25px; text-decoration: none; color: black; }
	
	</style>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>

<body onload="replycheck()">
<jsp:include page="/view/main/header.jsp" />
<jsp:include page="/view/main/money.jsp" />
<jsp:include page="/view/main/weather.jsp" />

<center>
<br><br><br><br>


<!-- 게시판 수정 -->
<table cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="5">게시판</td>
	</tr>
	<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">작성자&nbsp;&nbsp;</div>
			</td>
			<!-- 작성자 -->
			<td>${vo.tra_writer }</td>
			
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">제 목&nbsp;&nbsp;</div>
		</td>
		<!-- 제목 -->
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
					<c:if test="${vo.tra_filename!=null}"><!-- 파일네임이 널이 아니면. -->
					<img src="${pageContext.request.contextPath}/upload/${vo.tra_filename}" width="355" height="650" /><!-- 파일을 다운로드할수있게. -->
					
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
	
		<a href="TradeFileDownload.do?file_name=${vo.tra_filename}">
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
		<td colspan="5"><font size=2>
			<!-- 닉네임과 작성자가 똑같다면.수정할수있고 삭제할수있게. -->
			 <c:if test="${sessionScope.nickname ==vo.tra_writer }"> 
			<a href="./TradeBoardModifyFormAction.do?num=${vo.tra_num }">
			[수정]
			</a>&nbsp;&nbsp;
			<a href="./TradeBoardDeleteAction.do?num=${vo.tra_num}">
			[삭제]
			</a>&nbsp;&nbsp;
			 </c:if>
			
			<a href="./Tradelist.do">[목록]</a>&nbsp;&nbsp;
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

	
	
<!-- 아래부터  리플-->

<!-- 총 댓글 표현하기 위한 id 자바스크립트에서 innerhtml로 총 댓글수 넣어줌/ -->
<li id='sessiontotalreplylist' style="list-style: none; margin-bottom: 30;"></li>

<!--리플,수정,답글 폼은 자바스크립트에서 리플수만큼 만들어두고 innerhtml로 테이블 주입. -->
<div id='trareplypaging'>

</div>

<!-- 세션값이 있다면. -->
<c:if test="${not empty sessionScope.member }">
<input type="text" id="ajaxjung">
<input type="button" onclick="subajax('0','${sessionScope.member}')"value="댓글등록" onclick="replycheck()">
</c:if>

<!-- 가로로 댓글 리스트 보여주기위해 css에서 replypaging을 제어. -->
<ul class="replypaging">

<!-- 실질적으로 댓글페이징처리해주는곳. -->
<li id='sessionjavalist' style="list-style: none"></li>
</ul>


<script>

//구현해야할것.

window.addEventListener("hashchange",function(){
        pagestatus="move";
       currentpage=location.hash.substring(1,2);
       if(currentpage=="") currentpage="1";
       replycheck();

    } 
        
    )
  
//페이징 처리 참고. currentpagemove는 현재 내가 누른 페이지 이동창이 어느곳인지. <a href 값의 this를 가져옴.
function alink(currentpagemove){
	currentpage=currentpagemove.innerText;    //예시 <a href="~~">1 </a>  여기서 1이라는 값을 얻기위해 사용.
	if(currentpagemove.innerText=="이후"){ // 이전과 이후는 특정값이 아니라 밸류를 이전과 이후로 잡아줬기떄문에.
		//                         //지금 눌린노드에서 부모노드를찾아간후. 이전 노드를 찾아가고. 거기에있는 innerText값을 가져옴
								   //즉  1 2 3 이후  이렇게 돼있으면 3이라는 값을 찾기위해 노드를 직접 지정해줌. 여기서는 3 그리고 +1하면 4가되니. 페이지이동은 4페이지로
								   //이동가능.
	currentpage=String( Number(currentpagemove.parentNode.previousSibling.innerText)+1);
		
	}else if(currentpagemove.innerText=="이전"){
		//현재페이지를 this로 현재 html요소를 가져온후 a link 상위에있는 li요소를 가져온후 li안에 있는 숫자를 가져온후 +1해준다.
		//자바단에서 String으로 받기떄문에 text를 숫자형으로 바꿔준후 다시 string형태로 변환
		currentpage=String( Number(currentpagemove.parentNode.nextSibling.innerText)-1);
		
	}
	//그후 페이지상태를 move로 해줘서 눌러준 페이지로 이동할수있게끔 해준다.
	pagestatus="move";
	replycheck();
	
	
}
//페이징처리 참고

 //ajax 글 등록
 
 function subajax(ref,writer){
	if(document.getElementById('ajaxjung').value==null || document.getElementById('ajaxjung').value==""){
		alert("내용을 입력해주세여");
		return 
	}
				//작성자와 답글인지만 판단.
	var tradeboardnum=document.getElementById('tradeboardnum').value;   //글번호 또는 = writer
   	obj=new Object();  //ajax를위한 오브젝트 생성.
	obj.content=document.getElementById('ajaxjung').value;//글 내용 담아옴.
	obj.writer=document.getElementById('tradecontentcomment_id').value;//글 작성자
	obj.ref=ref;    //0 
	obj.writernum="0";  //누구의 댓글인지 근데 원댓글이기떄문에 0으로 셋팅 action에서 ref값이 1이여야 값을 요청함.원댓글이기떄문에 고유번호가  작성자참고번호.
	obj.numlv="0";
	obj.tranum= ${vo.tra_num};
	
	
	
	$.ajax({
         type:"post",
        async:true, 
        url:"./TradeBoardReplyWriteAction.do",
        data:{data : JSON.stringify(obj)},
        success:function(data2){
           if(data2[0].result!=null){
            alert(data2);          //null 이 아니라면 "댓글등록에 실패하셨습니다 라고 뜸
           }
           pagestatus="insert";   //페이지 status가 insert이기떄문에 가장 마지막페이지로이동.
           
           replycheck();		  //리플확인.
        }
	});
 }
 
 
 
 

 
 
 //답글.
 	function subajaxrep(replynum,Tradeboardreplyref,tradeboardwriter,Tradeboardrewriterrep,tradeBoarReplyNumlv,tradeboardreplywriterreplywriter){
						//리플번호     답글인지아닌지        작성자                 부모그룹번호             답글순서                        누구의답글을한건지 저장.
	    var tranum=document.getElementById('tradeboardnum').value;

		if(document.getElementById(replynum+"WRITE").value==null || document.getElementById(replynum+"WRITE").value==""){
 			alert("내용을 입력해주세요");
 			document.getElementById(replynum+"WRITE").focus();
 			return 
 		}
		//에이잭스 보내기위한 설정. 게시판번호,내용,답글인지,작성자,누구의답글인지,몇번쨰답글인지, 부모그룹 순서
 		obj =new Object();
 		obj.tranum=Number(document.getElementById('tradeboardnum').value);
 		obj.content=document.getElementById(replynum+"WRITE").value;
 		obj.ref="1";
 		obj.writer=tradeboardwriter;
 		obj.wrtierrepwriter=tradeboardreplywriterreplywriter;
 		obj.numlv=tradeBoarReplyNumlv;
 		obj.writernum=Tradeboardrewriterrep;
 		
 		
 		$.ajax({
 	         type:"post",
 	        async:false, 
 	        url:"./TradeBoardReplyWriteAction.do",
 	        data:{data : JSON.stringify(obj)},
 	        success:function(data2){
 	           if(data2[0].result!=null){
 	            alert(data2);     //null이 아니라는것은 실패했다는것이니 액션에서 실패했다고 뜨게함.
 	           }
 	          pagestatus="move";//답글다는사람이 있는페이지로 지정하기위해서.
 	           
 	           replycheck();	   //리플찍어줌.	
 	        }
 	}); 
 	}
 
 	function subajaxdelete(replynum){
 			  if(confirm("정말 삭제하시겠습니까?")){//삭제버튼을 누르면 실행됨.

 				obj=new Object();
 		 		obj.trarepnum=replynum;

 		 		$.ajax({
 		 	         type:"post",
 		 	        async:false, 
 		 	        url:"./TradeBoardReplyDeleteAction.do",
 		 	        data:{data : JSON.stringify(obj)},
 		 	        success:function(data2){
 		 	           if(data2[0].result!=null){
 		 	            alert(data2);   //삭제실패하면 뜨게만듬.
 		 	           }
 		 	          pagestatus="move";//삭제하고있는 페이지로 가기위해.
 		 	           
 		 	           replycheck();		
 		 	        }
 		 	}); 
 			  }else{//아니요 누르면 리턴.
 				return false;
 			   }
 		  
 		
 	}
 	
 
 //수정눌렀을떄 고유번호,게시판번호,컨텐츠
 	function submodi(tra_num,tra_tranum){
	 if(document.getElementById(tra_num+'MODI_VAL').value==null||document.getElementById(tra_num+'MODI_VAL').value==""){
		 alert("내용을 입력해주세요.");
		 return
	 }
	  //내용
	 var tradeBoardreply_Content =document.getElementById(tra_num+'MODI_VAL').value;
	 
	 
	obj=new Object();
	obj.trarepnum=tra_num;                  //리플내용.
	obj.content      =tradeBoardreply_Content; //바뀐내용
		
		
		$.ajax({
	         type:"post",
	        async:false, 
	        url:"./TradeBoardReplyModifyAction.do",
	        data:{data : JSON.stringify(obj)},
	        success:function(data2){
	           if(data2[0].result!=null){
	            alert(data2);  //널아니면 실패했다는것.
	           }
	          pagestatus="move";//삭제하는페이지로 가게하기위해서.
	           replycheck();		
	        }
	}); 
	 
 }
	
  
 //글 css정리
 
 //수정을눌렀을경우. 해당하는 댓글만 수정이 보이게끔하는것.
function fncModi(moditranum,dd){
	 		
	 
    var i=document.getElementsByClassName('modi'); 
    for (let index = 0; index < i.length; index++) {  //class가 modi의 길이만큼 가져와서.
        document.getElementsByClassName('read')[index].style.display = 'block';  //클래스가 read,modi,write인부분을 전부 보여주고 안보여주고 정함.
         																		//
        document.getElementsByClassName('modi')[index].style.display = 'none';
        document.getElementsByClassName('write')[index].style.display = 'none';
        
        
    }
   
                                                                      
  dd.parentNode.parentNode.style.display="none";    //직접 노드를 찾아줘서 그부분의 READ댓글 양식을 block시켜줌. 
    
    
 //수정폼을 보이게함.
// document.getElementById('MODI'+moditranum).style.display='block '; 원래는 이 코드 그러나 작동을안해서
 dd.parentNode.parentNode.nextSibling.style.display="block";
 
 
 
 
 //수정하고자하는 글의 내용을 수정폼에 주입해줌.  첫번쨰코드가 안돼서 두번쨰로 대체.
 //document.getElementById(moditranum+'MODI_VAL').value=document.getElementById(moditranum+'READ_VAL').innerText;
   dd.parentNode.parentNode.nextSibling.childNodes[1].childNodes[0].value=dd.parentNode.parentNode.childNodes[2].childNodes[0].innerText;
}



//답글
function fncWriteform(writenum,dd){
    var i=document.getElementsByClassName('read');//댓글목록수만큼 == read의 클래스 수만큼임.
	//read클래스(댓글내용)을 제외하고 수정,답글은 모두 눈에 안보이게 none시킴.
    for (let index = 0; index < i.length; index++) {
        document.getElementsByClassName('read')[index].style.display = 'block';
        document.getElementsByClassName('modi')[index].style.display = 'none';
        document.getElementsByClassName('write')[index].style.display = 'none';
    }
   

 
  //수정폼을 보이게하고.
 //document.getElementById('WRITE'+writenum).style.display='block';
   dd.parentNode.parentNode.style.display="block";

   //수정폼을 none으로 시키고. 
 //document.getElementById('MODI'+writenum).style.display='none';
   dd.parentNode.parentNode.nextSibling.style.display="none";
	
   //맨 아래 댓글창도 none으로 만들어줌.
 //document.getElementById('writeForm2').style.display='none';
   dd.parentNode.parentNode.nextSibling.nextSibling.style.display="block";
}

//수정취소 답글 취소를 눌렀을떄.
function fncmodicancle()
{
	
    var i=document.getElementsByClassName('read');
	//read폼만 살려놓고 댓글목록.
    for (let index = 0; index < i.length; index++) {
        document.getElementsByClassName('read')[index].style.display = '';
        document.getElementsByClassName('modi')[index].style.display = 'none';
        document.getElementsByClassName('write')[index].style.display = 'none';
    }

    //맨 아래 댓글창을 display시켜줌.
    document.getElementById('writeForm2').style.display='';
}


//시작할떄 currentpage는 1로시작.
var currentpage="1";
 
 //시작일때는 가장 처음에 달린 댓글부터.'start' 페이지 이동일떄는 
 var pagestatus="start";
 //session의 초기값은 없음임.
 var session="없음";

 
//리플목록 보여주는것.
 function replycheck(){
	//에이잭스로 보내주기위해 
	var obj=new Object();
	obj.tranum=${vo.tra_num};  //게시판번호
	obj.pagestatus=pagestatus;  //게시판이 이동인지 누구의 답글이나 수정인지 새로운 댓글인지 판단.
	obj.currentpage=currentpage;   //최근페이지를 판단.
	
	
	$.ajax({
         type:"post",
        async:false, 
        url:"./TradeBoardReplyListAction.do",
        data:{data:JSON.stringify(obj)},
        success:function(data2){
        var json=	JSON.parse(data2);
        	currentpage=String(json.page[0].currentpage); //최근페이지값을 받아와서 설정후
            sessionpage(json); //페이징처리 메소드
            
            if(json.session[0].session!=null){   //session이 널이 아니라는것은 로그인했다는것이니 session에 없음대신 값을 넣어줌.
            session=json.session[0].session;
            }
			//총댓글 목록을 보여줌.
            document.getElementById('sessiontotalreplylist').innerText='총 댓글 '+json.totalreply;
       		//실질적으로 html에 테이블들을 추가해주는 메소드.
			tableinsert(json);
        }
	
})
}



//페이징처리
function sessionpage(data){
	var j="";  //임시 태그저장소.
	var startpage=data.page[0].startpage; 
	var endpage=data.page[0].endpage;
	var pagecount=data.page[0].pagecount;
	
	if(pagestatus=="insert"){//만약 페이지상태가 insert면. 새댓글이라는 의미이니. start는 가장 마지막페이지블럭의 첫 페이지 블럭으로 설정.
		
		                   //(currentpage-1)/pageBlock) * pageBlock + 1
		                   //MAth.flooer 내림 역할 왜냐하면 제이슨에서 값 받아올떄는 실수형태로 받아옴.
		startpage=( Math.floor((data.page[0].currentpage-1) /data.page[0].pageblock ) )*data.page[0].pageblock  +1;
		endpage=data.page[0].pagecount;  //그리고 마지막 페이지는 가장 마지막 페이지번호로 설정.
	}
	//startpagenum을 ++gownrldnlgo 임시 변수를 담아줌.
	var tempnum=startpage;
	 //시작번호가 페이지블럭보다 크면 이전버튼을 생성.
		if(data.page[0].pageblock<startpage){
			j+="<li><a href='#"+(startpage-1)+"' onclick='alink(this)'>";
	 		j+="이전"+"</a></li>";
		}
	 //이전 4 5 6 이후    여기부분에서 4 5 6 부분 찍어주는곳.
	for(var i=startpage;i<=endpage;i++){
		j+="<li><a href='#"+tempnum+"' onclick='alink(this)'>";
		j+=tempnum+"</a></li>"
		tempnum++;
	}
	//페이지크기보다 한번에보여주는 마지막페이지가 작으면 이후를 만들고
	if(pagecount>endpage){
		j+="<li><a href='#"+(endpage+1)+"' onclick='alink(this)'>";
		j+="이후"+"</a></li>";
	}
	//테이블 주입.
	document.getElementById('sessionjavalist').innerHTML=j;
	}
	
	
	
	
///아래부터는 테이블 처리
function tableinsert(data){
	var i="";
	for(j in data.replyinfo){
	
	i+='<div>';
	i+='<table border="1" style="border-collapse: collapse">';
	
if(data.replyinfo[j].alive==0){
		          //read는 글쓰기 리드폼 부분.
	i+='<tr class="read" height="40" style="display:  ;" id="READ'+data.replyinfo[j].num+'" >';
	i+='<td width="90px;" >';
	//numref가 1이라면 답글이 있다는것.
		if( data.replyinfo[j].numref ==1){
	i+='<font size="1" color="gray">ㄴ'+data.replyinfo[j].trarep_writerrepwriter+'</font> ';	
		}
	//작성자
	i+=data.replyinfo[j].writer+'</td>';
	i+='<td width="10px;"">:</td>';
	                                   //컨텐츠 값 가져오기위해 댓글고유번호를 span id값으로 설정.												//올린날
    i+='<td width="330px;"><span id="'+data.replyinfo[j].num+'">'+data.replyinfo[j].content   +'</span> <font style="font-size:5px;" >'+data.replyinfo[j].trarep_date+'</font>&nbsp;&nbsp;</td>'
    				//작성자와 세션이 같다는것은 같은사람이니. 수정
    if(data.replyinfo[j].writer==data.session[0].session){
    i+='<td width="40px;"><span id="MODBTN" style="cursor:hand;" onclick="fncModi('+data.replyinfo[j].num+',this)">수정</span></td>'
    i+='<td width="40px;"><span  onclick="subajaxdelete('+data.replyinfo[j].num+')">삭제</span></td>';
    }  //작성자와 세션이 같은 if문 끝
    	
    	if(data.session[0].session!=null){
    i+='<td width="40px;"><span id="write_form" onclick="javascript:fncWriteform('+ data.replyinfo[j].num +',this)">답글</span></td>';
      	} //세션값과 글쓴이가 같은지.
	i+="</tr>";		
}	//alve==0세션끝나는곳
	//글이 살아있을떄 보여지는부분.
	
	//글이 죽어있다면.
	if(data.replyinfo[j].alive==1){
	i+='<tr class="read" height="25" style="display:  ;"  >   <td width="465px">삭제된 댓글입니다.</td>';
	i+='</tr><tr class="modi" style="display: none "><td></td></tr>';
	i+='<tr class="write" style="display: none "><td></td></tr>';
	i+='</table>';
	}

	//데이터 낭비 방지를 위해 alive가 0이여야만 수정 답글폼도 만듦.
	if(data.replyinfo[j].alive==0){
		
														//수정폼 자바스크립트로 제어하기위해 class와 id각각 설정해줌.	    
	i+='<tr class="modi" style="display: none " height="25" id="MODI'+data.replyinfo[j].num+'">';
	i+='<td width="50px">'+data.session[0].session+'</td>';
																//수정 id는 구분을위해 고유댓글번호MODI_VAL 이라는 값으로 설정.
	i+='<td width="350px;""><input type="text" id="'+data.replyinfo[j].num+'MODI_VAL" name="MODI_VAL" style="width:300px; height:25px;"></td>';
																//수정에 필요한것은 게시판번호와 댓글고유번호만 있으면됨 컨텐츠는 자스단에서 해결.
	i+='<td width="30px;"><input type="button" onclick="javascript:submodi('+data.replyinfo[j].num +','+data.replyinfo[j].tranum +');" value="입력"></td>';
	i+='<td onclick="javascript:fncmodicancle()">입력취소</td></tr>';
	

	var tempi="'";
	//답글일떄 마찬가지로 class와 id로 각각 나눠서 개별제어 한번에 제어를 가능하게 설정.
	i+='<tr class="write" style="display: none " height="25" id="WRITE'+data.replyinfo[j].num +'">';
	i+='<td>답글</td> <td>:</td>';
	                                                     //구분을위해 고유번호WRITE로 id설정.
	i+='<td width="350px"><input type="text" id="'+data.replyinfo[j].num+'WRITE" style="width: 360px; height:20px;"></td>';
	i+='<td><input type="button" value="입력" onclick="subajaxrep( ' +data.replyinfo[j].num+',1,'+tempi+data.session[0].session+tempi+','+data.replyinfo[j].trarep_writerrep+','+data.replyinfo[j].trarep_numref_lv+','+tempi+data.replyinfo[j].writer+tempi+') "></td>';
	
	
	i+='<td onclick="javascript:fncmodicancle()">취소</td>';
	i+='</tr>'
	i+='</table>';
	i+='</div>';
	}
	}
	//이모든것이 끝나면        저 아이디값태그안에 위의 태그들을 주입해줌.
	document.getElementById('trareplypaging').innerHTML=i;
	
}



</script>




</center>
<jsp:include page="/view/main/footer.html" />
</body>
</html>
