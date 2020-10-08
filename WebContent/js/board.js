/**
 * 
 */

currentpage = 1;


var writeServer = function(){
	
	console.log($('#wform').serializeArray());
	
	$.ajax({
		url:'/board/writer.do',
		type:'post',
		data : $('#wform').serializeArray(),
		success : function(res){
			readPageServer(1);
		},
		error : function(req){
			
		},
		dataType:'json'
	})
	
};

var updateServer = function(){

	//수정폼에 입력한 값을 가져온다

	console.log($('#mform').serializeArray());

	$.ajax({
		url:'/board/update.do',
		type: 'post',
			data: $('#mform').serializeArray(),
			dataType : 'json',
			success : function(res){
			
			},
			error : function(req){
				alert("상태 : " + req.status);
			}
	});
	
}

var deleteServer = function(bonum, but){
	$.ajax({
		url : '/board/delete.do',
		type:'get',
		data : {"bonum": bonum},
		dataType:'json',
		success : function(res){
			//alert(res.sw);
			//화면에서 지우기
			$(but).parents('.panel').remove();
			
		},
		error: function(req){
			
		}
		
	});
}


var readHitServer = function(bonum, list){
	hit = parseInt($(list).parents('.panel').find('.hitspan').text().trim());
	$.ajax({
		url: '/board/ReadHitUpdate',
		type: 'get',
		data : {"bonum": bonum},
		success : function(res){
			hit++;
			//alert(res.sw);
			$(list).parents('.panel').find('.hitspan').text(hit);
			
		},
		error : function(req){
			alert("상태 : " + req.status);
		},
		dataType:'json'
		
	});
	
};


var replyUpdateServer = function(){
	$.ajax({
		url:'/board/ReplyUpdate',
		type:'post',
		data : reply,
		success : function(res){
			alert(res.sw);
		},
		error : function(req){
			alert("상태 : " + req.status);
		},
		dataType:'json'
		
		
	});	
};



var replyDeleteServer = function(renum){
	$.ajax({
		url : '/board/Reply.do',
		type : "get",
		data : {"renum" : renum},
		dataType : 'json',
		success :function(res){
			alert(res.sw);
		},
		error : function(req){
			alert("상태 : " + req.status);
		}
	});
};
var replyListServer = function(bonum, but){
	$.ajax({
		url : "/board/ReplyList",
		type : "post",
		data : {"bonum" : bonum},
		
		dataType : 'json',
		success : function(res){
			$(but).parents('.panel').find('.pbody').find('.rep').remove();
			repcode = "";
			$.each(res,function(i){
				repcode +='<div class="panel-body rep">';
				repcode +='<p style = "width :80%; float:left;">';
				repcode += res[i].name + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';		
				repcode += res[i].redate + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				repcode += '<span class = "cont"> ' + res[i].cont + '</span>';
				repcode +='</p>';
				repcode +='<p style ="width:20%; float:right;">';
				repcode +='<button idx="'+ res[i].renum + '" id="r_modify" type ="button" name ="r_modify" class ="action">댓글수정</button>';
				repcode +='<button idx="'+ res[i].renum + '" type ="button" name ="r_delete" class ="action">댓글삭제</button>';
				repcode += '</p>'; 
				repcode += '</div>';
			})
			$(but).parents('.panel').find('.pbody').append(repcode);
		},
		error : function(req){
			alert("상태 " + req.status);
		}
	});
};
var replySaveServer = function(but){
	$.ajax({
		url : '/board/Reply.do',
		data : reply, //리플라이 객체
		type : 'post',
		dataType : 'json',
		success : function(res){
			
			replyListServer(reply.bonum, but);
		},
		error : function(req){
			alert("상태 : " + req.status);
		}
	});
};
var readPageServer = function(cpage){
	$.ajax({
		url : '/board/List.do',
		type : 'post',
		data : {'page' : cpage},
		dataType : 'json', 
		success : function (res) {
			code = ' <div class="panel-group" id="accordion">';
			$.each(res.datas,function(i){
			  code +='  <div class="panel panel-default">';
			  code +='      <div class="panel-heading">';
			  code += '     <h4 class="panel-title">';
			  code +='        <a name = "list" class ="action subject " idx = "'+res.datas[i].seq+'" data-toggle="collapse" "data-parent="#accordion" href="#collapse'+res.datas[i].seq +'">'+ res.datas[i].subject + '</a>';
			  code +='	        </h4>';
			  code +='      </div>';
			  code +='	     <div id="collapse'+ res.datas[i].seq + '" class="panel-collapse collapse ">';
		      code +='	      <div class="panel-body pbody">';
			  code +='<p style = "width :80%; float:left;">';
			  code +='작성자<span class="wspan">' +res.datas[i].writer + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			  code +='메일<span class="mspan">' +res.datas[i].mail + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			  code +='조회수<span class="hitspan">' +res.datas[i].hit + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			  code +='작성날자<span class="dspan">' +res.datas[i].wdate + '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			  code +='</p>';
			  code +='<p style ="width:20%; float:right;">';
			  code +='<button idx="'+ res.datas[i].seq + '" type ="button"  name ="modify" class ="action">수정</button>';
			  code +='<button idx="'+ res.datas[i].seq + '" type ="button" name ="delete" class ="action">삭제</button>';
			  code +='</p><hr style ="clear:both;">';
			  code += '<p style ="width:100%;"><span class="cspan">';
			  code += res.datas[i].content;
			  code += '</span></p>';
			  code +='<p style ="width:100%;">';
			  code +='<textarea class ="area" cols="60"></textarea>';
			  code += '</p>';
			  code += '<button idx="' + res.datas[i].seq + '" style = "height:45px; vertical-align:top;" type ="button" name ="reply" class ="action" >등록</button>';
		      code +='     	</div>';
			  code +='  </div>';
			  code +=' </div>';
			});
			code +='</div>';
			$('.box').html(code);
			//이전 버튼 출력하기
			
			
			$('#pagelist').empty();
			
			if(res.startp > 1 ){
				//pager = "";
				pager = '<ul class="pager">';
			    pager +='<li><a class = "prev" href="#">Previous</a></li>';
			    pager += '</ul>';
			    
			    $('#pagelist').append(pager);
			}
			
			
			
			//페이지버튼 출력하기
			pager  = '<ul class="pagination pager">';
			for(i = res.startp ; i <= res.endp; i++){
				if(currentpage == i){
					pager += '<li class="active"><a  class = "paging" href="#">' + i +'</a></li>';
				}else{
					pager += '<li><a class = "paging" href="#">'+ i +'</a></li>';
				}
				
			}
			pager += "</ul>";
			$('#pagelist').append(pager);
			
			//다음 버튼 출력하기
			
			if(res.endp < res.total){
				pager = '<ul class="pager">';
				pager += 	'<li><a class = "next" href="#">Next</a></li>';
				pager += '</ul>';
				$('#pagelist').append(pager);
			}
		},
		error :function(req){
			alert("상태 : " + req.status);
		}
	});
};

