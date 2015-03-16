<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${(receipt.image.height > 500) ||  (receipt.image.width > 300) }">
        <img alt="receipt" height="500" width="300" src="data:${receipt.image.format};base64,${receipt.image.base64}">
    </c:when>    
    <c:otherwise>
        <img alt="receipt" height="${receipt.image.height}" width="${receipt.image.width}" src="data:${receipt.image.format};base64,${receipt.image.base64}">
    </c:otherwise>
</c:choose>
