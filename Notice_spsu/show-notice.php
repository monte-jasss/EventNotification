<?php
	require 'connection.php';
	
	$result = array();
	
	$query = "select * from tb_notice";
	if($notice = mysqli_query($con, $query)){
		while($item = mysqli_fetch_array($notice)){
			array_push($result,array('id'=>$item['id'], 'subject'=>$item['subject'], 'content'=>nl2br($item['content'])));
		}
		echo json_encode($result);
	}
?>