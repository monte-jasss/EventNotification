<?php
	if(isset($_GET['files'])){
		$files = array();
		foreach(glob('timetable/*.*') as $file){
			array_push($files, basename($file));
		}
		header('Content-type: application/json');
		echo json_encode($files);
	}
?>