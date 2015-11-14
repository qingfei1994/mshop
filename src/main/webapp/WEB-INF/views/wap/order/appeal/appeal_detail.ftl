<!DOCTYPE html>
<html>
<head lang="en">
    <title>维权详情</title>
    <base href="${base}/" />
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0,  user-scalable=no" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name=" apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <link href="assets/wap/css/common/reset.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/css/common/common.css" rel="stylesheet" type="text/css"/>
    <link href="assets/wap/css/order/appeal/appeal.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="container">
		<section class="body auto-height">
            <div class="detail-block">
                <div class="block-title process">
                    <label>维权进度</label>
                </div>
                <ul class="process-list">
                    <li>
                        <div>
                            <span class="process-point current "></span>
                        </div>
                        <div>
                            <span class="process-info  current">
                                ${states.current}<br>
                                <#if flowStatus==0>
								 	 买家申请退款，等待商家处理中
								 	 <#elseif flowStatus==1>
								 	 商家拒绝退款
								 	 <#elseif flowStatus==2>
								 	 商家同意退款
								 	 <#elseif flowStatus==3>
								 	 买家撤销退款 			
								</#if>
								  <br>
                            </span>
                        </div>
                    </li>
                    <#if flowStatus!=0>
                    <li>
                        <div>
                            <span class="process-point  "></span>
                        </div>
                        <div>
                            <span class="process-info  ">
                              ${states.previous}<br>
								 买家申请退款，等待商家处理中<br>
                            </span>
                        </div>
                    </li>
                    </#if>
                </ul>
            </div>
            <div class="detail-block">
            	<#if flowStatus==1||flowStatus==2>
            			<#if flowStatus==1>
			                <div class="block-title refuse">
			                    <label>拒绝原因</label>
			                </div>
			                <p class="refuse-reason">${reason!!}</p>
			                 <#else>
		                	<div class="block-title refuse">
		                    <label>同意原因</label>
		                	</div>
		               	 <p class="refuse-reason">${reason!!}</p>
		                </#if>
                		
                </#if>
              
            </div>
            <div class="detail-block">
                <div class="block-title info">
                    <label class="open">维权信息<span class="fr" onclick="expandInfo();">展开</span></label>
                </div>
                <ul class="detail-list">
                    <li class="off">
                        <div>维权类型</div>
                        <div>
                        <#if clientAppeal.clapType==0>
                        	退款
          				<#elseif clientAppeal.clapType==1>
          					退货退款
                        </#if></div>
                    </li>
                      <li class="off">
                        <div>维权原因</div>
                        <div>${(clientAppeal.clapReason)!}</div>
                    </li>
                    <li class="off">
                        <div>退款金额</div>
                        <div>￥${(clientAppeal.clapMoney)!}</div>
                    </li>
                    <#if flowStatus==2>
                		<#if refundInfo??>
	                			<li class="off">
	                        		<div>实际退款</div>
	                        		<div>￥${(refundInfo.reinMoney)!}</div>
	                    		</li>
	                    		<li class="off">
	                        		<div>退款单号</div>
	                        		<div>${(refundInfo.reinNo)!}</div>
	                    		</li>
	                    		<li class="off">
	                        		<div>退款状态</div>
	                        		<div>
	                        			<#if refundInfo.reinStatus==0>
	                        					退款中
	                        				<#elseif refundInfo.reinStatus==1>
	                        					退款成功
	                        			</#if>
	                        		</div>
	                    		</li>
                		</#if>
                	</#if>
                
                    
                </ul>
            </div>
        </section>
		<footer>
			<#include "../../../common/_template/wap/copyright.ftl" />
		</footer>
	</div>
	<#-- 下面写需要引入的js模块，fastclick是一个解决移动端点击延迟和点透bug的插件，按照页面需要引入；如果页面不需要使用js模块，则把require.js也删掉  -->
	<#include "../../../common/_js/require_config_wap.ftl" />
	<script type="text/javascript" src="assets/requirejs/require.js"></script>
	<script type="text/javascript">
		requirejs.config(mssRequireConfig);
		require(['fastclick'], function(FastClick){
			FastClick.attach(document.body);
		});
		
		var expandInfo = function(){
				var $event=$('span.fr');
				var $parent=$event.parent('label');
				var className=$parent.attr('class');
				if(className=="open"){
					$('li.off').attr('class','open');
					$parent.attr('class','close');
					$event.html('收起');
				} else if(className="close") {
					$('li.open').attr('class','off');
					$parent.attr('class','open');
					$event.html('展开');
				}
		}
	</script>
</body>
</html>