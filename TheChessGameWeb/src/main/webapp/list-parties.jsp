<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TheChessGame Web Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body style="line-height: 1.25;">
<jsp:include page="/chess"/>
	<div style="text-align: center; margin: 10px; font-weight: bold; font-size: xx-large;">TheChessGame</div>
	</br>
	<div class="container">
	    <jsp:include page="search-form.jsp"/>
	    <br>
		<div>
		    <p style="margin: 5px; font-weight: bold; font-size: x-large;">Matches table</p>
		</div>
		<table class="table table-striped">
	      <thead>
	      <tr>
	          <th scope="col">#</th>
	          <th scope="col">Player 1</th>
	          <th scope="col">Player 2</th>
	          <th scope="col">Winner</th>
	          <th scope="col">Time</th>
	      </tr>
	      </thead>
				<%@ page isELIgnored="false" %>
	      <tbody>
	      	<div>${tableData}</div>
	      </tbody>
	  </table>
	</div>
</body>
</html>