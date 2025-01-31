<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Edit form...</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

 <div class="container mt-5 text-center">
	
    <form action="./UpdateProductServlet" method="post" enctype="multipart/form-data">
    	
    	<h2 class="text-center font-italic mb-1">Updating Product Data...</h2>
		
   		<div class="form-group">
              <label class="font-italic font-weight-bold" for="proId">Product ID:</label>
              <input type="text" class="form-control-sm" value="${existingProduct.proId}" name="proId" readonly="readonly">
        </div>
         
         <div class="form-group">
                <label class="font-italic font-weight-bold" for="proName">Product Name:</label>
                <input type="text" class="form-control-sm" value="${existingProduct.proName}" name="proName" required>
         </div>
         
         <div class="form-group">
                <label class="font-italic font-weight-bold" for="proPrice">proPrice:</label>
                <input type="number" class="form-control-sm" value="${existingProduct.proPrice}" name="proPrice" required>
         </div>
         
         <div class="form-group">
                <label class="font-italic font-weight-bold" for="proBrand">proBrand:</label>
                <input type="text" class="form-control-sm" value="${existingProduct.proBrand}" name="proBrand" required>
         </div>
         
         <div class="form-group">
                <label class="font-italic font-weight-bold" for="proMadeIn">Made In:</label>
                <input type="text" class="form-control-sm" value="${existingProduct.proMadeIn}" name="proMadeIn" required>
         </div>
            
            
       <div class="form-group">
                <label class="font-italic font-weight-bold" for="proMfgDate">Manufacturing Date:</label>
                <input type="date" class="form-control-sm" value="${existingProduct.proMfgDate}" name="proMfgDate" required>
       </div>
       
		<div class="form-group">
                <label class="font-italic font-weight-bold" for="proExpDate">Expiry Date:</label>
                <input type="date" class="form-control-sm" value="${existingProduct.proExpDate}" name="proExpDate" required>
        </div>
		
		<div class="form-group">
                <label class="font-italic font-weight-bold" for="proImage">Product Image:</label>
                <input type="file" class="form-control-file-sm" value="${existingProduct.proImage}" name="proImage" accept="image/*">
        </div>
       
        <div class="form-group">
         	<input type="submit" class="btn btn-success" class="form-control-file-sm" value="Update product"/>
        </div>
    </form>
   </div>
</body>
</html>
