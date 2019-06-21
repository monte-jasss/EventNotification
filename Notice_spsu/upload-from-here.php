<?php
	require 'connection.php';
	
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Upload Time-table by Monu Lakshkar</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body> 
<div class="container">
	<div class="panel panel-primary" style="width:50%;margin:0 auto;margin-top:5%;">
      <div class="panel-heading">Developed by : <b>Monu Lakshkar</b></div>
      <div class="panel-body">
		  <h2>Time-Table Upload</h2><br/>
		  <form class="form-horizontal" action="upload-time-table.php" method="post" enctype="multipart/form-data" style="width:80%;margin:0 auto;">
			<div class="form-group">
			  <label for="branch">Branch:</label>
			  <input type="text" class="form-control" list="br" id="branch" placeholder="Select Branch" name="branch" required/>
			  <datalist id="br">
				<?php 
					$qr = mysqli_query($con, "select branch from tb_branch");
					while($row = mysqli_fetch_array($qr)){ ?>
					<option value="<?php echo $row[0]; ?>">
				<?php } 
				?>
			  </datalist>
			</div>
			<div class="form-group">
			  <label for="semester">Semester:</label>       
			  <input type="text" class="form-control" list="sm" id="semester" placeholder="Select Semester" name="semester" required/>
			  <datalist id="sm">
				<?php 
					$qr = mysqli_query($con, "select semester from tb_semester");
					while($row = mysqli_fetch_array($qr)){ ?>
					<option value="<?php echo $row[0]; ?>">
				<?php } 
				?>
			  </datalist>
			</div>
			<div class="form-group">
			  <label for="timetable">Time-table:</label>     
			  <input type="file" class="form-control" id="timetable" name="fileToUpload" required/>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		  </form>
	  </div>
    </div>
</div>
<script>
	$(document).ready(function(){
		
	});
</script>
</body>
</html>
