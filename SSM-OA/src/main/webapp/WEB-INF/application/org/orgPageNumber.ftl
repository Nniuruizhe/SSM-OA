<!-- ���۷�ҳ -->
<#if total??>
	<#if (total > 0)>
		<div >
			<div >
			<!--  current=1ʱ��startIndex = -1  ��̨�ᱨ��
			  ����ʾ����һҳ
			 -->
				<#if current != 1>
					<!-- ��̨current��1��ʼ���� 1234,ǰ̨��0��ʼ 0123  
					  current=1ʱ��startIndex = -1
					 -->
					<a  href="javascript:loadOrgReturnNumber(${current}-2)">��һҳ</a>
				</#if>
				<#if startPage != 1>
					<a  href="javascript:loadOrgReturnNumber(0)">1</a>
				</#if>
				<#if page == 1><!-- �ܵ�ҳ��ֻ��һҳ -->
					<a  href="javascript:loadOrgReturnNumber(0)" >1</a>
				<#else><!-- page������1ҳ -->
					<#if startPage != 1>
						<span>...</span>
					</#if>
					<#list startPage..endPage as index>   <!-- for(int i = startPage;i<=endPage;i++){}  -->
						<#if current == index>
							<a style="color:blue;font-size:16px;"  href="javascript:void(0)" >${index}</a><!--   -->
						<#else>
							<a  href="javascript:loadOrgReturnNumber(${index}-1)">${index}</a>
						</#if>
					</#list>
					<#if endPage != page><!--   page = 20 ҳ endPage =6    -->
						<span>...</span><!-- 1 2 3 4 5 6���� 20 -->
						<a  href="javascript:loadOrgReturnNumber(${page}-1)">${page}</a>
					</#if>
				</#if>
				<#if page != current>
					<a  href="javascript:loadOrgReturnNumber(${current})">��һҳ</a>
				</#if>
			</div>
		</div>	
	<#else>
		<div >
			<div >
				<a  href="javascript:void(0)">��һҳ</a>
				<a  href="javascript:void(0)" >1</a>
				<a  href="javascript:void(0)">��һҳ</a>
			</div>
		</div>
	</#if>
<#else>
	<div >
		<div >
			<a  href="javascript:void(0)">��һҳ</a>
			<a  href="javascript:void(0)" >1</a>
			<a  href="javascript:void(0)">��һҳ</a>
		</div>
	</div>
</#if>
