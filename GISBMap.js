jQuery(document).ready(function() {   
	  
		 
			// 此变量在添加标注功能时，用于记录当前的click事件的处理函数
				var clickHandler;
			
			// 百度地图API功能
					var map=new BMap.Map("map1");
					map.centerAndZoom(new BMap.Point(117.013929,30.537164), 16);  // 初始化地图,设置中心点坐标和地图级别
					map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
					map.setCurrentCity("安庆市");          // 设置地图显示的城市 此项是必须设置的
					map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
					map.enableKeyboard();
					map.enableDragging();
					map.enableDoubleClickZoom();
					var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
					var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
					
					map.addControl(top_left_control);        
					map.addControl(top_left_navigation);   
					
					
					
					var markers=[{"position":{"lng":117.01485,"lat":30.531767},"status":"1","title":"安庆精益精化工有限公司"},
								 {"position":{"lng":117.00491,"lat":30.538575},"status":"1","title":"安庆长虹化工有限公司化工有限公司"},
								 {"position":{"lng":117.014356,"lat":30.531043},"status":"3","title":"安庆月铜钼业有限公司"},
								 {"position":{"lng":117.011894,"lat":30.534139},"status":"3","title":"安庆鑫富化工有限公司"},
								 {"position":{"lng":117.016054,"lat":30.541017},"status":"3","title":"安庆月铜环保有限公司"},
								 {"position":{"lng":117.015003,"lat":30.535247},"status":"3","title":"安庆飞凯高分子材料有限公司"}];
					function initDataMap(){
						for (var i = 0; i < markers.length; i++) {
							createMarker(i);
						}
					}
						
					initDataMap();
					//判断地图上企业的状态
					function createMarker(i){
						var color;
							 var marker;
							 var status_=markers[i].status;
							 if(status_=="1"){//正常
								 marker="normal.png";
								 color="green";
							 }else if(status_=="2"){//离线
								 marker="unline.png";
								 color="#7D7D7D";
							 }else if(status_=="3"){//故障
								 marker="warning.png";
								 color="#FF8000";
							 }else if(status_=="4"){//超标
								 marker="company.png";
								 color="#FF0000";
							 }
						
							var markerHtml = '<div style="position: absolute; margin: 0pt; padding: 0pt; width: 32px; height: 32px; left: 0px; top: -35px; overflow: hidden;">'
									+ '<img style="border:none;left:0px; top:0px; position:absolute;" src="Bmap/shigu.png"></div>';

							var pot = new BMap.Point(markers[i].position.lng,
									markers[i].position.lat);
							var richMarker = new BMapLib.RichMarker(markerHtml, pot, {
								"enableDragging" : false
							});	
							map.addOverlay(richMarker);//添加企业Marker标注
							
							richMarker.addEventListener("onclick",function(e) {
											var  infoWin= createInfoWindow(markers[i]);
											var position_=this.getPosition();
											infoWin.open(position_);
							});
					}
						 
						 
					function createInfoWindow(option){
								
								var content = '<div  class="panel">'+
												'<div title="" class="ddv panel-body panel-body-noheader panel-body-noborder" style="padding: 2px 0px; width: 100%;">'+
													'<style type="text/css">  .dv-table td{border:1px;text-align:center;padding:2px}.dv-label{font-weight:solid;color:#15428B;}</style>'+
													'<table cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered table-hover">'+
														'<tbody>'+
															'<tr>'+
																'<td class="dv-label">企业名称:</td>'+
																'<td>'+option.title+'</td>'+
																'<td class="dv-label">行政区域:</td>'+
																'<td>'+'大观区'+'</td>'+
															'</tr>'+
															'<tr>'+
																'<td class="dv-label">风险等级:</td>'+
																'<td>'+'重大风险源'+'</td>'+
																'<td class="dv-label">是否预案:</td>'+
																'<td>'+'是'+'</td>'+
															'</tr>'+
															'<tr>'+
																'<td class="dv-label">应急人员</td>'+
																'<td>'+'罗长青'+'</td>'+
																'<td class="dv-label">应急电话</td>'+
																'<td>'+'1392764442'+'</td>'+
															'</tr>'+
														'</tbody>'+
													'</table>'+
													'<iframe frameborder=0 width=500 height=410 marginheight=0 marginwidth=0 scrolling=no src="riskTab.html"></iframe>'+
													
													'<a href="riskSourceInfo?riskSourceId='+'1'+'"target="_blank">查看详细信息</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://www.3vfang.com/p/pre/xiaopangzi77/'+ '1'+ '/index.html" target="_blank">查看全景地图</a>'
												'</div>'+
											'</div>';
								var infoWindow = new BMapLib.SearchInfoWindow(map, content, {
									title: option.title, //标题
									offset: new BMap.Size(20,30),
									width: 500,//宽度
									height: 400, //高度
									panel : "panel", //检索结果面板
									enableAutoPan : true, //自动平移
									searchTypes :[
									]
								});
						return infoWindow;
					}
						 
					$("#monitorId").click(function(){
						$("#searchWin").window('open');
						$("#accidentWin").window('close');
						
					});
					$("#saveBtn").click(function(){
						addShijianMarkerAction();
					});
					
					function addShijianMarkerAction(){
						theLocation();
						var eventName=$("#eventName").val();
						var pollutionType=$("#pollutionType  option:selected").text();
						var pollution=$("#pollution   option:selected").text();
						var pollutionMedia=$("#pollutionMedia").val();
						var accidentType=$("#accidentType").val();
						var company=$("#company").val();
						
						
						var content = '<div  class="panel">'+
												'<div title="" class="ddv panel-body panel-body-noheader panel-body-noborder" style="padding: 2px 0px; width: 100%;height:280px">'+
													'<style type="text/css">  .dv-table td{border:1px;text-align:center;padding:2px}.dv-label{font-weight:solid;color:#15428B;width:30%}</style>'+
													'<table cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered table-hover">'+
														'<tbody>'+
															'<tr>'+
																'<td class="dv-label">事件名称:</td>'+
																'<td>'+eventName+'</td>'+
															
															'</tr>'+
															'<tr>'+
																'<td class="dv-label">事故类型:</td>'+
																'<td>'+accidentType+'</td>'+
															
															'</tr>'+
															'<tr>'+
																'<td class="dv-label">污染物:</td>'+
																'<td>'+pollution+'</td>'+
																
															'</tr>'+
															'<tr>'+
																'<td class="dv-label">污染介质:</td>'+
																'<td>'+pollutionMedia+'</td>'+
															'</tr>'+
															'<tr>'+
																'<td class="dv-label">关联企业:</td>'+
																'<td>'+company+'</td>'+
															'</tr>'+
														'</tbody>'+
													'</table>'+
													' <a target="_blank" href="test.html" class="icon-btn"><i class="fa fa-globe"></i><div> 生成应急预案 </div></a>'+
												'</div>'+
											'</div>';
							var infoWindow = new BMapLib.SearchInfoWindow(map, content, {
								title: '事故信息', //标题
								offset: new BMap.Size(10,20),
								width: 400,//宽度
								height: 280, //高度
								panel : "panel", //检索结果面板
								enableAutoPan : true, //自动平移
								searchTypes :[
								]
							});
							infoWindow.open(positionShijian);	
					
					}
				
					$("#createMk").click(function(){
						//单击获取点击的经纬度
						map.addEventListener("click",addMarkerAction);
						
					});
					var positionShijian;
					var shijianMarker;
					function addMarkerAction(e){
							var mkHtml = '<div style="position: absolute; margin: 0pt; padding: 0pt; width: 32px; height: 32px; left: -10px; top: -30px; overflow: hidden;">'
									+ '<img style="border:none;left:0px; top:0px; position:absolute;" src="Bmap/shijian.png"></div>';
							
							var pt = new BMap.Point(e.point.lng,
									e.point.lat);
									positionShijian=pt;
							var rhMk = new BMapLib.RichMarker(mkHtml, pt, {
								"enableDragging" : false
							});	
							shijianMarker=rhMk;
							map.addOverlay(rhMk);//添加企业Marker标注
							
							map.removeEventListener("click",addMarkerAction);
							
							$("#accidentWin").window('open');
					}
					
					
					function   initShijian(){
							var mkHtml = '<div style="position: absolute; margin: 0pt; padding: 0pt; width: 32px; height: 32px; left: -10px; top: -30px; overflow: hidden;">'
									+ '<img style="border:none;left:0px; top:0px; position:absolute;" src="Bmap/shijian.png"></div>';
							var rhMk = new BMapLib.RichMarker(mkHtml,positionShijian, {
								"enableDragging" : false
							});	
							addShijianMarkerAction();
							map.addOverlay(rhMk);//添加企业Marker标注
							
					}
					
					
					// 用经纬度设置地图中心点
					 var theLocation=function (){
						map.panTo(positionShijian);   
						map.setZoom(16);
						
					}
					
					
					
					$("#clearSearch").click(function(){
						map.clearOverlays(); 
						initDataMap();
						initShijian();
					});
					
					$("#searchBtn").click(function(){
						map.clearOverlays(); 
						console.log(positionShijian);
						initDataMap();
							console.log("log.................");
						initShijian();
						//搜索周围的监测点
						var position_ = positionShijian;
						var circle = new BMap.Circle(position_,1000,
									{fillColor:"red", 
									strokeWeight: 1 ,
									fillOpacity: 0.3, 
									strokeOpacity: 0.3,enableEditing:false});
						map.addOverlay(circle);
											
						var radius = parseInt(circle.getRadius());
						var url = "http://api.map.baidu.com/geosearch/v3/nearby?callback=?";
						$.getJSON(url, {
								'geotable_id': 146260,
								'radius':radius,
								'page_size':50,
								'location':''+position_.lng+','+position_.lat+'',
								'ak': 'E3972cbf0ee7057550dbf9fba82d97f1'  //用户ak
						},function(e) {
								var content = e.contents;
								if (content.length == 0) {
									alert("附件没有资源");
									return;
								}
								$.each(content, function(i, item){
									var point = new BMap.Point(item.location[0], item.location[1]);
									//  marker = new BMap.Marker(point);
									// alert(item.envclass);
									
									switch(item.resource_type)
									{
										case 1:
													var myIcon = new BMap.Icon("Bmap/monitor.png",new BMap.Size(23, 25), {    
													// 指定定位位置。   
													// 当标注显示在地图上时，其所指向的地理位置距离图标左上    
													// 角各偏移10像素和25像素。您可以看到在本例中该位置即是   
													// 图标中央下端的尖角位置。    
														offset: new BMap.Size(20, -10),    
														// 设置图片偏移。   
														// 当您需要从一幅较大的图片中截取某部分作为标注图标时，您   
														// 需要指定大图的偏移位置，此做法与css sprites技术类似。    
														imageOffset: new BMap.Size(0, 3)   // 设置图片偏移  
														
													});  

													var monitorMk = new BMap.Marker(point, {icon: myIcon});  
													monitorMk.addEventListener('click',function(){
													
															var contentPanel = "<iframe frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no src='monitorData.html'></iframe>";
																		 //创建检索信息窗口对象
																		 var searchInfoWindow = new BMapLib.SearchInfoWindow(map, contentPanel, {
																			 title  : item.title,       //标题
																			 width  : 500,//宽度
																			 height:300,//高度
																			 panel  : "panel",         //检索结果面板
																			 enableAutoPan : true,     //自动平移
																			 searchTypes   :[]
																		 });
																		 searchInfoWindow.open(monitorMk);
													});
													

														//  monitorMk.setLabel(label);
														
												
													map.addOverlay(monitorMk);
											break;
										case 2:
													var myIcon = new BMap.Icon("Bmap/wuzi.png", new BMap.Size(23, 25), {    
													// 指定定位位置。   
													// 当标注显示在地图上时，其所指向的地理位置距离图标左上    
													// 角各偏移10像素和25像素。您可以看到在本例中该位置即是   
													// 图标中央下端的尖角位置。    
														offset: new BMap.Size(10, 25),    
														// 设置图片偏移。   
														// 当您需要从一幅较大的图片中截取某部分作为标注图标时，您   
														// 需要指定大图的偏移位置，此做法与css sprites技术类似。    
														imageOffset: new BMap.Size(0, 3)   // 设置图片偏移    
														});  

													var monitorMk = new BMap.Marker(point, {icon: myIcon});  
													monitorMk.addEventListener('click',function(){
																var contentPanel = 	'<div  class="panel">'+
																						'<div title="" class="ddv panel-body panel-body-noheader panel-body-noborder" style="padding: 2px 0px; width: 100%;height:340px">'+
																								'<style type="text/css"> .tableStyle{margin-bottom:2px}  .dv-table td{border:1px;text-align:center;padding:2px}.dv-label{font-weight:solid;color:#15428B;width:30%}</style>'+
																								'<table cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered table-hover tableStyle">'+
																									'<tbody>'+
																										'<tr>'+
																											'<td class="dv-label">物资名称:</td>'+
																											'<td>'+item.title+'</td>'+
																										
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">所属单位:</td>'+
																											'<td>'+item.organization+'</td>'+
																										
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">存放位置:</td>'+
																											'<td>'+item.storage+'</td>'+
																											
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">用途:</td>'+
																											'<td>'+item.purpose+'</td>'+
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">数量:</td>'+
																											'<td>'+item.quantity+'</td>'+
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">负责人:</td>'+
																											'<td>'+item.contactor+'</td>'+
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">联系电话:</td>'+
																											'<td>'+item.telephone+'</td>'+
																										'</tr>'+
																									'</tbody>'+
																								'</table>'+
																								' <a id="driving" class="icon-btn"><i class="fa fa-globe"></i><div>查看</div></a>'+
																						'</div>'+
																				'</div>';
																		 //创建检索信息窗口对象
																		 var searchInfoWindow = new BMapLib.SearchInfoWindow(map, contentPanel, {
																			 title  : item.title,       //标题
																			 width  : 300,//宽度
																			 height:340,//高度
																			 panel  : "panel",         //检索结果面板
																			 enableAutoPan : true,     //自动平移
																			 searchTypes   :[]
																		 });
																		 searchInfoWindow.open(monitorMk);
													});
													var distance=parseInt(map.getDistance(point,position_));
													var polyline = new BMap.Polyline([point,position_], {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});  //定义折线
													map.addOverlay(polyline);     //添加折线到地图上
															
													var label = new BMap.Label("离事发地距离:"+distance+"米",{offset:new BMap.Size(20,5)});     	
														label.setStyle({ 
														backgroundColor :"0.05",
														border :"0", 
													});
													monitorMk.setLabel(label);
													map.addOverlay(monitorMk);
											break;
										case 3:
											break;
										case 4:
											break;
										case 5:
											break;
										case 6:
													var myIcon = new BMap.Icon("Bmap/car.png", new BMap.Size(23, 25), {    
													// 指定定位位置。   
													// 当标注显示在地图上时，其所指向的地理位置距离图标左上    
													// 角各偏移10像素和25像素。您可以看到在本例中该位置即是   
													// 图标中央下端的尖角位置。    
														offset: new BMap.Size(10, 25),    
														// 设置图片偏移。   
														// 当您需要从一幅较大的图片中截取某部分作为标注图标时，您   
														// 需要指定大图的偏移位置，此做法与css sprites技术类似。    
														imageOffset: new BMap.Size(0, 3)   // 设置图片偏移    
														});  

													var monitorMk = new BMap.Marker(point, {icon: myIcon});  
													monitorMk.addEventListener('click' ,function(){
															var contentPanel = 		'<div  class="panel">'+
																							'<div title="" class="ddv panel-body panel-body-noheader panel-body-noborder" style="padding: 2px 0px; width: 100%;height:260px">'+
																								'<style type="text/css"> .tableStyle{margin-bottom:2px}  .dv-table td{border:1px;text-align:center;padding:2px}.dv-label{font-weight:solid;color:#15428B;width:30%}</style>'+
																								'<table cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered table-hover tableStyle">'+
																									'<tbody>'+
																										'<tr>'+
																											'<td class="dv-label">车辆名称:</td>'+
																											'<td>'+item.title+'</td>'+
																										
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">车辆类型:</td>'+
																											'<td>'+item.tags+'</td>'+
																										
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">所属单位:</td>'+
																											'<td>'+item.organization+'</td>'+
																											
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">车牌号:</td>'+
																											'<td>'+item.license_plate+'</td>'+
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">数量:</td>'+
																											'<td>'+item.quantity+'辆</td>'+
																										'</tr>'+
																									'</tbody>'+
																								'</table>'+
																								
																								' <a id="driving" class="icon-btn"><i class="fa fa-globe"></i><div>驾车路径</div></a>'+
																							'</div>'+
																				'</div>';
														//创建检索信息窗口对象
														var searchInfoWindow = new BMapLib.SearchInfoWindow(map, contentPanel, {
																					title  : item.title,       //标题
																					width  : 300,//宽度
																					height:260,//高度
																					panel  : "panel",         //检索结果面板
																					enableAutoPan : true,     //自动平移
																					searchTypes   :[]
																				});
														searchInfoWindow.open(monitorMk);
														
														$("#driving").click(function(){
																searchInfoWindow.close();;
																// 实例化一个驾车导航用来生成路线
																var lushu;
																var drv = new BMap.DrivingRoute(map, {
																	onSearchComplete: function(res) {
																		if (drv.getStatus() == BMAP_STATUS_SUCCESS) {
																			var plan = res.getPlan(0);
																			var arrPois =[];
																			for(var j=0;j<plan.getNumRoutes();j++){
																				var route = plan.getRoute(j);
																				arrPois= arrPois.concat(route.getPath());
																			}
																			var  distance=plan.getDistance(true);
																			var  duration=plan.getDuration(true);
																			map.addOverlay(new BMap.Polyline(arrPois, {strokeColor: '#111'}));
																			map.setViewport(arrPois);
																			
																			lushu = new BMapLib.LuShu(map,arrPois,{
																			defaultContent:'到达事发地总路程:'+distance+",驾车时间需要:"+duration+"",//"从应急车辆所在地到事发地"
																			autoView:true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
																			icon  : new BMap.Icon('Bmap/driving.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)}),
																			speed: 100,
																			enableRotation:true,//是否设置marker随着道路的走向进行旋转
																			landmarkPois: []

																			}); 
																			lushu.start();
																			lushu.showInfoWindow();
																		}
																	}
																});
																
																drv.search(point, position_);
															
														});
														
													});
													
													
													
													map.addOverlay(monitorMk);
											break;
										case 7:
											break;
										case 8:
													var myIcon = new BMap.Icon("Bmap/jiankong.png", new BMap.Size(23, 25), {    
													// 指定定位位置。   
													// 当标注显示在地图上时，其所指向的地理位置距离图标左上    
													// 角各偏移10像素和25像素。您可以看到在本例中该位置即是   
													// 图标中央下端的尖角位置。    
														offset: new BMap.Size(10, 25),    
														// 设置图片偏移。   
														// 当您需要从一幅较大的图片中截取某部分作为标注图标时，您   
														// 需要指定大图的偏移位置，此做法与css sprites技术类似。    
														imageOffset: new BMap.Size(0, 3)   // 设置图片偏移    
														});  

													var monitorMk = new BMap.Marker(point, {icon: myIcon});  
													monitorMk.addEventListener('click',function(){
															var contentPanel = 	'<div  class="panel">'+
																						'<div title="" class="ddv panel-body panel-body-noheader panel-body-noborder" style="padding: 2px 0px; width: 100%;height:350px">'+
																								'<style type="text/css">  .dv-table td{border:1px;text-align:center;padding:2px}.dv-label{font-weight:solid;color:#15428B;width:30%}</style>'+
																								'<table cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered table-hover">'+
																									'<tbody>'+
																										'<tr>'+
																											'<td class="dv-label">物资名称:</td>'+
																											'<td>'+item.q+'</td>'+
																										
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">所属单位:</td>'+
																											'<td>'+item.organization+'</td>'+
																										
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">存放位置:</td>'+
																											'<td>'+item.storage+'</td>'+
																											
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">用途:</td>'+
																											'<td>'+item.purpose+'</td>'+
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">数量:</td>'+
																											'<td>'+item.quantity+'</td>'+
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">负责人:</td>'+
																											'<td>'+item.contactor+'</td>'+
																										'</tr>'+
																										'<tr>'+
																											'<td class="dv-label">联系电话:</td>'+
																											'<td>'+item.telephone+'</td>'+
																										'</tr>'+
																									'</tbody>'+
																								'</table>'+
																								' <a id="driving" class="icon-btn"><i class="fa fa-globe"></i><div>查看</div></a>'+
																						'</div>'+
																				'</div>';
																		 //创建检索信息窗口对象
																		 var searchInfoWindow = new BMapLib.SearchInfoWindow(map, contentPanel, {
																			 title  : item.title,       //标题
																			 width  : 300,//宽度
																			 height:350,//高度
																			 panel  : "panel",         //检索结果面板
																			 enableAutoPan : true,     //自动平移
																			 searchTypes   :[]
																		 });
																		 searchInfoWindow.open(monitorMk);
													});
														//var label = new BMap.Label(item.title,{offset:new BMap.Size(20,5)});     	
														//label.setStyle({ 
														//	backgroundColor :"0.05",
														//	border :"0", 
														//	});

														//  monitorMk.setLabel(label);
													map.addOverlay(monitorMk);
											break;
										case 9:
											break;
									}
								});
						});
					});
			
					
					
				
	  
				
		
      });
	  
	  	 
			 
	  
	 