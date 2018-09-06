$('input:radio').click( function(){
	if(!$(this).data("checked")){//判断是否为false
        $(this).prop("checked",true);//设置为true
        $(this).data("checked",true);//将data中的checked设置为true
    }else{
        $(this).prop("checked",false);//如果是true，就设置为false
        $(this).data("checked",false);//然后将data中的checked设置为false
    }
});

//数据框拖动
$('.panel-heading').bind('click', function() {
   $(this).children('i').toggleClass('glyphicon-chevron-up');
   var index = $(this).parents('.datapan').index();
   $(this).parents('.flow-right').children('.datapan').eq(index).children('.panel-body').toggle();
})



//Initialize JsPlumb
var color = "#E8C870";

function insertSelectTable(jsonValue){
	$("#flow-panel").empty();
	var instance = jsPlumb.getInstance({
		// notice the 'curviness' argument to this Bezier curve.  the curves on this page are far smoother
		// than the curves on the first demo, which use the default curviness value.      
		Connector : [ "Bezier", { curviness:50 } ],
		DragOptions : { cursor: "pointer", zIndex:2000 },
		PaintStyle : { strokeStyle:color, lineWidth:2 },
		EndpointStyle : { radius:5, fillStyle:color },
		HoverPaintStyle : {strokeStyle:"#7073EB" },
		EndpointHoverStyle : {fillStyle:"#7073EB" },
		Container:"flow-panel"
	});
	
	instance.doWhileSuspended(function() {
	      // declare some common values:
	      var arrowCommon = { foldback:0.8, fillStyle:color, width:5 },
	      // use three-arg spec to create two different arrows with the common values:
	      overlays = [
	        [ "Arrow", { location:0.8 }, arrowCommon ],
	        [ "Arrow", { location:0.2, direction:-1 }, arrowCommon ]
	      ];
	
		  var node;
		  var xpostion = 20;
		  var columArray = new Array();
		  for(var i=0; i<jsonValue.length; i++){
			    var nodeObj = JSON.parse(jsonValue[i]);
			  	node = addNode('flow-panel' , 'node'+i,nodeObj.tableName,{x:xpostion+'px',y:'40px'} ,nodeObj);
			  	columArray = nodeObj.cloumnList;
			  	console.log(columArray);
			  	addPorts(instance, 'node'+i, columArray,'input');
				addPorts(instance, 'node'+i, columArray,'output');
			    xpostion = xpostion + 400;
		  }
			
	      instance.draggable($('.node'));
    });
}

var radiao=new Array();
function addNode(parentId,nodeId,nodeLable,position,nodeObj) {
		
	var panel = d3.select("#" + parentId);
  	panel.append('div').style('width','380px')
    .style('position','absolute')
    .style('top',position.y).style('left',position.x)
    .attr('align','center')
    .attr('id',nodeId).classed('node',true);
  	
	var nodeChild = nodeObj.cloumnList;
	var html="";
	html+='<div class="panel panel-primary" id="panel-primary">';
	html+='<div class="panel-heading" style="position: absolute;width: 100%;top: -35px;left: 0px;">'+nodeLable+'</div>';
	html+='<div class="panel-body">';
	html+='<table width="100%">';
	html+='<tbody>';
	/*html+='<tr>';
	html+='<th></th> <th>列名</th><th>类型</th><th>注释</th>';
	html+='</tr>';*/
	for(var j=0; j<nodeChild.length; j++){
		var childObj = nodeChild[j];
		
		var commentStr = childObj.comment;
		if(commentStr.length > 6){
			commentStr = commentStr.substr(0,6) + "..";
		}
		
		html+='<tr>';
		html+='<td style=\"padding-bottom: 5px;padding-top:25px;\"><input type=\"checkbox\" value=\"'+nodeObj.tableName+'.'+childObj.field+'\"/></td>';
		html+='<td style=\"padding-bottom: 5px;padding-top:25px;\"><input type=\"radio\" class=\"left-radio\" value=\"'+nodeObj.tableName+'.'+childObj.field+'\"  style=\"position: absolute;left:-6px;\">'+childObj.field+'<input class=\"right-radio\" type=\"radio\"  value=\"'+nodeObj.tableName+'.'+childObj.field+'\" style=\"position: absolute;right:-6px;\"></td><td style=\"padding-bottom: 5px;padding-top:25px;\">'+childObj.type+'</td><td style=\"padding-bottom: 5px;padding-top:25px;\">'+commentStr+'</td>';
		html+='</tr>';
	}
	html+='</tbody>';
	html+='</table>';
	html+='</div>';
	html+='</div>';
	$('#'+nodeId).append(html);
	$(".right-radio").mousedown(function(){
		$(this).css("z-index","0").hide().prop('checked', true);
		radiao.push($(this).val());
	})
	$(".left-radio").mouseup(function(){
		$(this).css("z-index","0").hide().prop('checked', true);
		radiao.push($(this).val());
	})
    return jsPlumb.getSelector('#' + nodeId)[0];
}

function addPorts(instance, node, ports, type) {
	
	  //Assume horizental layout
	  var number_of_ports = ports.length;
	  var i = 0;
	  var height = $(node).height();  //Note, jquery does not include border for height
	  var y_offset = 1 / ( number_of_ports +1);
	  var y = 0;

	  for ( ; i < number_of_ports; i++ ) {
	    var anchor = [0,0,0,0];
	    var paintStyle = { radius:5, fillStyle:'#FF8891' };
	    var isSource = false, isTarget = false;
	    if ( type === 'output' ) {
	      anchor[0] = 1;
	      paintStyle.fillStyle = '#D4FFD6';
	      isSource = true;
	    } else {
	      isTarget =true;
	    }

	    anchor[1] = y + y_offset;
	    y = anchor[1];
	    instance.addEndpoint(node, {
	      paintStyle: paintStyle,
	      anchor:anchor,
	      maxConnections:-1,
	      isSource:isSource,
	      isTarget:isTarget
	    });
	  }
}


Array.prototype.contains = function ( needle ) {
	  for (i in this) {
	    if (this[i] == needle) return true;
	  }
	  return false;
}