function readServer(){
	
	
	$.ajax({
		url : '/Board/List.do',
		type : 'get',
		dataType : 'json',
		success : function(res){
			code = ' <div class="panel-group" id="accordion">';
			$.each(res,function(i){
			  code +='  <div class="panel panel-default">';
			  code +='      <div class="panel-heading">';
			  code += '     <h4 class="panel-title">';
			  code +='        <a data-toggle="collapse" data-parent="#accordion" href="#collapse'+res[i].seq +'">'+ res[i].subject + '</a>';
			  code +='	        </h4>';
			  code +='      </div>';
			  code +='	     <div id="collapse'+ res[i].seq + '" class="panel-collapse collapse ">';
		      code +='	      <div class="panel-body">';
			  code +='<p style = "width :80%; float:left;">';
			  code +='작성자' +res[i].writer + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			  code +='메일' +res[i].mail + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			  code +='조회수' +res[i].hit + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			  code +='작성날자' +res[i].wdate + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			  code +='</p>';
			  code +='<p style ="width:20%; float:right;">';
			  code +='<button idx="'+ res[i].seq + '" type ="button"   name ="modify" class ="action">수정</button>';
			  code +='<button idx="'+ res[i].seq + '" type ="button" name ="delete" class ="action">삭제</button>';
			  code +='</p><hr style ="clear:both;">';
			  code += '<p style ="width:100%;">';
			  code += res[i].content;
			  code += '</p>';
			  code +='<p style ="width:100%;">';
			  code +='<textarea class ="area" cols="60"></textarea>';
			  code += '</p>';
			  code += '<button idx="' + res[i].seq + '" style = "height:45px; vertical-align:top;" type ="button" name ="reply" class ="action" >등록</button>';
		      code +='     	</div>';
			  code +='  </div>';
			  code +=' </div>';
			});
			code +='</div>';
			$('.box').html(code);
		},
		error : function(req){
			alert("상태 : " + req.status);
		}
	});
}
