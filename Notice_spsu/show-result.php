<?php
	require 'connection.php';
	
	$enrollment = $_POST['enrollment'];
	$result = array();
	
	$query = "select * from tb_result where enrollment like '$enrollment'";
	if($res = mysqli_query($con, $query)){
		while($item = mysqli_fetch_array($res)){
			array_push($result,array('code'=>$item['code'], 'subject'=>$item['subject'], 'grade'=>$item['grade']));
		}
		echo json_encode($result);
	}
?>