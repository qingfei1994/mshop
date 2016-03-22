<html lang="cn" class="app fadeInUp animated"><head>
    <meta charset="utf-8">
    <title>配送单打印</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="${base}/assets/admin/css/pages/orders/print.css" rel="stylesheet">
   
    <style type="text/css">
        .btn {
            font-weight: 500;
            border-radius: 2px;
        }

        .btn-primary {
            color: #fff !important;
            background-color: #65bd77;
            border-color: #65bd77;
        }

        .btn-primary {
            color: #fff;
            background-color: #428bca;
            border-color: #357ebd;
        }

        .btn {
            display: inline-block;
            margin-bottom: 0;
            font-weight: normal;
            text-align: center;
            vertical-align: middle;
            cursor: pointer;
            background-image: none;
            border: 1px solid transparent;
            white-space: nowrap;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            border-radius: 4px;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }
    </style>
    <script src="${base}/assets/plugins/jquery-1.10.2.min.js"></script><style type="text/css"></style>
    

    <script type="text/javascript">
        var _speedMark = new Date(),
        www_version = 1201405081146;

        $(document).ready(function () {
            $("#btnPrint").click(function () {
                $('#btnPrint').hide();
                window.print();
            });
        });

    </script>
</head>
<body>
    <div class="page">
         <div class="col-sm-4 ">
            <button type="button" class="btn btn-primary" id="btnPrint">打印</button>
        </div>
        
        <#list entitys as entity>
        <div class="bill">
            <p class="title">配送单</p>
            <#if shopInformation??>
            <img src="${(shopInformation.shinLogo)!}" class="titleimg">
            </#if>
            <ul>
                <li style="width: 80mm">收货人 : ${(entity["coad_name"])!}  ${(entity["coad_phone"])!}</li>
            </ul>
            <ul>
                <li style="width: 80mm">付款方式 :
                <#if (entity["orin_pay_way"])??>
					<#if entity["orin_pay_way"] == 0>
						微信支付
					<#elseif entity["orin_pay_way"] == 1>
						支付宝
					<#elseif entity["orin_pay_way"] == 2>
						财付通
					<#elseif entity["orin_pay_way"] == 3>
						银行卡支付
					<#elseif entity["orin_pay_way"] == 4>
						货到付款
					<#elseif entity["orin_pay_way"] == 5>
						余额支付
					</#if>
				</#if>
				</li>
                <li class="text-right" style="width: 120mm">下单时间：${(entity["orin_order_time"])!}</li>
            </ul>
            <ul>
                <li style="width: 140mm">收货地址 : ${(entity["prov_regi_name"])!} ${(entity["city_regi_name"])!} ${(entity["coun_regi_name"])!} ${(entity["coad_detailed_address"])!}</li>
                <li class="text-right" style="width: 60mm">订单编号 : ${(entity["orin_no"])!}</li>
            </ul>
            <table>
                <colgroup><col style="width: 12mm">
                <col style="width: 35mm">
                <col style="width: 50mm">
                <col style="width: 25mm">
                <col style="width: 20mm">
                <col style="width: 25mm">
                <col style="width: 25mm">
                <col style="width: 30mm">
                </colgroup><tbody><tr>
                    <th>序号</th>
                    <th>商家编码</th>
                    <th>商品名称</th>
                    <th>规格</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>小计</th>
                    <th>备注</th>
                </tr>
                
                <#assign index=0>
                <#list goodsOrders as goodsOrder>
                	<#if (goodsOrder.orinId)?? && goodsOrder.orinId == entity["orin_id"]>
	                <tr>
	                    <td>${index+1}</td>
	                    <td>${(goodsOrder.goorCode)!}</td>
	                    <td class="left">${(goodsOrder.goorName)!}</td>
	                    <td></td>
	                    <td>${(goodsOrder.goorPrice)!}</td>
	                    <td>${(goodsOrder.goorCount)!}</td>
	                    <td>${(goodsOrder.goorRealityPay)!}</td>
	                    
	                    <#if index==0>
	                    <td rowspan="${goodsOrders?size + 1}">${(entity["shin_remark"])!}</td>
	                    </#if>
	                    <#assign index=index+goodsOrder.goorCount>
	                    
	                </tr>
                	</#if>
                </#list>

                <tr>
                   <td colspan="4" class="left">总计 : ${(entity["orin_total"])!}( 含运费:${(entity["orin_freight"])!}) <br>已付金额 : ${(entity["orin_total"])!}( 微信支付 : ${(entity["orin_total"])!} )<br><label style="color:red"> 待付金额 : 0.00</label></td>
                   <td>合计</td>
                   <td>${index}</td>
                   <td>${(entity["orin_total"])!}</td>
                </tr>
                
            </tbody></table>
            <ul>
            	<#if shopInformation??>
	                <li class="m-r">${(shopInformation.shinName)!}</li>
	                <li class="m-r">电话：<span>${(shopInformation.shinPhone)!}   </span></li>
	                <li class="m-r">地址：<span>${(shopInformation.shinAddress)!}</span></li>
	                <li style="float: right;">技术支持：<span></span></li>
                <#elseif distributorBasic??>
	                <li class="m-r">${(distributorBasic.dibaName)!}</li>
	                <li class="m-r">电话：<span>${(distributorBasic.dibaCompanyPhone)!}   </span></li>
	                <li class="m-r">地址：<span>${(distributorBasic.dibaDetailedAddress)!}</span></li>
	                <li style="float: right;">技术支持：<span></span></li>
                </#if>
            </ul>
        </div>
		</#list>
       
    </div>



</body></html>