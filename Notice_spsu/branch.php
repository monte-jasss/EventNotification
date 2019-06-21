<?php
	require 'connection.php';
	
	$result = array();
	
	$query = "select branch from tb_branch";
	if($notice = mysqli_query($con, $query)){
		while($item = mysqli_fetch_array($notice)){
			array_push($result,$item[0]);
		}
		echo json_encode($result);
	}
?>