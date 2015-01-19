<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<img alt="receipt" height="${reciept.image.height}" width="${reciept.image.width}" src="data:${reciept.image.format};base64,${reciept.image.base64}">