<%--
  Created by IntelliJ IDEA.
  User: wangqiang
  Date: 2021/12/23
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--	分页条开始	--%>
<div id="page_nav">
    <%--大于首页才显示---%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
    </c:if>

    <%--页码输出的开始--%>
    <%--情况1：如何总页码小于等于5的情况，页码的范围是：1-总页码--%>

    <c:choose>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1" />
            <c:set var="end2" value="${requestScope.page.pageTotal}" />
        </c:when>
        <%--情况2：总页码大于5的情况--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1" />
                    <c:set var="end2" value="5" />
                </c:when>
                <c:when test="${requestScope.page.pageNo >= requestScope.page.pageTotal - 3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal - 4}" />
                    <c:set var="end2" value="${requestScope.page.pageTotal}" />
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}" />
                    <c:set var="end2" value="${requestScope.page.pageNo + 2}" />
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end2}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页

    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            // 跳到指定的页码
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                // javaScript 语言中提供了一个location地址栏对象
                // 它有一个属性叫href，它可以获取了浏览器地址栏中的地址
                // href 属性可读、可写
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo="+ pageNo;
            })
        })
    </script>
</div>
<%--	分页条结束	--%>