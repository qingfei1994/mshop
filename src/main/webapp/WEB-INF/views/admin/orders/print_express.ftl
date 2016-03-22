<html lang="cn" class="app fadeInUp animated js no-touch no-android chrome no-firefox no-iemobile no-ie no-ie10 no-ie11 no-ios"><head>
    <meta charset="utf-8">
    <title>打印配送单</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!--[if lt IE 9]><script src="/assets/javascripts/ie8/inie8.min.js"></script><![endif]-->
    <link href="${base}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/assets/plugins/uniform/css/uniform.default.min.css" rel="stylesheet" type="text/css"/>
    <script src="${base}/assets/plugins/jquery-1.10.2.min.js"></script>

    
    <style type="text/css">
       body,pre,.courier-container pre{
        color:inherit!important;
    }
        .courier-container {
            /*padding-top: 0px;*/
            width: 766px;
            position: relative;
            overflow: hidden;
            border: 1px solid #dde9f5;
        }

            .courier-container .close {
                position: absolute;
                right: 1px;
            }

            .courier-container .item {
                line-height: 20px;
                float: left;
                position: absolute;
                top: 0px;
                left: 0px; 
                overflow: hidden;
                word-wrap: break-word;
            }
             

            .courier-container pre {
                height: 100%;
                float: left;
                cursor: move;
                width: 100%;
                border: none;
                background: none;
                font-size: 14px;
                font-family: "Microsoft YaHei",微软雅黑,"MicrosoftJhengHei",华文细黑,STHeiti,MingLiu!important;
            }

            .courier-container textarea {
                padding-left: 0px;
                margin: 0px;
                font-size: 12px;
                resize: none;
                outline: none;
                overflow: hidden;
                border: none;
            }

            .courier-container .resize {
                height: 6px;
                width: 6px;
                position: absolute;
                bottom: 0px;
                right: 0px;
                overflow: hidden;
                cursor: nw-resize;
            }
    </style>
    <script type="text/javascript">
        var _speedMark = new Date(),
            www_version = 1201405081146,
            base = '${base!}';
       
        $(document).ready(function () {

            var items = $(".item");
            if (items.length>0) {
                for (var i = 0; i < items.length; i++) {
                    var left=items[i].style.left;
                    var top=items[i].style.top;
                    items[i].style.left=(parseInt(left.substr(0,left.length-2))+0)+"px";
                    items[i].style.top=(parseInt(top.substr(0,top.length-2))+0)+"px";
                }
            }


            var ids = $('#ids').val();
            var index = $('#indexUrl').val();
            if (index == 0) {
                PostUrl();
            }
			//选择了地址以后，向服务器请求
            $("#btnAddress").change(function () {
                PostUrl();

            });
            //选择了快递公司以后，向服务器请求
            $("#btnExpress").change(function () {
                PostUrl();

            });
            function PostUrl() {
                var wateId = $('#btnExpress').val();
                var addressStr = $('#btnAddress').val();
                window.location.href = "${base}/common/waybill/printExpress?wateId=" + wateId + "&address=" + addressStr + "&ids=" + ids + "&index=" + index;
            }
            $("#btnPrint").click(function () {
                $('#btnFormPrint').hide();
                var imgs = document.querySelectorAll(".jsimg");
                for (var i = 0; i < imgs.length; i++) {
                    imgs[i].style.visibility = "hidden";
                }
				$('.panel').css("border","none");
				$('#btnPrint').hide();
                window.print();
            });

           var jsContainer = $('.js_container img');
           if(jsContainer.length>0){
           		var _this = jsContainer,
           		_src = _this.attr('src');
           		_this.attr('src', base+'/'+_src);
		   }

        });

    </script>

<style type="text/css"></style></head>

<body>

    <section class="vbox">

        <section class="scrollable  wrapper">
            <section class="panel panel-default">
                <div class="panel-body">
                    <form class="form-horizontal form-validate" method="post" action="#" novalidate="novalidate">
                        <input type="hidden" id="indexUrl" name="indexUrl" value="1">
                        <#assign index=0>
                        <input id="ids" type="hidden" name="ids" value="<#list orinIds as orinId><#if index!=0>,</#if>${orinId!}<#assign index=index+1></#list>">
                       
                      
                        <div class="form-group" id="btnFormPrint">
                        	<div class="col-sm-3">
                        	   <label class="control-label visible-ie8 visible-ie9">打印模板</label>
                                <select class="form-control " id="btnExpress" data-rule-required="true" aria-required="true">
                                    <option value="">请选择打印模版</option>
                                    <#list availableTemplates as template>
                                    <option value="${(template.wate_id)!}" <#if wateId?? && wateId == template.wate_id>selected</#if> >${(template.wate_name)!}-${(template.exco_name)!}</option>
                                    </#list>
                                </select>
                            </div>
                        	<div class="col-sm-3">
                        	<label class="control-label visible-ie8 visible-ie9">发货地址</label>
                                <select class="form-control " id="btnAddress" data-rule-required="true" aria-required="true">
                                    <option value="">请选择发货地址</option>
                                    <#list addresses as address>
                                    <option value="${(address["addr_id"])!}" 
                                    <#if addressId??>
                                    	<#if addressId == address["addr_id"]>
                                    	selected="selected"
                                    	</#if>
                                    <#elseif (address["addr_default_send_address"])?? && address["addr_default_send_address"]==1>
                                    selected="selected"
                                    </#if> 
                                    >${(address["addr_company_name"])!} ${(address["addr_phone"])!} ${(address["province_regi_name"])!} ${(address["city_regi_name"])!} ${(address["county_regi_name"])!}  ${(address["addr_detailed_address"])!}</option>
                                    </#list>
                                </select>
                            </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <button type="button" class="btn btn-primary" id="btnPrint">打印</button>
                                </div>
                            </div>
						
                        <div class="line line-dashed line-lg pull-in"></div>

                        <div class="form-group">
                            <!-- Begin -->
                            <#list waybillTemplates as waybillTemplate>
                            <div class="col-sm-10 scroll-x">
                                <div class="courier-container js_container" id="btnContent">
									${(waybillTemplate.wateContent)!}
                                </div>
                            </div>
                            
                            </#list>
                            <!-- End -->
                        </div>
                         </div>
					
                    </form>
                </div>
            </section>
        </section>
    </section>


<div class="msgbox"></div>
</body>
</html>