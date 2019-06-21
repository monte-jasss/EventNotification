<?php
	require 'connection.php';
	
	$result = array();
	
	$query = "select semester from tb_semester";
	if($notice = mysqli_query($con, $query)){
		while($item = mysqli_fetch_array($notice)){
			array_push($result,"".$item[0]);
		}
		echo json_encode($result);
	}
?>