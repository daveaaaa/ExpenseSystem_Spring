<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<img alt="receipt" height="${receipt.image.height}" width="${receipt.image.width}" src="data:${receipt.image.format};base64,${receipt.image.base64}">