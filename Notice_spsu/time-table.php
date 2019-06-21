<?php
	require 'connection.php';
	
	$semester = $_REQUEST['semester'];
	$branch = $_REQUEST['branch'];
	
	$query = "select image from tb_timetable where semester = '$semester' and branch = '$branch'";
	if($res = mysqli_query($con, $query)){
		$item = mysqli_fetch_array($res);
	}
	header("location: timetable/".$item[0]);
?>